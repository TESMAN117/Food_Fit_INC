/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Corte;
import Modelo.VO.VO_Corte;
import Vista.Frm_Cliente_Edit;
import Vista.Frm_Corte_Caja;
import Vista.Frm_Corte_Form;
import food_fit_inc.Celdas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class Ctrl_Corte extends Celdas implements ActionListener {
    
    Frm_Corte_Caja Caja;
    Frm_Corte_Form form;
    DAO_Corte Modelo;
    VO_Corte Datos;
    
    public Ctrl_Corte(Frm_Corte_Caja Caja, Frm_Corte_Form form, DAO_Corte Modelo, VO_Corte Datos) {
        this.Caja = Caja;
        this.Modelo = Modelo;
        this.Datos = Datos;
        this.form = form;
        this.Diseña_Tabla();
        this.Dideña_Boton();
        this.llenaGrid();
        this.Caja.setIMG("src\\Multimedia\\fondo.jpg");
        this.form.btn_Corte.addActionListener(this);
        this.form.btn_Realizar.addActionListener(this);
        this.form.btn_Salir.addActionListener(this);
        
        this.Caja.Btn_Salir.addActionListener(this);
        this.Caja.Btn_Mostrar.addActionListener(this);
        this.Caja.Btn_R_Corte.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.form.btn_Corte) {
            this.Mostrar_Corte();
        }
        
        if (e.getSource() == this.form.btn_Realizar) {
            this.Realizar_Corte();
        }
        
        if (e.getSource() == this.form.btn_Salir) {
            this.form.dispose();
        }
        
        if (e.getSource() == this.Caja.Btn_Salir) {
            this.Caja.dispose();
        }
        
        if (e.getSource() == this.Caja.Btn_Mostrar) {
            this.Mostrar();
        }
        
        if (e.getSource() == this.Caja.Btn_R_Corte) {
            this.Abreformulario_Corte();
        }
    }
    
    public void llenaGrid() {
        try {
            
            DefaultTableModel modelo = (DefaultTableModel) this.Caja.Tbl_Corte.getModel();
            
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            
            ResultSet rs = Modelo.Consulta(Ctrl_Login.ID_SUCURSAL);
            
            while (rs.next()) {
                
                String A1 = rs.getString("int_ID_Corte");
                String A2 = rs.getString("flt_Efectivo");
                String A3 = rs.getString("flt_Tarjeta");
                String A4 = rs.getString("flt_Cupones");
                String A5 = rs.getString("date_Fecha_Corte");
                Float Total = Float.valueOf(A2) + Float.valueOf(A3) + Float.valueOf(A4);
                String A6 = rs.getString("vch_Nombre");
                
                modelo.addRow(new Object[]{A1, A2, A3, A4, A5, String.valueOf(Total), A6});
            }
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
        
    }
    
    public void Abreformulario_Corte() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(form);
        form = new Frm_Corte_Form(f, true);
        this.Txtx_Cajas();
        form.setTitle("Formulario Corte de caja");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\Multimedia\\las-compras-en-linea.png");
        form.setIconImage(img);
        form.setVisible(true);
    }
    
    public void Realizar_Corte() {
        try {
            
            int suc = Ctrl_Login.ID_SUCURSAL;
            
            System.out.println(suc + "");
            Calendar calendar = new GregorianCalendar();
            String Fecha = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            
            ResultSet rs = Modelo.Corte_de_Caja(suc, Fecha);
            
            String Efectivo = "";
            String Tarjeta = "";
            String Cupon = "";
            
            while (rs.next()) {
                
                Efectivo = String.valueOf(rs.getFloat(1));
                Tarjeta = String.valueOf(rs.getFloat(2));
                Cupon = String.valueOf(rs.getFloat(3));
                
            }
            
            Datos.setFlt_Tarjeta(Float.valueOf(Tarjeta));
            Datos.setFlt_Efectivo(Float.valueOf(Efectivo));
            Datos.setFlt_Cupones(Float.valueOf(Cupon));
            Datos.setDate_Fecha_Corte(Fecha);
            Datos.setCLV_Sucursal(suc);
            
            int res = 0;
            
            if ("".equals(Efectivo) || "".equals(Tarjeta) || "".equals(Cupon) || "".equals(Fecha)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {
                
                try {
                    
                    res = Modelo.inserta(Datos);
                    
                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Corte del dia: " + Fecha + " Realizado");
                        
                        this.form.dispose();
                        this.llenaGrid();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al realizar CORTE");
                    }
                    
                } catch (Exception e) {
                    System.out.print("Error: " + e);
                }
                
            }
        } catch (Exception e) {
            
            System.out.println(e);
        }
        
    }
    
    public void Txtx_Cajas() {
        Calendar calendar = new GregorianCalendar();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        
        this.form.lbl_Fecha.setText("Fecha de Hoy: " + dia + "/" + (mes + 1) + "/" + año);
        
        this.form.lbl_Sucursal.setText(Ctrl_Login.NOMBRE_SUCURSAL);
    }
    
    public void Mostrar_Corte() {
        try {
            
            int suc = Ctrl_Login.ID_SUCURSAL;
            Calendar calendar = new GregorianCalendar();
            String Fecha = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            
            ResultSet rs = Modelo.Corte_de_Caja(suc, Fecha);
            
            String Efectivo = "";
            String Tarjeta = "";
            String Cupon = "";
            
            while (rs.next()) {
                
                Efectivo = String.valueOf(rs.getFloat(1));
                Tarjeta = String.valueOf(rs.getFloat(2));
                Cupon = String.valueOf(rs.getFloat(3));
                
            }
            
            JOptionPane.showMessageDialog(null, "El corte hasta el Momento es \n $" + Efectivo + " En efectivo \n"
                    + "$" + Tarjeta + " En Tarjeta \n"
                    + "$" + Cupon + " En cupones");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Comprobar si el dia de hoy se realizaron ventas");
        }
        
    }
    
    public void Mostrar() {
        
        ImageIcon icono = new ImageIcon("src/cantidad.png");
        int row = Caja.Tbl_Corte.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            
            String TOTAL = Caja.Tbl_Corte.getValueAt(Caja.Tbl_Corte.getSelectedRow(), 5).toString();
            String Fecha = Caja.Tbl_Corte.getValueAt(Caja.Tbl_Corte.getSelectedRow(), 4).toString();
            String EFEC = Caja.Tbl_Corte.getValueAt(Caja.Tbl_Corte.getSelectedRow(), 1).toString();
            String TARJ = Caja.Tbl_Corte.getValueAt(Caja.Tbl_Corte.getSelectedRow(), 2).toString();
            String CUP = Caja.Tbl_Corte.getValueAt(Caja.Tbl_Corte.getSelectedRow(), 3).toString();
            JOptionPane.showMessageDialog(form, "El total del corte es de " + TOTAL + " \n"
                    + "Con $" + EFEC + " en Efectivo \n"
                    + "$" + TARJ + " En Tarjeta \n"
                    + "$" + CUP + " En Cupones.", "Corte de caja del dia " + Fecha, 0, icono);
            
        }
    }
    
    public void Diseña_Tabla() {
        
        this.Caja.Tbl_Corte.getTableHeader().setReorderingAllowed(false);
        this.Caja.Tbl_Corte.setRowHeight(28);//tamaño de las celdas
        this.Caja.Tbl_Corte.setGridColor(new java.awt.Color(0, 0, 0));
        JTableHeader jtableHeader = this.Caja.Tbl_Corte.getTableHeader();
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
        
        this.Caja.Tbl_Corte.setTableHeader(jtableHeader);
        
        for (int i = 0; i < this.Caja.Tbl_Corte.getColumnCount(); i++) {
            
            try {
                this.Caja.Tbl_Corte.getColumnModel().getColumn(i).setCellRenderer(new Celdas("texto"));
            } catch (Exception es) {
                
                
            }
            
        }
    }
    
    public void Dideña_Boton() {
        
        ImageIcon ver_Btn1 = new ImageIcon("src\\Multimedia\\Botones\\ojo.png");
        ImageIcon ver_Btn2 = new ImageIcon("src\\Multimedia\\Botones\\btn_Mostrar_32px_2.png");
        ImageIcon ver_Btn3 = new ImageIcon("src\\Multimedia\\Botones\\btn_Mostrar_32px_3.png");
        
        ImageIcon Salir_Btn1 = new ImageIcon("src\\Multimedia\\Botones\\cerrar-sesion.png");
        
        this.Caja.Btn_Mostrar.setIcon(ver_Btn1);
        this.Caja.Btn_Mostrar.setBorderPainted(false);
        this.Caja.Btn_Mostrar.setRolloverIcon(ver_Btn2);
        this.Caja.Btn_Mostrar.setPressedIcon(ver_Btn3);
        
        this.Caja.Btn_Salir.setIcon(Salir_Btn1);
        
    }
    
}
