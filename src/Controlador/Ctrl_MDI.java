/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Persona;
import Modelo.DAO.DAO_Area;
import Modelo.DAO.DAO_Categoria_Platillo;
import Modelo.DAO.DAO_Linea;
import Modelo.DAO.DAOl_Marca;
import Modelo.DAO.DAO_Puesto;
import Modelo.DAO.DAO_Sucursal;
import Modelo.VO.VO_Area;
import Modelo.VO.VO_Categoria_Platillo;
import Modelo.VO.VO_Linea;
import Modelo.VO.VO_Marca;
import Modelo.VO.VO_Persona;
import Modelo.VO.VO_Puesto;
import Modelo.VO.VO_Sucursal;
import Vista.Frm_Catalogo_Area;
import Vista.Frm_Catalogo_Categoria_Platillo;
import Vista.Frm_Catalogo_Marca;
import Vista.Frm_Catalogo_Linea;
import Vista.Frm_Catalogo_Persona;
import Vista.Frm_Catalogo_Puesto;
import Vista.Frm_Catalogo_Sucursal;
import Vista.Frm_Persona_Edit;
import Vista.Frm_Sucursal_Edit;
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

    public Ctrl_MDI(MDI_Food MDI) {
        this.MDI = MDI;
        this.MDI.sub_mnu_Sucursal.addActionListener(this);
        this.MDI.sub_mnu_Area.addActionListener(this);
        this.MDI.sub_mnu_Marca.addActionListener(this);
        this.MDI.sub_mnu_Linea.addActionListener(this);
        this.MDI.sub_mnu_Categoria.addActionListener(this);
        this.MDI.sub_mnu_Puestos.addActionListener(this);
        this.MDI.sub_mnu_Persona.addActionListener(this);

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

    }

    public void AbreCatalago_Sucursal() throws SQLException {
        if (Sucursal == null || Sucursal.isClosed()) {
            Sucursal = new Frm_Catalogo_Sucursal();
            Frm_Sucursal_Edit Abre = new Frm_Sucursal_Edit();
            DAO_Sucursal Mo = new DAO_Sucursal();
            VO_Sucursal vo = new VO_Sucursal();
            Ctrl_Sucursal ctrl = new Ctrl_Sucursal(Sucursal, Mo, Abre,vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Sucursal.setSize(x, y);

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
            Ctrl_Area ctrl = new Ctrl_Area(Mo, Area, vo);
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

            Ctrl_Marca ctrl = new Ctrl_Marca(Mo, Marca, Mo_VO);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Marca.setSize(x, y);

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
            Ctrl_Linea ctrl = new Ctrl_Linea(Mo, Linea, vo_linea);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Linea.setSize(x, y);

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
            Ctrl_Categoria_Platillo ctrl = new Ctrl_Categoria_Platillo(Mo, Categoria, Vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Categoria.setSize(x, y);

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
            Ctrl_Puesto ctrl = new Ctrl_Puesto(Mo, Puestos, vo);
            int x = MDI.jDesktopPane1.getWidth();
            int y = MDI.jDesktopPane1.getHeight();
            Puestos.setSize(x, y);

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

            MDI.jDesktopPane1.add(Persona);
        } else {
            JOptionPane.showMessageDialog(null, "Formulario Abierto!!!");
        }
        Persona.setVisible(true);

    }

}
