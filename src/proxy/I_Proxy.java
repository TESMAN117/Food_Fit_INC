/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import Modelo.VO.VO_Login;
import java.sql.ResultSet;

/**
 *
 * @author jesus
 */
public interface I_Proxy {

    ResultSet I_Inicia(VO_Login x);

    ResultSet I_Sucursal();

}
