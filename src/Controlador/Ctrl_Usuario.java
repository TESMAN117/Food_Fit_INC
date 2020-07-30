/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Usuario;
import Modelo.VO.VO_Usuario;
import Vista.Frm_Catalogo_Usuario;
import Vista.Frm_Empleado_Edit;
import Vista.Frm_Tablas;
import Vista.Frm_Usuario_Edit;
import food_fit_inc.Celdas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jesus
 */
public class Ctrl_Usuario extends Celdas implements ActionListener {

    Frm_Catalogo_Usuario Usuario;
    Frm_Usuario_Edit form;
    Frm_Tablas tabla;

    VO_Usuario Datos;
    DAO_Usuario Modelo;

    public Ctrl_Usuario(Frm_Catalogo_Usuario Usuario, Frm_Usuario_Edit form, Frm_Tablas Tabla, VO_Usuario Datos, DAO_Usuario Modelo) {
        this.Usuario = Usuario;
        this.form = form;
        this.tabla = Tabla;
        this.Datos = Datos;
        this.Modelo = Modelo;
        this.Diseña_Tabla(this.Usuario.Tbl_Usuario);
        this.Dideña_Boton();
        this.llenaGrid();
        this.Usuario.setIMG("src\\Multimedia\\fondo.jpg");
        this.Usuario.btn_Insertar.addActionListener(this);
        this.Usuario.Btn_Actualizar.addActionListener(this);
        this.Usuario.Btn_Eliminar.addActionListener(this);
        this.Usuario.Btn_Mostrar.addActionListener(this);
        this.Usuario.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.Usuario.btn_Insertar) {

            this.Abreformulario_Edit();

        }

        if (e.getSource() == this.Usuario.Btn_Actualizar) {
            this.Pasa_datos();

        }

        if (e.getSource() == this.Usuario.Btn_Eliminar) {

            this.Eliminar();

        }

        if (e.getSource() == this.Usuario.Btn_Mostrar) {

        }

        if (e.getSource() == this.Usuario.Btn_Salir) {

            this.Usuario.dispose();

        }

        if (e.getSource() == this.form.btn_Insertar) {

            this.Insertar();

        }

        if (e.getSource() == this.form.btn_Cancelar) {
            this.form.dispose();

        }

        if (e.getSource() == this.form.btn_Actualizar) {

            this.Actualizar();

        }

    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Usuario_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Usuario");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.lbl_Titulo.setText("Agregar Usuario");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID_USER.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.btn_Insertar.setVisible(true);
        llenaGrid();
        form.setVisible(true);
    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Usuario.Tbl_Usuario.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta();

            while (rs.next()) {

                String A1 = rs.getString("int_ID_Usuario");
                String A2 = rs.getString("vch_Perfil");
                String A3 = rs.getString("vch_Usuario");
                String A4 = rs.getString("vch_Password");
                String A5 = rs.getString("CLV_Empleado_User");
                String A6 = rs.getString("vch_Nombre_Persona");
                String A7 = rs.getString("vch_A_Paterno");
                String A8 = rs.getString("vch_A_Materno");
                String A9 = rs.getString("int_ID_Perfil");

                modelo.addRow(new Object[]{A1, A2, A3, A4, A5, A6, A7 + " " + A8, A9});

            }

            this.Usuario.Tbl_Usuario.removeColumn(this.Usuario.Tbl_Usuario.getColumnModel().getColumn(7));

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar() {

        int Perfil = Integer.valueOf(form.lbl_ID_Perfil.getText());
        String User = form.txt_User.getText();
        String Pass = form.txt_Pass.getText();
        String Pass_confirm = form.txt_Pass_Confir.getText();
        int Empleado = Integer.valueOf(form.lbl_ID_Empleado.getText());

        int res = 0;

        if (Modelo.Consulta_Usuario_Igual(User, Empleado) == true) {

            JOptionPane.showMessageDialog(null, "El usuario Establecio ya existe o el empleado ya tiene un usuario establecido");

        } else {

            if (Pass.equals(Pass_confirm)) {

                if (this.Validar() == true) {

                    Datos.setInt_Tipo_Usuario(Perfil);
                    Datos.setVch_Usuario(User);

                    String Password_MD5 = DigestUtils.md5Hex(Pass);

                    Datos.setVch_Password(Password_MD5);
                    Datos.setCLV_Empleado_User(Empleado);
                    String a[] = {String.valueOf(Perfil), User, Pass, String.valueOf(Empleado)};

                    int campos_vacios = 0;

                    for (int i = 0; a.length > i; i++) {

                        if (a[i] == null) {
                            campos_vacios = campos_vacios + 1;
                        }
                    }

                    if (campos_vacios > 0) {
                        JOptionPane.showMessageDialog(null, "Campos vacios");
                    } else {

                        try {

                            res = Modelo.inserta(Datos);

                            if (res != 0) {
                                JOptionPane.showMessageDialog(null, "Datos Correctamente Insertados");
                                llenaGrid();
                                LimpiarCajas();
                                form.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al Insertar Datos");

                            }
                        } catch (Exception e) {
                            System.out.print("Error: " + e);
                        }

                    }

                }

            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no Coinciden");
            }

        }

        return res;

    }

