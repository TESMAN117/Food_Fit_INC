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
public class VO_Corte {

    private int int_ID_Corte;
    private Float flt_Efectivo;
    private Float flt_Tarjeta;
    private Float flt_Cupones;
    private String date_Fecha_Corte;
    private int CLV_Sucursal;

    public int getInt_ID_Corte() {
        return int_ID_Corte;
    }

    public void setInt_ID_Corte(int int_ID_Corte) {
        this.int_ID_Corte = int_ID_Corte;
    }

    public Float getFlt_Efectivo() {
        return flt_Efectivo;
    }

    public void setFlt_Efectivo(Float flt_Efectivo) {
        this.flt_Efectivo = flt_Efectivo;
    }

    public Float getFlt_Tarjeta() {
        return flt_Tarjeta;
    }

    public void setFlt_Tarjeta(Float flt_Tarjeta) {
        this.flt_Tarjeta = flt_Tarjeta;
    }

    public Float getFlt_Cupones() {
        return flt_Cupones;
    }

    public void setFlt_Cupones(Float flt_Cupones) {
        this.flt_Cupones = flt_Cupones;
    }

    public String getDate_Fecha_Corte() {
        return date_Fecha_Corte;
    }

    public void setDate_Fecha_Corte(String date_Fecha_Corte) {
        this.date_Fecha_Corte = date_Fecha_Corte;
    }

    public int getCLV_Sucursal() {
        return CLV_Sucursal;
    }

    public void setCLV_Sucursal(int CLV_Sucursal) {
        this.CLV_Sucursal = CLV_Sucursal;
    }

}
