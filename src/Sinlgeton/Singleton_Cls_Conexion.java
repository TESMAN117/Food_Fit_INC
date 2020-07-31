/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinlgeton;

import Controlador.Controlador;
import Controlador.Ctrl_Login;
import Vista.Frm_Datos_del_Servidor;
import Vista.frm_Login;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class Singleton_Cls_Conexion {

    private static Connection conect = null;

    private static Boolean state = false;

    public Boolean getState() {
        return state;
    }

    JFrame DS;

    private static String cade = "";
    private static String usuario = "";
    private static String passwor = "";

    /* public Connection conexion(JFrame formulario) {
        this.DS = formulario;

        leerArchivo();
        return conect;
    }*/
    public static Connection conexion() {

        if (conect == null) {
             leerArchivo();
        }

        return conect;
    }

    public void crearArchivo(String usu, String con, String basedatos) {
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("src\\servidor.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);

            //escribe los datos en el archivo
            bfwriter.write("jdbc:mysql://localhost/" + basedatos + "," + usu + "," + con);

            //cierra el buffer intermedio
            bfwriter.close();
            System.out.println("Archivo creado satisfactoriamente..");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //crea el archivo en disco, retorna la lista de estudiantes
    public static boolean leerArchivo() {
        // crea el flujo para leer desde el archivo
        File file = new File("src\\servidor.txt");
        if (file.exists() == true) {
            Scanner scanner;
            try {
                //se pasa el flujo al objeto scanner
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    // el objeto scanner lee linea a linea desde el archivo
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    //se usa una expresión regular
                    //que valida que antes o despues de una coma (,) exista cualquier cosa
                    //parte la cadena recibida cada vez que encuentre una coma				
                    delimitar.useDelimiter("\\s*,\\s*");
                    cade = delimitar.next();
                    usuario = delimitar.next();
                    passwor = delimitar.next();

                }
                //se cierra el ojeto scanner
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                Class.forName("org.gjt.mm.mysql.Driver");
                conect = DriverManager.getConnection(cade, usuario, passwor);

                state = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error9: " + e);
                System.out.println("err: " + e);

            }

        } else {
            JOptionPane.showMessageDialog(null, "No se localizo el Archivo con los datos del servidor..");

        }
        return state;

    }

}
