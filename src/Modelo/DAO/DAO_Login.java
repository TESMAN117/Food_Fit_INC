/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Modelo.VO.VO_Login;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import proxy.I_Proxy;

/**
 *
 * @author jesus
 */
public class DAO_Login {

    private Connection cnn = Singleton_Cls_Conexion.conexion();

    public ResultSet Iniciar_sesion(VO_Login x) {

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

   /* @Override
    public ResultSet I_Inicia(VO_Login x) {
        return this.Iniciar_sesion(x);
    }

    @Override
    public ResultSet I_Sucursal() {
        return this.Sucursal();
    }
*/
}
