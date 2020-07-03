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
public class VO_Empleado {

    private int ID_Emleado;
    private int CLV_Persona;
    private int CLV_Puesto;
    private int CLV_Area;
    private int CLV_Jefe;
    private String Img_Foto_Empleado;

    public int getID_Emleado() {
        return ID_Emleado;
    }

    public void setID_Emleado(int ID_Emleado) {
        this.ID_Emleado = ID_Emleado;
    }

    public int getCLV_Persona() {
        return CLV_Persona;
    }

    public void setCLV_Persona(int CLV_Persona) {
        this.CLV_Persona = CLV_Persona;
    }

    public int getCLV_Puesto() {
        return CLV_Puesto;
    }

    public void setCLV_Puesto(int CLV_Puesto) {
        this.CLV_Puesto = CLV_Puesto;
    }

    public int getCLV_Area() {
        return CLV_Area;
    }

    public void setCLV_Area(int CLV_Area) {
        this.CLV_Area = CLV_Area;
    }

    public int getCLV_Jefe() {
        return CLV_Jefe;
    }

    public void setCLV_Jefe(int CLV_Jefe) {
        this.CLV_Jefe = CLV_Jefe;
    }

    public String getImg_Foto_Empleado() {
        return Img_Foto_Empleado;
    }

    public void setImg_Foto_Empleado(String Img_Foto_Empleado) {
        this.Img_Foto_Empleado = Img_Foto_Empleado;
    }

}
