/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Sinlgeton.Singleton_Cls_Conexion;
import Modelo.VO.VO_Categoria_Platillo;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DAO_Categoria_Platillo {

    private Connection cnn = Singleton_Cls_Conexion.conexion();
    
    public ResultSet Consulta_Categoria() {
        
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbl_categoria_platillo ORDER BY int_ID_Categoria_Platillo";

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

    public int inserta_Categoria(VO_Categoria_Platillo x) {
        int r = 0;
        String Insert = "INSERT INTO tbl_categoria_platillo  (vch_Nombre_Categoria_Platillo,Img_categoria_platillo)VALUES ('" + x.getNombre_Categoria() + "','" + x.getIMG_Categoria() + "');";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Categoria(VO_Categoria_Platillo x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_categoria_platillo SET  vch_Nombre_Categoria_Platillo = '" + x.getNombre_Categoria() + "', Img_categoria_platillo = '" + x.getIMG_Categoria() + "' WHERE int_ID_Categoria_Platillo = " + x.getID_CATEGORIA() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualiza_Categoria_SIMG(VO_Categoria_Platillo x) {
        int r = 0;
        String Actualizar = "UPDATE tbl_categoria_platillo SET  vch_Nombre_Categoria_Platillo = '" + x.getNombre_Categoria() + "' WHERE int_ID_Categoria_Platillo = " + x.getID_CATEGORIA() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int elimina_Categoria(VO_Categoria_Platillo x) {
        int r = 0;
        String Delete = "DELETE FROM tbl_categoria_platillo WHERE int_ID_Categoria_Platillo = " + x.getID_CATEGORIA() + ";";

        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Delete);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

}
