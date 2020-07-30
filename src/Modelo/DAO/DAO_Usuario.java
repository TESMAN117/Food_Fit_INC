/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Controlador.Ctrl_Login;
import Modelo.VO.VO_Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Usuario {

   private Connection cnn = Cls_Conexion.conexion();

    public ResultSet Consulta() {
        
        ResultSet rs = null;
        String consulta = "SELECT\n"
                + "  int_ID_Usuario,\n"
                + "  int_ID_Perfil,\n"
                + "  vch_Perfil,\n"
                + "  vch_Usuario,\n"
                + "  vch_Password,\n"
                + "  int_ID_Empleado,\n"
                + "  vch_Nombre_Persona,\n"
                + "  vch_A_Paterno,\n"
                + "  vch_A_Materno,\n"
                + "  CLV_Empleado_User,\n"
                + "  CLV_Persona,\n"
                + "  Tipito\n"
                + "FROM tbl_usuario INNER JOIN tbl_Perfil ON tbl_usuario.`CLV_Tipo_Usuario` = tbl_Perfil.`int_ID_Perfil`\n"
                + "INNER JOIN tbl_empleado ON tbl_empleado.`int_ID_Empleado` = tbl_usuario.`CLV_Empleado_User` \n"
                + "INNER JOIN tbl_persona ON tbl_persona.`int_ID_Persona` = tbl_empleado.`CLV_Persona`\n"
                + "WHERE tbl_persona.`CLV_Sucursal_Persoanl` = " + Ctrl_Login.ID_SUCURSAL + ";";

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

    public int inserta(VO_Usuario x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_usuario\n"
                + "            (CLV_Tipo_Usuario,\n"
                + "             vch_Usuario,\n"
                + "             vch_Password,\n"
                + "             CLV_Empleado_User,\n"
                + "             Tipito)\n"
                + "VALUES (" + x.getInt_Tipo_Usuario() + ",\n"
                + "        '" + x.getVch_Usuario() + "',\n"
                + "        '" + x.getVch_Password() + "',\n"
                + "        " + x.getCLV_Empleado_User() + ",\n"
                + "        'Dentro');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza(VO_Usuario x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_usuario\n"
                + "SET\n"
                + "  CLV_Tipo_Usuario = " + x.getInt_Tipo_Usuario() + ",\n"
                + "  vch_Usuario = '" + x.getVch_Usuario() + "',\n"
                + "  vch_Password = '" + x.getVch_Password() + "',\n"
                + "  CLV_Empleado_User = " + x.getCLV_Empleado_User() + "\n"
                + "WHERE int_ID_Usuario = " + x.getInt_ID_Usuario() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina(VO_Usuario x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_usuario WHERE int_ID_Usuario =" + x.getInt_ID_Usuario() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Delete);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            return r;
        }

    }

    public boolean Consulta_Usuario_Igual(String Nombre, int Empleado) {
        boolean Existe = false;

        
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_usuario \n"
                + "WHERE tbl_usuario.`vch_Usuario` = '" + Nombre + "' OR tbl_usuario.`CLV_Empleado_User` = " + Empleado + ";";

        try {
            Statement stm;
            stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery(consulta);

            while (rs.next()) {

                Existe = true;
            }

            return Existe;

        } catch (Exception e) {
            System.out.println("Error al hacer la consulta");

        }
        return Existe;
    }

    public ResultSet Consulta(String Opcion) {
      
        ResultSet rs = null;

        String consulta = "";

        if ("Perfil".equals(Opcion)) {

            consulta = "SELECT * FROM tbl_perfil";

        }

        if ("Usuario".equals(Opcion)) {

            consulta = "SELECT\n"
                    + "int_ID_Empleado,\n"
                    + "vch_Nombre_Persona,\n"
                    + "vch_A_Paterno,\n"
                    + "vch_A_Materno,\n"
                    + "vch_Correo,\n"
                    + "vch_RFC\n"
                    + "FROM tbl_empleado AS Empleado INNER JOIN tbl_persona ON Empleado.`CLV_Persona` = tbl_persona.`int_ID_Persona`\n"
                    + "WHERE NOT EXISTS (SELECT * FROM tbl_usuario AS Usuario WHERE Empleado.int_ID_Empleado = Usuario.`CLV_Empleado_User`) AND\n"
                    + "tbl_persona.`CLV_Sucursal_Persoanl` = " + Ctrl_Login.ID_SUCURSAL + ";";

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

}
