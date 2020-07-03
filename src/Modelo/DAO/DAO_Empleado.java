/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Empleado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class DAO_Empleado {

    Cls_Conexion conec = new Cls_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Empleado(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = " ";
        if ("Empleado".equals(opcion)) {
            consulta = "SELECT int_ID_Empleado,CLV_Persona,vch_Nombre_Persona,vch_A_Paterno,\n"
                    + "                vch_A_Materno,CLV_Puesto,vch_Puesto,CLV_Area,vch_Area,CLV_Jefe,\n"
                    + "                Img_Foto_Empleado\n"
                    + "                FROM tbl_empleado INNER JOIN tbl_persona ON tbl_empleado.`CLV_Persona` = tbl_persona.`int_ID_Persona`\n"
                    + "                INNER JOIN tbl_puesto ON tbl_empleado.`CLV_Puesto` = tbl_puesto.`int_ID_puesto`\n"
                    + "                INNER JOIN tbl_Area ON tbl_empleado.`CLV_Area` = tbl_area.`int_ID_area`;";
        }
        if ("Persona".equals(opcion)) {
            consulta = "SELECT * FROM tbl_persona; ";
        }

        if ("Puesto".equals(opcion)) {
            consulta = "SELECT * FROM tbl_puesto;";
        }

        if ("Area".equals(opcion)) {
            consulta = "SELECT * FROM tbl_Area;";
        }

        if ("Jefe".equals(opcion)) {
            consulta = "SELECT\n"
                    + "int_ID_Empleado,\n"
                    + "vch_Nombre_Persona,\n"
                    + "vch_A_Paterno,\n"
                    + "vch_A_Materno\n"
                    + "FROM tbl_empleado INNER JOIN tbl_persona ON CLV_Persona = int_ID_Persona;";
        }

        try {
            Statement stm;
            stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery(consulta);

            return rs;

        } catch (Exception e) {
            System.out.println("Error al hacer la consulta");

        }
        return null;
    }

    public ResultSet Consulta_Especifica(int opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "select int_ID_Empleado,CLV_Persona,vch_Nombre_Persona,vch_A_Paterno,\n"
                + "vch_A_Materno,CLV_Puesto,vch_Puesto,CLV_Area,vch_Area,CLV_Jefe,\n"
                + "Img_Foto_Empleado\n"
                + "from tbl_empleado inner join tbl_persona on tbl_empleado.`CLV_Persona` = tbl_persona.`int_ID_Persona`\n"
                + "inner join tbl_puesto on tbl_empleado.`CLV_Puesto` = tbl_puesto.`int_ID_puesto`\n"
                + "inner JOIN tbl_Area on tbl_empleado.`CLV_Area` = tbl_area.`int_ID_area`\n"
                + "where int_ID_Empleado = " + opcion + ";";

        try {
            Statement stm;
            stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery(consulta);

            return rs;

        } catch (Exception e) {
            System.out.println("Error al hacer la consulta");

        }
        return null;
    }

    public ResultSet Consulta_Jefe(int opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "";

        consulta = "SELECT \n"
                + "vch_Nombre_Persona,\n"
                + "vch_A_Paterno,\n"
                + "vch_A_Materno\n"
                + "FROM tbl_persona \n"
                + "WHERE tbl_persona.int_ID_Persona = (SELECT tbl_empleado.CLV_Persona FROM tbl_empleado WHERE tbl_empleado.`int_ID_Empleado` = " + opcion + ");";

        try {
            Statement stm;
            stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery(consulta);

            return rs;

        } catch (Exception e) {
            System.out.println("Error al hacer la consulta");

        }
        return null;
    }

    public int inserta_Empleado(VO_Empleado x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_empleado\n"
                + "            (CLV_Persona,\n"
                + "             CLV_Puesto,\n"
                + "             CLV_Area,\n"
                + "             CLV_Jefe,\n"
                + "             Img_Foto_Empleado)\n"
                + "VALUES (\n"
                + "        " + x.getCLV_Persona() + ",\n"
                + "        " + x.getCLV_Puesto() + ",\n"
                + "        " + x.getCLV_Area() + ",\n"
                + "        " + x.getCLV_Jefe() + ",\n"
                + "        '" + x.getImg_Foto_Empleado() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Empleado(VO_Empleado x) {
        JOptionPane.showMessageDialog(null, "Con IMG");
        JOptionPane.showMessageDialog(null, x.getCLV_Persona() +" "+x.getCLV_Puesto() +" "+x.getCLV_Area() +" "
        +x.getCLV_Jefe() +" "+x.getImg_Foto_Empleado() +" "+x.getID_Emleado() +" ");
        int r = 0;
        String Actualizar = "UPDATE tbl_empleado\n"
                + "SET \n"
                + "  CLV_Persona = " + x.getCLV_Persona() + ",\n"
                + "  CLV_Puesto = " + x.getCLV_Puesto() + ",\n"
                + "  CLV_Area = " + x.getCLV_Area() + ",\n"
                + "  CLV_Jefe = " + x.getCLV_Jefe() + ",\n"
                + "  Img_Foto_Empleado = '" + x.getImg_Foto_Empleado() + "' \n"
                + "WHERE int_ID_Empleado = " + x.getID_Emleado() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_EmpleadoSIMG(VO_Empleado x) {
        
        JOptionPane.showMessageDialog(null, "Sin IMG");
        int r = 0;
        String Actualizar = "UPDATE tbl_empleado\n"
                + "SET \n"
                + "  CLV_Persona = " + x.getCLV_Persona() + ",\n"
                + "  CLV_Puesto = " + x.getCLV_Puesto() + ",\n"
                + "  CLV_Area = " + x.getCLV_Area() + ",\n"
                + "  CLV_Jefe = " + x.getCLV_Jefe() + "\n"
                + "WHERE int_ID_Empleado = " + x.getID_Emleado() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina_Empleado(VO_Empleado xz) {
        int r = 0;
        String Delete = "DELETE FROM tbl_empleado WHERE int_ID_Empleado =" + xz.getID_Emleado() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Delete);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

}
