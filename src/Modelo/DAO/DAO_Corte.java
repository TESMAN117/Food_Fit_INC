/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Cliente;
import Modelo.VO.VO_Corte;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class DAO_Corte {

    private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Consulta(int OPcion) {
        
        ResultSet rs = null;
        String consulta = "SELECT\n"
                + "int_ID_Corte,\n"
                + "flt_Efectivo,\n"
                + "flt_Tarjeta,\n"
                + "flt_Cupones,\n"
                + "date_Fecha_Corte,\n"
                + "vch_Nombre\n"
                + "FROM tbl_cortes_caja INNER JOIN tbl_sucursal ON CLV_Sucursal = int_ID_Sucursal WHERE CLV_Sucursal = " + OPcion + ";";

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

    public ResultSet Corte_de_Caja(int ID_Sucursal, String Fecha) {
        
        ResultSet rs = null;

        try {

            CallableStatement ps = cnn.prepareCall("{CALL SP_Corte_Caja(?,?)}");
            ps.setInt(1, ID_Sucursal);
            ps.setString(2, Fecha);
            ps.execute();

            rs = ps.getResultSet();

            return rs;
        } catch (Exception e) {
            System.out.println(" Errrrr " + e);
        } finally {

            return rs;
        }

    }

    public int inserta(VO_Corte x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_cortes_caja\n"
                + "            (flt_Efectivo,\n"
                + "             flt_Tarjeta,\n"
                + "             flt_Cupones,\n"
                + "             date_Fecha_Corte,\n"
                + "             CLV_Sucursal)\n"
                + "VALUES (" + x.getFlt_Efectivo() + ",\n"
                + "        " + x.getFlt_Tarjeta() + ",\n"
                + "        " + x.getFlt_Cupones() + ",\n"
                + "        '" + x.getDate_Fecha_Corte() + "',\n"
                + "        " + x.getCLV_Sucursal() + ");";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

}
