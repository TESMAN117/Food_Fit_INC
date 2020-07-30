/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Puesto;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Puesto {

 private Connection cnn = Cls_Conexion.conexion();

    public ResultSet Consulta_Marca() {
        
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_puesto ORDER BY int_ID_puesto";

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

    public int inserta_Puesto(VO_Puesto x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_puesto (vch_Puesto,flt_Saldo)VALUES ('" + x.getPuesto() + "','" + x.getSueldo() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Puesto(VO_Puesto x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_puesto SET vch_Puesto = '" + x.getPuesto() + "',  flt_Saldo = " + x.getSueldo() + " WHERE int_ID_puesto = " + x.getID_Puesto() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina_Puesto(VO_Puesto x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_puesto WHERE `int_ID_puesto` = " + x.getID_Puesto() + ";";

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
