/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food_fit_inc;

import javax.swing.JDialog;


/**
 *
 * @author jesus
 */
public class Frame_Dialog extends JDialog {

    private Panel_Image fondo = new Panel_Image();

    public Frame_Dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(fondo);
        
    }
    
     public Frame_Dialog() {
        
        setContentPane(fondo);
        
    }

    public void setIMG(String img) {

        fondo.SetImagen(img);
    }

}
