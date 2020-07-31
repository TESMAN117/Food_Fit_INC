/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Modelo.VO.VO_Platillo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Platillo {

   private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Consulta(String tipo) {
        
        ResultSet rs = null;
        String consulta = "";
        if ("Platillo".equals(tipo)) {
            consulta = "SELECT\n"
                + "int_ID_Platillo,\n"
                + "vch_Nombre_Platillo,\n"
                + "vch_Presentacion_Platillo,\n"
                + "flt_Precio,\n"
                + "vch_Nombre_Categoria_Platillo,\n"
                + "vch_Nombre,\n"
                + "vch_IMG_Platillo\n"
                + "FROM tbl_platillo INNER JOIN tbl_categoria_platillo ON tbl_platillo.`CLV_Categoria` = tbl_categoria_platillo.`int_ID_Categoria_Platillo`\n"
                + "INNER JOIN tbl_sucursal ON  tbl_platillo.`CLV_Sucursal_Platillo` = tbl_sucursal.`int_ID_Sucursal`;";
        }

        if ("Categoria".equals(tipo)) {
            consulta = "SELECT * FROM tbl_categoria_platillo;";
        }

        if ("Sucursal".equals(tipo)) {
            consulta = "SELECT * FROM tbl_sucursal;";
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

    public ResultSet Consulta(int tipo) {
        
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_platillo WHERE int_ID_Platillo = " + tipo + ";";

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

    public int inserta(VO_Platillo x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_platillo\n"
                + "            (vch_Nombre_Platillo,\n"
                + "             vch_Presentacion_Platillo,\n"
                + "             flt_Precio,\n"
                + "             CLV_Categoria,\n"
                + "             CLV_Sucursal_Platillo,\n"
                + "             vch_IMG_Platillo)\n"
                + "VALUES (\n"
                + "        '" + x.getVch_Nombre_Platillo() + "',\n"
                + "        '" + x.getVch_Presentacion_Platillo() + "',\n"
                + "        " + x.getFlt_Precio() + ",\n"
                + "        " + x.getCLV_Categoria() + ",\n"
                + "        " + x.getCLV_Sucursal_Platillo() + ",\n"
                + "        '" + x.getVch_IMG_Platillo() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza(VO_Platillo x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_platillo\n"
                + "SET \n"
                + "  vch_Nombre_Platillo = '" + x.getVch_Nombre_Platillo() + "',\n"
                + "  vch_Presentacion_Platillo = '" + x.getVch_Presentacion_Platillo() + "',\n"
                + "  flt_Precio = " + x.getFlt_Precio() + ",\n"
                + "  CLV_Categoria = " + x.getCLV_Categoria() + ",\n"
                + "  CLV_Sucursal_Platillo = " + x.getCLV_Sucursal_Platillo() + ",\n"
                + "  vch_IMG_Platillo = '" + x.getVch_IMG_Platillo() + "'\n"
                + "WHERE int_ID_Platillo = " + x.getInt_ID_Platillo() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_SIMG(VO_Platillo x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_platillo\n"
                + "SET \n"
                + "  vch_Nombre_Platillo = '" + x.getVch_Nombre_Platillo() + "',\n"
                + "  vch_Presentacion_Platillo = '" + x.getVch_Presentacion_Platillo() + "',\n"
                + "  flt_Precio = " + x.getFlt_Precio() + ",\n"
                + "  CLV_Categoria = " + x.getCLV_Categoria() + ",\n"
                + "  CLV_Sucursal_Platillo = " + x.getCLV_Sucursal_Platillo() + "\n"
                + "WHERE int_ID_Platillo = " + x.getInt_ID_Platillo() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina(VO_Platillo x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_platillo WHERE int_ID_Platillo =" + x.getInt_ID_Platillo() + ";";

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
