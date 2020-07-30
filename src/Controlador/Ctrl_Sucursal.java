/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Sucursal;
import Modelo.VO.VO_Sucursal;
import Vista.Frm_Catalogo_Sucursal;
import Vista.Frm_Sucursal_Edit;
import Vista.MDI_Food;
import food_fit_inc.Celdas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jesus
 */
public class Ctrl_Sucursal extends Celdas implements ActionListener {

    Frm_Catalogo_Sucursal Sucursal;
    DAO_Sucursal Modelo_Sucursal;
    Frm_Sucursal_Edit Formulario_edit;
    VO_Sucursal vo_sucursal;

    public Ctrl_Sucursal(Frm_Catalogo_Sucursal Sucursal, DAO_Sucursal Modelo_Sucursal, Frm_Sucursal_Edit Formulario_edit, VO_Sucursal vo_sucursal) throws SQLException {
        this.Sucursal = Sucursal;
        this.Modelo_Sucursal = Modelo_Sucursal;
        this.Formulario_edit = Formulario_edit;
        this.vo_sucursal = vo_sucursal;
        this.Diseña_Tabla(this.Sucursal.tbl_Sucursal);
        this.Dideña_Boton();
        this.llenaGrid();
        this.Sucursal.setIMG("src\\Multimedia\\fondo.jpg");
        this.Sucursal.btn_Agregar.addActionListener(this);
        this.Sucursal.btn_Actualizar.addActionListener(this);
        this.Sucursal.btn_Eliminar.addActionListener(this);
        this.Sucursal.btn_Mostrar.addActionListener(this);
        this.Sucursal.btn_Exit.addActionListener(this);

        this.Formulario_edit.btn_Insertar.addActionListener(this);
        this.Formulario_edit.btn_Cancelar.addActionListener(this);
        this.Formulario_edit.btn_Actualizar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Formulario_edit.btn_Insertar) {
            Insertar_Sucursal();
        }

        if (e.getSource() == Formulario_edit.btn_Actualizar) {
            Actualizar_Sucursal();
        }

        if (e.getSource() == Formulario_edit.btn_Cancelar) {
            Formulario_edit.dispose();
            LimpiarCajas();
        }

        if (e.getSource() == Sucursal.btn_Agregar) {
            Abreformulario_Edit();
        }

        if (e.getSource() == Sucursal.btn_Actualizar) {
            Modificar_Campos();
        }

        if (e.getSource() == Sucursal.btn_Eliminar) {

            try {
                Eliminar();
            } catch (SQLException | RemoteException ex) {
                Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == Sucursal.btn_Mostrar) {
            Mostrar();
        }
        if (e.getSource() == Sucursal.btn_Exit) {
            Sucursal.dispose();
        }
    }

