/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOl_Marca;
import Modelo.VO.VO_Marca;
import Vista.Frm_Catalogo_Marca;
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

    public Ctrl_Marca(DAOl_Marca Modelo_Marca, Frm_Catalogo_Marca Marca_, VO_Marca vo_Marca) {
        this.Modelo_Marca = Modelo_Marca;
        this.Marca_ = Marca_;
        this.vo_Marca = vo_Marca;
        llenaGrid();
        this.Marca_.btn_Insertar.addActionListener(this);
        this.Marca_.Btn_Actualizar.addActionListener(this);
        this.Marca_.Btn_Eliminar.addActionListener(this);
        this.Marca_.Btn_Mostrar.addActionListener(this);
        this.Marca_.Btn_Salir.addActionListener(this);
        this.Marca_.txt_Marca_nombre.addActionListener(this);

        this.Marca_.Tbl_Marca.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 2) {
                    String ID = Marca_.Tbl_Marca.getValueAt(Marca_.Tbl_Marca.getSelectedRow(), 0).toString();
                    String Nomb = Marca_.Tbl_Marca.getValueAt(Marca_.Tbl_Marca.getSelectedRow(), 1).toString();
                    Marca_.txt_Marca_nombre.setText(Nomb);
                    Marca_.lbl_ID.setText(ID);
                    Marca_.lbl_Titulo.setText("--Actualizando Datos--");
                }
            }
        }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Marca_.btn_Insertar) {
            Insertar_Marca();
        }

        if (e.getSource() == Marca_.Btn_Actualizar) {
            Actualizar_Marca();
        }

        if (e.getSource() == Marca_.Btn_Eliminar) {
            Eliminar_Marca();
        }

        if (e.getSource() == Marca_.Btn_Salir) {

            Marca_.dispose();
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

        String nom = Marca_.txt_Marca_nombre.getText();
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

        String ID_A = Marca_.lbl_ID.getText();
        String nom = Marca_.txt_Marca_nombre.getText();
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

        Marca_.txt_Marca_nombre.setText("");
        Marca_.lbl_ID.setText("");
        Marca_.lbl_Titulo.setText("Datos Area");
    }

}
