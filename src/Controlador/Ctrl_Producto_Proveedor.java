/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Producto_Proveedor;
import Modelo.VO.VO_Producto_Proveedor;
import Vista.Frm_Catalogo_Producto_Proveedor;
import Vista.Frm_Empleado_Edit;
import Vista.Frm_Producto_Proveedor_Edit;
import Vista.Frm_Tablas;
import com.toedter.calendar.JDateChooser;
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
import java.io.File;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Ctrl_Producto_Proveedor extends Celdas implements ActionListener {

    DAO_Producto_Proveedor Modelo;
    VO_Producto_Proveedor vo_producto;
    Frm_Catalogo_Producto_Proveedor Producto;
    Frm_Producto_Proveedor_Edit form;
    Frm_Tablas tabla;

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public Ctrl_Producto_Proveedor(DAO_Producto_Proveedor Modelo, VO_Producto_Proveedor vo_producto, Frm_Catalogo_Producto_Proveedor Producto, Frm_Producto_Proveedor_Edit from, Frm_Tablas tabla) {
        this.Modelo = Modelo;
        this.vo_producto = vo_producto;
        this.Producto = Producto;
        this.form = from;
        this.tabla = tabla;
        this.Diseña_Tabla(this.Producto.Tbl_Producto);
        this.Dideña_Boton();
        this.llenaGrid();
        this.Producto.setIMG("src\\Multimedia\\fondo.jpg");
        this.Producto.btn_Insertar.addActionListener(this);
        this.Producto.Btn_Actualizar.addActionListener(this);
        this.Producto.Btn_Eliminar.addActionListener(this);
        this.Producto.Btn_Mostrar.addActionListener(this);
        this.Producto.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);
        this.form.btn_Proveedor.addActionListener(this);
        this.form.btn_Marca.addActionListener(this);
        this.form.btn_Linea.addActionListener(this);
        this.form.btn_Sucursal.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Producto.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Producto.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Producto.Btn_Eliminar) {
            Eliminar();
        }

        if (e.getSource() == form.btn_Proveedor) {
            Abreformulario_Tabla("Proveedor");
        }

        if (e.getSource() == form.btn_Marca) {
            Abreformulario_Tabla("Marca");
        }

        if (e.getSource() == form.btn_Linea) {
            Abreformulario_Tabla("Linea");
        }

        if (e.getSource() == form.btn_Sucursal) {
            Abreformulario_Tabla("Sucursal");
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
        form = new Frm_Producto_Proveedor_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Producto Proveedor");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.lbl_Titulo.setText("Agregar Producto Proveedor");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.ID_LABEL.setVisible(false);
        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Producto.Tbl_Producto.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta("Producto");

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Producto_Proveedor"));
                String A2 = rs.getString("vch_Nombre_Producto_provee");
                String A3 = rs.getString("vch_Presentacon_Prod_Provee");
                String A4 = rs.getString("vch_Tipo_Existencia");
                String A5 = rs.getString("int_Cantidad_Exis");
                String A6 = rs.getString("CLV_Proveedor");
                String A7 = rs.getString("flt_Costo_Unitario");
                String A8 = rs.getString("CLV_Marca");
                String A9 = rs.getString("CLV_Linea");
                String A10 = rs.getString("CLV_Sucursal_Proveedor");
                String A11 = rs.getString("date_Fecha_Compra");
                String A12 = rs.getString("flt_Total");

                modelo.addRow(new Object[]{A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar() {

        String Nombre = this.form.txt_Nombre.getText();
        String Presentacion = this.form.txt_Presentacion.getText();
        String Tipo_Exis = this.form.CBM_Tipo_Existencia.getSelectedItem().toString();
        int Cantidad = Integer.valueOf(this.form.txt_Cantidad.getText());
        int CLV_Proveedor = Integer.valueOf(form.lbl_Proveedor.getText());
        float costo = Float.valueOf(this.form.txt_Costo_Unitario.getText());
        int Marca = Integer.valueOf(form.lbl_Marca.getText());
        int Linea = Integer.valueOf(form.lbl_Linea.getText());
        int Sucursal = Integer.valueOf(form.lbl_Sucursal.getText());
        String Fecha = getfecha(this.form.date_Fecha);
        float total = Cantidad * costo;

        vo_producto.setVch_Nombre_Producto_provee(Nombre);
        vo_producto.setVch_Presentacon_Prod_Provee(Presentacion);
        vo_producto.setVch_Tipo_Existencia(Tipo_Exis);
        vo_producto.setInt_Cantidad_Exis(Cantidad);
        vo_producto.setCLV_Proveedor(CLV_Proveedor);
        vo_producto.setFlt_Costo_Unitario(costo);
        vo_producto.setCLV_Marca(Marca);
        vo_producto.setCLV_Linea(Linea);
        vo_producto.setCLV_Sucursal_Proveedor(CLV_Proveedor);
        vo_producto.setDate_Fecha_Compra(Fecha);
        vo_producto.setFlt_Total(total);

        String a[] = {Nombre, Presentacion, Tipo_Exis, String.valueOf(Cantidad),
            String.valueOf(CLV_Proveedor), String.valueOf(costo), String.valueOf(Marca),
            String.valueOf(Linea), String.valueOf(Sucursal), Fecha, String.valueOf(total)};

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

                res = Modelo.inserta(vo_producto);

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

        int ID = Integer.valueOf(this.form.lbl_ID.getText());
        String Nombre = this.form.txt_Nombre.getText();
        String Presentacion = this.form.txt_Presentacion.getText();
        String Tipo_Exis = this.form.CBM_Tipo_Existencia.getSelectedItem().toString();
        int Cantidad = Integer.valueOf(this.form.txt_Cantidad.getText());
        int CLV_Proveedor = Integer.valueOf(form.lbl_Proveedor.getText());
        float costo = Float.valueOf(this.form.txt_Costo_Unitario.getText());
        int Marca = Integer.valueOf(form.lbl_Marca.getText());
        int Linea = Integer.valueOf(form.lbl_Linea.getText());
        int Sucursal = Integer.valueOf(form.lbl_Sucursal.getText());
        String Fecha = getfecha(this.form.date_Fecha);
        float total = Cantidad * costo;

        vo_producto.setInt_ID_Producto_Proveedor(ID);
        vo_producto.setVch_Nombre_Producto_provee(Nombre);
        vo_producto.setVch_Presentacon_Prod_Provee(Presentacion);
        vo_producto.setVch_Tipo_Existencia(Tipo_Exis);
        vo_producto.setInt_Cantidad_Exis(Cantidad);
        vo_producto.setCLV_Proveedor(CLV_Proveedor);
        vo_producto.setFlt_Costo_Unitario(costo);
        vo_producto.setCLV_Marca(Marca);
        vo_producto.setCLV_Linea(Linea);
        vo_producto.setCLV_Sucursal_Proveedor(CLV_Proveedor);
        vo_producto.setDate_Fecha_Compra(Fecha);
        vo_producto.setFlt_Total(total);

        String a[] = {String.valueOf(ID), Nombre, Presentacion, Tipo_Exis, String.valueOf(Cantidad),
            String.valueOf(CLV_Proveedor), String.valueOf(costo), String.valueOf(Marca),
            String.valueOf(Linea), String.valueOf(Sucursal), Fecha, String.valueOf(total)};

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

                res = Modelo.Actualiza(vo_producto);

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

    public void Pasa_datos() {

        int row = Producto.Tbl_Producto.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Producto_Proveedor_Edit(f, true);
            form.setTitle("Formulario Actualizar Producto Proveedor");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);

            Datos_actualizar(Integer.valueOf(Producto.Tbl_Producto.getValueAt(Producto.Tbl_Producto.getSelectedRow(), 0).toString()));

            /*1 */ form.txt_Nombre.setText(Producto.Tbl_Producto.getValueAt(Producto.Tbl_Producto.getSelectedRow(), 1).toString());
            /*2*/ form.txt_Presentacion.setText(Producto.Tbl_Producto.getValueAt(Producto.Tbl_Producto.getSelectedRow(), 2).toString());
            /*3*/ form.CBM_Tipo_Existencia.setSelectedItem(Producto.Tbl_Producto.getValueAt(Producto.Tbl_Producto.getSelectedRow(), 3).toString());
            /*4*/ form.txt_Cantidad.setText(Producto.Tbl_Producto.getValueAt(Producto.Tbl_Producto.getSelectedRow(), 4).toString());

            /*6*/ form.txt_Costo_Unitario.setText(Producto.Tbl_Producto.getValueAt(Producto.Tbl_Producto.getSelectedRow(), 5).toString());

            Date fecha;
            String fechita = Producto.Tbl_Producto.getValueAt(Producto.Tbl_Producto.getSelectedRow(), 0).toString();
            fecha = this.StringADate(fechita);

            /*10*/ this.form.date_Fecha.setDate(fecha);

            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void Eliminar() {

        int row = this.Producto.Tbl_Producto.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Producto.Tbl_Producto.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vo_producto.setInt_ID_Producto_Proveedor(clave);
                r = Modelo.elimina(vo_producto);

                if (r != 0) {
                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void Abreformulario_Tabla(String tipo) {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(tabla);
        tabla = new Frm_Tablas(f, true);

        tabla.setTitle("Tabla " + tipo);
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        tabla.setIconImage(img);
        tabla.veri.setText(tipo);
        llenaTABLAS(tipo);
        this.tabla.Tbl_Tabla.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {

                if (evnt.getClickCount() == 2) {

                    if ("Producto".equals(tabla.veri.getText())) {
                        String ID_ = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String texto = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_Proveedor.setText(texto);
                        form.lbl_Proveedor.setText(ID_);

                    }

                    if (tabla.veri.getText().equals("Marca")) {
                        String ID_ = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String texto = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_Marca.setText(texto);
                        form.lbl_Marca.setText(ID_);
                    }

                    if (tabla.veri.getText().equals("Linea")) {
                        String ID_ = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String texto = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_Linea.setText(texto);
                        form.lbl_Linea.setText(ID_);
                    }

                    if (tabla.veri.getText().equals("Sucursal")) {
                        String ID_ = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String texto = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_Sucursal.setText(texto);
                        form.lbl_Sucursal.setText(ID_);
                    }

                    tabla.dispose();

                }
            }
        }
        );
        llenaGrid();

        tabla.setVisible(true);
    }

    public void llenaTABLAS(String valor) {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.tabla.Tbl_Tabla.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta(valor);

            if (valor.equals("Producto")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Proveedor"));
                    String A2 = rs.getString("vch_Nombre_Proveedor");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

            if (valor.equals("Marca")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_Id_marca"));
                    String A2 = rs.getString("vch_Marca");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

            if (valor.equals("Linea")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_linea"));
                    String A2 = rs.getString("vch_Linea");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

            if (valor.equals("Sucursal")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Sucursal"));
                    String A2 = rs.getString("vch_Nombre");
                    String A3 = rs.getString("vch_Direccion");

                    modelo.addRow(new Object[]{A1, A2 + " " + A3});
                }

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public String getfecha(JDateChooser f) {

        if (f.getDate() != null) {
            return formato.format(f.getDate());
        } else {
            return null;
        }

    }

    public Date StringADate(String fecha) {
        SimpleDateFormat formato_del_Texto = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaE = null;
        try {
            fechaE = formato_del_Texto.parse(fecha);
            return fechaE;
        } catch (ParseException ex) {
            return null;
        }
    }

    public void LimpiarCajas() {

        this.form.txt_Linea.setText("");
        this.form.txt_Marca.setText("");
        this.form.txt_Proveedor.setText("");
        this.form.txt_Sucursal.setText("");

        this.form.txt_Nombre.setText("");
        this.form.txt_Presentacion.setText("");
        this.form.CBM_Tipo_Existencia.setSelectedItem("Seleccionar la unidad");
        this.form.txt_Cantidad.setText("");
        this.form.txt_Costo_Unitario.setText("");

        this.form.lbl_Proveedor.setText("");
        this.form.lbl_Marca.setText("");
        this.form.lbl_Linea.setText("");
        this.form.lbl_Sucursal.setText("");
        this.form.lbl_ID.setText("");

        this.form.date_Fecha.setDate(null);

    }

    public void Datos_actualizar(int dato) {

        try {

            ResultSet rs = Modelo.Consulta(dato);

            while (rs.next()) {
                String ID_Producto = String.valueOf(rs.getInt("int_ID_Producto_Proveedor"));
                String CVL_Proveedor = rs.getString("CLV_Proveedor");
                String CLV_Marca = rs.getString("CLV_Marca");
                String CLV_Linea = rs.getString("CLV_Linea");
                String CLV_Sucursal = rs.getString("CLV_Sucursal_Proveedor");

                form.lbl_ID.setText(ID_Producto);
                form.lbl_Proveedor.setText(CVL_Proveedor);
                form.lbl_Marca.setText(CLV_Marca);
                form.lbl_Linea.setText(CLV_Linea);
                form.lbl_Sucursal.setText(CLV_Sucursal);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
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

        this.Producto.btn_Insertar.setIcon(insert_Btn1);
        this.Producto.btn_Insertar.setBorderPainted(true);
        this.Producto.btn_Insertar.setRolloverIcon(insert_Btn2);
        this.Producto.btn_Insertar.setPressedIcon(insert_Btn3);

        this.Producto.Btn_Actualizar.setIcon(Update_Btn1);
        this.Producto.Btn_Actualizar.setBorderPainted(false);
        this.Producto.Btn_Actualizar.setRolloverIcon(Update_Btn2);
        this.Producto.Btn_Actualizar.setPressedIcon(Update_Btn3);

        this.Producto.Btn_Eliminar.setIcon(Del_Btn1);
        this.Producto.Btn_Eliminar.setBorderPainted(false);
        this.Producto.Btn_Eliminar.setRolloverIcon(Del_Btn2);
        this.Producto.Btn_Eliminar.setPressedIcon(Del_Btn3);

        this.Producto.Btn_Mostrar.setIcon(ver_Btn1);
        this.Producto.Btn_Mostrar.setBorderPainted(false);
        this.Producto.Btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Producto.Btn_Mostrar.setPressedIcon(ver_Btn3);

        this.Producto.Btn_Salir.setIcon(Salir_Btn1);

    }
}
