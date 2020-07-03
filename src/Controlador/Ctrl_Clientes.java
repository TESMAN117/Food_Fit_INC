/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Cliente;
import Modelo.VO.VO_Cliente;
import Vista.Frm_Catalogo_Cliente;
import Vista.Frm_Cliente_Edit;
import Vista.Frm_Tablas;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class Ctrl_Clientes implements ActionListener {

    DAO_Cliente Modelo;
    VO_Cliente vo_cliente;
    Frm_Catalogo_Cliente Cliente;
    Frm_Cliente_Edit form;
    Frm_Tablas tabla_;

    public Ctrl_Clientes(DAO_Cliente Modelo, VO_Cliente vo_cliente, Frm_Catalogo_Cliente Cliente, Frm_Cliente_Edit form, Frm_Tablas tabla) {
        this.Modelo = Modelo;
        this.vo_cliente = vo_cliente;
        this.Cliente = Cliente;
        this.form = form;
        this.tabla_ = tabla;

        this.llenaGrid();

        this.Cliente.btn_Insertar.addActionListener(this);
        this.Cliente.Btn_Actualizar.addActionListener(this);
        this.Cliente.Btn_Eliminar.addActionListener(this);
        this.Cliente.Btn_Mostrar.addActionListener(this);
        this.Cliente.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);
        this.form.btn_persona.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Cliente.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Cliente.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Cliente.Btn_Eliminar) {
            Eliminar();
        }

        if (e.getSource() == Cliente.Btn_Salir) {
            this.Cliente.dispose();
        }

        if (e.getSource() == form.btn_persona) {
            Abreformulario_Tabla();
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
        form = new Frm_Cliente_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Cliente");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.Lbl_Titulo.setText("Agregar Cliente");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);

        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void Pasa_datos() {

        int row = Cliente.Tbl_Cliente.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Cliente_Edit(f, true);
            form.setTitle("Formulario Actualizar Cliente");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);

            form.txt_Nombre.setText(Cliente.Tbl_Cliente.getValueAt(Cliente.Tbl_Cliente.getSelectedRow(), 1).toString());
            form.txt_Apellidos.setText(Cliente.Tbl_Cliente.getValueAt(Cliente.Tbl_Cliente.getSelectedRow(), 2).toString());
            form.lbl_ID.setText(Cliente.Tbl_Cliente.getValueAt(Cliente.Tbl_Cliente.getSelectedRow(), 0).toString());

            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Cliente.Tbl_Cliente.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta();

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Cliente"));
                String A2 = rs.getString("vch_Nombre_Persona");
                String A3 = rs.getString("vch_A_Paterno") + " " + rs.getString("vch_A_Materno");

                modelo.addRow(new Object[]{A1, A2, A3});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar() {

        int Persona = 0;
        Persona = Integer.valueOf(form.LBL_Perso.getText());

        vo_cliente.setPersona_clv(Persona);

        int res = 0;

        if (form.txt_Nombre.getText().equals(" ") || form.txt_Apellidos.getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo.inserta(vo_cliente);

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

        int Persona = 0;
        Persona = Integer.valueOf(form.LBL_Perso.getText());
        int id = Integer.valueOf(form.lbl_ID.getText());

        vo_cliente.setPersona_clv(Persona);
        vo_cliente.setID(id);

        int res = 0;
        int row = this.Cliente.Tbl_Cliente.getSelectedRow();

        if (form.txt_Nombre.getText().equals(" ") || form.txt_Apellidos.getText().equals(" ") || form.lbl_ID.getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo.Actualiza(vo_cliente);

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

        int row = Cliente.Tbl_Cliente.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Cliente.Tbl_Cliente.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vo_cliente.setID(clave);
                r = Modelo.elimina(vo_cliente);

                if (r != 0) {

                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void Abreformulario_Tabla() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(tabla_);
        tabla_ = new Frm_Tablas(f, true);
        tabla_.setTitle("Tabla Persona");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        tabla_.setIconImage(img);
        llenaTABLAS();
        this.tabla_.Tbl_Tabla.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 2) {

                    String Id = tabla_.Tbl_Tabla.getValueAt(tabla_.Tbl_Tabla.getSelectedRow(), 0).toString();
                    String clave = tabla_.Tbl_Tabla.getValueAt(tabla_.Tbl_Tabla.getSelectedRow(), 1).toString();

                    String[] parts = clave.split(" ");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    

                    form.txt_Nombre.setText(part1);
                    form.txt_Apellidos.setText(part2 + " " + part3);
                    form.LBL_Perso.setText(Id);
                    tabla_.dispose();

                }
            }
        }
        );
        tabla_.setVisible(true);
    }

    public void llenaTABLAS() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.tabla_.Tbl_Tabla.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta_Persona();

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Persona"));
                String A2 = rs.getString("vch_Nombre_Persona");
                String A3 = rs.getString("vch_A_Paterno");
                String A4 = rs.getString("vch_A_Materno");

                modelo.addRow(new Object[]{A1, A2 + " " + A3 + " " + A4});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void LimpiarCajas() {

        form.txt_Nombre.setText("");
        form.txt_Apellidos.setText("");

    }
}