    public int Actualizar() {

        int ID_USER = Integer.valueOf(form.lbl_ID_USER.getText());
        int Perfil = Integer.valueOf(form.lbl_ID_Perfil.getText());
        String User = form.txt_User.getText();
        String Pass = form.txt_Pass.getText();
        String Pass_confirm = form.txt_Pass_Confir.getText();
        int Empleado = Integer.valueOf(form.lbl_ID_Empleado.getText());

        int res = 0;

        if (Modelo.Consulta_Usuario_Igual(User, Empleado) == true) { //INICIO CONSULTA USER --- TRUE

            JOptionPane.showMessageDialog(null, "El usuario Establecio ya existe o el empleado ya tiene un usuario establecido");

        } else { //INICIO CONSULTA USER --- fALSE

            if (Pass.equals(Pass_confirm)) { //INICIO  CONFIRMA PASSWORD --- TRUE

                if (this.Validar() == true) { //INICIO  VALIDA PASSWORD --- TRUE

                    Datos.setInt_ID_Usuario(ID_USER);
                    Datos.setInt_Tipo_Usuario(Perfil);
                    Datos.setVch_Usuario(User);
                    Datos.setVch_Password(Pass);
                    Datos.setCLV_Empleado_User(Empleado);
                    String a[] = {String.valueOf(ID_USER), String.valueOf(Perfil), User, Pass, String.valueOf(Empleado)};
                    int campos_vacios = 0;

                    for (int i = 0; a.length > i; i++) {

                        if (a[i] == null) {
                            campos_vacios = campos_vacios + 1;
                        }
                    }

                    int row = this.Usuario.Tbl_Usuario.getSelectedRow();

                    if (campos_vacios > 0) {
                        JOptionPane.showMessageDialog(null, "Campos vacios");
                    } else {

                        try {

                            res = Modelo.Actualiza(Datos);

                            if (res != 0) {

                                JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                                llenaGrid();
                                LimpiarCajas();
                                form.dispose();

                            } else {
                                JOptionPane.showMessageDialog(null, "Error al Actualizar Datos");
                            }

                        } catch (Exception e) {
                            System.out.print(e);
                        }

                    }

                } //INICIO  CONFIRMA PASSWORD --- false

            } else {//fin  CONFIRMA PASSWORD --- False
                JOptionPane.showMessageDialog(null, "Las contraseñas no Coinciden");
            }//fin  CONFIRMA PASSWORD --- False
        }

        return res;
    }

