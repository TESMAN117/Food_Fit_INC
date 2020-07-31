/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Controlador.Ctrl_Login;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Mis_Datos_Facade {

     private Connection cnn = Singleton_Cls_Conexion.conexion();
    


    public ResultSet Mi_Data(int ID_User, int ID_Sucursal) {
        
        ResultSet rs = null;

        try {

            CallableStatement ps = cnn.prepareCall("{CALL SP_Mis_Datos(?,?)}");
            ps.setInt(1, ID_User);
            ps.setInt(2, ID_Sucursal);
            ps.execute();

            rs = ps.getResultSet();

            return rs;
        } catch (Exception e) {
            System.out.println(" Errrrr " + e);
        } finally {

            return rs;
        }

    }

    public int Actualiza_primera(String Pass) {
        int r = 0;
        String Actualizar = "UPDATE tbl_usuario\n"
                + "SET\n"
                + "  vch_Password = '" + Pass + "',\n"
                + "  Primera_vez = false\n"
                + "WHERE int_ID_Usuario = " + Ctrl_Login.ID_Usuario + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_fecha_cambio(String cambio, String Pass) {
        int r = 0;
        String Actualizar = "UPDATE tbl_usuario\n"
                + "SET\n"
                + "  vch_Password = '" + Pass + "',\n"
                + "  Cambio_ = '" + cambio + "' \n"
                + "WHERE int_ID_Usuario = " + Ctrl_Login.ID_Usuario + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

}
