/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Login;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Login {

    Cls_Conexion conec = new Cls_Conexion();

    private Connection cnn;

    public ResultSet Iniciar_sesion(VO_Login x) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_usuario WHERE vch_Usuario = '" + x.getUser() + "' AND vch_Password = '" + x.getPass() + "';";

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

    public ResultSet Sucursal() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_sucursal;";

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
