/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Categoria_Platillo;
import Modelo.DAO.DAO_Puesto;
import Modelo.VO.VO_Categoria_Platillo;
import Vista.Frm_Catalogo_Categoria_Platillo;
import Vista.Frm_Catalogo_Puesto;
import Vista.Frm_Categoria_Platillo_Edit;
import Vista.Frm_Marca_Edit;
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
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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

/**
 *sd
 * @author jesus
 */
public class Ctrl_Categoria_Platillo extends Celdas implements ActionListener {

    File fichero = null;
    File dest;
    File borraeer;
    String IMG_BORRAR;

    DAO_Categoria_Platillo Modelo_Categoria;
    Frm_Catalogo_Categoria_Platillo Categoria;
    VO_Categoria_Platillo vo_categoria;
    Frm_Categoria_Platillo_Edit form;

    public Ctrl_Categoria_Platillo(DAO_Categoria_Platillo Modelo_Categoria, Frm_Catalogo_Categoria_Platillo Categoria, VO_Categoria_Platillo vo_categoria, Frm_Categoria_Platillo_Edit form) {
        this.Modelo_Categoria = Modelo_Categoria;
        this.Categoria = Categoria;
        this.vo_categoria = vo_categoria;
        this.form = form;
        this.Diseña_Tabla();
        llenaGrid();

        this.Categoria.setIMG("src\\Multimedia\\fondo.jpg");
        this.Categoria.btn_Insertar.addActionListener(this);
        this.Categoria.Btn_Actualizar.addActionListener(this);
        this.Categoria.Btn_Eliminar.addActionListener(this);
        this.Categoria.Btn_Mostrar.addActionListener(this);
        this.Categoria.Btn_Salir.addActionListener(this);

        this.form.btn_Examinar.addActionListener(this);

        this.form.btn_Insertar.addActionListener(this);
        this.form.btn_Cancelar.addActionListener(this);
        this.form.btn_Actualizar.addActionListener(this);

        this.Categoria.Tbl_Categoria.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {

                    String imagine = Categoria.Tbl_Categoria.getValueAt(Categoria.Tbl_Categoria.getSelectedRow(), 2).toString();

                    IMG_BORRAR = "src\\Multimedia\\IMG_CAT_PLATILLOS\\" + imagine;

                }
            }
        }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Categoria.btn_Insertar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Categoria.Btn_Actualizar) {
            Pasa_datos();
        }

        if (e.getSource() == Categoria.Btn_Eliminar) {
            Eliminar_Categoria();
        }

        if (e.getSource() == form.btn_Examinar) {
            examina_img();
        }

        if (e.getSource() == Categoria.Btn_Salir) {
            Categoria.dispose();
        }

        if (e.getSource() == form.btn_Insertar) {
            Insertar_Categoria();
        }

        if (e.getSource() == form.btn_Cancelar) {
            LimpiarCajas();
            form.dispose();
        }
        if (e.getSource() == form.btn_Actualizar) {
            Actualizar_Categoria();
        }

        if (e.getSource() == Categoria.Btn_Mostrar) {
            Mostrar();
        }

    }

    public void Abreformulario_Edit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Categoria_Platillo_Edit(f, true);
        LimpiarCajas();
        form.setTitle("Formulario Agregar Categoria");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\categoria.png");
        form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
        Dideña_txt();
        form.setIconImage(img);
        form.lbl_Titulo.setText("Agregar Categoria");
        form.btn_Actualizar.setVisible(false);
        form.lbl_ID.setVisible(false);
        form.btn_Insertar.setVisible(true);
        llenaGrid();

        form.setVisible(true);
    }

    public void Pasa_datos() {

        int row = Categoria.Tbl_Categoria.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Categoria_Platillo_Edit(f, true);
            form.setTitle("Formulario Actualizar Categoria");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\categoria.png");
            form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
            form.setIconImage(img);
            Dideña_txt();
            form.lbl_Titulo.setText("Actualizando datos de la Categoria");
            String ID = Categoria.Tbl_Categoria.getValueAt(Categoria.Tbl_Categoria.getSelectedRow(), 0).toString();
            String Nomb = Categoria.Tbl_Categoria.getValueAt(Categoria.Tbl_Categoria.getSelectedRow(), 1).toString();
            String imagine = Categoria.Tbl_Categoria.getValueAt(Categoria.Tbl_Categoria.getSelectedRow(), 2).toString();
            form.txt_Categoria_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);

            form.txt_Foto.setText(imagine);

            fichero = new File("src\\Multimedia\\IMG_CAT_PLATILLOS\\" + form.txt_Foto.getText());
            ImageIcon icon = new ImageIcon(fichero.toString());

            System.out.print("Ficehero ac = " + fichero.getName());
            IMG_BORRAR = "src\\Multimedia\\IMG_CAT_PLATILLOS\\" + fichero.getName();

            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.Lbl_IMG_Cat.getWidth(), form.Lbl_IMG_Cat.getHeight(), Image.SCALE_DEFAULT));

            form.Lbl_IMG_Cat.setText(null);

            form.Lbl_IMG_Cat.setIcon(icono);
            form.btn_Actualizar.setVisible(true);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

    public void llenaGrid() {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Categoria.Tbl_Categoria.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_Categoria.Consulta_Categoria();

            while (rs.next()) {

                String ID_Puesto = String.valueOf(rs.getInt("int_ID_Categoria_Platillo"));
                String Nombre = rs.getString("vch_Nombre_Categoria_Platillo");
                String Imagen = rs.getString("Img_categoria_platillo");

                modelo.addRow(new Object[]{ID_Puesto, Nombre, Imagen});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public int Insertar_Categoria() {

        String nom = form.txt_Categoria_nombre.getText();
        String img = form.txt_Foto.getText();
        String Ruta = img;

        vo_categoria.setNombre_Categoria(nom);
        vo_categoria.setIMG_Categoria(img);

        int res = 0;

        if ("".equals(nom) || "".equals(img)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                dest = new File("src\\Multimedia\\IMG_CAT_PLATILLOS\\" + fichero.getName()); // fondo
                if (dest.exists()) {
                    JOptionPane.showMessageDialog(null, "Esta Imagen Ya esxiste.. O hay una con el mismo nombre. \n Verificar ");
                } else {

                    res = Modelo_Categoria.inserta_Categoria(vo_categoria);

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

    public int Actualizar_Categoria() {

        int ID_A = Integer.parseInt(form.lbl_ID.getText());
        String nom = form.txt_Categoria_nombre.getText();
        String img = form.txt_Foto.getText();
        String state = "";

        int res = 0;
        int row = this.Categoria.Tbl_Categoria.getSelectedRow();

        vo_categoria.setID_CATEGORIA(ID_A);
        vo_categoria.setNombre_Categoria(nom);
        vo_categoria.setIMG_Categoria(img);
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {

            if ("".equals(ID_A) || "".equals(nom) || form.Lbl_IMG_Cat.getIcon() == null) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {
                    dest = new File("src\\Multimedia\\IMG_CAT_PLATILLOS\\" + fichero.getName());

                    if (dest.exists()) {
                        // JOptionPane.showMessageDialog(null, "Esta Imagen Ya esxiste.. O hay una con el mismo nombre. \n Verificar ");
                        int dato = JOptionPane.showConfirmDialog(null, "Esta Imagen Ya esxiste . \n ¿Desea conservar?");
                        if (dato == 0) {
                            res = Modelo_Categoria.Actualiza_Categoria_SIMG(vo_categoria);
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
                        res = Modelo_Categoria.Actualiza_Categoria(vo_categoria);
                        if (res != 0) {
                            borraeer = new File(IMG_BORRAR);
                            JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                            borraeer.delete();
                            Files.copy(fichero.toPath(), dest.toPath());
                            llenaGrid();
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
        }

        return res;

    }

    public void Eliminar_Categoria() {

        int row = Categoria.Tbl_Categoria.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Categoria.Tbl_Categoria.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vo_categoria.setID_CATEGORIA(clave);
                r = Modelo_Categoria.elimina_Categoria(vo_categoria);

                if (r != 0) {

                    borraeer = new File(IMG_BORRAR);
                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");

                    borraeer.delete();
                    llenaGrid();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }

        }
    }

    public void examina_img() {
        try {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Seleccionar Imagen", "jpg", "png");
            file.setFileFilter(filtro);

            if (file.showOpenDialog(Categoria) == JFileChooser.APPROVE_OPTION) {
                fichero = file.getSelectedFile();

                //Categoria.txt_Foto.setText(fichero.getAbsolutePath());
                form.txt_Foto.setText(fichero.getName());
                ImageIcon icon = new ImageIcon(fichero.toString());

                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.Lbl_IMG_Cat.getWidth(), form.Lbl_IMG_Cat.getHeight(), Image.SCALE_DEFAULT));
                form.Lbl_IMG_Cat.setText(null);
                form.Lbl_IMG_Cat.setIcon(icono);

            }

        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    public void LimpiarCajas() {

        form.txt_Categoria_nombre.setText("");
        form.lbl_ID.setText("");
        form.txt_Foto.setText("");
        form.Lbl_IMG_Cat.setIcon(null);
        form.lbl_Titulo.setText("Datos Categoria");
    }

    public void Diseña_Tabla() {

        this.Categoria.Tbl_Categoria.getTableHeader().setReorderingAllowed(false);
        this.Categoria.Tbl_Categoria.setRowHeight(28);//tamaño de las celdas
        this.Categoria.Tbl_Categoria.setGridColor(new java.awt.Color(0, 0, 0));
        JTableHeader jtableHeader = this.Categoria.Tbl_Categoria.getTableHeader();
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

        this.Categoria.Tbl_Categoria.setTableHeader(jtableHeader);

        for (int i = 0; i < this.Categoria.Tbl_Categoria.getColumnCount(); i++) {

            this.Categoria.Tbl_Categoria.getColumnModel().getColumn(i).setCellRenderer(new Celdas("texto"));
        }
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

    public void Mostrar() {

        int row = Categoria.Tbl_Categoria.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Categoria_Platillo_Edit(f, true);
            form.setTitle("Formulario Mostrar Categoria");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\categoria.png");
            form.setIconImage(img);
            Dideña_txt();
            form.lbl_Titulo.setText("Mostrar datos de la Categoria");
            String ID = Categoria.Tbl_Categoria.getValueAt(Categoria.Tbl_Categoria.getSelectedRow(), 0).toString();
            String Nomb = Categoria.Tbl_Categoria.getValueAt(Categoria.Tbl_Categoria.getSelectedRow(), 1).toString();
            String imagine = Categoria.Tbl_Categoria.getValueAt(Categoria.Tbl_Categoria.getSelectedRow(), 2).toString();
            form.txt_Categoria_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);

            form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
            form.txt_Foto.setText(imagine);

            form.txt_Categoria_nombre.setEnabled(false);

            form.txt_Foto.setEnabled(false);;

            fichero = new File("src\\Multimedia\\IMG_CAT_PLATILLOS\\" + form.txt_Foto.getText());
            ImageIcon icon = new ImageIcon(fichero.toString());

            System.out.print("Ficehero ac = " + fichero.getName());
            IMG_BORRAR = "src\\Multimedia\\IMG_CAT_PLATILLOS\\" + fichero.getName();

            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(form.Lbl_IMG_Cat.getWidth(), form.Lbl_IMG_Cat.getHeight(), Image.SCALE_DEFAULT));

            form.Lbl_IMG_Cat.setText(null);

            form.Lbl_IMG_Cat.setIcon(icono);
            form.btn_Actualizar.setVisible(false);
            form.btn_Insertar.setVisible(false);

            form.setVisible(true);
            llenaGrid();
        }

    }

}
