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
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class Ctrl_Linea implements ActionListener {

    DAO_Linea Modelo_Linea;
    Frm_Catalogo_Linea Linea_;
    VO_Linea vo_linea;
    Frm_Linea_Edit form;

    public Ctrl_Linea(DAO_Linea Modelo_Linea, Frm_Catalogo_Linea Linea_, VO_Linea vo_linea, Frm_Linea_Edit form) {
        this.Modelo_Linea = Modelo_Linea;
        this.Linea_ = Linea_;
        this.vo_linea = vo_linea;
        this.form = form;
        llenaGrid();
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

}
