/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Puesto;
import Modelo.VO.VO_Puesto;
import Vista.Frm_Catalogo_Puesto;
import Vista.Frm_Marca_Edit;
import Vista.Frm_Puesto_Edit;
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
public class Ctrl_Puesto implements ActionListener {

    DAO_Puesto Modelo_Puesto;
    Frm_Catalogo_Puesto Puesto;
    VO_Puesto vo_puesto;
    Frm_Puesto_Edit form;

    public Ctrl_Puesto(DAO_Puesto Modelo_Puesto, Frm_Catalogo_Puesto Puesto, VO_Puesto vo_puesto,Frm_Puesto_Edit form) {
        this.Modelo_Puesto = Modelo_Puesto;
        this.Puesto = Puesto;
        this.vo_puesto = vo_puesto;
        this.form=form;
        llenaGrid();
        this.Puesto.btn_Insertar.addActionListener(this);
        this.Puesto.Btn_Actualizar.addActionListener(this);
        this.Puesto.Btn_Eliminar.addActionListener(this);
        this.Puesto.Btn_Mostrar.addActionListener(this);
        this.Puesto.Btn_Salir.addActionListener(this);
        

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Puesto.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Puesto.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Puesto.Btn_Eliminar) {
            Eliminar_Puesto();
        }

        if (e.getSource() == Puesto.Btn_Salir) {
            Puesto.dispose();
        }

        if (e.getSource() == form.btn_Insertar) {
            Insertar_Puesto();
        }

        if (e.getSource() == form.btn_Cancelar) {
            LimpiarCajas();
            form.dispose();
        }
        if (e.getSource() == form.btn_Actualizar) {
            Actualizar_Puesto();
        }

    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Puesto_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Puesto");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.lbl_Titulo.setText("Agregar Puesto");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void Pasa_datos() {

        int row = Puesto.Tbl_Puesto.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Puesto_Edit(f, true);
            form.setTitle("Formulario Actualizar Puesto");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);

            String ID = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 0).toString();
            String Nomb = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 1).toString();
            String Saldito = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 2).toString();
            form.txt_Puesto_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);
            form.lbl_Titulo.setText("--Actualizando Datos--");
            form.txt_Saldo.setText(Saldito);

            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Puesto.Tbl_Puesto.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_Puesto.Consulta_Marca();

            while (rs.next()) {

                String ID_Puesto = String.valueOf(rs.getInt("int_ID_puesto"));
                String Nombre = rs.getString("vch_Puesto");
                String Saldo = rs.getString("flt_Saldo");

                modelo.addRow(new Object[]{ID_Puesto, Nombre, Saldo});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar_Puesto() {

        String nom = form.txt_Puesto_nombre.getText();
        float Saldo = Float.parseFloat(form.txt_Saldo.getText());

        vo_puesto.setPuesto(nom);
        vo_puesto.setSueldo(Saldo);

        int res = 0;

        if ("".equals(nom) || "".equals(Saldo)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo_Puesto.inserta_Puesto(vo_puesto);

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

    public int Actualizar_Puesto() {

        int ID_A = Integer.parseInt(form.lbl_ID.getText());
        String nom = form.txt_Puesto_nombre.getText();
        float Saldo = Float.parseFloat(form.txt_Saldo.getText());
        int res = 0;
        int row = this.Puesto.Tbl_Puesto.getSelectedRow();

        vo_puesto.setID_Puesto(ID_A);
        vo_puesto.setPuesto(nom);
        vo_puesto.setSueldo(Saldo);
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {

            if ("".equals(ID_A) || "".equals(nom) || "".equals(Saldo)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = Modelo_Puesto.Actualiza_Puesto(vo_puesto);

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

    public void Eliminar_Puesto() {

        int row = Puesto.Tbl_Puesto.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Puesto.Tbl_Puesto.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vo_puesto.setID_Puesto(clave);
                r = Modelo_Puesto.elimina_Puesto(vo_puesto);

                if (r != 0) {

                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();
                    form.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void LimpiarCajas() {

        form.txt_Puesto_nombre.setText("");
        form.lbl_ID.setText("");
        form.txt_Saldo.setText("");
        form.lbl_Titulo.setText("Datos Puesto");
    }

}
