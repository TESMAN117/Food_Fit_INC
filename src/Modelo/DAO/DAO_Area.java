/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Modelo.VO.VO_Area;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Area {

   private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Consulta_Area() {
       
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_area ORDER BY int_ID_area";

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

    public int inserta_Area(VO_Area x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_area (vch_Area)VALUES ('" + x.getArea() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Area(VO_Area x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_area SET vch_Area = '" + x.getArea() + "' WHERE int_ID_area = " + x.getID_Area() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina_Area(VO_Area x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_area WHERE int_ID_area =" + x.getID_Area() + ";";

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
