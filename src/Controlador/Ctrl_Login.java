/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Login;
import Modelo.DAO.DAO_MDI;
import Modelo.VO.VO_Login;
import Vista.Frm_Password_Confirm;
import Vista.Frm_Tablas;
import Vista.MDI_Food;
import Vista.frm_Login;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jesus
 */
public class Ctrl_Login implements ActionListener {

    frm_Login Vista_login;
    DAO_Login Modelo_login;

    MDI_Food MDI;
    Ctrl_MDI ctrl_mdi;
    VO_Login vo_login;
    Frm_Tablas tabla;

    

    public static int ID_SUCURSAL = 0;

    public static String NOMBRE_SUCURSAL = "";

    public static String Primera_vez = "false";

    public static String Fecha_cambio = "";

    public static int ID_Usuario = 0;

    private String Tipo_user, nick, contra;

    String Datos[];

    public Ctrl_Login(frm_Login Vista_login, DAO_Login Modelo_login, VO_Login vo_login, Frm_Tablas tabla) {
        this.Vista_login = Vista_login;
        this.Modelo_login = Modelo_login;
        this.vo_login = vo_login;
        this.tabla = tabla;
        
        this.Vista_login.setIMG("src\\Multimedia\\67611159_152587362494082_752442445936984064_o.jpg");
        this.Vista_login.Btn_Start.addActionListener(this);
        this.Vista_login.btn_Exit.addActionListener(this);
        this.Vista_login.txt_pass.addActionListener(this);
        this.Vista_login.txt_User.addActionListener(this);
    }

    public void Logeo() {

        String Pass = Vista_login.txt_pass.getText();
        String User = Vista_login.txt_User.getText();

        String Password_MD5 = DigestUtils.md5Hex(Pass);

        vo_login.setPass(Password_MD5);
        vo_login.setUser(User);
        if ("".equals(User) || "".equals(Pass)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {
                ResultSet rs = Modelo_login.Iniciar_sesion(vo_login);

                Datos = new String[1];

                if (rs.next()) {

                    Tipo_user = rs.getString("CLV_Tipo_Usuario");

                    nick = rs.getString("vch_Usuario");
                    contra = rs.getString("vch_Password");
                    Datos[0] = rs.getString("CLV_Empleado_User");

                    this.ID_Usuario = rs.getInt("int_ID_Usuario");
                    this.Primera_vez = rs.getString("Primera_vez");
                    this.Fecha_cambio = rs.getString("Cambio_");

                    JOptionPane.showMessageDialog(null, "Bienvenido " + nick);
                    
                    MDI = new MDI_Food();
                    
                    MDI.setTitle("Foot Fit INC");
                    
                    MDI.setExtendedState(this.MDI.MAXIMIZED_BOTH);
                    

                    ctrl_mdi = new Ctrl_MDI(MDI, this.ID_SUCURSAL, this.NOMBRE_SUCURSAL, Datos);
                    
                    MDI.setVisible(true);
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Windows".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(MDI_Food.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    Vista_login.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "No Existe nungun usuaer");
                }

            } catch (Exception e) {
                System.out.println("errorLOG:/ " + e);
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Vista_login.Btn_Start) {
            Logeo();
        }

        if (e.getSource() == Vista_login.btn_Exit) {
            System.exit(0);
            JOptionPane.showMessageDialog(null, "Datos: ");
        }

    }

    public boolean Verifica() {

        boolean state = false;
        if ("".equals(this.Vista_login.lbl_Sucursal.getText())) {
            JOptionPane.showMessageDialog(null, "No se especifica una sucursal,Por favor Expecificar");

            // this.Abreformulario_Tabla("Sucursal");
            // this.leerArchivo();
        } else {
            state = true;
        }

        return state;
    }

    public void Abreformulario_Tabla(String tipo) {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(tabla);
        tabla = new Frm_Tablas(f, true);

        tabla.setTitle("Tabla " + tipo);
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        tabla.setIconImage(img);
        tabla.veri.setText(tipo);
        llenaTABLAS(tipo);

        this.tabla.Tbl_Tabla.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {

                if (evnt.getClickCount() == 2) {

                    if (tabla.veri.getText().equals("Sucursal")) {
                        String ID_SUC = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String SUC = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        crearArchivo(Integer.valueOf(ID_SUC), SUC);
                    }

                    tabla.dispose();

                }
            }
        }
        );
        tabla.setVisible(true);
    }

    public void llenaTABLAS(String valor) {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.tabla.Tbl_Tabla.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = this.Modelo_login.Sucursal();

            if (valor.equals("Sucursal")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Sucursal"));
                    String A2 = rs.getString("vch_Nombre");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void crearArchivo(int ID_SUCURSAL, String Nombre) {
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("src\\sucursal.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);

            //escribe los datos en el archivo
            bfwriter.write(ID_SUCURSAL + "-" + Nombre);

            //cierra el buffer intermedio
            bfwriter.close();
            System.out.println("Archivo creado satisfactoriamente..");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //crea el archivo en disco, retorna la lista de estudiantes
    public void leerArchivo() {
        // crea el flujo para leer desde el archivo
        File file = new File("src\\sucursal.txt");
        if (file.exists() == true) {
            Scanner scanner;
            try {
                //se pasa el flujo al objeto scanner
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    // el objeto scanner lee linea a linea desde el archivo
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    //se usa una expresión regular
                    //que valida que antes o despues de una coma (,) exista cualquier cosa
                    //parte la cadena recibida cada vez que encuentre una coma				
                    delimitar.useDelimiter("\\s*-\\s*");
                    this.ID_SUCURSAL = Integer.valueOf(delimitar.next());
                    this.NOMBRE_SUCURSAL = delimitar.next();
                    this.Vista_login.lbl_Sucursal.setText(ID_SUCURSAL + "-" + NOMBRE_SUCURSAL);
                }
                //se cierra el ojeto scanner
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {

        }

    }

}
