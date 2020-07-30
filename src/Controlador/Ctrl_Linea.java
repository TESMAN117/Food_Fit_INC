/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Linea;
import Modelo.VO.VO_Linea;
import Vista.Frm_Catalogo_Linea;
import Vista.Frm_Linea_Edit;
import Vista.Frm_Marca_Edit;
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
import java.awt.event.MouseListener;
import java.sql.ResultSet;
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
public class Ctrl_Linea extends Celdas implements ActionListener {

    DAO_Linea Modelo_Linea;
    Frm_Catalogo_Linea Linea_;
    VO_Linea vo_linea;
    Frm_Linea_Edit form;

    public Ctrl_Linea(DAO_Linea Modelo_Linea, Frm_Catalogo_Linea Linea_, VO_Linea vo_linea, Frm_Linea_Edit form) {
        this.Modelo_Linea = Modelo_Linea;
        this.Linea_ = Linea_;
        this.vo_linea = vo_linea;
        this.form = form;
        this.Diseña_Tabla();
        this.Dideña_Boton();
        this.llenaGrid();
        this.Linea_.setIMG("src\\Multimedia\\fondo.jpg");
        this.Linea_.btn_Insertar.addActionListener(this);
        this.Linea_.Btn_Actualizar.addActionListener(this);
        this.Linea_.Btn_Eliminar.addActionListener(this);
        this.Linea_.Btn_Mostrar.addActionListener(this);
        this.Linea_.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Linea_.btn_Insertar) {
            this.Abreformulario_Edit();
        }

        if (e.getSource() == Linea_.Btn_Actualizar) {
            this.Pasa_datos();
        }

        if (e.getSource() == Linea_.Btn_Eliminar) {
            Eliminar_Linea();
        }

        if (e.getSource() == Linea_.Btn_Salir) {
            Linea_.dispose();

        }

        if (e.getSource() == form.btn_Insertar) {
            this.Insertar_Linea();

        }

        if (e.getSource() == form.btn_Actualizar) {

            this.Actualizar_Linea();
        }

        if (e.getSource() == form.btn_Cancelar) {
            form.dispose();

        }
    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Linea_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Linea");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.lbl_Titulo.setText("Agregar Linea");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void Pasa_datos() {

        int row = Linea_.Tbl_Linea.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Linea_Edit(f, true);
            form.setTitle("Formulario Actualizar Linea");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);
            form.lbl_Titulo.setText("Actualizando datos de la Linea");
            String ID = Linea_.Tbl_Linea.getValueAt(Linea_.Tbl_Linea.getSelectedRow(), 0).toString();
            String Nomb = Linea_.Tbl_Linea.getValueAt(Linea_.Tbl_Linea.getSelectedRow(), 1).toString();
            form.txt_Linea_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);
            form.lbl_Titulo.setText("Actualizando datos");

            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Linea_.Tbl_Linea.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_Linea.Consulta_Linea();

            while (rs.next()) {

                String ID_Area = String.valueOf(rs.getInt("int_ID_area"));
                String Nombre = rs.getString("vch_Area");

                modelo.addRow(new Object[]{ID_Area, Nombre});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar_Linea() {

        String nom = form.txt_Linea_nombre.getText();
        vo_linea.setLinea(nom);

        int res = 0;

        if ("".equals(nom)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo_Linea.inserta_Linea(vo_linea);

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

    public int Actualizar_Linea() {

        String ID_A = form.lbl_ID.getText();
        String nom = form.txt_Linea_nombre.getText();
        int res = 0;
        int row = this.Linea_.Tbl_Linea.getSelectedRow();

        vo_linea.setID_Linea(Integer.valueOf(ID_A));
        vo_linea.setLinea(nom);
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {

            if ("".equals(ID_A) || "".equals(nom)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = Modelo_Linea.Actualiza_Linea(vo_linea);

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
        }

        return res;

    }

    public void Eliminar_Linea() {

        int row = Linea_.Tbl_Linea.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Linea_.Tbl_Linea.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vo_linea.setID_Linea(clave);
                r = Modelo_Linea.elimina_Linea(vo_linea);

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

        form.txt_Linea_nombre.setText("");
        form.lbl_ID.setText("ID");
        form.lbl_Titulo.setText("Datos Linea");
    }

    public void Diseña_Tabla() {

        this.Linea_.Tbl_Linea.getTableHeader().setReorderingAllowed(false);
        this.Linea_.Tbl_Linea.setRowHeight(28);//tamaño de las celdas
        this.Linea_.Tbl_Linea.setGridColor(new java.awt.Color(0, 0, 0));
        JTableHeader jtableHeader = this.Linea_.Tbl_Linea.getTableHeader();
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

        this.Linea_.Tbl_Linea.setTableHeader(jtableHeader);

        for (int i = 0; i < this.Linea_.Tbl_Linea.getColumnCount(); i++) {

            this.Linea_.Tbl_Linea.getColumnModel().getColumn(i).setCellRenderer(new Celdas("texto"));
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

        this.Linea_.btn_Insertar.setIcon(insert_Btn1);
        this.Linea_.btn_Insertar.setBorderPainted(true);
        this.Linea_.btn_Insertar.setRolloverIcon(insert_Btn2);
        this.Linea_.btn_Insertar.setPressedIcon(insert_Btn3);

        this.Linea_.Btn_Actualizar.setIcon(Update_Btn1);
        this.Linea_.Btn_Actualizar.setBorderPainted(false);
        this.Linea_.Btn_Actualizar.setRolloverIcon(Update_Btn2);
        this.Linea_.Btn_Actualizar.setPressedIcon(Update_Btn3);

        this.Linea_.Btn_Eliminar.setIcon(Del_Btn1);
        this.Linea_.Btn_Eliminar.setBorderPainted(false);
        this.Linea_.Btn_Eliminar.setRolloverIcon(Del_Btn2);
        this.Linea_.Btn_Eliminar.setPressedIcon(Del_Btn3);

        this.Linea_.Btn_Mostrar.setIcon(ver_Btn1);
        this.Linea_.Btn_Mostrar.setBorderPainted(false);
        this.Linea_.Btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Linea_.Btn_Mostrar.setPressedIcon(ver_Btn3);

        this.Linea_.Btn_Salir.setIcon(Salir_Btn1);

    }
}
