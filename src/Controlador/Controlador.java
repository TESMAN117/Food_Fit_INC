/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.Cls_Conexion;
import Modelo.DAO.DAO_Login;
import Modelo.VO.VO_Login;
import Vista.Frm_Datos_del_Servidor;
import Vista.frm_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class Controlador {

    //MODELOS
    Cls_Conexion Modelo_conexion;
    DAO_Login Modelo_Login;

    //FORMULARIOS
    frm_Login Login;
    Frm_Datos_del_Servidor Datos_server;

    // Constructor del CONTROLADOR -- 
    public Controlador(Cls_Conexion Modelo_conexion, frm_Login Login, Frm_Datos_del_Servidor Datos_server) {
        this.Modelo_conexion = Modelo_conexion;
        this.Login = Login;
        this.Datos_server = Datos_server;
    }

    public static void main(String args[]) throws IOException {
        //ConfiguracionMdi();
        Cls_Conexion Modelo_I = new Cls_Conexion();
        frm_Login Login_I = new frm_Login();
        Frm_Datos_del_Servidor Datos_server_I = new Frm_Datos_del_Servidor();
        Controlador Ctrl = new Controlador(Modelo_I, Login_I, Datos_server_I);

        Ctrl.Iniciar();

    }

    public void Iniciar() throws IOException {
        Datos_server.setTitle("Datos del Servidor"); //Agra el titulo

        Datos_server.setLocationRelativeTo(null); // Agrega posisicon

        Modelo_conexion.conexion(); // Invoca el medoto conexion del MODELO -- Recibe el formulario de datos servidor.

        if (Modelo_conexion.getState() == true) {
            Modelo_Login = new DAO_Login();
            VO_Login vo_login = new VO_Login();
            Ctrl_Login Abre = new Ctrl_Login(Login, Modelo_Login, vo_login);
            Login.setVisible(true);
        }

        if (Modelo_conexion.getState() == false) {

            Ctrl_Datos_Server DS = new Ctrl_Datos_Server(Modelo_conexion, Datos_server);

            Datos_server.setVisible(true);

        }
    }

}