    public void Abreformulario_Edit() {
        try {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(Formulario_edit);
            Formulario_edit = new Frm_Sucursal_Edit(f, true);

            LimpiarCajas();
            Formulario_edit.setTitle("Formulario Agregar Sucursal");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
            Formulario_edit.setIconImage(img);
            Formulario_edit.txt_Titulos.setText("Agregar Sucursal");
            Formulario_edit.btn_Actualizar.setVisible(false);
            Formulario_edit.txt_ID.setVisible(false);
            Formulario_edit.btn_Insertar.setVisible(true);
            llenaGrid();
            carga_combo(Formulario_edit.cmb_estado);
            //carga_combo();

            Formulario_edit.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenaGrid() throws SQLException {
        try {

            DefaultTableModel modelo = (DefaultTableModel) Sucursal.tbl_Sucursal.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo_Sucursal.Consulta_Sucursal();

            while (rs.next()) {

                String ID_Sucursal = String.valueOf(rs.getInt("int_ID_Sucursal"));
                String Nombre = rs.getString("vch_Nombre");
                String Direccion = rs.getString("vch_Direccion");
                String Municipio = rs.getString("vch_Municipio");
                String Estado = rs.getString("vch_Estado");

                modelo.addRow(new Object[]{ID_Sucursal, Nombre, Direccion, Municipio, Estado});
            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void Modificar_Campos() {
        int row = Sucursal.tbl_Sucursal.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            try {
                Frame f = javax.swing.JOptionPane.getFrameForComponent(Formulario_edit);
                Formulario_edit = new Frm_Sucursal_Edit(f, true);

                Formulario_edit.setTitle("Formulario Actualizar Sucursal");

                Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
                Formulario_edit.setIconImage(img);

                Formulario_edit.txt_Titulos.setText("ACTUALIZAR Sucursal");
                Formulario_edit.btn_Insertar.setVisible(false);
                Formulario_edit.btn_Actualizar.setVisible(true);
                int fila = Sucursal.tbl_Sucursal.getSelectedRow();
                carga_combo(Formulario_edit.cmb_estado);
                Formulario_edit.txt_ID.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 0).toString());
                Formulario_edit.txt_Nombre.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 1).toString());
                Formulario_edit.txt_Direcion.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 2).toString());
                Formulario_edit.txt_municipio.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 3).toString());
                // Formulario_edit.txt_Estado.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 4).toString());
                Formulario_edit.cmb_estado.setSelectedItem(Sucursal.tbl_Sucursal.getValueAt(fila, 4).toString());
                Formulario_edit.txt_ID.setEditable(false);
                Formulario_edit.setVisible(true);
                llenaGrid();
            } catch (SQLException ex) {
                Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int Insertar_Sucursal() {

        String esta = Formulario_edit.cmb_estado.getSelectedItem().toString();

        String nom = Formulario_edit.txt_Nombre.getText();
        String dir = Formulario_edit.txt_Direcion.getText();
        String muni = Formulario_edit.txt_municipio.getText();

        vo_sucursal.setESTADO(esta);
        vo_sucursal.setNOMBRE(nom);
        vo_sucursal.setDIRECCION(dir);
        vo_sucursal.setMUNICIPIO(muni);
        int res = 0;
        if ("Seleccionar estado".equals(esta)) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado");
        } else {
            if ("".equals(esta) || "".equals(nom) || "".equals(dir) || "".equals(muni)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = Modelo_Sucursal.insertaSucursal(vo_sucursal);

                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Insertados");
                        try {
                            llenaGrid();
                        } catch (SQLException ex) {
                            Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Formulario_edit.dispose();
                        LimpiarCajas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Insertar Datos");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        return res;

    }

    public void Mostrar() {
        int row = Sucursal.tbl_Sucursal.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            try {
                Frame f = javax.swing.JOptionPane.getFrameForComponent(Formulario_edit);
                Formulario_edit = new Frm_Sucursal_Edit(f, true);

                Formulario_edit.setTitle("Formulario Visualizar Sucursal");

                Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
                Formulario_edit.setIconImage(img);

                Formulario_edit.txt_Titulos.setText("Visualizar Sucursal");
                Formulario_edit.btn_Insertar.setVisible(false);
                Formulario_edit.btn_Actualizar.setVisible(false);
                int fila = Sucursal.tbl_Sucursal.getSelectedRow();
                carga_combo(Formulario_edit.cmb_estado);
                Formulario_edit.txt_ID.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 0).toString());
                Formulario_edit.txt_Nombre.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 1).toString());
                Formulario_edit.txt_Direcion.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 2).toString());
                Formulario_edit.txt_municipio.setText(Sucursal.tbl_Sucursal.getValueAt(fila, 3).toString());
                Formulario_edit.cmb_estado.setSelectedItem(Sucursal.tbl_Sucursal.getValueAt(fila, 4).toString());

                Formulario_edit.txt_ID.setEditable(false);
                Formulario_edit.txt_Nombre.setEditable(false);
                Formulario_edit.txt_Direcion.setEditable(false);
                Formulario_edit.txt_municipio.setEditable(false);
                Formulario_edit.cmb_estado.setEditable(false);

                Formulario_edit.setVisible(true);
                llenaGrid();
            } catch (SQLException ex) {
                Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int Actualizar_Sucursal() {

        String ID_S = Formulario_edit.txt_ID.getText();
        String esta = Formulario_edit.cmb_estado.getSelectedItem().toString();
        String nom = Formulario_edit.txt_Nombre.getText();
        String dir = Formulario_edit.txt_Direcion.getText();
        String muni = Formulario_edit.txt_municipio.getText();

        vo_sucursal.setID_SUCURSAL(Integer.valueOf(ID_S));
        vo_sucursal.setESTADO(esta);
        vo_sucursal.setNOMBRE(nom);
        vo_sucursal.setDIRECCION(dir);
        vo_sucursal.setMUNICIPIO(muni);
        int res = 0;

        if ("".equals(esta) || "".equals(nom) || "".equals(dir) || "".equals(muni) || "".equals(ID_S)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo_Sucursal.ActualizaSucursal(vo_sucursal);

                if (res != 0) {
                    JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                    try {
                        llenaGrid();
                    } catch (SQLException ex) {
                        Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Formulario_edit.dispose();
                    LimpiarCajas();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Actualizar Datos");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return res;

    }

    public void Eliminar() throws SQLException, RemoteException {

        int row = Sucursal.tbl_Sucursal.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) Sucursal.tbl_Sucursal.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                vo_sucursal.setID_SUCURSAL(clave);
                int r;
                r = Modelo_Sucursal.eliminaSucursal(vo_sucursal);

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

        this.Formulario_edit.txt_ID.setText("");
        this.Formulario_edit.txt_Nombre.setText("");
        this.Formulario_edit.txt_Direcion.setText("");
        this.Formulario_edit.txt_municipio.setText("");
    }

    public void carga_combo() {
        JSONParser parser = new JSONParser();
        try {
            //Object obj = parser.parse(new FileReader("src\\food_fit_inc\\estados.json"));

            JSONArray a = (JSONArray) parser.parse(new FileReader("src\\food_fit_inc\\estados.json"));

            for (Object o : a) {
                JSONObject person = (JSONObject) o;

                String name = (String) person.get("nombre");
                System.out.println(name);

                //JSONArray cars = (JSONArray) person.get("cars");

                /* for (Object c : cars) {
                    System.out.println(c + "");
                }*/
            }
        } catch (IOException ex) {
            Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void carga_combo(JComboBox combo) {
        try {
            JSONParser parser = new JSONParser();

            JSONArray a = (JSONArray) parser.parse(new FileReader("src\\food_fit_inc\\estados.json"));

            for (Object o : a) {
                JSONObject person = (JSONObject) o;

                String name = (String) person.get("nombre");
                combo.addItem(name);

            }
        } catch (IOException ex) {
            Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Ctrl_Sucursal.class.getName()).log(Level.SEVERE, null, ex);
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

        this.Sucursal.btn_Agregar.setIcon(insert_Btn1);
        this.Sucursal.btn_Agregar.setBorderPainted(true);
        this.Sucursal.btn_Agregar.setRolloverIcon(insert_Btn2);
        this.Sucursal.btn_Agregar.setPressedIcon(insert_Btn3);

        this.Sucursal.btn_Actualizar.setIcon(Update_Btn1);
        this.Sucursal.btn_Actualizar.setBorderPainted(false);
        this.Sucursal.btn_Actualizar.setRolloverIcon(Update_Btn2);
        this.Sucursal.btn_Actualizar.setPressedIcon(Update_Btn3);

        this.Sucursal.btn_Eliminar.setIcon(Del_Btn1);
        this.Sucursal.btn_Eliminar.setBorderPainted(false);
        this.Sucursal.btn_Eliminar.setRolloverIcon(Del_Btn2);
        this.Sucursal.btn_Eliminar.setPressedIcon(Del_Btn3);

        this.Sucursal.btn_Mostrar.setIcon(ver_Btn1);
        this.Sucursal.btn_Mostrar.setBorderPainted(false);
        this.Sucursal.btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Sucursal.btn_Mostrar.setPressedIcon(ver_Btn3);

        this.Sucursal.btn_Exit.setIcon(Salir_Btn1);

    }
}
