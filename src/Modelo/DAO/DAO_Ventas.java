/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Modelo.VO.VO_Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class DAO_Ventas {

  private Connection cnn = Singleton_Cls_Conexion.conexion();
    public ResultSet Consulta_Categorias() {

        ResultSet rs = null;

        String consulta = "SELECT * FROM tbl_categoria_platillo;";

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
        String consulta = "SELECT * FROM tbl_platillo WHERE CLV_Categoria = " + opcion + ";";

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

    public ResultSet Consulta_EMP(int opcion) {

        ResultSet rs = null;
        String consulta = "SELECT\n"
                + "int_ID_Empleado,\n"
                + "vch_Nombre_Persona,\n"
                + "vch_A_Paterno,\n"
                + "vch_A_Materno,\n"
                + "vch_Telefono\n"
                + "FROM tbl_persona INNER JOIN tbl_empleado ON tbl_persona.`int_ID_Persona` = tbl_empleado.`CLV_Persona`\n"
                + "WHERE tbl_empleado.`int_ID_Empleado` = " + opcion + ";";

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

    public ResultSet Consulta_platillo(int opcion) {

        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_platillo WHERE int_ID_Platillo = " + opcion + ";";

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

    public int insertar(JTable tabla, VO_Venta x) {

        int r = 0;
        int idGenerado = 0;
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        

        try {

            /* if (tabla.getRowCount() > 0) {

                for (int i = 0; i < tabla.getRowCount(); i++) {

                    total = total + (Float.valueOf(tabla.getValueAt(i, 2).toString()) * Float.valueOf(tabla.getValueAt(i, 3).toString()));
                }

            }*/
            String Insert = "INSERT INTO tbl_ventas\n"
                    + "            (vch_Numero_Venta,\n"
                    + "             CLV_Cliente,\n"
                    + "             CLV_Empleado,\n"
                    + "             flt_Total_Venta,\n"
                    + "             date_Fecha_Venta,\n"
                    + "             CLV_Sucursal,\n"
                    + "             CLV_Tipo_Pago)\n"
                    + "VALUES ('" + x.getVch_Numero_Venta() + "',\n"
                    + "        " + x.getCLV_Cliente() + ",\n"
                    + "        " + x.getCLV_Empleado() + ",\n"
                    + "        " + x.getFlt_Total_Venta() + ",\n"
                    + "        '" + x.getDate_Fecha_Venta() + "',\n"
                    + "        " + x.getCLV_Sucursal() + ",\n"
                    + "        " + x.getCLV_Tipo_Pago() + ");";

            PreparedStatement statement = cnn.prepareStatement(Insert, Statement.RETURN_GENERATED_KEYS);
            r = statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);

                this.insertar_detalle(tabla, idGenerado);
            } else {
                System.out.print("No se inserto por que no alcanza");

            }

            // Statement stm = (Statement) cnn.createStatement();
            // r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int insertar_detalle(JTable tabla, int id) {
        int r = 0;
        Calendar calendar = new GregorianCalendar();
        String Fecha = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        try {

            if (tabla.getRowCount() > 0) {

                for (int i = 0; i < tabla.getRowCount(); i++) {

                    ResultSet rs = null;

                    String consulta = " SELECT * FROM tbl_platillo WHERE int_ID_Platillo = " + Integer.valueOf(tabla.getValueAt(i, 0).toString()) + ";";

                    Statement stmn;
                    stmn = (Statement) cnn.createStatement();
                    rs = stmn.executeQuery(consulta);
                    String Nombre = "";
                    String presentacion = "";
                    String Categoria = "";
                    String Precio = "";

                    while (rs.next()) {

                        Nombre = rs.getString("vch_Nombre_Platillo");
                        presentacion = rs.getString("vch_Presentacion_Platillo");
                        Categoria = rs.getString("CLV_Categoria");
                        Precio = rs.getString("flt_Precio");

                    }

                    String Insert = "INSERT INTO tbl_venta_detalle\n"
                            + "            (CLV_Ventas,\n"
                            + "             CLV_Platillo,\n"
                            + "             vch_Nombre_Platillo,\n"
                            + "             vch_Presentacion_Platillo,\n"
                            + "             int_Cantidad,\n"
                            + "             flt_Precio,\n"
                            + "             Categoria,\n"
                            + "            date_Fecha_Venta)\n"
                            + "VALUES (" + id + ",\n"
                            + "        " + Integer.valueOf(tabla.getValueAt(i, 0).toString()) + ",\n"
                            + "        '" + Nombre + "',\n"
                            + "        '" + presentacion + "',\n"
                            + "        " + Integer.valueOf(tabla.getValueAt(i, 2).toString()) + ",\n"
                            + "        " + Float.valueOf(Precio) + ",\n"
                            + "        " + Categoria + ",\n"
                            + "        '" + Fecha + "');";

                    Statement stm = (Statement) cnn.createStatement();
                    r = stm.executeUpdate(Insert);
                }

            }

        } catch (Exception e) {
            System.out.println("LOLO + " + e);
        } finally {

            return r;
        }
    }

    public ResultSet Consultas(String opcion) {

        ResultSet rs = null;
        String consulta = "";

        if ("Cliente".equals(opcion)) {
            consulta = "SELECT\n"
                    + "int_ID_Cliente,\n"
                    + "vch_Nombre_Persona,\n"
                    + "vch_A_Paterno,\n"
                    + "vch_A_Materno,\n"
                    + "vch_Telefono\n"
                    + "FROM tbl_persona INNER JOIN tbl_cliente ON tbl_persona.`int_ID_Persona` = tbl_cliente.`CLV_Persona_Cliente`;";
        }
        /*
        if ("Empleado".equals(opcion)) {
            consulta = "SELECT\n"
                    + "int_ID_Empleado,\n"
                    + "vch_Nombre_Persona,\n"
                    + "vch_A_Paterno,\n"
                    + "vch_A_Materno,\n"
                    + "vch_Telefono\n"
                    + "FROM tbl_persona INNER JOIN tbl_empleado ON tbl_persona.`int_ID_Persona` = tbl_empleado.`CLV_Persona`;";
        }*/
        if ("Pago".equals(opcion)) {
            consulta = "SELECT * FROM tbl_tipo_pago;";
        }
        /*
        if ("Sucursal".equals(opcion)) {
            consulta = "SELECT * FROM tbl_sucursal;";
        }*/

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

    public ResultSet Serie() {

        ResultSet rs = null;

        String consulta = "SELECT MAX(vch_Numero_Venta) AS 'Serie' FROM tbl_ventas;";

        try {

            Statement stm;
            stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery(consulta);

            return rs;
        } catch (Exception e) {
            System.out.println("Error al hacer la consulta_esta");

        }
        return null;
    }

}
