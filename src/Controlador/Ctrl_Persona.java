/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Persona;
import Modelo.VO.VO_Persona;
import Vista.Frm_Catalogo_Persona;
import Vista.Frm_Persona_Edit;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class Ctrl_Persona implements ActionListener {

    DAO_Persona Modelo_persona;
    VO_Persona vo_persona;
    Frm_Catalogo_Persona Persona;
    Frm_Persona_Edit Formulario_edit;

    public Ctrl_Persona(DAO_Persona Modelo_persona, VO_Persona vo_persona, Frm_Catalogo_Persona Persona, Frm_Persona_Edit frm_persona) {
        this.Modelo_persona = Modelo_persona;
        this.vo_persona = vo_persona;
        this.Persona = Persona;
        this.Formulario_edit = frm_persona;
        llenaGrid();

        this.Persona.btn_Insertar.addActionListener(this);
        this.Persona.Btn_Actualizar.addActionListener(this);
        this.Persona.Btn_Eliminar.addActionListener(this);
        this.Persona.Btn_Mostrar.addActionListener(this);
        this.Persona.Btn_Salir.addActionListener(this);

        this.Formulario_edit.btn_Actualizar.addActionListener(this);
        this.Formulario_edit.btn_Cancelar.addActionListener(this);
        this.Formulario_edit.btn_Insertar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Persona.btn_Insertar) {
            this.Abreformulario_Edit();

        }

        if (e.getSource() == Persona.Btn_Actualizar) {
            this.Modificar_Campos();

        }

        if (e.getSource() == Persona.Btn_Eliminar) {
            this.Eliminar_Marca();

        }

        if (e.getSource() == Persona.Btn_Mostrar) {
            this.Mostrar();

        }

        if (e.getSource() == Persona.Btn_Salir) {
            this.Persona.dispose();
        }

        if (e.getSource() == Formulario_edit.btn_Insertar) {

            Insertar_Marca();
        }

        if (e.getSource() == Formulario_edit.btn_Actualizar) {
            Actualizar_Marca();
        }

        if (e.getSource() == Formulario_edit.btn_Cancelar) {
            Formulario_edit.dispose();
        }

    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(Formulario_edit);
        Formulario_edit = new Frm_Persona_Edit(f, true);
        LimpiarCajas();
        Formulario_edit.setTitle("Formulario Agregar Persona");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        Formulario_edit.setIconImage(img);
        Formulario_edit.lbl_Titulo.setText("Agregar Persona");
        Formulario_edit.btn_Actualizar.setVisible(false);
        Formulario_edit.lbl_ID.setVisible(false);
        Formulario_edit.btn_Insertar.setVisible(true);
        llenaGrid();
        Carga_combo(Formulario_edit.CBB_Sucursal);
        //carga_combo();
        Formulario_edit.setVisible(true);
    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Persona.Tbl_Persona.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_persona.Consulta_Persona("Tabla");

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Persona"));
                String A2 = rs.getString("vch_Nombre_Persona");
                String A3 = rs.getString("vch_A_Paterno") + " " + rs.getString("vch_A_Materno");
                String A4 = rs.getString("vch_Correo");
                String A5 = rs.getString("vch_Telefono");
                String A6 = rs.getString("vch_RFC");
                String A7 = rs.getString("CLV_Sucursal_Persoanl");
                String A8 = rs.getString("vch_Codigo_Postal");
                String A9 = rs.getString("vch_Domicilio");

                modelo.addRow(new Object[]{A1, A2, A3, A4, A5, A6, A7, A8, A9});
            }

        } catch (SQLException e) {

            System.out.println(e);
        }

    }

    public void Carga_combo(JComboBox combo) {
        try {

            ResultSet rs = Modelo_persona.Consulta_Persona("Combo");

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Sucursal"));
                String A2 = rs.getString("vch_Nombre");

                combo.addItem(A1 + "-" + A2);
            }

        } catch (SQLException e) {

            System.out.println(e);
        }

    }

    public int Insertar_Marca() {

        vo_persona.setNombre(this.Formulario_edit.txt_Nombre.getText());
        vo_persona.setAP(this.Formulario_edit.txt_APaterno.getText());
        vo_persona.setAM(this.Formulario_edit.txt_AMaterno.getText());
        vo_persona.setCorreo(this.Formulario_edit.txt_Correo.getText());
        vo_persona.setTelefono(this.Formulario_edit.txt_Telefono.getText());
        vo_persona.setRFC(this.Formulario_edit.txt_RFC.getText());
        String ID_SUCU = this.Formulario_edit.CBB_Sucursal.getSelectedItem().toString();
        String[] parts = ID_SUCU.split("-");
        String IDDD = parts[0]; // 123

        vo_persona.setID_Sucursal(Integer.valueOf(IDDD));
        vo_persona.setCP(this.Formulario_edit.txt_CP.getText());
        vo_persona.setDomicilio(this.Formulario_edit.txt_Domicilio.getText());

        String a[] = {vo_persona.getNombre(), vo_persona.getAP(), vo_persona.getAM(), vo_persona.getCorreo(), vo_persona.getTelefono(),
            vo_persona.getRFC(), String.valueOf(vo_persona.getID_Sucursal()), vo_persona.getCP(), vo_persona.getDomicilio()};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {
            JOptionPane.showMessageDialog(null, i);

            if ("".equals(a[i].toString())) {

                campos_vacios = campos_vacios + 1;

            }
        }
        
        int res = 0;

        if (campos_vacios > 0) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo_persona.inserta_Persona(vo_persona);
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

    public void Modificar_Campos() {
        int row = Persona.Tbl_Persona.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(Formulario_edit);
            Formulario_edit = new Frm_Persona_Edit(f, true);
            Formulario_edit.setTitle("Formulario Actualizar Persona");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            Formulario_edit.setIconImage(img);
            Formulario_edit.lbl_Titulo.setText("ACTUALIZAR Persona");
            Formulario_edit.btn_Insertar.setVisible(false);
            Formulario_edit.btn_Actualizar.setVisible(true);
            int fila = Persona.Tbl_Persona.getSelectedRow();
            Carga_combo(Formulario_edit.CBB_Sucursal);
            Formulario_edit.lbl_ID.setText(Persona.Tbl_Persona.getValueAt(fila, 0).toString());
            Formulario_edit.txt_Nombre.setText(Persona.Tbl_Persona.getValueAt(fila, 1).toString());
            String apellidos = Persona.Tbl_Persona.getValueAt(fila, 2).toString();
            String[] parts = apellidos.split(" ");
            String AP = parts[0]; // 123
            String AM = parts[1]; // 654321
            Formulario_edit.txt_APaterno.setText(AP);
            Formulario_edit.txt_AMaterno.setText(AM);
            Formulario_edit.txt_Correo.setText(Persona.Tbl_Persona.getValueAt(fila, 3).toString());
            Formulario_edit.txt_Telefono.setText(Persona.Tbl_Persona.getValueAt(fila, 4).toString());
            Formulario_edit.txt_RFC.setText(Persona.Tbl_Persona.getValueAt(fila, 5).toString());
            Formulario_edit.CBB_Sucursal.setSelectedItem(Persona.Tbl_Persona.getValueAt(fila, 6).toString());
            Formulario_edit.txt_CP.setText(Persona.Tbl_Persona.getValueAt(fila, 7).toString());
            Formulario_edit.txt_Domicilio.setText(Persona.Tbl_Persona.getValueAt(fila, 8).toString());
            Formulario_edit.setVisible(true);
            llenaGrid();
        }
    }

    public void Mostrar() {
        int row = Persona.Tbl_Persona.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(Formulario_edit);
            Formulario_edit = new Frm_Persona_Edit(f, true);
            Formulario_edit.setTitle("Formulario Actualizar Sucursal");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            Formulario_edit.setIconImage(img);
            Formulario_edit.lbl_Titulo.setText("ACTUALIZAR Persona");
            Formulario_edit.btn_Insertar.setVisible(false);
            Formulario_edit.btn_Actualizar.setVisible(true);
            int fila = Persona.Tbl_Persona.getSelectedRow();
            Carga_combo(Formulario_edit.CBB_Sucursal);
            Formulario_edit.lbl_ID.setText(Persona.Tbl_Persona.getValueAt(fila, 0).toString());
            Formulario_edit.txt_Nombre.setText(Persona.Tbl_Persona.getValueAt(fila, 1).toString());
            String apellidos = Persona.Tbl_Persona.getValueAt(fila, 2).toString();
            String[] parts = apellidos.split(" ");
            String AP = parts[0]; // 123
            String AM = parts[1]; // 654321
            Formulario_edit.txt_APaterno.setText(AP);
            Formulario_edit.txt_AMaterno.setText(AM);
            Formulario_edit.txt_Correo.setText(Persona.Tbl_Persona.getValueAt(fila, 3).toString());
            Formulario_edit.txt_Telefono.setText(Persona.Tbl_Persona.getValueAt(fila, 4).toString());
            Formulario_edit.txt_RFC.setText(Persona.Tbl_Persona.getValueAt(fila, 5).toString());
            Formulario_edit.CBB_Sucursal.setSelectedItem(Persona.Tbl_Persona.getValueAt(fila, 6).toString());
            Formulario_edit.txt_CP.setText(Persona.Tbl_Persona.getValueAt(fila, 7).toString());
            Formulario_edit.txt_Domicilio.setText(Persona.Tbl_Persona.getValueAt(fila, 8).toString());
            //------------------------------------------------

            this.Formulario_edit.txt_Nombre.setEditable(false);
            this.Formulario_edit.txt_APaterno.setEditable(false);
            this.Formulario_edit.txt_AMaterno.setEditable(false);
            this.Formulario_edit.txt_Correo.setEditable(false);
            this.Formulario_edit.txt_Telefono.setEditable(false);
            this.Formulario_edit.txt_RFC.setEditable(false);
            this.Formulario_edit.CBB_Sucursal.setEditable(false);
            this.Formulario_edit.txt_CP.setEditable(false);
            this.Formulario_edit.txt_Domicilio.setEditable(false);
            this.Formulario_edit.lbl_Titulo.setText("Mostrar Datos Persona");

            Formulario_edit.setVisible(true);
            llenaGrid();
        }
    }

    public int Actualizar_Marca() {

        int res = 0;
        int row = this.Persona.Tbl_Persona.getSelectedRow();

        // vo_Marca.setID_MARCA(Integer.parseInt(ID_A));
        vo_persona.setID(Integer.valueOf(this.Formulario_edit.lbl_ID.getText()));
        vo_persona.setNombre(this.Formulario_edit.txt_Nombre.getText());
        vo_persona.setAP(this.Formulario_edit.txt_APaterno.getText());
        vo_persona.setAM(this.Formulario_edit.txt_AMaterno.getText());
        vo_persona.setCorreo(this.Formulario_edit.txt_Correo.getText());
        vo_persona.setTelefono(this.Formulario_edit.txt_Telefono.getText());
        vo_persona.setRFC(this.Formulario_edit.txt_RFC.getText());
        String ID_SUCU = this.Formulario_edit.CBB_Sucursal.getSelectedItem().toString();
        String[] parts = ID_SUCU.split("-");
        String IDDD = parts[0]; // 123

        vo_persona.setID_Sucursal(Integer.valueOf(IDDD));
        vo_persona.setCP(this.Formulario_edit.txt_CP.getText());
        vo_persona.setDomicilio(this.Formulario_edit.txt_Domicilio.getText());

        String a[] = {String.valueOf(vo_persona.getID()), vo_persona.getNombre(), vo_persona.getAP(), vo_persona.getAM(), vo_persona.getCorreo(), vo_persona.getTelefono(),
            vo_persona.getRFC(), String.valueOf(vo_persona.getID_Sucursal()), vo_persona.getCP(), vo_persona.getDomicilio()};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {

            if ("".equals(a[i].toString())) {

                campos_vacios = campos_vacios + 1;

            }
        }

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {

            if (campos_vacios > 0) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = Modelo_persona.Actualiza_Persona(vo_persona);

                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                        llenaGrid();
                        Formulario_edit.dispose();
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

        int row = Persona.Tbl_Persona.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Persona.Tbl_Persona.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                vo_persona.setID(clave);
                int r;
                r = Modelo_persona.elimina_Persona(vo_persona);

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

        this.Formulario_edit.lbl_ID.setText("");
        this.Formulario_edit.txt_Nombre.setText("");
        this.Formulario_edit.txt_APaterno.setText("");
        this.Formulario_edit.txt_AMaterno.setText("");
        this.Formulario_edit.txt_Correo.setText("");
        this.Formulario_edit.txt_Telefono.setText("");
        this.Formulario_edit.txt_RFC.setText("");
        this.Formulario_edit.CBB_Sucursal.setSelectedItem("Selecionar");
        this.Formulario_edit.txt_CP.setText("");
        this.Formulario_edit.txt_Domicilio.setText("");
        this.Formulario_edit.lbl_Titulo.setText("Accion");
    }

}
