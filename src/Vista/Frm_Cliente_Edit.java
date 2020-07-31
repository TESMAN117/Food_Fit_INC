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
public class Frm_Cliente_Edit extends Frame_Dialog {

    /**
     * Creates new form Frm_Cliente_Edit
     * @param parent
     * @param modal
     */
    public Frm_Cliente_Edit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public Frm_Cliente_Edit() {
       
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Apellidos = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        lbl_ID = new javax.swing.JLabel();
        LBL_Perso = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Lbl_Titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnl_Container.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        pnl_Container.setOpaque(false);

        btn_Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/actualizar_form.png"))); // NOI18N
        btn_Actualizar.setText("Actualizar");

        btn_Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/cerrar.png"))); // NOI18N

        btn_Insertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/insertar.png"))); // NOI18N
        btn_Insertar.setText("Insertar");

        txt_Apellidos.setColumns(20);
        txt_Apellidos.setRows(5);
        jScrollPane1.setViewportView(txt_Apellidos);

        jLabel4.setText("Apellidos");

        jLabel3.setText("Nombre");

        lbl_ID.setText("ID");

        LBL_Perso.setText("pér");

        jLabel2.setText("ID");

        Lbl_Titulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Lbl_Titulo.setText("Lbl_Accion");

        btn_persona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/agregar.png"))); // NOI18N

        javax.swing.GroupLayout pnl_ContainerLayout = new javax.swing.GroupLayout(pnl_Container);
        pnl_Container.setLayout(pnl_ContainerLayout);
        pnl_ContainerLayout.setHorizontalGroup(
            pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_ContainerLayout.createSequentialGroup()
                        .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_ContainerLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_ContainerLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Lbl_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LBL_Perso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(73, 73, 73))
                    .addGroup(pnl_ContainerLayout.createSequentialGroup()
                        .addComponent(btn_Insertar)
                        .addGap(26, 26, 26)
                        .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Actualizar)
                        .addContainerGap())))
        );
        pnl_ContainerLayout.setVerticalGroup(
            pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lbl_Titulo)
                .addGap(30, 30, 30)
                .addComponent(LBL_Perso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ContainerLayout.createSequentialGroup()
                        .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel4))
                            .addGroup(pnl_ContainerLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btn_persona)
                        .addGap(86, 86, 86)))
                .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Actualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Insertar)
                        .addComponent(btn_Cancelar)))
                .addContainerGap())
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
                .addComponent(pnl_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel LBL_Perso;
    public javax.swing.JLabel Lbl_Titulo;
    public static final javax.swing.JButton btn_Actualizar = new javax.swing.JButton();
    public static final javax.swing.JButton btn_Cancelar = new javax.swing.JButton();
    public static final javax.swing.JButton btn_Insertar = new javax.swing.JButton();
    public static final javax.swing.JButton btn_persona = new javax.swing.JButton();
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbl_ID;
    public javax.swing.JPanel pnl_Container;
    public javax.swing.JTextArea txt_Apellidos;
    public javax.swing.JTextField txt_Nombre;
    // End of variables declaration//GEN-END:variables
}
