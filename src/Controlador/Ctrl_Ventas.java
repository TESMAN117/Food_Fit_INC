/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Ventas;
import Modelo.VO.VO_Venta;
import Modelo.VO.VO_Venta_Detalle;
import Vista.Frm_Tablas;
import Vista.Frm_Ventas;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jesus
 */
public class Ctrl_Ventas extends Celdas implements ActionListener {

    DAO_Ventas Modelo;
    VO_Venta vo_ventas;
    VO_Venta_Detalle vo_ventas_D;
    Frm_Ventas Ventas;
    Frm_Tablas tabla;

    private List<JButton> Labeles;
    private int INDICE;

    private List<JButton> platillos;
    private int INDICE2;

    private int ID_SUCURSAL;
    private String SUCURSAL;
    private int ID_EMPLEADO;

    public Ctrl_Ventas(DAO_Ventas Modelo, VO_Venta vo_ventas, VO_Venta_Detalle vo_ventas_D, Frm_Ventas Ventas, Frm_Tablas tabla, int id, String name, int empleado) {
        this.Modelo = Modelo;
        this.vo_ventas = vo_ventas;
        this.vo_ventas_D = vo_ventas_D;
        this.Ventas = Ventas;
        this.tabla = tabla;
        this.ID_SUCURSAL = id;
        this.SUCURSAL = name;
        this.ID_EMPLEADO = empleado;
        txt_texto();
        this.imagen();
        //this.Oculta();
        this.pinta_categorias();
        this.Ventas.lbl_Total.setText("0.0");
        this.Saca_Serie();
        this.Oculta();

        this.Diseña_Tabla(this.Ventas.tbl_Compra);

        this.Ventas.setIMG("src\\Multimedia\\fondo.jpg");

        this.Ventas.txt_Sucursal.setText(SUCURSAL);
        this.Ventas.lbl_Sucursal.setText(String.valueOf(ID_SUCURSAL));

        this.Ventas.btn_Comprar.addActionListener(this);
        this.Ventas.btn_Cancelar.addActionListener(this);
        this.Ventas.btn_Salir.addActionListener(this);
        this.Ventas.btn_Quitar.addActionListener(this);

        this.Ventas.btn_Cliente.addActionListener(this);

        this.Ventas.btn_Tipo_Pago.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Ventas.btn_Comprar) {
            this.Comprar();
        }

        if (e.getSource() == Ventas.btn_Cancelar) {
            DefaultTableModel modelo = (DefaultTableModel) this.Ventas.tbl_Compra.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }

        if (e.getSource() == Ventas.btn_Salir) {
            Ventas.dispose();
        }

        if (e.getSource() == Ventas.btn_Quitar) {
            this.quitar();
        }

        if (e.getSource() == Ventas.btn_Cliente) {
            this.Abreformulario_Tabla("Cliente");
        }

