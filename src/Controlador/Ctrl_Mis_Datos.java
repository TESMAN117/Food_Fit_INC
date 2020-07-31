/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Facade.Facade;
import Modelo.DAO.DAO_Mis_Datos_Facade;
import Vista.Frm_Empleado_Edit;
import Vista.Frm_Mis_Datos;
import Vista.Frm_Password_Confirm;
import food_fit_inc.Frame_Interno;
import food_fit_inc.Panel_Image;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jesus
 */
public class Ctrl_Mis_Datos implements ActionListener {
    
    Frm_Mis_Datos Mi_Data;
    // Panel_Image IMG_panel;
    // Frame_Interno Internal;

    DAO_Mis_Datos_Facade Modelo;
    
    Frm_Password_Confirm form;
    
    Facade Fachada;
    
    File Ficehro = null;
    
    boolean actutalikzo_ = true;
    
    private String que_hace = "";
    Calendar calendar = new GregorianCalendar();
    String Fecha = calendar.get(Calendar.YEAR) + "-0" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    
    int SUCURSAL = Ctrl_Login.ID_SUCURSAL;
    int USUARIO = Ctrl_Login.ID_Usuario;
//Panel_Image IMG_panel, Frame_Interno Internal,

    public Ctrl_Mis_Datos(Frm_Mis_Datos Mi_Data, DAO_Mis_Datos_Facade Modelo, Frm_Password_Confirm primera) {
        this.Mi_Data = Mi_Data;
        // this.IMG_panel = IMG_panel;
        // this.Internal = Internal;
        this.Modelo = Modelo;
        this.form = primera;
        
        this.Fachada = new Facade(USUARIO, SUCURSAL, Mi_Data, Modelo);
        
        this.Mi_Data.setIMG("src\\Multimedia\\fondo.jpg");
        this.Mi_Data.btn_Ver_password.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);
        this.form.btn_exitt.addActionListener(this);
        this.Mi_Data.btn_Salir.addActionListener(this);
        this.Imagen();
        
        this.Fachada.Mis_Datos();
        imf_empleado();
        
        if (Ctrl_Login.Primera_vez.equals("true")) {
            this.que_hace = "primera";
        }
        if (Ctrl_Login.Fecha_cambio.equals(Fecha)) {
            this.que_hace = "fecha";
            
        }

