/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package libratech.auth;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import libratech.util.firebaseInit;
import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import java.awt.Font;
import libratech.models.auth;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author DEXTER GWAPO
 */
public class login extends javax.swing.JFrame {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    private DatabaseReference user = _firebase.getReference("user");
    private Font customFont;


    public login() {
        customFont = new Font("poppinsr", Font.BOLD, 12);
        setLocationRelativeTo(null);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        initComponents();
        ScaleImage();
        initFont();
        new firebaseInit().initFirebase();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        emailaddlabel = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        pwdlabel = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        forgot = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        donthave = new javax.swing.JLabel();
        here = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 165, 0));
        jPanel2.setLayout(null);
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 120, 380, 320);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 440, 560);

        jButton2.setBackground(new java.awt.Color(255, 165, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Login");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(510, 400, 320, 30);

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(51, 51, 51));
        title.setText("LOGIN");
        jPanel1.add(title);
        title.setBounds(620, 90, 90, 30);

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setLayout(null);

        emailaddlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emailaddlabel.setForeground(new java.awt.Color(51, 51, 51));
        emailaddlabel.setText("Email Address");
        jPanel3.add(emailaddlabel);
        emailaddlabel.setBounds(10, 10, 90, 16);
        jPanel3.add(email);
        email.setBounds(10, 30, 310, 30);

        pwdlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pwdlabel.setForeground(new java.awt.Color(51, 51, 51));
        pwdlabel.setText("Password");
        jPanel3.add(pwdlabel);
        pwdlabel.setBounds(10, 70, 70, 16);

        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        jPanel3.add(pass);
        pass.setBounds(10, 90, 310, 30);

        forgot.setText("Forgot Password?");
        jPanel3.add(forgot);
        forgot.setBounds(230, 120, 100, 20);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(500, 170, 330, 140);

        jPanel4.setBackground(new java.awt.Color(255, 165, 0));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("X");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel5, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel4);
        jPanel4.setBounds(820, 0, 50, 20);

        donthave.setText("Don't have an account yet? Click");
        jPanel1.add(donthave);
        donthave.setBounds(510, 360, 180, 16);

        here.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        here.setForeground(new java.awt.Color(255, 165, 0));
        here.setText("here");
        jPanel1.add(here);
        here.setBounds(688, 360, 37, 16);

        jButton3.setBackground(new java.awt.Color(255, 165, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Sign up");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(510, 330, 320, 30);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(872, 558));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String email_address = email.getText();
        char[] passwordChars = pass.getPassword();
        String password = new String(passwordChars);

        if (email.getText().toString().equals("") || pass.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
            try {
                auth auth = new auth(email_address, password);
                auth.signUp();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FirebaseAuthException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        boolean loginn = false;
        String email_address = email.getText();
        char[] passwordChars = pass.getPassword();
        String password = new String(passwordChars);

        if (email.getText().toString().equals("") || pass.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
            try {
                auth auth = new auth(email_address, password);
                loginn = auth.login();
            } catch (FileNotFoundException ex) {
                loginn = false;
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FirebaseAuthException ex) {
                loginn = false;
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (loginn) {

        }
    }//GEN-LAST:event_jButton3ActionPerformed
    public void ScaleImage() {
        ImageIcon icon = new ImageIcon("resources1\\undraw_travel_booking_re_6umu.png");
        Image img = icon.getImage().getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        int iconWidth = scaledIcon.getIconWidth();
        int iconHeight = scaledIcon.getIconHeight();

// calculate the x and y position to center the image within the panel
        int x = (jPanel2.getWidth() - iconWidth) / 2;
        int y = (jPanel2.getHeight() - iconHeight) / 2;

// create a new JLabel to hold the icon and set its position within the panel
        JLabel label = new JLabel();
        label.setIcon(scaledIcon);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(x, y, iconWidth, iconHeight);

// add the label to the panel
        jPanel2.add(label);
    }

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel donthave;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailaddlabel;
    private javax.swing.JLabel forgot;
    private javax.swing.JLabel here;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel pwdlabel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
    private void setCustomFont(JLabel label) {
        label.setFont(customFont);
    }
    public void initFont() {
        setCustomFont(title);
        setCustomFont(emailaddlabel);
        setCustomFont(donthave);
    }
}