        if (e.getSource() == Ventas.btn_Tipo_Pago) {
            this.Abreformulario_Tabla("Pago");
        }

    }

    public int Comprar() {
        try {
            int Cliente = Integer.valueOf(this.Ventas.lbl_Cliente.getText());
            int empleado = Integer.valueOf(this.Ventas.lbl_Empleado.getText());
            int sucursal = Integer.valueOf(this.Ventas.lbl_Sucursal.getText());
            int tipo = Integer.valueOf(this.Ventas.lbl_Tipo_Pago.getText());
            Calendar calendar = new GregorianCalendar();
            String Fecha = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            float to = Float.valueOf(this.Ventas.lbl_Total.getText());

            vo_ventas.setCLV_Cliente(Cliente);
            vo_ventas.setCLV_Empleado(empleado);
            vo_ventas.setCLV_Sucursal(sucursal);
            vo_ventas.setCLV_Tipo_Pago(tipo);
            vo_ventas.setDate_Fecha_Venta(Fecha);
            vo_ventas.setFlt_Total_Venta(to);
            vo_ventas.setVch_Numero_Venta(this.Ventas.txt_no_Venta.getText());
        } catch (Exception exx) {
        }
        int res = 0;
        DefaultTableModel modelo = (DefaultTableModel) this.Ventas.tbl_Compra.getModel();

        if ("0.0".equals(String.valueOf(Float.valueOf(this.Ventas.lbl_Total.getText()))) || Ventas.lbl_Cliente.getText() == null || Ventas.lbl_Empleado.getText() == null
                || Ventas.lbl_Sucursal.getText() == null || Ventas.lbl_Tipo_Pago.getText() == null || modelo.getRowCount() < 0) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = Modelo.insertar(this.Ventas.tbl_Compra, vo_ventas);

                if (res != 0) {
                    JOptionPane.showMessageDialog(null, "Compra realizada correctamente");

                    while (modelo.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }
                    this.Limpiar();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al Comprar");

                }

            } catch (Exception e) {
                System.out.print("Error: " + e);
            }

        }

        return res;

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

                    if ("Cliente".equals(tabla.veri.getText())) {
                        String ID_PERSONA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String PERSONA = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        Ventas.txt_Cliente.setText(PERSONA);
                        Ventas.lbl_Cliente.setText(ID_PERSONA);

                    }

                    if (tabla.veri.getText().equals("Pago")) {
                        String ID_JEFE = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 0).toString();
                        String JEFE = tabla.Tbl_Tabla.getValueAt(tabla.Tbl_Tabla.getSelectedRow(), 1).toString();
                        Ventas.txt_Tipo_Pago.setText(JEFE);
                        Ventas.lbl_Tipo_Pago.setText(ID_JEFE);
                    }

                    tabla.dispose();

                }
            }
        }
        );

        tabla.setVisible(true);
    }

    public void llenaTABLAS(String valor) {
        try {

            DefaultTableModel modelo = (DefaultTableModel) this.tabla.Tbl_Tabla.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            ResultSet rs = Modelo.Consultas(valor);

            if (valor.equals("Cliente")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Cliente"));
                    String A2 = rs.getString("vch_Nombre_Persona");
                    String A3 = rs.getString("vch_A_Paterno");
                    String A4 = rs.getString("vch_A_Materno");

                    modelo.addRow(new Object[]{A1, A2 + " " + A3 + " " + A4});
                }

            }

            if (valor.equals("Pago")) {

                while (rs.next()) {

                    String A1 = String.valueOf(rs.getInt("int_ID_Tipo_Pago"));
                    String A2 = rs.getString("vch_Tipo_Pago");

                    modelo.addRow(new Object[]{A1, A2});
                }

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void pinta_categorias() {

        try {

            ResultSet rs = Modelo.Consulta_Categorias();

            Labeles = new ArrayList<>();
            INDICE = 0;

            while (rs.next()) {

                String ID_Categoria = String.valueOf(rs.getInt("int_ID_Categoria_Platillo"));
                String Nombre = rs.getString("vch_Nombre_Categoria_Platillo");
                String Imagen = rs.getString("Img_categoria_platillo");

                ImageIcon icon = new ImageIcon("src\\Multimedia\\IMG_CAT_PLATILLOS\\" + Imagen);

                JButton label = new JButton();
                label.setBounds(20, 20, 100, 100);
                label.setToolTipText(ID_Categoria + "-" + Nombre);

                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
                label.setIcon(icono);

                this.Ventas.pnl_Categoria.add(label);

                Labeles.add(label);

                label.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Ventas.pnl_Platillo.removeAll();

                        pinta_Platillos_x_cate(Integer.valueOf(ID_Categoria));

                    }

                });

                this.Ventas.pnl_Categoria.updateUI();

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void pinta_Platillos_x_cate(int ID) {

        try {

            ResultSet rs = Modelo.Consulta(ID);

            platillos = new ArrayList<>();
            INDICE2 = 0;

            while (rs.next()) {

                String ID_Categoria = String.valueOf(rs.getInt("int_ID_Platillo"));
                String Nombre = rs.getString("vch_Nombre_Platillo") + "  " + rs.getString("vch_Presentacion_Platillo");
                String Imagen = rs.getString("vch_IMG_Platillo");

                ImageIcon icon = new ImageIcon("src\\Multimedia\\IMG_PLATILLOS\\" + Imagen);

                JButton label = new JButton();
                label.setBounds(20, 20, 100, 100);
                label.setToolTipText(ID_Categoria + "-" + Nombre);

                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
                label.setIcon(icono);

                this.Ventas.pnl_Platillo.add(label);
                platillos.add(label);

                label.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String Texto = label.getToolTipText();

                        String[] parts = Texto.split("-");
                        String part1 = parts[0]; // ID

                        int ID_PLATILLO = Integer.valueOf(part1);

                        Pasa_a_tabla(ID_PLATILLO);

                    }

                });

                this.Ventas.pnl_Platillo.updateUI();

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void Pasa_a_tabla(int ID_PLATILLO) {

        try {

            DefaultTableModel modelo = (DefaultTableModel) this.Ventas.tbl_Compra.getModel();

            ResultSet rs = Modelo.Consulta_platillo(ID_PLATILLO);
            ImageIcon icono = new ImageIcon("src/Multimedia/Numero.png");

            String Cantidad = (String) JOptionPane.showInputDialog(null, "Intriduce la cantidad", "Cantidad", 0, icono, null, null);

            boolean esnumero;
            
            try {
                Integer.parseInt(Cantidad);
                esnumero = true;

            } catch (NumberFormatException nfe) {
                
                esnumero = false;

            }

            if (Cantidad == null ||  esnumero == false) {
                Cantidad = "0";
            } else {
                String ID_platillo = "";
                String Nombre = "";
                String Presentacion = "";
                String Precio = "";
                while (rs.next()) {

                    ID_platillo = rs.getString("int_ID_Platillo");
                    Nombre = rs.getString("vch_Nombre_Platillo");
                    Presentacion = rs.getString("vch_Presentacion_Platillo");
                    Precio = rs.getString("flt_Precio");

                }
                modelo.addRow(new Object[]{ID_platillo, Nombre + " " + Presentacion, Cantidad, Precio});
            }

            this.totalizar();

        } catch (Exception ex) {

        }

    }

    public void totalizar() {
        double t = 0.0;
        double p = 0.0;

        if (this.Ventas.tbl_Compra.getRowCount() > 0) {

            for (int i = 0; i < this.Ventas.tbl_Compra.getRowCount(); i++) {

                if (Integer.valueOf(this.Ventas.tbl_Compra.getValueAt(i, 2).toString()) > 0) {

                    p = Double.valueOf(this.Ventas.tbl_Compra.getValueAt(i, 2).toString()) * Double.valueOf(this.Ventas.tbl_Compra.getValueAt(i, 3).toString());
                    t += p;

                } else {

                    System.out.print("Hay una cantidad en 0");
                }

            }
            this.Ventas.lbl_Total.setText(String.valueOf(t));
        } else {

            this.Ventas.lbl_Total.setText("0.0");
        }

    }

    public void imagen() {
        ImageIcon icon = new ImageIcon("src\\Multimedia\\Botones\\comprar tota.png");
        this.Ventas.lbl_Imagen.setText(null);
        this.Ventas.lbl_Imagen.setSize(129, 107);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(Ventas.lbl_Imagen.getWidth(), Ventas.lbl_Imagen.getHeight(),
                Image.SCALE_DEFAULT));
        this.Ventas.lbl_Imagen.setIcon(icono);

    }

    public void Saca_Serie() {
        String serie = "";

        try {

            ResultSet rs = Modelo.Serie();

            while (rs.next()) {

                String Valor = rs.getString("Serie");

                String[] parts = Valor.split("_");
                String part1 = parts[1];

                int contador = Integer.valueOf(part1) + 1;

                serie = String.valueOf(contador);

            }

            this.Ventas.txt_no_Venta.setText("Vnt_" + serie);

        } catch (Exception ex) {
            System.out.println("Error master " + ex);

        }

    }

    public void quitar() {
        int row = Ventas.tbl_Compra.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {

            DefaultTableModel mode = (DefaultTableModel) Ventas.tbl_Compra.getModel();
            mode.removeRow(row);
            this.totalizar();

        }

    }

    public void Limpiar() {

        this.Ventas.txt_Cliente.setText("");
        this.Ventas.lbl_Cliente.setText("");
        //this.Ventas.txt_Empleado.setText("");
        // this.Ventas.lbl_Empleado.setText("");
        //this.Ventas.txt_Sucursal.setText("");
        // this.Ventas.lbl_Sucursal.setText("");
        this.Ventas.txt_Tipo_Pago.setText("");
        this.Ventas.lbl_Tipo_Pago.setText("");

        this.Ventas.lbl_Total.setText("0.0");

        this.Saca_Serie();

    }

    public void Oculta() {
        this.Ventas.txt_Cliente.setEnabled(false);
        this.Ventas.lbl_Cliente.setVisible(false);
        this.Ventas.txt_Empleado.setEnabled(false);
        this.Ventas.lbl_Empleado.setVisible(false);
        this.Ventas.txt_Sucursal.setEnabled(false);
        this.Ventas.lbl_Sucursal.setVisible(false);
        this.Ventas.txt_Tipo_Pago.setEnabled(false);
        this.Ventas.lbl_Tipo_Pago.setVisible(false);

    }

    public void txt_texto() {
        try {

            ResultSet rs = Modelo.Consulta_EMP(this.ID_EMPLEADO);

            while (rs.next()) {

                String A1 = String.valueOf(rs.getInt("int_ID_Empleado"));
                String A2 = rs.getString("vch_Nombre_Persona");
                String A3 = rs.getString("vch_A_Paterno");
                String A4 = rs.getString("vch_A_Materno");

                this.Ventas.txt_Empleado.setText(A2 + " " + A3 + " " + A4);
                this.Ventas.lbl_Empleado.setText(String.valueOf(this.ID_EMPLEADO));

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

}
