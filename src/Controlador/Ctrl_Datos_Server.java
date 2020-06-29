/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.Cls_Conexion;
import Vista.Frm_Datos_del_Servidor;
import Vista.frm_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class Ctrl_Datos_Server implements ActionListener {

    Cls_Conexion Modelo_Conectar; // MODELO conexion
    Frm_Datos_del_Servidor frm_datos; // VISTA de formularo datos servidor

    public Ctrl_Datos_Server(Cls_Conexion Modelo_Conectar, Frm_Datos_del_Servidor frm_datos) {
        this.Modelo_Conectar = Modelo_Conectar; // Instancia el MODELO.
        this.frm_datos = frm_datos; // Instancia el FORMULARIO Datos del servidor.
        this.frm_datos.txt_base.addActionListener(this);  // Agrega accion del boton
        this.frm_datos.txt_pass.addActionListener(this);  // Agrega accion del boton
        this.frm_datos.txt_user.addActionListener(this);  // Agrega accion del boton
        this.frm_datos.btn_conectar.addActionListener(this); // Agrega accion del boton
        this.frm_datos.btn_cancelar.addActionListener(this); // Agrega accion del boton
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm_datos.btn_conectar) {
            try {
                ///Inicio IF  CONECTAR
                Conectar();
            } catch (IOException ex) {
                Logger.getLogger(Ctrl_Datos_Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } ///Fin IF  CONECTAR

        if (e.getSource() == frm_datos.btn_cancelar) { ///Inicio IF  CANCELAR
            System.exit(0);
        }/// Fin IF CANCELAR

    }

    public void Conectar() throws IOException {
        if ("".equals(frm_datos.txt_user.getText()) || "".equals(frm_datos.txt_pass.getText()) || "".equals(frm_datos.txt_base.getText())) {

            JOptionPane.showMessageDialog(null, "CAMPOS vacios");
        } else {
            Modelo_Conectar.crearArchivo(frm_datos.txt_user.getText(), frm_datos.txt_pass.getText(), frm_datos.txt_base.getText());
            frm_datos.dispose();
            Modelo_Conectar.leerArchivo();

            Cls_Conexion Modelo_I = new Cls_Conexion();
            frm_Login Login_I = new frm_Login();
            Frm_Datos_del_Servidor Datos_server_I = new Frm_Datos_del_Servidor();
            Controlador Ctrl = new Controlador(Modelo_I, Login_I, Datos_server_I);

            Ctrl.Iniciar();
        }

    }

    public void Conectar2() {
        Modelo_Conectar.leerArchivo();
    }

}
