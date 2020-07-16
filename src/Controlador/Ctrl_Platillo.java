/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Platillo;
import Modelo.VO.VO_Platillo;
import Vista.Frm_Catalogo_Platillo;
import Vista.Frm_Empleado_Edit;
import Vista.Frm_Platillo_Edit;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class Ctrl_Platillo implements ActionListener {

    File fichero = null;
    File dest;
    File borraeer;
    String IMG_BORRAR;

    DAO_Platillo Modelo;
    VO_Platillo vo_platillo;
    Frm_Catalogo_Platillo Platillo;
    Frm_Platillo_Edit form;
    Frm_Tablas tabla;

    public Ctrl_Platillo(DAO_Platillo Modelo, VO_Platillo vo_platillo, Frm_Catalogo_Platillo Platillo, Frm_Platillo_Edit form, Frm_Tablas tabla) {
        this.Modelo = Modelo;
        this.vo_platillo = vo_platillo;
        this.Platillo = Platillo;
        this.form = form;
        this.tabla = tabla;
        llenaGrid();
        this.Platillo.btn_Insertar.addActionListener(this);
        this.Platillo.Btn_Actualizar.addActionListener(this);
        this.Platillo.Btn_Eliminar.addActionListener(this);
        this.Platillo.Btn_Mostrar.addActionListener(this);
        this.Platillo.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);
        this.form.btn_Examinar.addActionListener(this);
        this.form.btn_Categoria.addActionListener(this);
        this.form.btn_Sucursal.addActionListener(this);

        this.Platillo.Tbl_Platillo.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {

                    String imagine = Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 6).toString();

                    IMG_BORRAR = "src\\Multimedia\\IMG_PLATILLOS\\" + imagine;

                }
            }
        }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Platillo.btn_Insertar) {
            Abreformulario_Edit();
        }
        if (e.getSource() == Platillo.Btn_Salir) {
            Platillo.dispose();
        }

        if (e.getSource() == Platillo.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Platillo.Btn_Eliminar) {
            Eliminar();
        }

        if (e.getSource() == form.btn_Examinar) {
            examina_img();
        }

        if (e.getSource() == form.btn_Categoria) {
            Abreformulario_Tabla("Categoria");
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
        form = new Frm_Platillo_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Platillo");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.Lbl_Titulo.setText("Agregar Platillo");
        form.btn_Actualizar.setVisible(false);
        form.Lbl_ID.setVisible(false);

        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Platillo.Tbl_Platillo.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consulta("Platillo");

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Platillo"));
                String A2 = rs.getString("vch_Nombre_Platillo");
                String A3 = rs.getString("vch_Presentacion_Platillo");
                String A4 = rs.getString("flt_Precio");
                String A5 = rs.getString("vch_Nombre_Categoria_Platillo");
                String A6 = rs.getString("vch_Nombre");
                String A7 = rs.getString("vch_IMG_Platillo");

                modelo.addRow(new Object[]{A1, A2, A3, A4, A5, A6, A7});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar() {

        String Nombre = form.txt_nombre.getText();
        String Presentacion = form.txt_presentacion.getText();
        float Precio = Float.valueOf(form.txt_precio.getText());
        int Categoria = Integer.valueOf(form.lbl_cat.getText());
        int Sucursal = Integer.valueOf(form.lbl_suc.getText());
        String img = form.txt_img.getText();

        vo_platillo.setVch_Nombre_Platillo(Nombre);
        vo_platillo.setVch_Presentacion_Platillo(Presentacion);
        vo_platillo.setFlt_Precio(Precio);
        vo_platillo.setCLV_Categoria(Categoria);
        vo_platillo.setCLV_Sucursal_Platillo(Sucursal);
        vo_platillo.setVch_IMG_Platillo(img);

        String a[] = {Nombre, Presentacion, String.valueOf(Precio), String.valueOf(Categoria), String.valueOf(Sucursal), img};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {

            if (" ".equals(a[i].toString())) {

                campos_vacios = campos_vacios + 1;

            }
        }

        int res = 0;

        if (campos_vacios > 0 || form.lbl_IMG.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {
                dest = new File("src\\Multimedia\\IMG_PLATILLOS\\" + fichero.getName()); // fondo
                if (dest.exists()) {
                    JOptionPane.showMessageDialog(null, "Esta Imagen Ya esxiste.. O hay una con el mismo nombre. \n Verificar ");
                } else {

                    res = Modelo.inserta(vo_platillo);

                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Insertados");
                        Files.copy(fichero.toPath(), dest.toPath());
                        llenaGrid();
                        LimpiarCajas();
                        form.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Insertar Datos");
                    }
                }
            } catch (Exception e) {
                System.out.print("Error: " + e);
            }

        }

        return res;

    }

    public int Actualizar() {

        String Nombre = form.txt_nombre.getText();
        String Presentacion = form.txt_presentacion.getText();
        float Precio = Float.valueOf(form.txt_precio.getText());
        int Categoria = Integer.valueOf(form.lbl_cat.getText());
        int Sucursal = Integer.valueOf(form.lbl_suc.getText());
        String img = form.txt_img.getText();
        int ID_PLAT = Integer.valueOf(form.Lbl_ID.getText());
        String state = "";

        String a[] = {Nombre, Presentacion, String.valueOf(Precio), String.valueOf(Categoria), String.valueOf(Sucursal), img, String.valueOf(ID_PLAT)};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {

            if (" ".equals(a[i].toString())) {

                campos_vacios = campos_vacios + 1;

            }
        }

        vo_platillo.setVch_Nombre_Platillo(Nombre);
        vo_platillo.setVch_Presentacion_Platillo(Presentacion);
        vo_platillo.setFlt_Precio(Precio);
        vo_platillo.setCLV_Categoria(Categoria);
        vo_platillo.setCLV_Sucursal_Platillo(Sucursal);
        vo_platillo.setVch_IMG_Platillo(img);
        vo_platillo.setInt_ID_Platillo(ID_PLAT);

        int res = 0;
        int row = this.Platillo.Tbl_Platillo.getSelectedRow();

        if (campos_vacios > 0) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {
                dest = new File("src\\Multimedia\\IMG_PLATILLOS\\" + fichero.getName());

                if (dest.exists()) {
                    // JOptionPane.showMessageDialog(null, "Esta Imagen Ya esxiste.. O hay una con el mismo nombre. \n Verificar ");
                    int dato = JOptionPane.showConfirmDialog(null, "Esta Imagen Ya esxiste . \n ¿Desea conservar?");
                    if (dato == 0) {
                        res = Modelo.Actualiza_SIMG(vo_platillo);
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados-SIMG");
                        llenaGrid();
                        LimpiarCajas();
                        form.dispose();
                        state = "SIMG";

                    } else {
                        state = "SIMG";
                    }

                    if (dato == 2) {
                        LimpiarCajas();

                    }

                }

                if ("SIMG".equals(state)) {
                    System.out.print(state);

                } else {
                    res = Modelo.Actualiza(vo_platillo);

                    if (res != 0) {
                        borraeer = new File(IMG_BORRAR);
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                        borraeer.delete();
                        llenaGrid();
                        Files.copy(fichero.toPath(), dest.toPath());
                        LimpiarCajas();
                        form.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Actualizar Datos");
                    }
                }

            } catch (Exception e) {
                System.out.print(e);
            }

        }

        return res;

    }

    public void Eliminar() {

        int row = Platillo.Tbl_Platillo.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Platillo.Tbl_Platillo.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vo_platillo.setInt_ID_Platillo(clave);
                r = Modelo.elimina(vo_platillo);

                if (r != 0) {
                    borraeer = new File(IMG_BORRAR);
                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();

                    borraeer.delete();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void LimpiarCajas() {

        form.txt_nombre.setText("");
        form.txt_presentacion.setText("");
        form.txt_categoria.setText("");
        form.txt_sucursal.setText("");
        form.txt_precio.setText("");
        form.txt_img.setText("");
        form.Lbl_ID.setText("");
        form.lbl_cat.setText("");
        form.lbl_suc.setText("");
        form.Lbl_Titulo.setText("");
        form.lbl_IMG.setIcon(null);

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

                    if ("Categoria".equals(tabla.veri.getText())) {
                        String ID_Cat = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String CATE = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_categoria.setText(CATE);
                        form.lbl_cat.setText(ID_Cat);

                    }

                    if (tabla.veri.getText().equals("Sucursal")) {
                        String ID_Suc = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String SUCURSAL = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_sucursal.setText(SUCURSAL);
                        form.lbl_suc.setText(ID_Suc);
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

            if (valor.equals("Categoria")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Categoria_Platillo"));
                    String A2 = rs.getString("vch_Nombre_Categoria_Platillo");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

            if (valor.equals("Sucursal")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Sucursal"));
                    String A2 = rs.getString("vch_Nombre");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void examina_img() {
        try {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Seleccionar Imagen", "jpg", "png");
            file.setFileFilter(filtro);

            if (file.showOpenDialog(Platillo) == JFileChooser.APPROVE_OPTION) {
                fichero = file.getSelectedFile();

                //Categoria.txt_Foto.setText(fichero.getAbsolutePath());
                form.txt_img.setText(fichero.getName());
                ImageIcon icon = new ImageIcon(fichero.toString());

                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.lbl_IMG.getWidth(), form.lbl_IMG.getHeight(), Image.SCALE_DEFAULT));
                form.lbl_IMG.setText(null);
                form.lbl_IMG.setIcon(icono);

            }

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void Pasa_datos() {

        int row = Platillo.Tbl_Platillo.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Platillo_Edit(f, true);
            form.setTitle("Formulario Actualizar Platillo");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            form.setIconImage(img);

            Datos_actualizar(Integer.valueOf(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 0).toString()));

            form.txt_nombre.setText(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 1).toString());
            form.txt_presentacion.setText(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 2).toString());
            form.txt_precio.setText(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 3).toString());
            form.txt_categoria.setText(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 4).toString());
            form.txt_sucursal.setText(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 5).toString());
            form.txt_img.setText(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 6).toString());
            form.Lbl_ID.setText(Platillo.Tbl_Platillo.getValueAt(Platillo.Tbl_Platillo.getSelectedRow(), 0).toString());

            fichero = new File("src\\Multimedia\\IMG_PLATILLOS\\" + form.txt_img.getText());
            ImageIcon icon = new ImageIcon(fichero.toString());
            IMG_BORRAR = "src\\Multimedia\\IMG_PLATILLOS\\" + fichero.getName();

            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.lbl_IMG.getWidth(), form.lbl_IMG.getHeight(), Image.SCALE_DEFAULT));

            form.lbl_IMG.setText(null);

            form.lbl_IMG.setIcon(icono);

            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void Datos_actualizar(int dato) {

        try {

            ResultSet rs = Modelo.Consulta(dato);

            while (rs.next()) {

                String CVL_CAT = rs.getString("CLV_Categoria");
                String CLV_SUC = rs.getString("CLV_Sucursal_Platillo");

                form.lbl_cat.setText(CVL_CAT);
                form.lbl_suc.setText(CVL_CAT);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }

}