        // JOptionPane.showMessageDialog(null, Ctrl_Login.Fecha_cambio + " / " + Fecha);
        this.Primeravez();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.Mi_Data.btn_Ver_password) {
            
            this.ver_password();
        }
        
        if (e.getSource() == this.form.btn_Actualizar) {
            
            JOptionPane.showMessageDialog(null, "acciona");
            this.actualiza();
        }
        
        if (e.getSource() == this.Mi_Data.btn_Salir) {
            this.Mi_Data.dispose();
        }
        
        if (e.getSource() == this.form.btn_exitt) {
            System.exit(0);
        }
        
    }
    String State = "ver";
    
    private void Imagen() {
        
        this.Mi_Data.lbl_Fit.setSize(576, 166);
        
        ImageIcon icon = new ImageIcon("src\\Multimedia\\food.gif");
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(this.Mi_Data.lbl_Fit.getWidth(), this.Mi_Data.lbl_Fit.getHeight(), Image.SCALE_DEFAULT));
        
        this.Mi_Data.lbl_Fit.setIcon(icono);
    }
    
    public void ver_password() {
        
        if ("ver".equals(State)) {
            
            ImageIcon icono = new ImageIcon("src/cantidad.png");
            
            String Pass = (String) JOptionPane.showInputDialog(null, "Para ver tu contraseña Autentificate", "Autentificar", 0, icono, null, null);
            
            if (Pass == null) {
                Pass = "";
            } else {
                String Password_MD5 = DigestUtils.md5Hex(Pass);
                
                if (Password_MD5.equals(this.Mi_Data.txt_Password.getText())) {
                    
                    this.Mi_Data.txt_Password.setText(Pass);
                    this.Mi_Data.txt_Password.setEchoChar((char) 0);
                    State = "Mostrado";
                    
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
                }
            }
            
        } else {
            
            this.Mi_Data.txt_Password.setEchoChar('*');
            String Pass = this.Mi_Data.txt_Password.getText();
            String Password_MD5 = DigestUtils.md5Hex(Pass);
            
            this.Mi_Data.txt_Password.setText(Password_MD5);
            State = "ver";
        }
        
    }
    
    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Password_Confirm(f, true);
        
        form.setTitle("Actualizar Contraseña");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\Botones\\contrasena_16.png");
        this.form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
        form.setIconImage(img);
        form.btn_Actualizar.setVisible(true);
        form.setVisible(true);
    }
    
    public void Primeravez() {
        
        if (Ctrl_Login.Primera_vez.equals("true")) {
            this.que_hace = "primera";
            Abreformulario_Edit();
            
        }
        
        if (Ctrl_Login.Fecha_cambio.equals(Fecha)) {
            
            JOptionPane.showMessageDialog(null, "Entra");
            this.que_hace = "fecha";
            Abreformulario_Edit();
            
        }
        
    }
    
    public void actualiza() {
        
        String Pass = this.form.txt_Pass_Nueva.getText();
        String Pass_confirm = this.form.txt_Pass_Confirm.getText();
        
        boolean ffff = false;
        
        if (this.que_hace.equals("primera")) {
            if (Pass.equals(Pass_confirm)) {
                
                if (this.Validar() == true) {
                    
                    String Password_MD5 = DigestUtils.md5Hex(Pass);
                    
                    try {
                        
                        int res = Modelo.Actualiza_primera(Password_MD5);
                        
                        if (res != 0) {
                            JOptionPane.showMessageDialog(null, "Contraseña actualizada");
                            
                            form.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Insertar Datos");
                            
                        }
                    } catch (Exception e) {
                        System.out.print("Error: " + e);
                    }
                    
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no Coinciden");
            }
            
        }
        
        if (this.que_hace.equals("fecha")) {
            if (Pass.equals(Pass_confirm)) {
                
                if (this.Validar() == true) {
                    
                    String Password_MD5 = DigestUtils.md5Hex(Pass);
                    
                    try {
                        Calendar calendar = new GregorianCalendar();
                        int anio = calendar.get(Calendar.YEAR);
                        int mes = calendar.get(Calendar.MONTH) + 1;
                        int dia = calendar.get(Calendar.DAY_OF_MONTH);
                        
                        mes = mes + 1;
                        
                        String f = anio + "-" + mes + "-" + dia;
                        
                        int res = Modelo.Actualiza_fecha_cambio(f, Password_MD5);
                        
                        if (res != 0) {
                            JOptionPane.showMessageDialog(null, "Datos Correctamente Insertados");
                            
                            form.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Insertar Datos");
                            
                        }
                    } catch (Exception e) {
                        System.out.print("Error: " + e);
                    }
                    
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no Coinciden");
            }
            
        }
        
    }
    
    public boolean Validar() {
        
        boolean rs = false;
        
        String password = this.form.txt_Pass_Nueva.getText();
        
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        boolean Estado = password.matches(pattern);
        
        char clave;
        byte contNumero = 0, contLetraMay = 0, contLetraMin = 0, contSpecial = 0;
        for (byte i = 0; i < password.length(); i++) {
            clave = password.charAt(i);
            String passValue = String.valueOf(clave);
            if (passValue.matches("[A-Z]")) {
                contLetraMay++;
            } else if (passValue.matches("[a-z]")) {
                contLetraMin++;
            } else if (passValue.matches("[0-9]")) {
                contNumero++;
            } else if (passValue.matches("[@#$%^&+=]")) {
                contSpecial++;
            }
        }
        
        if (Estado == true && contLetraMay >= 1 && contLetraMin >= 1 && contNumero >= 1 && contSpecial >= 2) {
            
            rs = true;
            
            return rs;
            
        } else {
            
            JOptionPane.showMessageDialog(null, "La contraseña no cumple con los requisitos establecidos!!");
            
        }
        
        return rs;
    }
    
    public void imf_empleado() {
        this.Mi_Data.lbl_IMG.setSize(346, 238);
        Ficehro = new File("src\\Multimedia\\IMG_EMPLEADOS\\" + this.Mi_Data.txt_IMG.getText());
        ImageIcon icon = new ImageIcon(Ficehro.toString());
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(this.Mi_Data.lbl_IMG.getWidth(), this.Mi_Data.lbl_IMG.getHeight(), Image.SCALE_DEFAULT));
        
        this.Mi_Data.lbl_IMG.setText(null);
        
        this.Mi_Data.lbl_IMG.setIcon(icono);
    }
    
    public void abreono() {
        if (actutalikzo_ == true) {
            
        }
        
        if (actutalikzo_ == false) {
            
            System.exit(0);
        }
    }
}
