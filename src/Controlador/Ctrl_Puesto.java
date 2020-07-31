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
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jesus
 */
public class Ctrl_Puesto extends Celdas implements ActionListener {

    DAO_Puesto Modelo_Puesto;
    Frm_Catalogo_Puesto Puesto;
    VO_Puesto vo_puesto;
    Frm_Puesto_Edit form;

    public Ctrl_Puesto(DAO_Puesto Modelo_Puesto, Frm_Catalogo_Puesto Puesto, VO_Puesto vo_puesto, Frm_Puesto_Edit form) {
        this.Modelo_Puesto = Modelo_Puesto;
        this.Puesto = Puesto;
        this.vo_puesto = vo_puesto;
        this.form = form;
        this.Diseña_Tabla(this.Puesto.Tbl_Puesto);
        this.Dideña_Boton();
        this.llenaGrid();
        this.Puesto.setIMG("src\\Multimedia\\fondo.jpg");
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
        
        if (e.getSource() == Puesto.Btn_Mostrar) {
            this.Mostrar();
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
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\Botones\\Puesto.png");
        Diseña_txt();
        form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
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
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\Botones\\Puesto.png");
            Diseña_txt();
            form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
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
    
    public void Mostrar() {

        int row = Puesto.Tbl_Puesto.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
            form = new Frm_Puesto_Edit(f, true);
            form.setTitle("Formulario Actualizar Puesto");
            Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\Botones\\Puesto.png");
            Diseña_txt();
            Desactiva_txt();
            form.setIMG("src\\Multimedia\\Fondo_Form.jpg");
            form.setIconImage(img);

            String ID = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 0).toString();
            String Nomb = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 1).toString();
            String Saldito = Puesto.Tbl_Puesto.getValueAt(Puesto.Tbl_Puesto.getSelectedRow(), 2).toString();
            form.txt_Puesto_nombre.setText(Nomb);
            form.lbl_ID.setText(ID);
            form.lbl_Titulo.setText("--Actualizando Datos--");
            form.txt_Saldo.setText(Saldito);

            form.btn_Actualizar.setVisible(false);
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

        this.Puesto.btn_Insertar.setIcon(insert_Btn1);
        this.Puesto.btn_Insertar.setBorderPainted(true);
        this.Puesto.btn_Insertar.setRolloverIcon(insert_Btn2);
        this.Puesto.btn_Insertar.setPressedIcon(insert_Btn3);

        this.Puesto.Btn_Actualizar.setIcon(Update_Btn1);
        this.Puesto.Btn_Actualizar.setBorderPainted(false);
        this.Puesto.Btn_Actualizar.setRolloverIcon(Update_Btn2);
        this.Puesto.Btn_Actualizar.setPressedIcon(Update_Btn3);

        this.Puesto.Btn_Eliminar.setIcon(Del_Btn1);
        this.Puesto.Btn_Eliminar.setBorderPainted(false);
        this.Puesto.Btn_Eliminar.setRolloverIcon(Del_Btn2);
        this.Puesto.Btn_Eliminar.setPressedIcon(Del_Btn3);

        this.Puesto.Btn_Mostrar.setIcon(ver_Btn1);
        this.Puesto.Btn_Mostrar.setBorderPainted(false);
        this.Puesto.Btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Puesto.Btn_Mostrar.setPressedIcon(ver_Btn3);

        this.Puesto.Btn_Salir.setIcon(Salir_Btn1);

    }
    
      public void Diseña_txt() {
        
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
