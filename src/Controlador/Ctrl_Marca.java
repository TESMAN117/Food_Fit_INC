/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOl_Marca;
import Modelo.VO.VO_Marca;
import Vista.Frm_Catalogo_Marca;
import Vista.Frm_Marca_Edit;
import Vista.Frm_Persona_Edit;
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
public class Ctrl_Marca implements ActionListener {

    DAOl_Marca Modelo_Marca;
    Frm_Catalogo_Marca Marca_;
    VO_Marca vo_Marca;
    Frm_Marca_Edit form;

    public Ctrl_Marca(DAOl_Marca Modelo_Marca, Frm_Catalogo_Marca Marca_, VO_Marca vo_Marca, Frm_Marca_Edit form) {
        this.Modelo_Marca = Modelo_Marca;
        this.Marca_ = Marca_;
        this.vo_Marca = vo_Marca;
        this.form = form;
        llenaGrid();
        this.Marca_.btn_Insertar.addActionListener(this);
        this.Marca_.Btn_Actualizar.addActionListener(this);
        this.Marca_.Btn_Eliminar.addActionListener(this);
        this.Marca_.Btn_Mostrar.addActionListener(this);
        this.Marca_.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Marca_.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Marca_.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Marca_.Btn_Eliminar) {
            Eliminar_Marca();
        }

        if (e.getSource() == Marca_.Btn_Salir) {

            Marca_.dispose();
        }

        if (e.getSource() == form.btn_Insertar) {
            Insertar_Marca();
        }

        if (e.getSource() == form.btn_Cancelar) {
            LimpiarCajas();
            form.dispose();
        }
        if (e.getSource() == form.btn_Actualizar) {
            Actualizar_Marca();
        }

    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Marca_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Marca");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.lbl_Titulo.setText("Agregar Marca");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void Pasa_datos() {

        int row = Marca_.Tbl_Marca.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Marca_Edit(f, true);
            form.setTitle("Formulario Actualizar Marca");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);
            form.lbl_Titulo.setText("Actualizando datos de la Marca");
            String ID = Marca_.Tbl_Marca.getValueAt(Marca_.Tbl_Marca.getSelectedRow(), 0).toString();
            String Nomb = Marca_.Tbl_Marca.getValueAt(Marca_.Tbl_Marca.getSelectedRow(), 1).toString();
            form.txt_Marca_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);
            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Marca_.Tbl_Marca.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_Marca.Consulta_Marca();

            while (rs.next()) {

                String ID_Area = String.valueOf(rs.getInt("int_Id_marca"));
                String Nombre = rs.getString("vch_Marca");

                modelo.addRow(new Object[]{ID_Area, Nombre});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar_Marca() {

        String nom = form.txt_Marca_nombre.getText();
        vo_Marca.setNombre_Marca(nom);

        int res = 0;

        if ("".equals(nom)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo_Marca.inserta_Marca(vo_Marca);

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

    public int Actualizar_Marca() {

        String ID_A = form.lbl_ID.getText();
        String nom = form.txt_Marca_nombre.getText();
        int res = 0;
        int row = this.Marca_.Tbl_Marca.getSelectedRow();

        vo_Marca.setID_MARCA(Integer.parseInt(ID_A));
        vo_Marca.setNombre_Marca(nom);
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {

            if ("".equals(ID_A) || "".equals(nom)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = Modelo_Marca.Actualiza_Marca(vo_Marca);

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

    public void Eliminar_Marca() {

        int row = Marca_.Tbl_Marca.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Marca_.Tbl_Marca.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                vo_Marca.setID_MARCA(clave);
                int r;
                r = Modelo_Marca.elimina_Marca(vo_Marca);

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

        form.txt_Marca_nombre.setText("");
        form.lbl_ID.setText("");
        form.lbl_Titulo.setText("Datos Marca");
    }

}
