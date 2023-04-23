package libratech.dashboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import libratech.auth.login;
import libratech.auth.splash;
import static libratech.auth.splash.isInternetConnected;
import libratech.design.ImageScaler;
import libratech.design.ImageAvatar;
import libratech.design.RoundedPanel;
import libratech.design.RoundedPanelBorderless;
import libratech.design.iconColor;
import libratech.models.Dashboard.*;
import libratech.util.firebaseInit;

/**
 *
 * @author HB-user
 */
public class home extends javax.swing.JFrame {

    private retrieveInfo listener = new retrieveInfo();
    Timer t;
    long startTime = System.currentTimeMillis();

    public home() {
        initComponents();
        new firebaseInit().initFirebase();
        ImageScaler scaler = new ImageScaler();
        scaler.scaleImage(jLabel3, "src\\main\\resources\\logo.png");
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-fill.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initFont();
        Timer timer = new Timer(1000, e -> {
            String uid = uidkey.getText();
            listener.addChildListener(uid, (String school_name, String school_id, String url) -> {
                try {
                    school_n.setText(school_name);
                    idnum.setText(school_id);
                    URL url1 = new URL(url);
                    BufferedImage image = ImageIO.read(url1);
                    
                    ImageIcon icon = new ImageIcon(image);
                    Image img = icon.getImage().getScaledInstance(imageAvatar1.getWidth(), imageAvatar1.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(img);
                    imageAvatar1.setImage(scaledIcon);
                } catch (IOException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
        timer.setRepeats(false);
        timer.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new RoundedPanelBorderless(12, new Color(0, 4, 36, 0));
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new RoundedPanelBorderless(12, new java.awt.Color(63,106,149,44));
        jPanel14 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel15 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        jPanel16 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel18 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        jPanel19 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel20 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        jPanel21 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        uidkey = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        imageAvatar1 = new libratech.design.ImageAvatar();
        school_n = new javax.swing.JLabel();
        idnum = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(4, 28, 52));

        jPanel6.setBackground(new java.awt.Color(4, 28, 52));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("X");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel5, new java.awt.GridBagConstraints());

        jPanel6.setOpaque(false);
        jPanel7.setBackground(new java.awt.Color(4, 28, 52));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("-");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel6, new java.awt.GridBagConstraints());

        jLabel3.setPreferredSize(new java.awt.Dimension(50, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(250, 250, 250));
        jLabel8.setText("LIBRATECH");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(250, 250, 250));
        jLabel9.setText("Library System Solution");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(782, 782, 782)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel9)))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(4, 28, 52));

        jPanel10.setBackground(new java.awt.Color(63,106,149, 44));
        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(50, 50));

        jPanel14.setBackground(new java.awt.Color(41,182,246, 65));
        jPanel14.setOpaque(false);
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel14.add(filler8);

        jLabel10.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel14.add(jLabel10);

        jLabel11.setText("       ");
        jPanel14.add(jLabel11);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(250, 250, 250));
        jLabel14.setText("Dashboard");
        jPanel14.add(jLabel14);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel15.setOpaque(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(50, 50));

        jPanel16.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel16.setOpaque(false);
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel16.add(filler9);

        jLabel15.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel16.add(jLabel15);

        jLabel16.setText("       ");
        jPanel16.add(jLabel16);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(224, 224, 224));
        jLabel17.setText("Books");
        jPanel16.add(jLabel17);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel18.setOpaque(false);
        jPanel18.setPreferredSize(new java.awt.Dimension(50, 50));

        jPanel19.setBackground(new java.awt.Color(41,182,246, 65));
        jPanel19.setOpaque(false);
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel19.add(filler10);

        jLabel18.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel19.add(jLabel18);

        jLabel19.setText("       ");
        jPanel19.add(jLabel19);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(224, 224, 224));
        jLabel20.setText("User");
        jPanel19.add(jLabel20);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel20.setOpaque(false);
        jPanel20.setPreferredSize(new java.awt.Dimension(50, 50));

        jPanel21.setBackground(new java.awt.Color(41,182,246, 65));
        jPanel21.setOpaque(false);
        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel21.add(filler11);

        jLabel21.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel21.add(jLabel21);

        jLabel22.setText("       ");
        jPanel21.add(jLabel22);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(224, 224, 224));
        jLabel23.setText("Settings");
        jPanel21.add(jLabel23);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        uidkey.setText("weqwewq");

        jPanel5.setBackground(new java.awt.Color(4, 28, 52));

        school_n.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        school_n.setForeground(new java.awt.Color(250, 250, 250));
        school_n.setText("School Name");

        idnum.setBackground(new java.awt.Color(250, 250, 250));
        idnum.setForeground(new java.awt.Color(250, 250, 250));
        idnum.setText("ID Number");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(school_n)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(idnum)
                        .addGap(61, 61, 61))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(school_n)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idnum)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(uidkey)
                .addContainerGap(61, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 593, Short.MAX_VALUE)
                .addComponent(uidkey)
                .addGap(166, 166, 166)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setLayout(new java.awt.CardLayout());
        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        // TODO add your handling code here:
        //jPanel15 = new RoundedPanelBorderless(12, new java.awt.Color(63, 106, 149, 44));
        jPanel6.setBackground(Color.RED);
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        // TODO add your handling code here:
        jPanel6.setBackground(Color.RED);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        // TODO add your handling code here:
        jPanel6.setBackground(Color.GREEN);
    }//GEN-LAST:event_jPanel6MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel idnum;
    private libratech.design.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel school_n;
    public javax.swing.JLabel uidkey;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        //try {
        jLabel14.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel8.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        jLabel9.setFont(new Font("Poppins Regular", Font.PLAIN, 14));
        jLabel5.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        jLabel6.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        school_n.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        idnum.setFont(new Font("Poppins Regular", Font.BOLD, 12));

//            String s_id = listener.addChildListener(jLabel2.getText())[1];
//            
//            idnum.setText(s_id);
//            
//            String url = listener.addChildListener(jLabel2.getText())[2];
//            URL url1 = new URL(url);
//            BufferedImage image = ImageIO.read(url1);
//            
//            school_name.setText(url);
//            ImageIcon icon = new ImageIcon(image);
//            Image img = icon.getImage().getScaledInstance(imageAvatar1.getWidth(), imageAvatar1.getHeight(), Image.SCALE_SMOOTH);
//            ImageIcon scaledIcon = new ImageIcon(img);
//            imageAvatar1.setImage(scaledIcon);
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
