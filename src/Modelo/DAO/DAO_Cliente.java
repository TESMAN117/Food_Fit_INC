/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Modelo.VO.VO_Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Cliente {

   private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Consulta() {
        
        ResultSet rs = null;
        String consulta = "SELECT int_ID_Cliente,vch_Nombre_Persona,vch_A_Paterno,vch_A_Materno FROM tbl_cliente INNER JOIN tbl_persona ON CLV_Persona_Cliente = int_ID_Persona;";

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

    public ResultSet Consulta_Persona() {
        
        ResultSet rs = null;
        String consulta = "SELECT  * FROM tbl_persona;";

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

    public int inserta(VO_Cliente x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_cliente (CLV_Persona_Cliente)VALUES (" + x.getPersona_clv() + ");";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza(VO_Cliente x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_cliente SET  CLV_Persona_Cliente = " + x.getPersona_clv() + " WHERE int_ID_Cliente = " + x.getID() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina(VO_Cliente x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_cliente WHERE int_ID_Cliente =" + x.getID() + ";";

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
