/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.dashboard;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;

/**
 *
 * @author Carocoy
 */
public class done_import extends javax.swing.JPanel {

    ImageScaler scaler = new ImageScaler();

    public done_import() {
        initComponents();
        setOpaque(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
        txt.setEditable(false);
        initFont();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(graphics);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmlabel = new javax.swing.JLabel();
        exit = new libratech.design.MyButtonborderless();
        txt = new javax.swing.JTextPane();

        confirmlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmlabel.setText("Book Import Successful");

        exit.setForeground(new java.awt.Color(224, 224, 224));
        exit.setText("Go to Book section");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        txt.setText("The books you have imported are now available for everyone to explore and enjoy. Your contribution plays a significant role in fostering a vibrant and diverse literary community within Libratech.");
        txt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(confirmlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        home home = new home();
        home.setVisible(true);
        home.jPanel15.setBackground(Color.decode("#0E2C4A"));
        home.jPanel10.setBackground(Color.decode("#041C34"));
        home.jPanel18.setBackground(Color.decode("#041C34"));
        home.jPanel20.setBackground(Color.decode("#041C34"));
        scaler.scaleImage(home.jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(home.jLabel15, "src\\main\\resources\\book-fill.png");
        scaler.scaleImage(home.jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(home.jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) home.jPanel3.getLayout();
        cardLayout.show(home.jPanel3, "book");
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(done_import.this);
        frame.dispose();
    }//GEN-LAST:event_exitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel confirmlabel;
    private libratech.design.MyButtonborderless exit;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        confirmlabel.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        txt.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        exit.setFont(new Font("Poppins Regular", Font.BOLD, 12));
    }
}