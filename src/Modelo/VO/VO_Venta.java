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
public class VO_Venta {

    private int int_ID_Venta;
    private String vch_Numero_Venta;
    private int CLV_Cliente;
    private int CLV_Empleado;
    private float flt_Total_Venta;
    private String date_Fecha_Venta;
    private int CLV_Sucursal;
    private int CLV_Tipo_Pago;

    public int getInt_ID_Venta() {
        return int_ID_Venta;
    }

    public void setInt_ID_Venta(int int_ID_Venta) {
        this.int_ID_Venta = int_ID_Venta;
    }

    public String getVch_Numero_Venta() {
        return vch_Numero_Venta;
    }

    public void setVch_Numero_Venta(String vch_Numero_Venta) {
        this.vch_Numero_Venta = vch_Numero_Venta;
    }

    public int getCLV_Cliente() {
        return CLV_Cliente;
    }

    public void setCLV_Cliente(int CLV_Cliente) {
        this.CLV_Cliente = CLV_Cliente;
    }

    public int getCLV_Empleado() {
        return CLV_Empleado;
    }

    public void setCLV_Empleado(int CLV_Empleado) {
        this.CLV_Empleado = CLV_Empleado;
    }

    public float getFlt_Total_Venta() {
        return flt_Total_Venta;
    }

    public void setFlt_Total_Venta(float flt_Total_Venta) {
        this.flt_Total_Venta = flt_Total_Venta;
    }

    public String getDate_Fecha_Venta() {
        return date_Fecha_Venta;
    }

    public void setDate_Fecha_Venta(String date_Fecha_Venta) {
        this.date_Fecha_Venta = date_Fecha_Venta;
    }

    public int getCLV_Sucursal() {
        return CLV_Sucursal;
    }

    public void setCLV_Sucursal(int CLV_Sucursal) {
        this.CLV_Sucursal = CLV_Sucursal;
    }

    public int getCLV_Tipo_Pago() {
        return CLV_Tipo_Pago;
    }

    public void setCLV_Tipo_Pago(int CLV_Tipo_Pago) {
        this.CLV_Tipo_Pago = CLV_Tipo_Pago;
    }

}
