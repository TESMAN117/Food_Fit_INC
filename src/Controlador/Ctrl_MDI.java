/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Persona;
import Modelo.DAO.DAO_Area;
import Modelo.DAO.DAO_Categoria_Platillo;
import Modelo.DAO.DAO_Cliente;
import Modelo.DAO.DAO_Empleado;
import Modelo.DAO.DAO_Linea;
import Modelo.DAO.DAO_Platillo;
import Modelo.DAO.DAO_Producto_Proveedor;
import Modelo.DAO.DAO_Proveedor;
import Modelo.DAO.DAOl_Marca;
import Modelo.DAO.DAO_Puesto;
import Modelo.DAO.DAO_Sucursal;
import Modelo.DAO.DAO_Ventas;
import Modelo.VO.VO_Area;
import Modelo.VO.VO_Categoria_Platillo;
import Modelo.VO.VO_Cliente;
import Modelo.VO.VO_Empleado;
import Modelo.VO.VO_Linea;
import Modelo.VO.VO_Marca;
import Modelo.VO.VO_Persona;
import Modelo.VO.VO_Platillo;
import Modelo.VO.VO_Producto_Proveedor;
import Modelo.VO.VO_Proveedor;
import Modelo.VO.VO_Puesto;
import Modelo.VO.VO_Sucursal;
import Modelo.VO.VO_Venta;
import Modelo.VO.VO_Venta_Detalle;
import Vista.Frm_Area_Edit;
import Vista.Frm_Catalogo_Area;
import Vista.Frm_Catalogo_Categoria_Platillo;
import Vista.Frm_Catalogo_Cliente;
import Vista.Frm_Catalogo_Empleado;
import Vista.Frm_Catalogo_Marca;
import Vista.Frm_Catalogo_Linea;
import Vista.Frm_Catalogo_Persona;
import Vista.Frm_Catalogo_Platillo;
import Vista.Frm_Catalogo_Producto_Proveedor;
import Vista.Frm_Catalogo_Proveedor;
import Vista.Frm_Catalogo_Puesto;
import Vista.Frm_Catalogo_Sucursal;
import Vista.Frm_Categoria_Platillo_Edit;
import Vista.Frm_Cliente_Edit;
import Vista.Frm_Empleado_Edit;
import Vista.Frm_Linea_Edit;
import Vista.Frm_Marca_Edit;
import Vista.Frm_Persona_Edit;
import Vista.Frm_Platillo_Edit;
import Vista.Frm_Producto_Proveedor_Edit;
import Vista.Frm_Proveedor_Edit;
import Vista.Frm_Puesto_Edit;
import Vista.Frm_Sucursal_Edit;
import Vista.Frm_Tablas;
import Vista.Frm_Ventas;
import Vista.MDI_Food;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class Ctrl_MDI implements ActionListener {

    //MDI
    MDI_Food MDI;

    //Catalogos
    Frm_Catalogo_Sucursal Sucursal = null;
    Frm_Catalogo_Area Area = null;
    Frm_Catalogo_Marca Marca = null;
    Frm_Catalogo_Linea Linea = null;
    Frm_Catalogo_Categoria_Platillo Categoria = null;
    Frm_Catalogo_Puesto Puestos = null;
    Frm_Catalogo_Persona Persona = null;
    Frm_Catalogo_Empleado Empelado = null;
    Frm_Catalogo_Cliente Cliente = null;
    Frm_Catalogo_Platillo Platillo = null;
    Frm_Catalogo_Proveedor Proveedor = null;
    Frm_Catalogo_Producto_Proveedor Producto = null;
    Frm_Ventas Ventas = null;
    
    int ID_SUCURSAL;
    String NOMBRE;
    String Dato[];
    
    public Ctrl_MDI(MDI_Food MDI, int ID_SUC, String Nombre, String[] data) {
        this.MDI = MDI;
        this.ID_SUCURSAL = ID_SUC;
        this.NOMBRE = Nombre;
        this.Dato = data;
        
        this.MDI.sub_mnu_Sucursal.addActionListener(this);
        this.MDI.sub_mnu_Area.addActionListener(this);
        this.MDI.sub_mnu_Marca.addActionListener(this);
        this.MDI.sub_mnu_Linea.addActionListener(this);
        this.MDI.sub_mnu_Categoria.addActionListener(this);
        this.MDI.sub_mnu_Puestos.addActionListener(this);
        this.MDI.sub_mnu_Persona.addActionListener(this);
        this.MDI.sub_mnu_Empleado.addActionListener(this);
        this.MDI.sub_mnu_Cliente.addActionListener(this);
        this.MDI.sub_mnu_Platillo.addActionListener(this);
        this.MDI.sub_mnu_Proveedor.addActionListener(this);
        this.MDI.sub_mnu_Prove_Productos.addActionListener(this);
        this.MDI.sub_mnu_Ventas.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == MDI.sub_mnu_Sucursal) {
            try {
                AbreCatalago_Sucursal();
            } catch (SQLException ex) {
                Logger.getLogger(Ctrl_MDI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (e.getSource() == MDI.sub_mnu_Area) {
            
            try {
                AbreCatalago_Area();
            } catch (Exception ex) {
                System.out.print(ex);
            }
        }
        
        if (e.getSource() == MDI.sub_mnu_Marca) {
            AbreCatalago_Marca();
        }
        
        if (e.getSource() == MDI.sub_mnu_Linea) {
            AbreCatalago_Linea();
        }
        
        if (e.getSource() == MDI.sub_mnu_Categoria) {
            AbreCatalago_Categoria();
        }
        
        if (e.getSource() == MDI.sub_mnu_Puestos) {
            AbreCatalago_Puestos();
        }
        
        if (e.getSource() == MDI.sub_mnu_Persona) {
            
            AbreCatalago_Persona();
            
        }
        
        if (e.getSource() == MDI.sub_mnu_Empleado) {
            
            AbreCatalago_Empleado();
            
        }
        
        if (e.getSource() == MDI.sub_mnu_Cliente) {
            
            AbreCatalago_Cliente();
            
        }
        
        if (e.getSource() == MDI.sub_mnu_Platillo) {
            
            AbreCatalago_Platillo();
            
        }
        if (e.getSource() == MDI.sub_mnu_Proveedor) {
            
            AbreCatalago_Proveedor();
            
        }
        
        if (e.getSource() == MDI.sub_mnu_Prove_Productos) {
            
            AbreCatalago_Producto();
            
        }
        
        if (e.getSource() == MDI.sub_mnu_Ventas) {
            
            AbreCatalago_Ventas();
            
        }
        
    }
    
    public void AbreCatalago_Sucursal() throws SQLException {
        if (Sucursal == null || Sucursal.isClosed()) {
            Sucursal = new Frm_Catalogo_Sucursal();
            Frm_Sucursal_Edit Abre = new Frm_Sucursal_Edit();
            DAO_Sucursal Mo = new DAO_Sucursal();
            VO_Sucursal vo = new VO_Sucursal();
            Ctrl_Sucursal ctrl = new Ctrl_Sucursal(Sucursal, Mo, Abre, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Sucursal.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Sucursal);
            
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Sucursal.setVisible(true);
        
    }
    
    public void AbreCatalago_Area() {
        if (Area == null || Area.isClosed()) {
            Area = new Frm_Catalogo_Area();
            DAO_Area Mo = new DAO_Area();
            VO_Area vo = new VO_Area();
            Frm_Area_Edit form = new Frm_Area_Edit();
            Ctrl_Area ctrl = new Ctrl_Area(Mo, Area, vo, form);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Area.setSize(x, y);

            MDI.jDesktopPane1.add(Area);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Area.setVisible(true);
        
    }
    
    public void AbreCatalago_Marca() {
        if (Marca == null || Marca.isClosed()) {
            Marca = new Frm_Catalogo_Marca();
            DAOl_Marca Mo = new DAOl_Marca();
            VO_Marca Mo_VO = new VO_Marca();
            Frm_Marca_Edit form = new Frm_Marca_Edit();
            
            Ctrl_Marca ctrl = new Ctrl_Marca(Mo, Marca, Mo_VO, form);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Marca.setSize(x, y);
           // MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Marca);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Marca.setVisible(true);
        
    }
    
    public void AbreCatalago_Linea() {
        if (Linea == null || Linea.isClosed()) {
            Linea = new Frm_Catalogo_Linea();
            DAO_Linea Mo = new DAO_Linea();
            VO_Linea vo_linea = new VO_Linea();
            Frm_Linea_Edit form = new Frm_Linea_Edit();
            Ctrl_Linea ctrl = new Ctrl_Linea(Mo, Linea, vo_linea, form);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Linea.setSize(x, y);
           // MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Linea);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Linea.setVisible(true);
        
    }
    
    public void AbreCatalago_Categoria() {
        if (Categoria == null || Categoria.isClosed()) {
            Categoria = new Frm_Catalogo_Categoria_Platillo();
            DAO_Categoria_Platillo Mo = new DAO_Categoria_Platillo();
            VO_Categoria_Platillo Vo = new VO_Categoria_Platillo();
            Frm_Categoria_Platillo_Edit form = new Frm_Categoria_Platillo_Edit();
            Ctrl_Categoria_Platillo ctrl = new Ctrl_Categoria_Platillo(Mo, Categoria, Vo, form);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Categoria.setSize(x, y);
           // MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Categoria);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Categoria.setVisible(true);
        
    }
    
    public void AbreCatalago_Puestos() {
        if (Puestos == null || Puestos.isClosed()) {
            Puestos = new Frm_Catalogo_Puesto();
            DAO_Puesto Mo = new DAO_Puesto();
            VO_Puesto vo = new VO_Puesto();
            Frm_Puesto_Edit form = new Frm_Puesto_Edit();
            Ctrl_Puesto ctrl = new Ctrl_Puesto(Mo, Puestos, vo, form);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Puestos.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Puestos);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Puestos.setVisible(true);
        
    }
    
    public void AbreCatalago_Persona() {
        
        if (Persona == null || Persona.isClosed()) {
            Persona = new Frm_Catalogo_Persona();
            DAO_Persona Mo = new DAO_Persona();
            Frm_Persona_Edit form = new Frm_Persona_Edit();
            VO_Persona vo = new VO_Persona();
            Ctrl_Persona ctrl = new Ctrl_Persona(Mo, vo, Persona, form);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Persona.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Persona);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Persona.setVisible(true);
        
    }
    
    public void AbreCatalago_Empleado() {
        
        if (Empelado == null || Empelado.isClosed()) {
            Empelado = new Frm_Catalogo_Empleado();
            DAO_Empleado Mo = new DAO_Empleado();
            Frm_Empleado_Edit form = new Frm_Empleado_Edit();
            VO_Empleado vo = new VO_Empleado();
            Frm_Tablas tabla = new Frm_Tablas();
            Ctrl_Empleado ctrl = new Ctrl_Empleado(vo, Mo, Empelado, form, tabla);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Empelado.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Empelado);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Empelado.setVisible(true);
        
    }
    
    public void AbreCatalago_Cliente() {
        
        if (Cliente == null || Cliente.isClosed()) {
            Cliente = new Frm_Catalogo_Cliente();
            DAO_Cliente Mo = new DAO_Cliente();
            Frm_Cliente_Edit form = new Frm_Cliente_Edit();
            VO_Cliente vo = new VO_Cliente();
            Frm_Tablas tabla = new Frm_Tablas();
            Ctrl_Clientes ctrl = new Ctrl_Clientes(Mo, vo, Cliente, form, tabla);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Cliente.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Cliente);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Cliente.setVisible(true);
        
    }
    
    public void AbreCatalago_Platillo() {
        if (Platillo == null || Platillo.isClosed()) {
            Platillo = new Frm_Catalogo_Platillo();
            DAO_Platillo Mo = new DAO_Platillo();
            Frm_Platillo_Edit form = new Frm_Platillo_Edit();
            VO_Platillo vo = new VO_Platillo();
            Frm_Tablas tabla = new Frm_Tablas();
            Ctrl_Platillo ctrl = new Ctrl_Platillo(Mo, vo, Platillo, form, tabla);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Platillo.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Platillo);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Platillo.setVisible(true);
        
    }
    
    public void AbreCatalago_Proveedor() {
        if (Proveedor == null || Proveedor.isClosed()) {
            Proveedor = new Frm_Catalogo_Proveedor();
            DAO_Proveedor Mo = new DAO_Proveedor();
            Frm_Proveedor_Edit form = new Frm_Proveedor_Edit();
            VO_Proveedor vo = new VO_Proveedor();
            
            Ctrl_Proveedor ctrl = new Ctrl_Proveedor(Mo, vo, Proveedor, form);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Proveedor.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Proveedor);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Proveedor.setVisible(true);
        
    }
    
    public void AbreCatalago_Producto() {
        if (Producto == null || Producto.isClosed()) {
            Producto = new Frm_Catalogo_Producto_Proveedor();
            DAO_Producto_Proveedor Mo = new DAO_Producto_Proveedor();
            Frm_Producto_Proveedor_Edit form = new Frm_Producto_Proveedor_Edit();
            VO_Producto_Proveedor vo = new VO_Producto_Proveedor();
            Frm_Tablas tabla = new Frm_Tablas();
            
            Ctrl_Producto_Proveedor ctrl = new Ctrl_Producto_Proveedor(Mo, vo, Producto, form, tabla);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Producto.setSize(x, y);
            MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Producto);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Producto.setVisible(true);
        
    }
    
    public void AbreCatalago_Ventas() {
        if (Ventas == null || Ventas.isClosed()) {
            Ventas = new Frm_Ventas();
            DAO_Ventas Mo = new DAO_Ventas();
            Frm_Producto_Proveedor_Edit form = new Frm_Producto_Proveedor_Edit();
            VO_Venta vo = new VO_Venta();
            VO_Venta_Detalle vo_Deta = new VO_Venta_Detalle();
            Frm_Tablas tabla = new Frm_Tablas();
            
            Ctrl_Ventas ctrl = new Ctrl_Ventas(Mo, vo, vo_Deta, Ventas, tabla, this.ID_SUCURSAL, this.NOMBRE, Integer.valueOf(Dato[0]));
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Ventas.setSize(x, y);
            //MDI.jDesktopPane1.removeAll();
            MDI.jDesktopPane1.add(Ventas);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Ventas.setVisible(true);
        
    }
    
}
