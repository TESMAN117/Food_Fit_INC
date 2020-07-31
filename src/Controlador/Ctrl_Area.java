/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Area;
import Modelo.VO.VO_Area;
import Vista.Frm_Area_Edit;
import Vista.Frm_Catalogo_Area;
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
public class Ctrl_Area extends Celdas implements ActionListener {

    DAO_Area Modelo_Area;
    Frm_Catalogo_Area Area_;
    VO_Area vo_Area;
    Frm_Area_Edit form;

    public Ctrl_Area(DAO_Area Modelo_Area, Frm_Catalogo_Area Area, VO_Area vo_Area, Frm_Area_Edit form) {
        this.Modelo_Area = Modelo_Area;
        this.Area_ = Area;
        this.vo_Area = vo_Area;
        this.form = form;

        this.Diseña_Tabla();
        llenaGrid();
        this.Area_.setIMG("src\\Multimedia\\fondo.jpg");
        this.Area_.btn_Insertar.addActionListener(this);
        this.Area_.Btn_Actualizar.addActionListener(this);
        this.Area_.Btn_Eliminar.addActionListener(this);
        this.Area_.Btn_Mostrar.addActionListener(this);
        this.Area_.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Area_.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Area_.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Area_.Btn_Eliminar) {
            Eliminar_Area();
        }

        if (e.getSource() == Area_.Btn_Mostrar) {
            this.Mostrar();
        }

        if (e.getSource() == Area_.Btn_Salir) {
            Area_.dispose();
        }

        if (e.getSource() == form.btn_Insertar) {
            Insertar_Area();
        }

        if (e.getSource() == form.btn_Cancelar) {
            form.dispose();
        }

        if (e.getSource() == form.btn_Actualizar) {
            Actualizar_Area();
        }

    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Area_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Area");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\Botones\\Area.png");
        form.setIconImage(img);
        form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
        form.lbl_Titulo.setText("Agregar Area");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.btn_Insertar.setVisible(true);
        form.setLocation(225, 175);
        llenaGrid();

        form.setVisible(true);
    }

    public void Pasa_datos() {

        int row = Area_.Tbl_Area.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Area_Edit(f, true);
            form.setTitle("Formulario Actualizar Area");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);
            form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
            form.lbl_Titulo.setText("Actualizando datos del Area");
            String ID = Area_.Tbl_Area.getValueAt(Area_.Tbl_Area.getSelectedRow(), 0).toString();
            String Nomb = Area_.Tbl_Area.getValueAt(Area_.Tbl_Area.getSelectedRow(), 1).toString();
            form.txt_Area_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);
            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Area_.Tbl_Area.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_Area.Consulta_Area();

            while (rs.next()) {

                String ID_Area = String.valueOf(rs.getInt("int_ID_area"));
                String Nombre = rs.getString("vch_Area");

                modelo.addRow(new Object[]{ID_Area, Nombre});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar_Area() {

        String nom = form.txt_Area_nombre.getText();
        vo_Area.setArea(nom);

        int res = 0;

        if ("".equals(nom)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo_Area.inserta_Area(vo_Area);

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

    public int Actualizar_Area() {

        String ID_A = form.lbl_ID.getText();
        String nom = form.txt_Area_nombre.getText();

        int res = 0;
        int row = this.Area_.Tbl_Area.getSelectedRow();

        vo_Area.setID_Area(Integer.valueOf(ID_A));
        vo_Area.setArea(nom);
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {

            if ("ID".equals(ID_A) || "".equals(nom)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = Modelo_Area.Actualiza_Area(vo_Area);

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

    public void Eliminar_Area() {

        int row = Area_.Tbl_Area.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Area_.Tbl_Area.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                vo_Area.setID_Area(clave);
                int r;
                r = Modelo_Area.elimina_Area(vo_Area);

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

        form.txt_Area_nombre.setText("");
        form.lbl_ID.setText("ID");
        form.lbl_Titulo.setText("Datos Area");
    }

    public void Diseña_Tabla() {

        this.Area_.Tbl_Area.getTableHeader().setReorderingAllowed(false);
        this.Area_.Tbl_Area.setRowHeight(28);//tamaño de las celdas
        this.Area_.Tbl_Area.setGridColor(new java.awt.Color(0, 0, 0));
        JTableHeader jtableHeader = this.Area_.Tbl_Area.getTableHeader();
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

        this.Area_.Tbl_Area.setTableHeader(jtableHeader);

        for (int i = 0; i < this.Area_.Tbl_Area.getColumnCount(); i++) {

            this.Area_.Tbl_Area.getColumnModel().getColumn(i).setCellRenderer(new Celdas("texto"));
        }
    }

    public void Mostrar() {

        int row = Area_.Tbl_Area.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Area_Edit(f, true);
            form.setTitle("Formulario Mostrar Area");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);
            form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
            form.lbl_Titulo.setText("Mostrando datos del Area");
            String ID = Area_.Tbl_Area.getValueAt(Area_.Tbl_Area.getSelectedRow(), 0).toString();
            String Nomb = Area_.Tbl_Area.getValueAt(Area_.Tbl_Area.getSelectedRow(), 1).toString();
            form.txt_Area_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);
            form.txt_Area_nombre.setEnabled(false);
            form.btn_Actualizar.setVisible(false);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

}
