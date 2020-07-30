/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Linea;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Linea {

    private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Consulta_Linea() {
        
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_linea_producto ORDER BY int_ID_linea";

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

    public int inserta_Linea(VO_Linea x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_linea_producto (vch_Linea)VALUES ('" + x.getLinea() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Linea(VO_Linea x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_linea_producto SET vch_Linea = '" + x.getLinea() + "' WHERE int_ID_linea = " + x.getLinea() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina_Linea(VO_Linea xz) {
        int r = 0;
        String Delete = "DELETE FROM tbl_linea_producto WHERE int_ID_linea =" + xz.getID_Linea() + ";";

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
