/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food_fit_inc;

import javax.swing.JInternalFrame;

/**
 *
 * @author jesus
 */
public class Frame_Interno extends JInternalFrame {

    private Panel_Image fondo = new Panel_Image();

    public Frame_Interno() {
        setContentPane(fondo);
        
    }

    public void setIMG(String img) {

        fondo.SetImagen(img);
    }

}
