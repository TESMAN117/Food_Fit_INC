/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import Modelo.DAO.DAO_Login;
import Modelo.VO.VO_Login;
import java.sql.ResultSet;

/**
 *
 * @author jesus
 */
public class proxy implements I_Proxy {

    DAO_Login Modelo;

    public proxy() {
        Modelo = new DAO_Login();
    }

    @Override
    public ResultSet I_Inicia(VO_Login x) {
        ResultSet rs = null;
        try {
            rs = Modelo.Iniciar_sesion(x);
            return rs;
        } catch (Exception ex) {
            rs = null;
        }

        return rs;
    }

    @Override
    public ResultSet I_Sucursal() {

        ResultSet rs = null;

        try {
            rs = Modelo.Sucursal();
            return rs;
        } catch (Exception ex) {
            rs = null;
        }
        return rs;

    }

}
