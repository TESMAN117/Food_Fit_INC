/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Proveedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Proveedor {

    private Connection cnn = Cls_Conexion.conexion();

    public ResultSet Consulta() {
        
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_proveedor;";

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

    public int inserta(VO_Proveedor x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_proveedor\n"
                + "            (\n"
                + "             vch_Nombre_Proveedor,\n"
                + "            vch_Telefono,\n"
                + "             vch_Direccon)\n"
                + "VALUES ('" + x.getNombre() + "',\n"
                + "        '" + x.getTelefono() + "',\n"
                + "        '" + x.getDireccion() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza(VO_Proveedor x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_proveedor\n"
                + "SET vch_Nombre_Proveedor = '" + x.getNombre() + "',\n"
                + "  vch_Telefono = '" + x.getTelefono() + "',\n"
                + "  vch_Direccon = '" + x.getDireccion() + "'\n"
                + "WHERE int_ID_Proveedor = " + x.getID_Proveedor() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina(VO_Proveedor x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_proveedor WHERE int_ID_Proveedor =" + x.getID_Proveedor() + ";";

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
