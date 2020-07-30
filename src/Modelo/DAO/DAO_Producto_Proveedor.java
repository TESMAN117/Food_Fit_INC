/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Producto_Proveedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Producto_Proveedor {

    private Connection cnn = Cls_Conexion.conexion();

    public ResultSet Consulta(String opcion) {
        
        ResultSet rs = null;
        String consulta = " ";

        if ("Producto".equals(opcion)) {
            consulta = "SELECT * FROM tbl_producto_proveedor;";
        }

        if ("Proveedor".equals(opcion)) {
            consulta = "SELECT * FROM tbl_proveedor;";
        }

        if ("Marca".equals(opcion)) {
            consulta = "SELECT * FROM tbl_marca_producto;";
        }

        if ("Linea".equals(opcion)) {
            consulta = "SELECT * FROM tbl_linea_producto;";
        }

        if ("Sucursal".equals(opcion)) {
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

    public ResultSet Consulta(int opcion) {
        
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_producto_proveedor WHERE int_ID_Producto_Proveedor = " + opcion + ";";

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

    public int inserta(VO_Producto_Proveedor x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_producto_proveedor\n"
                + "(\n"
                + "vch_Nombre_Producto_provee,\n"
                + "vch_Presentacon_Prod_Provee,\n"
                + "vch_Tipo_Existencia,\n"
                + "int_Cantidad_Exis,\n"
                + "CLV_Proveedor,\n"
                + "flt_Costo_Unitario,\n"
                + "CLV_Marca,\n"
                + "CLV_Linea,\n"
                + "CLV_Sucursal_Proveedor,\n"
                + "date_Fecha_Compra,flt_Total)\n"
                + "VALUES (\n"
                + "'" + x.getVch_Nombre_Producto_provee() + "',\n"
                + "'" + x.getVch_Presentacon_Prod_Provee() + "',\n"
                + "'" + x.getVch_Tipo_Existencia() + "',\n"
                + "" + x.getInt_Cantidad_Exis() + ",\n"
                + "" + x.getCLV_Proveedor() + ",\n"
                + "" + x.getFlt_Costo_Unitario() + ",\n"
                + "" + x.getCLV_Marca() + ",\n"
                + "" + x.getCLV_Linea() + ",\n"
                + "" + x.getCLV_Sucursal_Proveedor() + ",\n"
                + "'" + x.getDate_Fecha_Compra() + "'," + x.getFlt_Total() + ");";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza(VO_Producto_Proveedor x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_producto_proveedor\n"
                + "SET \n"
                + "  vch_Nombre_Producto_provee = '" + x.getVch_Nombre_Producto_provee() + "',\n"
                + "  vch_Presentacon_Prod_Provee = '" + x.getVch_Presentacon_Prod_Provee() + "',\n"
                + "  vch_Tipo_Existencia = '" + x.getVch_Tipo_Existencia() + "',\n"
                + "  int_Cantidad_Exis = " + x.getInt_Cantidad_Exis() + ",\n"
                + "  CLV_Proveedor = " + x.getCLV_Proveedor() + ",\n"
                + "  flt_Costo_Unitario = " + x.getFlt_Costo_Unitario() + ",\n"
                + "  CLV_Marca = " + x.getCLV_Marca() + ",\n"
                + "  CLV_Linea = " + x.getCLV_Linea() + ",\n"
                + "  CLV_Sucursal_Proveedor = " + x.getCLV_Sucursal_Proveedor() + ",\n"
                + "  date_Fecha_Compra = '" + x.getDate_Fecha_Compra() + "'\n"
                + "  flt_Total = " + x.getFlt_Total() + "\n"
                + "WHERE int_ID_Producto_Proveedor = " + x.getInt_ID_Producto_Proveedor() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina(VO_Producto_Proveedor x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_producto_proveedor WHERE int_ID_Producto_Proveedor = " + x.getInt_ID_Producto_Proveedor() + ";";

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
