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
public class VO_Puesto {

    private int ID_Puesto;
    private String Puesto;
    private float Sueldo;

    public int getID_Puesto() {
        return ID_Puesto;
    }

    public void setID_Puesto(int ID_Puesto) {
        this.ID_Puesto = ID_Puesto;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }

    public float getSueldo() {
        return Sueldo;
    }

    public void setSueldo(float Sueldo) {
        this.Sueldo = Sueldo;
    }

}
