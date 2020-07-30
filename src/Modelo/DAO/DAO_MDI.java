/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Controlador.Ctrl_Login;
import Modelo.VO.VO_Usuario;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_MDI {

    private Connection cnn = Cls_Conexion.conexion();
    public int Actualiza(String Pass) {
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

}
