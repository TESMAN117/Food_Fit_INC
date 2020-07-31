/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Modelo.VO.VO_Persona;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Persona {

    private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Consulta_Persona(String Opcion) {

        ResultSet rs = null;
        String consulta = "";
        if (Opcion.equals("Tabla")) {
            consulta = "SELECT * FROM tbl_persona ORDER BY int_ID_Persona";
        }

        if (Opcion.equals("Combo")) {
            consulta = "SELECT * FROM tbl_sucursal";
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

    public int inserta_Persona(VO_Persona x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_persona(vch_Nombre_Persona,vch_A_Paterno,vch_A_Materno,vch_Correo,vch_Telefono,vch_RFC,CLV_Sucursal_Persoanl,vch_Codigo_Postal,vch_Domicilio)\n"
                + "VALUES ('" + x.getNombre() + "','" + x.getAP() + "','" + x.getAM() + "','" + x.getCorreo() + "','" + x.getTelefono() + "','" + x.getRFC() + "'," + x.getID_Sucursal() + ",'" + x.getCP() + "','" + x.getDomicilio() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Persona(VO_Persona x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_persona\n"
                + "SET \n"
                + "  vch_Nombre_Persona = '" + x.getNombre() + "',\n"
                + "  vch_A_Paterno = '" + x.getAP() + "',\n"
                + "  vch_A_Materno = '" + x.getAM() + "',\n"
                + "  vch_Correo = '" + x.getCorreo() + "',\n"
                + "  vch_Telefono = '" + x.getTelefono() + "',\n"
                + "  vch_RFC = '" + x.getRFC() + "',\n"
                + "  CLV_Sucursal_Persoanl = " + x.getID_Sucursal() + ",\n"
                + "  vch_Codigo_Postal = '" + x.getCP() + "',\n"
                + "  vch_Domicilio = '" + x.getDomicilio() + "'\n"
                + "WHERE int_ID_Persona = " + x.getID() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina_Persona(VO_Persona x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_persona WHERE int_ID_Persona =" + x.getID() + ";";

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