    public void Eliminar() {

        int row = Usuario.Tbl_Usuario.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Usuario.Tbl_Usuario.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                Datos.setInt_ID_Usuario(clave);
                r = Modelo.elimina(Datos);

                if (r != 0) {

                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void LimpiarCajas() {

        form.txt_Empleado.setText("");
        form.txt_Pass.setText("");
        form.txt_Pass_Confir.setText("");
        form.txt_Perfil.setText("");
        form.txt_User.setText("");
        form.lbl_ID.setText("");
        form.lbl_ID_Empleado.setText("");
        form.lbl_ID_Perfil.setText("");
        form.lbl_ID_USER.setText("");
        form.lbl_Titulo.setText("");

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

                    if ("Perfil".equals(tabla.veri.getText())) {
                        String ID_PERSONA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String PERSONA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_Perfil.setText(PERSONA);
                        form.lbl_ID_Perfil.setText(ID_PERSONA);

                    }

                    if (tabla.veri.getText().equals("Usuario")) {
                        String ID_Area = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String AREA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_Empleado.setText(AREA);
                        form.lbl_ID_Empleado.setText(ID_Area);
                    }
                    tabla.dispose();

                }
            }
        }
        );
        llenaGrid();

        tabla.setVisible(true);
    }

    public void llenaTABLAS(String valor) {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.tabla.Tbl_Tabla.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta(valor);

            if (valor.equals("Perfil")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Perfil"));
                    String A2 = rs.getString("vch_Perfil");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

            if (valor.equals("Usuario")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Empleado"));
                    String A2 = rs.getString("vch_Nombre_Persona");
                    String A3 = rs.getString("vch_A_Paterno");
                    String A4 = rs.getString("vch_A_Materno");
                    String A5 = rs.getString("vch_RFC");

                    modelo.addRow(new Object[]{A1, A2 + " " + A3 + " " + A4 + " - RFC: " + A5});
                }

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void Pasa_datos() {

        int row = Usuario.Tbl_Usuario.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Usuario_Edit(f, true);
            form.setTitle("Formulario Actualizar Usuario");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);

            form.txt_Perfil.setText(Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 1).toString());
            form.txt_User.setText(Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 2).toString());
            form.txt_Pass.setText(Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 3).toString());
            form.txt_Pass_Confir.setText(Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 3).toString());
            String N_Completo = Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 5).toString() + " " + Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 6).toString();
            form.txt_Empleado.setText(N_Completo);

            form.lbl_ID_Perfil.setText(this.Usuario.Tbl_Usuario.getModel().getValueAt(this.Usuario.Tbl_Usuario.getSelectedRow(), 7).toString());
            form.lbl_ID_Empleado.setText(Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 4).toString());

            form.lbl_ID.setText(Usuario.Tbl_Usuario.getValueAt(Usuario.Tbl_Usuario.getSelectedRow(), 0).toString());

            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public boolean Validar() {

        boolean rs = false;

        String password = this.form.txt_Pass.getText();

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

    public void Diseña_Tabla(JTable Tabla) {

        Tabla.getTableHeader().setReorderingAllowed(false);
        Tabla.setRowHeight(28);//tamaño de las celdas
        Tabla.setGridColor(new java.awt.Color(0, 0, 0));
        JTableHeader jtableHeader = Tabla.getTableHeader();
        jtableHeader.setDefaultRenderer(new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JComponent jcomponent = null;

                if (value instanceof String) {
                    jcomponent = new JLabel((String) value);
                    ((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.CENTER);
                    ((JLabel) jcomponent).setSize(30, jcomponent.getWidth());
                    ((JLabel) jcomponent).setPreferredSize(new Dimension(6, jcomponent.getWidth()));
                }

                //jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(221, 211, 211)));
                jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
                jcomponent.setOpaque(true);
                //jcomponent.setBackground( new Color(236,234,219) );
                //jcomponent.setBackground(new Color(65, 65, 65));
                jcomponent.setBackground(Color.decode("#9DE7A3"));
                jcomponent.setToolTipText("Tabla Seguimiento");
                jcomponent.setForeground(Color.black);

                return jcomponent;
            }
        });

        Tabla.setTableHeader(jtableHeader);

        for (int i = 0; i < Tabla.getColumnCount(); i++) {

            Tabla.getColumnModel().getColumn(i).setCellRenderer(new Celdas("texto"));
        }
    }

    public void Dideña_Boton() {

        ImageIcon insert_Btn1 = new ImageIcon("src\\Multimedia\\Botones\\guardar-el-archivo.png");
        ImageIcon insert_Btn2 = new ImageIcon("src\\Multimedia\\Botones\\btn_Guardar_32px_2.png");
        ImageIcon insert_Btn3 = new ImageIcon("src\\Multimedia\\Botones\\btn_Guardar_32px_3.png");

        ImageIcon Update_Btn1 = new ImageIcon("src\\Multimedia\\Botones\\actualizar.png");
        ImageIcon Update_Btn2 = new ImageIcon("src\\Multimedia\\Botones\\btn_Actualizar_32px_2.png");
        ImageIcon Update_Btn3 = new ImageIcon("src\\Multimedia\\Botones\\btn_Actualizar_32px_3.png");

        ImageIcon Del_Btn1 = new ImageIcon("src\\Multimedia\\Botones\\eliminar.png");
        ImageIcon Del_Btn2 = new ImageIcon("src\\Multimedia\\Botones\\btn_Eliminar_32px_2.png");
        ImageIcon Del_Btn3 = new ImageIcon("src\\Multimedia\\Botones\\btn_Eliminar_32px_3.png");

        ImageIcon ver_Btn1 = new ImageIcon("src\\Multimedia\\Botones\\ojo.png");
        ImageIcon ver_Btn2 = new ImageIcon("src\\Multimedia\\Botones\\btn_Mostrar_32px_2.png");
        ImageIcon ver_Btn3 = new ImageIcon("src\\Multimedia\\Botones\\btn_Mostrar_32px_3.png");

        ImageIcon Salir_Btn1 = new ImageIcon("src\\Multimedia\\Botones\\cerrar-sesion.png");

        this.Usuario.btn_Insertar.setIcon(insert_Btn1);
        this.Usuario.btn_Insertar.setBorderPainted(true);
        this.Usuario.btn_Insertar.setRolloverIcon(insert_Btn2);
        this.Usuario.btn_Insertar.setPressedIcon(insert_Btn3);

        this.Usuario.Btn_Actualizar.setIcon(Update_Btn1);
        this.Usuario.Btn_Actualizar.setBorderPainted(false);
        this.Usuario.Btn_Actualizar.setRolloverIcon(Update_Btn2);
        this.Usuario.Btn_Actualizar.setPressedIcon(Update_Btn3);

        this.Usuario.Btn_Eliminar.setIcon(Del_Btn1);
        this.Usuario.Btn_Eliminar.setBorderPainted(false);
        this.Usuario.Btn_Eliminar.setRolloverIcon(Del_Btn2);
        this.Usuario.Btn_Eliminar.setPressedIcon(Del_Btn3);

        this.Usuario.Btn_Mostrar.setIcon(ver_Btn1);
        this.Usuario.Btn_Mostrar.setBorderPainted(false);
        this.Usuario.Btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Usuario.Btn_Mostrar.setPressedIcon(ver_Btn3);

        this.Usuario.Btn_Salir.setIcon(Salir_Btn1);

    }
}
