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
public class VO_Platillo {

    private int int_ID_Platillo;
    private String vch_Nombre_Platillo;
    private String vch_Presentacion_Platillo;
    private float flt_Precio;
    private int CLV_Categoria;
    private int CLV_Sucursal_Platillo;
    private String vch_IMG_Platillo;

    public int getInt_ID_Platillo() {
        return int_ID_Platillo;
    }

    public void setInt_ID_Platillo(int int_ID_Platillo) {
        this.int_ID_Platillo = int_ID_Platillo;
    }

    public String getVch_Nombre_Platillo() {
        return vch_Nombre_Platillo;
    }

    public void setVch_Nombre_Platillo(String vch_Nombre_Platillo) {
        this.vch_Nombre_Platillo = vch_Nombre_Platillo;
    }

    public String getVch_Presentacion_Platillo() {
        return vch_Presentacion_Platillo;
    }

    public void setVch_Presentacion_Platillo(String vch_Presentacion_Platillo) {
        this.vch_Presentacion_Platillo = vch_Presentacion_Platillo;
    }

    public float getFlt_Precio() {
        return flt_Precio;
    }

    public void setFlt_Precio(float flt_Precio) {
        this.flt_Precio = flt_Precio;
    }

    public int getCLV_Categoria() {
        return CLV_Categoria;
    }

    public void setCLV_Categoria(int CLV_Categoria) {
        this.CLV_Categoria = CLV_Categoria;
    }

    public int getCLV_Sucursal_Platillo() {
        return CLV_Sucursal_Platillo;
    }

    public void setCLV_Sucursal_Platillo(int CLV_Sucursal_Platillo) {
        this.CLV_Sucursal_Platillo = CLV_Sucursal_Platillo;
    }

    public String getVch_IMG_Platillo() {
        return vch_IMG_Platillo;
    }

    public void setVch_IMG_Platillo(String vch_IMG_Platillo) {
        this.vch_IMG_Platillo = vch_IMG_Platillo;
    }

}
