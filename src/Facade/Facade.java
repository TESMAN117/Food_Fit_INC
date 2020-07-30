/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Controlador.Ctrl_Login;
import Modelo.DAO.DAO_Mis_Datos;
import Vista.Frm_Mis_Datos;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class Facade {

    private int USUARIO;
    private int SUCURSAL;
    private Frm_Mis_Datos Mi_Data;
    DAO_Mis_Datos Modelo;

    public Facade(int USUARIO,int SUCURSAL, Frm_Mis_Datos Mi_Data, DAO_Mis_Datos Modelo) {
        this.USUARIO = USUARIO;
         this.SUCURSAL = SUCURSAL;
        this.Mi_Data = Mi_Data;
        this.Modelo = Modelo;
    }

    public void Mis_Datos() {
        try {


            ResultSet rs = Modelo.Mi_Data(USUARIO, SUCURSAL);

            //Datos Usuario
            String Perfil = "";
            String Usuario = "";
            String Password = "";

            //Datos Empleado
            String Puesto = "";
            String Area = "";
            int Jefe = 0;
            float Sueldo = 0;
            String Imagen = "";

            //Datos Personales
            String Nombre = "";
            String APaterno = "";
            String AMaterno = "";
            String Correo = "";
            String Telefono = "";
            String RFC = "";
            String Codigo_P = "";
            String Domicilio = "";
            String Sucursal = "";

            while (rs.next()) {

                //Datos Usuario
                Perfil = rs.getString("vch_Perfil");
                Usuario = rs.getString("vch_Usuario");
                Password = rs.getString("vch_Password");

                //Datos Empleado
                Puesto = rs.getString("vch_Puesto");
                Area = rs.getString("vch_Area");
                Jefe = rs.getInt("CLV_Jefe");
                Sueldo = rs.getFloat("flt_Saldo");
                Imagen = rs.getString("Img_Foto_Empleado");

                //Datos Personales
                Nombre = rs.getString("vch_Nombre_Persona");
                APaterno = rs.getString("vch_A_Paterno");
                AMaterno = rs.getString("vch_A_Materno");
                Correo = rs.getString("vch_Correo");
                Telefono = rs.getString("vch_Telefono");
                RFC = rs.getString("vch_RFC");
                Codigo_P = rs.getString("vch_Codigo_Postal");
                Domicilio = rs.getString("vch_Domicilio");
                Sucursal = Ctrl_Login.NOMBRE_SUCURSAL;

            }
            //Datos Usuario
            this.Mi_Data.txt_Perfil.setText(Perfil);
            this.Mi_Data.txt_Usuario.setText(Usuario);
            this.Mi_Data.txt_Password.setText(Password);

            //Datos Empleado
            this.Mi_Data.txt_Puesto.setText(Puesto);
            this.Mi_Data.txt_Area.setText(Area);
            this.Mi_Data.txt_Jefe.setText(Jefe + " ");
            this.Mi_Data.txt_Saldo.setText(Sueldo + "");
            this.Mi_Data.txt_IMG.setText(Imagen);

            //Datos Personales
            this.Mi_Data.txt_Nombre.setText(Nombre);
            this.Mi_Data.txt_APaterno.setText(APaterno);
            this.Mi_Data.txt_AMaterno.setText(AMaterno);
            this.Mi_Data.txt_Correo.setText(Correo);
            this.Mi_Data.txt_Telefono.setText(Telefono);
            this.Mi_Data.txt_RFC.setText(RFC);
            this.Mi_Data.txt_CP.setText(Codigo_P);
            this.Mi_Data.txt_Domicilio.setText(Domicilio);
            this.Mi_Data.txt_Sucursal.setText(Sucursal);

        } catch (Exception ex) {

            System.out.print("Errorr : " + ex);

        }

    }

}
