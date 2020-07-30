/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.VO;

/**
 *
 * @author jesus
 */
public class VO_Usuario {

    private int int_ID_Usuario;
    private int int_Tipo_Usuario;
    private String vch_Usuario;
    private String vch_Password;
    private int CLV_Cliente_Usuario;
    private int CLV_Empleado_User;
    private String Tipito;

    public int getInt_ID_Usuario() {
        return int_ID_Usuario;
    }

    public void setInt_ID_Usuario(int int_ID_Usuario) {
        this.int_ID_Usuario = int_ID_Usuario;
    }

    public int getInt_Tipo_Usuario() {
        return int_Tipo_Usuario;
    }

    public void setInt_Tipo_Usuario(int int_Tipo_Usuario) {
        this.int_Tipo_Usuario = int_Tipo_Usuario;
    }

    public String getVch_Usuario() {
        return vch_Usuario;
    }

    public void setVch_Usuario(String vch_Usuario) {
        this.vch_Usuario = vch_Usuario;
    }

    public String getVch_Password() {
        return vch_Password;
    }

    public void setVch_Password(String vch_Password) {
        this.vch_Password = vch_Password;
    }

    public int getCLV_Cliente_Usuario() {
        return CLV_Cliente_Usuario;
    }

    public void setCLV_Cliente_Usuario(int CLV_Cliente_Usuario) {
        this.CLV_Cliente_Usuario = CLV_Cliente_Usuario;
    }

    public int getCLV_Empleado_User() {
        return CLV_Empleado_User;
    }

    public void setCLV_Empleado_User(int CLV_Empleado_User) {
        this.CLV_Empleado_User = CLV_Empleado_User;
    }

    public String getTipito() {
        return Tipito;
    }

    public void setTipito(String Tipito) {
        this.Tipito = Tipito;
    }

}
