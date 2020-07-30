/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food_fit_inc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author jesus
 */
public class Panel_Image extends JPanel {

    private Image imagen;

    public Panel_Image() {
        imagen = null;
    }

    public Panel_Image(String IMG) {
        if (IMG != null) {
            imagen = new ImageIcon(getClass().getResource(IMG)).getImage();

        }
    }

    public void SetImagen(String IMG) {

        if (IMG != null) {
            imagen = new ImageIcon(IMG).getImage();
           

        } else {
            imagen = null;

        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        if (imagen != null) {
            
            g.drawImage(imagen, 0, 0, getWidth(),getHeight(), this);

            this.setOpaque(false);
        } else {
            this.setOpaque(true);

        }

        super.paint(g);

    }
    
    @Override
    public void paintComponent(Graphics g) {

        if (imagen != null) {
            
            g.drawImage(imagen, 0, 0, getWidth(),getHeight(), this);

            this.setOpaque(false);
        } else {
            this.setOpaque(true);

        }

        super.paintComponent(g);

    }
}
