/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VO_Sucursal;
import com.google.gson.Gson;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import javax.swing.JComboBox;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jesus
 */
public class DAO_Sucursal {

    Cls_Conexion conec = new Cls_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Sucursal() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_sucursal;";

        try {
            Statement stm;
            stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery(consulta);

            return rs;

        } catch (Exception e) {
            System.out.println("Error al hacer la consulta");

        }
        return null;
    }

    public int insertaSucursal(VO_Sucursal x) throws RemoteException {
        int r = 0;
        String Insert = "INSERT INTO tbl_sucursal (vch_Direccion,vch_Nombre,vch_Municipio,vch_Estado)  VALUES ('" + x.getDIRECCION() + "','" + x.getNOMBRE() + "','" + x.getMUNICIPIO() + "','" + x.getESTADO() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int ActualizaSucursal(VO_Sucursal x) throws RemoteException {
        int r = 0;
        String Actualizar = "UPDATE tbl_sucursal SET vch_Direccion = '" + x.getDIRECCION() + "', vch_Nombre = '" + x.getNOMBRE() + "',vch_Municipio = '" + x.getMUNICIPIO() + "', vch_Estado = '" + x.getESTADO() + "'WHERE int_ID_Sucursal = " + x.getID_SUCURSAL() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int eliminaSucursal(VO_Sucursal x) throws RemoteException {
        int r = 0;
        String Delete = "DELETE FROM tbl_sucursal WHERE int_ID_Sucursal =" + x.getID_SUCURSAL() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Delete);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public void carga_combo(JComboBox Combo_Estado) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src\\food_fit_inc\\estados.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String Estado = (String) jsonObject.get("nombre");
            System.out.println("nombre:" + Estado);

            Combo_Estado.addItem("Seleccionar Estado");

            // recorrer arreglo
            JSONArray leng = (JSONArray) jsonObject.get("nombre");
            System.out.println("nombre:");
            Iterator iterator = leng.iterator();
            while (iterator.hasNext()) {
                Combo_Estado.addItem(Estado);
            }

        } catch (Exception ex) {
            System.err.println("Error: " + ex.toString());
        } finally {

        }

    }

}
