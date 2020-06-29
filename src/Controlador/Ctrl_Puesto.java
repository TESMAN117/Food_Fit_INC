/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Puesto;
import Modelo.VO.VO_Puesto;
import Vista.Frm_Catalogo_Puesto;
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

    public Ctrl_Puesto(DAO_Puesto Modelo_Puesto, Frm_Catalogo_Puesto Puesto, VO_Puesto vo_puesto) {
        this.Modelo_Puesto = Modelo_Puesto;
        this.Puesto = Puesto;
        this.vo_puesto = vo_puesto;
        llenaGrid();
        this.Puesto.btn_Insertar.addActionListener(this);
        this.Puesto.Btn_Actualizar.addActionListener(this);
        this.Puesto.Btn_Eliminar.addActionListener(this);
        this.Puesto.Btn_Mostrar.addActionListener(this);
        this.Puesto.Btn_Salir.addActionListener(this);
        this.Puesto.txt_Puesto_nombre.addActionListener(this);
        this.Puesto.txt_Saldo.addActionListener(this);

        this.Puesto.Tbl_Puesto.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 2) {
                    String ID = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 0).toString();
                    String Nomb = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 1).toString();
                    String Saldito = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 2).toString();
                    Puesto.txt_Puesto_nombre.setText(Nomb);
                    Puesto.lbl_ID.setText(ID);
                    Puesto.lbl_Titulo.setText("--Actualizando Datos--");
                    Puesto.txt_Saldo.setText(Saldito);
                }
            }
        }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Puesto.btn_Insertar) {
            Insertar_Puesto();
        }

        if (e.getSource() == Puesto.Btn_Actualizar) {
            Actualizar_Puesto();
        }

        if (e.getSource() == Puesto.Btn_Eliminar) {
            Eliminar_Puesto();
        }

        if (e.getSource() == Puesto.Btn_Salir) {
            Puesto.dispose();
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

        String nom = Puesto.txt_Puesto_nombre.getText();
        float Saldo = Float.parseFloat(Puesto.txt_Puesto_nombre.getText());

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

        int ID_A = Integer.parseInt(Puesto.lbl_ID.getText());
        String nom = Puesto.txt_Puesto_nombre.getText();
        float Saldo = Float.parseFloat(Puesto.txt_Puesto_nombre.getText());
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
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void LimpiarCajas() {

        Puesto.txt_Puesto_nombre.setText("");
        Puesto.lbl_ID.setText("");
        Puesto.txt_Saldo.setText("");
        Puesto.lbl_Titulo.setText("Datos Puesto");
    }

}
