/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAO_Login;
import Modelo.VO.VO_Login;
import Vista.MDI_Food;
import Vista.frm_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class Ctrl_Login implements ActionListener {

    frm_Login Vista_login;
    DAO_Login Modelo_login;

    MDI_Food MDI;
    Ctrl_MDI ctrl_mdi;
    VO_Login vo_login;

    private String Tipo_user, nick, contra;

    public Ctrl_Login(frm_Login Vista_login, DAO_Login Modelo_login,VO_Login vo_login) {
        this.Vista_login = Vista_login;
        this.Modelo_login = Modelo_login;
        this.vo_login = vo_login;

        this.Vista_login.Btn_Start.addActionListener(this);
        this.Vista_login.btn_Exit.addActionListener(this);
        this.Vista_login.txt_pass.addActionListener(this);
        this.Vista_login.txt_User.addActionListener(this);
    }

    public void Logeo() {

        String Pass = Vista_login.txt_pass.getText();
        String User = Vista_login.txt_User.getText();
        
        vo_login.setPass(Pass);
        vo_login.setUser(User);
        if ("".equals(User) || "".equals(Pass)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {
                ResultSet rs = Modelo_login.Iniciar_sesion(vo_login);

                if (rs.next()) {

                    Tipo_user = rs.getString("vch_Tipo_Usuario");
                    nick = rs.getString("vch_Usuario");
                    contra = rs.getString("vch_Password");
                    JOptionPane.showMessageDialog(null, "Bienvenido " + nick);
                    MDI = new MDI_Food();
                    MDI.setTitle("Foot Fit INC");
                    MDI.setExtendedState(this.MDI.MAXIMIZED_BOTH);
                    ctrl_mdi = new Ctrl_MDI(MDI);
                    MDI.setVisible(true);
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Windows".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(MDI_Food.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    Vista_login.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "No Existe nungun usuaer");
                }

            } catch (Exception e) {
                System.out.println("errorLOG: " + e);
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Vista_login.Btn_Start) {
            Logeo();
        }

        if (e.getSource() == Vista_login.btn_Exit) {
            System.exit(0);
            JOptionPane.showMessageDialog(null, "Datos: ");
        }

    }

    public static void ConfiguracionMdi() {

    }

}
