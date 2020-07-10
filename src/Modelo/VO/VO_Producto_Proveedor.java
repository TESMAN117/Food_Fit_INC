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
public class VO_Producto_Proveedor {

    private int int_ID_Producto_Proveedor;
    private String vch_Nombre_Producto_provee;
    private String vch_Presentacon_Prod_Provee;
    private String vch_Tipo_Existencia;
    private int int_Cantidad_Exis;
    private int CLV_Proveedor;
    private float flt_Costo_Unitario;
    private int CLV_Marca;
    private int CLV_Linea;
    private int CLV_Sucursal_Proveedor;
    private String date_Fecha_Compra;
    private float flt_Total;
    

    public int getInt_ID_Producto_Proveedor() {
        return int_ID_Producto_Proveedor;
    }

    public void setInt_ID_Producto_Proveedor(int int_ID_Producto_Proveedor) {
        this.int_ID_Producto_Proveedor = int_ID_Producto_Proveedor;
    }

    public String getVch_Nombre_Producto_provee() {
        return vch_Nombre_Producto_provee;
    }

    public void setVch_Nombre_Producto_provee(String vch_Nombre_Producto_provee) {
        this.vch_Nombre_Producto_provee = vch_Nombre_Producto_provee;
    }

    public String getVch_Presentacon_Prod_Provee() {
        return vch_Presentacon_Prod_Provee;
    }

    public void setVch_Presentacon_Prod_Provee(String vch_Presentacon_Prod_Provee) {
        this.vch_Presentacon_Prod_Provee = vch_Presentacon_Prod_Provee;
    }

    public String getVch_Tipo_Existencia() {
        return vch_Tipo_Existencia;
    }

    public void setVch_Tipo_Existencia(String vch_Tipo_Existencia) {
        this.vch_Tipo_Existencia = vch_Tipo_Existencia;
    }

    public int getInt_Cantidad_Exis() {
        return int_Cantidad_Exis;
    }

    public void setInt_Cantidad_Exis(int int_Cantidad_Exis) {
        this.int_Cantidad_Exis = int_Cantidad_Exis;
    }

    public int getCLV_Proveedor() {
        return CLV_Proveedor;
    }

    public void setCLV_Proveedor(int CLV_Proveedor) {
        this.CLV_Proveedor = CLV_Proveedor;
    }

    public float getFlt_Costo_Unitario() {
        return flt_Costo_Unitario;
    }

    public void setFlt_Costo_Unitario(float flt_Costo_Unitario) {
        this.flt_Costo_Unitario = flt_Costo_Unitario;
    }

    public int getCLV_Marca() {
        return CLV_Marca;
    }

    public void setCLV_Marca(int CLV_Marca) {
        this.CLV_Marca = CLV_Marca;
    }

    public int getCLV_Linea() {
        return CLV_Linea;
    }

    public void setCLV_Linea(int CLV_Linea) {
        this.CLV_Linea = CLV_Linea;
    }

    public int getCLV_Sucursal_Proveedor() {
        return CLV_Sucursal_Proveedor;
    }

    public void setCLV_Sucursal_Proveedor(int CLV_Sucursal_Proveedor) {
        this.CLV_Sucursal_Proveedor = CLV_Sucursal_Proveedor;
    }

    public String getDate_Fecha_Compra() {
        return date_Fecha_Compra;
    }

    public void setDate_Fecha_Compra(String date_Fecha_Compra) {
        this.date_Fecha_Compra = date_Fecha_Compra;
    }

    public float getFlt_Total() {
        return flt_Total;
    }

    public void setFlt_Total(float flt_Total) {
        this.flt_Total = flt_Total;
    }
    
    
}
