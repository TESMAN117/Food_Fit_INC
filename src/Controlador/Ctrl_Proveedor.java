/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Proveedor;
import Modelo.VO.VO_Proveedor;
import Vista.Frm_Catalogo_Proveedor;
import Vista.Frm_Platillo_Edit;
import Vista.Frm_Proveedor_Edit;
import food_fit_inc.Celdas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jesus
 */
public class Ctrl_Proveedor extends Celdas implements ActionListener {

    DAO_Proveedor Modelo;
    VO_Proveedor vo_proveedor;
    Frm_Catalogo_Proveedor Proveedor;
    Frm_Proveedor_Edit form;

    public Ctrl_Proveedor(DAO_Proveedor Modelo, VO_Proveedor vo_proveedor, Frm_Catalogo_Proveedor Proveedor, Frm_Proveedor_Edit form) {
        this.Modelo = Modelo;
        this.vo_proveedor = vo_proveedor;
        this.Proveedor = Proveedor;
        this.form = form;
        this.Diseña_Tabla(this.Proveedor.Tbl_Proveedor);
        this.Dideña_Boton();
        this.llenaGrid();
        this.Proveedor.setIMG("src\\Multimedia\\fondo.jpg");
        this.Proveedor.btn_Insertar.addActionListener(this);
        this.Proveedor.Btn_Actualizar.addActionListener(this);
        this.Proveedor.Btn_Eliminar.addActionListener(this);
        this.Proveedor.Btn_Mostrar.addActionListener(this);
        this.Proveedor.Btn_Salir.addActionListener(this);
        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Proveedor.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Proveedor.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Proveedor.Btn_Eliminar) {
            Eliminar();
        }

        if (e.getSource() == form.btn_Cancelar) {
            form.dispose();
        }

        if (e.getSource() == form.btn_Insertar) {
            Insertar();
        }

        if (e.getSource() == form.btn_Actualizar) {
            Actualizar();
        }

    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Proveedor_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Proveedor");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.lbl_titulo.setText("Agregar Proveedor");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);

        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Proveedor.Tbl_Proveedor.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta();

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Proveedor"));
                String A2 = rs.getString("vch_Nombre_Proveedor");
                String A3 = rs.getString("vch_Telefono");
                String A4 = rs.getString("vch_Direccon");

                modelo.addRow(new Object[]{A1, A2, A3, A4});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar() {

        String Nombre = form.txt_Nombre.getText();
        String Telefono = form.txt_Telefono.getText();
        String Direcion = form.txt_Direccion.getText();

        vo_proveedor.setNombre(Nombre);
        vo_proveedor.setTelefono(Telefono);
        vo_proveedor.setDireccion(Direcion);

        String a[] = {Nombre, Telefono, Direcion};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {

            if (" ".equals(a[i].toString())) {

                campos_vacios = campos_vacios + 1;

            }
        }

        int res = 0;

        if (campos_vacios > 0) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo.inserta(vo_proveedor);

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

        return res;

    }

    public int Actualizar() {

        String Nombre = form.txt_Nombre.getText();
        String telefono = form.txt_Telefono.getText();
        String Direccion = form.txt_Direccion.getText();

        int ID_PROVE = Integer.valueOf(form.lbl_ID.getText());

        String a[] = {Nombre, telefono, Direccion, String.valueOf(ID_PROVE)};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {

            if (" ".equals(a[i].toString())) {

                campos_vacios = campos_vacios + 1;

            }
        }

        vo_proveedor.setID_Proveedor(ID_PROVE);
        vo_proveedor.setNombre(Nombre);
        vo_proveedor.setTelefono(telefono);
        vo_proveedor.setDireccion(Direccion);

        int res = 0;
        int row = this.Proveedor.Tbl_Proveedor.getSelectedRow();

        if (campos_vacios > 0) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo.Actualiza(vo_proveedor);

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

        return res;

    }

    public void Eliminar() {

        int row = Proveedor.Tbl_Proveedor.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Proveedor.Tbl_Proveedor.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                this.vo_proveedor.setID_Proveedor(clave);
                int r;

                r = Modelo.elimina(vo_proveedor);

                if (r != 0) {

                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void Pasa_datos() {

        int row = Proveedor.Tbl_Proveedor.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Proveedor_Edit(f, true);
            form.setTitle("Formulario Actualizar Proveedor");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);

            form.txt_Nombre.setText(Proveedor.Tbl_Proveedor.getValueAt(Proveedor.Tbl_Proveedor.getSelectedRow(), 1).toString());
            form.txt_Telefono.setText(Proveedor.Tbl_Proveedor.getValueAt(Proveedor.Tbl_Proveedor.getSelectedRow(), 2).toString());
            form.txt_Direccion.setText(Proveedor.Tbl_Proveedor.getValueAt(Proveedor.Tbl_Proveedor.getSelectedRow(), 3).toString());

            form.lbl_ID.setText(Proveedor.Tbl_Proveedor.getValueAt(Proveedor.Tbl_Proveedor.getSelectedRow(), 0).toString());
            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void LimpiarCajas() {

        form.txt_Nombre.setText(" ");
        form.txt_Telefono.setText(" ");
        form.txt_Direccion.setText(" ");
        form.lbl_ID.setText(" ");
        form.lbl_titulo.setText(" ");

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

        this.Proveedor.btn_Insertar.setIcon(insert_Btn1);
        this.Proveedor.btn_Insertar.setBorderPainted(true);
        this.Proveedor.btn_Insertar.setRolloverIcon(insert_Btn2);
        this.Proveedor.btn_Insertar.setPressedIcon(insert_Btn3);

        this.Proveedor.Btn_Actualizar.setIcon(Update_Btn1);
        this.Proveedor.Btn_Actualizar.setBorderPainted(false);
        this.Proveedor.Btn_Actualizar.setRolloverIcon(Update_Btn2);
        this.Proveedor.Btn_Actualizar.setPressedIcon(Update_Btn3);

        this.Proveedor.Btn_Eliminar.setIcon(Del_Btn1);
        this.Proveedor.Btn_Eliminar.setBorderPainted(false);
        this.Proveedor.Btn_Eliminar.setRolloverIcon(Del_Btn2);
        this.Proveedor.Btn_Eliminar.setPressedIcon(Del_Btn3);

        this.Proveedor.Btn_Mostrar.setIcon(ver_Btn1);
        this.Proveedor.Btn_Mostrar.setBorderPainted(false);
        this.Proveedor.Btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Proveedor.Btn_Mostrar.setPressedIcon(ver_Btn3);

        this.Proveedor.Btn_Salir.setIcon(Salir_Btn1);

    }
}
