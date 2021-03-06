/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import food_fit_inc.Frame_Dialog;

/**
 *
 * @author jesus
 */
public class Frm_Categoria_Platillo_Edit extends Frame_Dialog {

    /**
     * Creates new form Frm_Categoria_Platillo_Edit
     */
    public Frm_Categoria_Platillo_Edit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Frm_Categoria_Platillo_Edit() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Container = new javax.swing.JPanel();
        txt_Categoria_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_ID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Lbl_IMG_Cat = new javax.swing.JLabel();
        txt_Foto = new javax.swing.JTextField();
        lbl_Titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnl_Container.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        pnl_Container.setMaximumSize(new java.awt.Dimension(400, 300));

        jLabel4.setText("ID");

        jLabel3.setText("Nombre Categoria");

        jLabel5.setText("Imagen");

        btn_Examinar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/anadir-imagen_24px.png"))); // NOI18N
        btn_Examinar.setText("Examinar");

        Lbl_IMG_Cat.setMaximumSize(new java.awt.Dimension(184, 190));

        txt_Foto.setMaximumSize(new java.awt.Dimension(255, 10));

        lbl_Titulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_Titulo.setText("Datos Categoria Platillo");

        btn_Insertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/insertar.png"))); // NOI18N
        btn_Insertar.setText("Insertar");

        btn_Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/cerrar.png"))); // NOI18N

        btn_Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/actualizar_form.png"))); // NOI18N
        btn_Actualizar.setText("Actualizar");

        javax.swing.GroupLayout pnl_ContainerLayout = new javax.swing.GroupLayout(pnl_Container);
        pnl_Container.setLayout(pnl_ContainerLayout);
        pnl_ContainerLayout.setHorizontalGroup(
            pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ContainerLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ContainerLayout.createSequentialGroup()
                        .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140))
                            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_Categoria_nombre))))
                    .addGroup(pnl_ContainerLayout.createSequentialGroup()
                        .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btn_Examinar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Lbl_IMG_Cat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbl_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                    .addComponent(btn_Insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(btn_Actualizar))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_ContainerLayout.setVerticalGroup(
            pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Categoria_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Foto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ContainerLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btn_Examinar))
                    .addGroup(pnl_ContainerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Lbl_IMG_Cat, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_Actualizar)
                    .addComponent(btn_Insertar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_Container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_Container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Lbl_IMG_Cat;
    public static final javax.swing.JButton btn_Actualizar = new javax.swing.JButton();
    public static final javax.swing.JButton btn_Cancelar = new javax.swing.JButton();
    public static final javax.swing.JButton btn_Examinar = new javax.swing.JButton();
    public static final javax.swing.JButton btn_Insertar = new javax.swing.JButton();
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel lbl_ID;
    public javax.swing.JLabel lbl_Titulo;
    public javax.swing.JPanel pnl_Container;
    public javax.swing.JTextField txt_Categoria_nombre;
    public javax.swing.JTextField txt_Foto;
    // End of variables declaration//GEN-END:variables
}
