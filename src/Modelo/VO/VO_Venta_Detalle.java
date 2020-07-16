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
public class VO_Venta_Detalle {
    
  private int int_ID_Detalle;
  private int CLV_Ventas;
  private int CLV_Platillo;
  private String vch_Nombre_Platillo;
  private String vch_Presentacion_Platillo;
  private int int_Cantidad;
  private float flt_Precio;
  private String Categoria;
  private String date_Fecha_Venta;

    public int getInt_ID_Detalle() {
        return int_ID_Detalle;
    }

    public void setInt_ID_Detalle(int int_ID_Detalle) {
        this.int_ID_Detalle = int_ID_Detalle;
    }

    public int getCLV_Ventas() {
        return CLV_Ventas;
    }

    public void setCLV_Ventas(int CLV_Ventas) {
        this.CLV_Ventas = CLV_Ventas;
    }

    public int getCLV_Platillo() {
        return CLV_Platillo;
    }

    public void setCLV_Platillo(int CLV_Platillo) {
        this.CLV_Platillo = CLV_Platillo;
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

    public int getInt_Cantidad() {
        return int_Cantidad;
    }

    public void setInt_Cantidad(int int_Cantidad) {
        this.int_Cantidad = int_Cantidad;
    }

    public float getFlt_Precio() {
        return flt_Precio;
    }

    public void setFlt_Precio(float flt_Precio) {
        this.flt_Precio = flt_Precio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getDate_Fecha_Venta() {
        return date_Fecha_Venta;
    }

    public void setDate_Fecha_Venta(String date_Fecha_Venta) {
        this.date_Fecha_Venta = date_Fecha_Venta;
    }
  
  
   
}
