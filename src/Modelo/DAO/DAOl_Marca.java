/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Marca;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAOl_Marca {

   private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Consulta_Marca() {
       
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_marca_producto ORDER BY int_Id_marca";

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

    public int inserta_Marca(VO_Marca x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_marca_producto (vch_Marca)VALUES ('" + x.getNombre_Marca() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Marca(VO_Marca x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_marca_producto SET vch_Marca = '" + x.getNombre_Marca() + "' WHERE int_Id_marca = " + x.getID_MARCA() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina_Marca(VO_Marca x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_marca_producto WHERE int_Id_marca =" + x.getID_MARCA() + ";";

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
