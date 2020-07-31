/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import food_fit_inc.Frame_Interno;

/**
 *
 * @author jesus
 */
public class Frm_Catalogo_Categoria_Platillo extends Frame_Interno {

    /**
     * Creates new form Frm_Area
     */
    public Frm_Catalogo_Categoria_Platillo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Btn_Actualizar = new javax.swing.JButton();
        btn_Insertar = new javax.swing.JButton();
        Btn_Salir = new javax.swing.JButton();
        Btn_Eliminar = new javax.swing.JButton();
        Btn_Mostrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_Categoria = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(2000, 2000));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Elementos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N
        jPanel1.setOpaque(false);

        Btn_Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/actualizar.png"))); // NOI18N
        Btn_Actualizar.setText("Actualizar");
        Btn_Actualizar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Actualizar_32px_3.png"))); // NOI18N
        Btn_Actualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Actualizar_32px_2.png"))); // NOI18N

        btn_Insertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/guardar-el-archivo.png"))); // NOI18N
        btn_Insertar.setText("Insertar");
        btn_Insertar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Guardar_32px_3.png"))); // NOI18N
        btn_Insertar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Guardar_32px_2.png"))); // NOI18N

        Btn_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/cerrar-sesion.png"))); // NOI18N
        Btn_Salir.setText("Salir");

        Btn_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/eliminar.png"))); // NOI18N
        Btn_Eliminar.setText("Eliminar");
        Btn_Eliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Eliminar_32px_3.png"))); // NOI18N
        Btn_Eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Eliminar_32px_2.png"))); // NOI18N

        Btn_Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/ojo.png"))); // NOI18N
        Btn_Mostrar.setText("Mostrar");
        Btn_Mostrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Mostrar_32px_3.png"))); // NOI18N
        Btn_Mostrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Botones/btn_Mostrar_32px_2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btn_Insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Btn_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(Btn_Mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(Btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Tbl_Categoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre Categoria", "Imagen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tbl_Categoria);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Btn_Actualizar;
    public javax.swing.JButton Btn_Eliminar;
    public javax.swing.JButton Btn_Mostrar;
    public javax.swing.JButton Btn_Salir;
    public javax.swing.JTable Tbl_Categoria;
    public javax.swing.JButton btn_Insertar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
