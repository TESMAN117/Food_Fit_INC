/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Empleado;
import Modelo.VO.VO_Empleado;
import Vista.Frm_Catalogo_Empleado;
import Vista.Frm_Empleado_Edit;
import Vista.Frm_Puesto_Edit;
import Vista.Frm_Tablas;
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
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class Ctrl_Empleado extends Celdas implements ActionListener {

    File fichero = null;
    File dest;
    File borraeer;
    String IMG_BORRAR;

    VO_Empleado vo_empleado;
    DAO_Empleado Modelo_empleado;
    Frm_Catalogo_Empleado Empleado;
    Frm_Empleado_Edit form;

    Frm_Tablas tabla;

    public Ctrl_Empleado(VO_Empleado vo_empleado, DAO_Empleado dao_empleado, Frm_Catalogo_Empleado Empleado, Frm_Empleado_Edit form, Frm_Tablas tabla) {
        this.vo_empleado = vo_empleado;
        this.Modelo_empleado = dao_empleado;
        this.Empleado = Empleado;
        this.form = form;
        this.tabla = tabla;

        this.Diseña_Tabla();
        this.Dideña_Boton();
        this.llenaGrid();
        this.Empleado.setIMG("src\\Multimedia\\fondo.jpg");
        this.Empleado.btn_Insertar.addActionListener(this);
        this.Empleado.Btn_Actualizar.addActionListener(this);
        this.Empleado.Btn_Eliminar.addActionListener(this);
        this.Empleado.Btn_Mostrar.addActionListener(this);
        this.Empleado.Btn_Salir.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);
        this.form.btn_Examinar.addActionListener(this);
        this.form.btn_Persona.addActionListener(this);
        this.form.btn_Puesto.addActionListener(this);
        this.form.btn_Area.addActionListener(this);
        this.form.btn_Jefe.addActionListener(this);

        this.Empleado.Tbl_Empleado.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {

                    String imagine = Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 5).toString();

                    IMG_BORRAR = "src\\Multimedia\\IMG_EMPLEADOS\\" + imagine;

                }
            }
        }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Empleado.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Empleado.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Empleado.Btn_Eliminar) {
            Eliminar();
        }

        if (e.getSource() == Empleado.Btn_Mostrar) {
            Mostrar();
        }

        if (e.getSource() == form.btn_Examinar) {
            examina_img();
        }

        if (e.getSource() == form.btn_Persona) {
            Abreformulario_Tabla("Persona");
        }

        if (e.getSource() == form.btn_Puesto) {
            Abreformulario_Tabla("Puesto");
        }

        if (e.getSource() == form.btn_Area) {
            Abreformulario_Tabla("Area");
        }

        if (e.getSource() == form.btn_Jefe) {
            Abreformulario_Tabla("Jefe");
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
        form = new Frm_Empleado_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Empleado");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\empleado.png");
        Dideña_txt();
        form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
        form.setIconImage(img);
        form.Lbl_Titulo.setText("Agregar Empleado");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.ID_LABEL.setVisible(false);

        form.lbl_Area.setVisible(false);
        form.lbl_Puesto.setVisible(false);
        form.lbl_Persona.setVisible(false);
        form.lbl_Jefe.setVisible(false);

        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void Pasa_datos() {

        int row = Empleado.Tbl_Empleado.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Empleado_Edit(f, true);
            form.setTitle("Formulario Actualizar Empleado");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\empleado.png");
        Dideña_txt();
        form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
            form.setIconImage(img);

            Datos_actualizar(Integer.valueOf(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 0).toString()));

            form.txt_Persona.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 1).toString());
            form.Txt_Puesto.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 2).toString());
            form.Txt_Area.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 3).toString());
            form.Txt_Jefe.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 4).toString());
            form.Txt_Img.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 5).toString());
            form.lbl_ID.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 0).toString());

            fichero = new File("src\\Multimedia\\IMG_EMPLEADOS\\" + form.Txt_Img.getText());
            ImageIcon icon = new ImageIcon(fichero.toString());
            IMG_BORRAR = "src\\Multimedia\\IMG_EMPLEADOS\\" + fichero.getName();

            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.lbl_IMG.getWidth(), form.lbl_IMG.getHeight(), Image.SCALE_DEFAULT));

            form.lbl_IMG.setText(null);

            form.lbl_IMG.setIcon(icono);
            form.lbl_Area.setVisible(false);
            form.lbl_Puesto.setVisible(false);
            form.lbl_Persona.setVisible(false);
            form.lbl_Jefe.setVisible(false);
            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void Mostrar() {

        int row = Empleado.Tbl_Empleado.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Empleado_Edit(f, true);
            form.setTitle("Formulario Actualizar Empleado");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\empleado.png");
            Dideña_txt();
            Desactiva_txt();
            form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
            form.setIconImage(img);

            Datos_actualizar(Integer.valueOf(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 0).toString()));

            form.txt_Persona.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 1).toString());
            form.Txt_Puesto.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 2).toString());
            form.Txt_Area.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 3).toString());
            form.Txt_Jefe.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 4).toString());
            form.Txt_Img.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 5).toString());
            form.lbl_ID.setText(Empleado.Tbl_Empleado.getValueAt(Empleado.Tbl_Empleado.getSelectedRow(), 0).toString());

            fichero = new File("src\\Multimedia\\IMG_EMPLEADOS\\" + form.Txt_Img.getText());
            ImageIcon icon = new ImageIcon(fichero.toString());
            IMG_BORRAR = "src\\Multimedia\\IMG_EMPLEADOS\\" + fichero.getName();

            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.lbl_IMG.getWidth(), form.lbl_IMG.getHeight(), Image.SCALE_DEFAULT));

            form.lbl_IMG.setText(null);

            form.lbl_IMG.setIcon(icono);
            form.lbl_Area.setVisible(false);
            form.lbl_Puesto.setVisible(false);
            form.lbl_Persona.setVisible(false);
            form.lbl_Jefe.setVisible(false);

            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Empleado.Tbl_Empleado.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_empleado.Consulta_Empleado("Empleado");

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Empleado"));
                String A2 = rs.getString("vch_Nombre_Persona") + " " + rs.getString("vch_A_Paterno") + " " + rs.getString("vch_A_Materno");
                String A3 = rs.getString("vch_Puesto");
                String A4 = rs.getString("vch_Area");
                String A5 = rs.getString("CLV_Jefe");
                String A6 = rs.getString("Img_Foto_Empleado");

                modelo.addRow(new Object[]{A1, A2, A3, A4, A5, A6});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar() {

        int Persona = Integer.valueOf(form.lbl_Persona.getText());
        int Puesto = Integer.valueOf(form.lbl_Puesto.getText());
        int Area = Integer.valueOf(form.lbl_Area.getText());
        int Jefe = Integer.valueOf(form.lbl_Jefe.getText());
        String img = form.Txt_Img.getText();

        vo_empleado.setCLV_Area(Area);
        vo_empleado.setCLV_Jefe(Jefe);
        vo_empleado.setCLV_Persona(Persona);
        vo_empleado.setCLV_Puesto(Puesto);
        vo_empleado.setImg_Foto_Empleado(img);

        String a[] = {form.txt_Persona.getText(), form.lbl_Puesto.getText(), form.Txt_Area.getText(), form.Txt_Jefe.getText()};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {

            if (a[i] == null) {

                campos_vacios = campos_vacios + 1;

            }
        }

        int res = 0;

        if (campos_vacios > 0 || img == " ") {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {
                dest = new File("src\\Multimedia\\IMG_EMPLEADOS\\" + fichero.getName()); // fondo
                if (dest.exists()) {
                    JOptionPane.showMessageDialog(null, "Esta Imagen Ya esxiste.. O hay una con el mismo nombre. \n Verificar ");
                } else {

                    res = Modelo_empleado.inserta_Empleado(vo_empleado);

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

        int Persona = Integer.valueOf(form.lbl_Persona.getText());
        int Puesto = Integer.valueOf(form.lbl_Puesto.getText());
        int Area = Integer.valueOf(form.lbl_Area.getText());
        int Jefe = Integer.valueOf(form.lbl_Jefe.getText());
        String img = form.Txt_Img.getText();
        int ID_EMP = Integer.valueOf(form.lbl_ID.getText());
        String state = "";

        String a[] = {form.txt_Persona.getText(), form.lbl_Puesto.getText(), form.Txt_Area.getText(), form.Txt_Jefe.getText(), form.lbl_ID.getText()};

        int campos_vacios = 0;

        for (int i = 0; a.length > i; i++) {

            if (a[i] == null) {

                campos_vacios = campos_vacios + 1;

            }
        }

        vo_empleado.setID_Emleado(ID_EMP);
        vo_empleado.setCLV_Persona(Persona);
        vo_empleado.setCLV_Puesto(Puesto);
        vo_empleado.setCLV_Area(Area);
        vo_empleado.setCLV_Jefe(Jefe);
        vo_empleado.setImg_Foto_Empleado(img);

        int res = 0;
        int row = this.Empleado.Tbl_Empleado.getSelectedRow();

        if (campos_vacios > 0 || form.lbl_IMG.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {
                dest = new File("src\\Multimedia\\IMG_EMPLEADOS\\" + fichero.getName());

                if (dest.exists()) {
                    // JOptionPane.showMessageDialog(null, "Esta Imagen Ya esxiste.. O hay una con el mismo nombre. \n Verificar ");
                    int dato = JOptionPane.showConfirmDialog(null, "Esta Imagen Ya esxiste . \n ¿Desea conservar?");
                    if (dato == 0) {
                        res = Modelo_empleado.Actualiza_EmpleadoSIMG(vo_empleado);
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
                    res = Modelo_empleado.Actualiza_Empleado(vo_empleado);

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

        int row = Empleado.Tbl_Empleado.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Empleado.Tbl_Empleado.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vo_empleado.setID_Emleado(clave);
                r = Modelo_empleado.elimina_Empleado(vo_empleado);

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

        form.txt_Persona.setText("");
        form.Txt_Puesto.setText("");
        form.Txt_Area.setText("");
        form.Txt_Jefe.setText("");
        form.Txt_Img.setText("");
        form.lbl_ID.setText("");
        form.lbl_IMG.setIcon(null);

    }

    public void Datos_actualizar(int dato) {

        try {
            System.out.println("eL DATO ES " + dato);
            ResultSet rs = Modelo_empleado.Consulta_Especifica(dato);

            while (rs.next()) {
                String ID_EMPLEADO = String.valueOf(rs.getInt("int_ID_Empleado"));
                String CVL_PERSONA = rs.getString("CLV_Persona");
                String CLV_PUESTO = rs.getString("CLV_Puesto");
                String CLV_AREA = rs.getString("CLV_Area");
                String CLV_JEFE = rs.getString("CLV_Jefe");
                String FOTO = rs.getString("Img_Foto_Empleado");

                form.lbl_ID.setText(ID_EMPLEADO);
                form.lbl_Persona.setText(CVL_PERSONA);
                form.lbl_Puesto.setText(CLV_PUESTO);
                form.lbl_Area.setText(CLV_AREA);
                form.lbl_Jefe.setText(CLV_JEFE);
            }
        } catch (Exception e) {

            System.out.println(e);
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

                    if ("Persona".equals(tabla.veri.getText())) {
                        String ID_PERSONA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String PERSONA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.txt_Persona.setText(PERSONA);
                        form.lbl_Persona.setText(ID_PERSONA);

                    }

                    if (tabla.veri.getText().equals("Area")) {
                        String ID_Area = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String AREA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.Txt_Area.setText(AREA);
                        form.lbl_Area.setText(ID_Area);
                    }

                    if (tabla.veri.getText().equals("Puesto")) {
                        String ID_Puesto = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String Puesto = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.Txt_Puesto.setText(Puesto);
                        form.lbl_Puesto.setText(ID_Puesto);
                    }

                    if (tabla.veri.getText().equals("Jefe")) {
                        String ID_JEFE = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String JEFE = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        form.Txt_Jefe.setText(JEFE);
                        form.lbl_Jefe.setText(ID_JEFE);
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

            ResultSet rs = Modelo_empleado.Consulta_Empleado(valor);

            if (valor.equals("Persona")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Persona"));
                    String A2 = rs.getString("vch_Nombre_Persona");
                    String A3 = rs.getString("vch_A_Paterno");
                    String A4 = rs.getString("vch_A_Materno");

                    modelo.addRow(new Object[]{A1, A2 + A3 + A4});
                }

            }

            if (valor.equals("Jefe")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Empleado"));
                    String A2 = rs.getString("vch_Nombre_Persona");
                    String A3 = rs.getString("vch_A_Paterno");
                    String A4 = rs.getString("vch_A_Materno");

                    modelo.addRow(new Object[]{A1, A2 + A3 + A4});
                }

            }

            if (valor.equals("Puesto")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_puesto"));
                    String A2 = rs.getString("vch_Puesto");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

            if (valor.equals("Area")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_area"));
                    String A2 = rs.getString("vch_Area");

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

            if (file.showOpenDialog(Empleado) == JFileChooser.APPROVE_OPTION) {
                fichero = file.getSelectedFile();

                //Categoria.txt_Foto.setText(fichero.getAbsolutePath());
                form.Txt_Img.setText(fichero.getName());
                ImageIcon icon = new ImageIcon(fichero.toString());

                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.lbl_IMG.getWidth(), form.lbl_IMG.getHeight(), Image.SCALE_DEFAULT));
                form.lbl_IMG.setText(null);
                form.lbl_IMG.setIcon(icono);

            }

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void Diseña_Tabla() {

        this.Empleado.Tbl_Empleado.getTableHeader().setReorderingAllowed(false);
        this.Empleado.Tbl_Empleado.setRowHeight(28);//tamaño de las celdas
        this.Empleado.Tbl_Empleado.setGridColor(new java.awt.Color(0, 0, 0));
        JTableHeader jtableHeader = this.Empleado.Tbl_Empleado.getTableHeader();
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

        this.Empleado.Tbl_Empleado.setTableHeader(jtableHeader);

        for (int i = 0; i < this.Empleado.Tbl_Empleado.getColumnCount(); i++) {

            this.Empleado.Tbl_Empleado.getColumnModel().getColumn(i).setCellRenderer(new Celdas("texto"));
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

        this.Empleado.btn_Insertar.setIcon(insert_Btn1);
        this.Empleado.btn_Insertar.setBorderPainted(true);
        this.Empleado.btn_Insertar.setRolloverIcon(insert_Btn2);
        this.Empleado.btn_Insertar.setPressedIcon(insert_Btn3);

        this.Empleado.Btn_Actualizar.setIcon(Update_Btn1);
        this.Empleado.Btn_Actualizar.setBorderPainted(false);
        this.Empleado.Btn_Actualizar.setRolloverIcon(Update_Btn2);
        this.Empleado.Btn_Actualizar.setPressedIcon(Update_Btn3);

        this.Empleado.Btn_Eliminar.setIcon(Del_Btn1);
        this.Empleado.Btn_Eliminar.setBorderPainted(false);
        this.Empleado.Btn_Eliminar.setRolloverIcon(Del_Btn2);
        this.Empleado.Btn_Eliminar.setPressedIcon(Del_Btn3);

        this.Empleado.Btn_Mostrar.setIcon(ver_Btn1);
        this.Empleado.Btn_Mostrar.setBorderPainted(false);
        this.Empleado.Btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Empleado.Btn_Mostrar.setPressedIcon(ver_Btn3);

        this.Empleado.Btn_Salir.setIcon(Salir_Btn1);

    }

    public void Dideña_txt() {

        JTextField caja;
        JTextArea Area;
        try {
            for (int i = 0; i < this.form.pnl_Container.getComponentCount(); i++) {

                if (this.form.pnl_Container.getComponent(i).getClass().getName().equals("javax.swing.JTextField")) {

                    caja = (JTextField) this.form.pnl_Container.getComponent(i);

                    caja.setBorder(BorderFactory.createLineBorder(Color.white));
                    caja.setBackground(Color.WHITE);
                }

                if (this.form.pnl_Container.getComponent(i).getClass().getName().equals("javax.swing.JTextArea")) {

                    Area = (JTextArea) this.form.pnl_Container.getComponent(i);

                    Area.setBorder(BorderFactory.createLineBorder(Color.white));
                    Area.setBackground(Color.WHITE);
                }

            }
        } catch (Exception ex) {
            System.out.print("ss " + ex);
        }

    }

    public void Desactiva_txt() {

        JTextField caja;
        JTextArea Area;
        try {
            for (int i = 0; i < this.form.pnl_Container.getComponentCount(); i++) {

                if (this.form.pnl_Container.getComponent(i).getClass().getName().equals("javax.swing.JTextField")) {

                    caja = (JTextField) this.form.pnl_Container.getComponent(i);

                    caja.setEnabled(false);
                }

                if (this.form.pnl_Container.getComponent(i).getClass().getName().equals("javax.swing.JTextArea")) {

                    Area = (JTextArea) this.form.pnl_Container.getComponent(i);

                    Area.setEnabled(false);
                }

            }
        } catch (Exception ex) {
            System.out.print("ss " + ex);
        }

    }
}
