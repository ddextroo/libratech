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
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import java.awt.Container;
import libratech.dashboard.home;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import libratech.models.auth;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import libratech.design.RoundedPanel;
import libratech.design.RoundedPanelBorderless;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import libratech.admin.home_admin;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.loading;
import libratech.models.EmailValidation;
import libratech.models.aes;
import libratech.models.getUID;
import libratech.models.pushValueExisting;

/**
 *
 * @author DEXTER GWAPO
 */
public class login extends javax.swing.JFrame {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    private DatabaseReference user = _firebase.getReference("user");
    private DatabaseReference dbRef;
    private RoundedPanel cornerRadius;
    int posX = 0, posY = 0;
    boolean selected;
    File file = new File("uid.txt");
    File file1 = new File("remember.txt");
    aes aes = new aes();
    boolean authh = false;
    EmailValidation validate = new EmailValidation();

    public login() {

        this.firebaseAuth = FirebaseAuth.getInstance();
        new firebaseInit().initFirebase();
        this.dbRef = FirebaseDatabase.getInstance().getReference("users");
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //sets frame position when mouse dragged			
                setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);

            }
        });
        setLocationRelativeTo(null);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        initComponents();
        GlassPanePopup.install(this);
        initFont();
        new firebaseInit().initFirebase();
        ImageIcon icon1 = new ImageIcon("src\\main\\resources\\logo.png");
        this.setIconImage(icon1.getImage());
        ImageScaler scaler = new ImageScaler();
        scaler.scaleImage(jLabel4, "src\\main\\resources\\logo.png");
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel8 = new RoundedPanelBorderless(12, new Color(41,182,246));
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        emailaddlabel = new javax.swing.JLabel();
        pwdlabel = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(12, new Color(245,245,245));
        email = new javax.swing.JTextField();
        jPanel7 = new RoundedPanel(12, new Color(245,245,245));
        pass = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel4 = new RoundedPanelBorderless(12, new Color(250, 250, 250,0));
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        donthave = new javax.swing.JLabel();
        here = new javax.swing.JLabel();
        jPanel10 = new RoundedPanelBorderless(12, new Color(250, 250, 250,0));
        jLabel7 = new javax.swing.JLabel();
        myButtonborderless1 = new libratech.design.MyButtonborderless();

        jButton1.setText("jButton1");

        jTextField1.setText("jTextField1");

        jPanel8.setOpaque(false);
        jPanel8.setBackground(new java.awt.Color(41, 182, 246));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("_");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel6, new java.awt.GridBagConstraints());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jPanel2.setBackground(new java.awt.Color(4, 28, 52));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setForeground(new java.awt.Color(250, 250, 250));
        jPanel9.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText("LIBRATECH");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 250, 250));
        jLabel3.setText("Library System Solution");

        jLabel4.setPreferredSize(new java.awt.Dimension(230, 230));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel2.add(jPanel9, java.awt.BorderLayout.CENTER);

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(51, 51, 51));
        title.setText("LOGIN");

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setLayout(null);

        emailaddlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emailaddlabel.setForeground(new java.awt.Color(51, 51, 51));
        emailaddlabel.setText("Email Address");
        jPanel3.add(emailaddlabel);
        emailaddlabel.setBounds(10, 10, 90, 16);

        pwdlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pwdlabel.setForeground(new java.awt.Color(51, 51, 51));
        pwdlabel.setText("Password");
        jPanel3.add(pwdlabel);
        pwdlabel.setBounds(10, 70, 70, 16);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        email.setBackground(new java.awt.Color(245, 245, 245));
        email.setBorder(null);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setOpaque(false);

        jPanel3.add(jPanel6);
        jPanel6.setBounds(10, 30, 310, 33);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        pass.setBackground(new java.awt.Color(245, 245, 245));
        pass.setBorder(null);
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setOpaque(false);

        jPanel3.add(jPanel7);
        jPanel7.setBounds(10, 90, 310, 33);

        jCheckBox1.setText("Remember me");
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1);
        jCheckBox1.setBounds(10, 130, 130, 30);

        jPanel4.setOpaque(false);
        jPanel4.setBackground(new java.awt.Color(250, 250, 250));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(4, 28, 52));
        jLabel5.setText("X");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel5, new java.awt.GridBagConstraints());

        donthave.setText("Don't have an account yet? Click");
        jPanel5.add(donthave);

        here.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        here.setForeground(new java.awt.Color(4, 28, 52));
        here.setText("here");
        here.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        here.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hereMouseClicked(evt);
            }
        });
        jPanel5.add(here);

        jPanel10.setOpaque(false);
        jPanel10.setBackground(new java.awt.Color(250, 250, 250));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel7.setBackground(new java.awt.Color(4, 28, 52));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(4, 28, 52));
        jLabel7.setText("-");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel7, new java.awt.GridBagConstraints());

        myButtonborderless1.setForeground(new java.awt.Color(250, 250, 250));
        myButtonborderless1.setText("Login");
        myButtonborderless1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(myButtonborderless1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(280, 280, 280)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(myButtonborderless1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        getContentPane().add(jPanel1);

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

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void hereMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hereMouseClicked
        // TODO add your handling code here:
        signup signup = new signup();
        signup.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_hereMouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            selected = true;
        } else {
            selected = false;
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void myButtonborderless1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless1ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new loading());
        if (email.getText().toString().equals("") || pass.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
            dbRef = FirebaseDatabase.getInstance().getReference("users");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        String loginn = "", key = "", passwd;
                        String email_address = email.getText();
                        char[] passwordChars = pass.getPassword();
                        String password = new String(passwordChars);
                        UserRecord userRecord = firebaseAuth.getUserByEmail(email_address);
                        String uid1 = userRecord.getUid();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            String uiddb = child.child("uid").getValue(String.class);
                            String passdb = child.child("pass").getValue(String.class);
                            System.out.println("UID_DB = " + uiddb + ": UID = " + uid1);
                            System.out.println("PASS_DB = " + passdb + ": PASS = " + password);
                            if (uiddb.equals(uid1) && passdb.equals(password)) {

                                GlassPanePopup.closePopupAll();
                                authh = true;
                                key = uiddb;
                                loginn = "true";
                                System.out.println(key + " " + loginn);

                                if (validate.validate(email_address)) {
                                    if (selected) {
                                        try {
                                            System.out.println("Key: " + key);
                                            key = aes.encryptString(key, aes.getPassword());
                                            try {
                                                FileWriter writer = new FileWriter(file);
                                                FileWriter writer1 = new FileWriter(file1);
                                                writer.write(key);
                                                writer.close();
                                                writer1.write("true");
                                                writer1.close();

                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        } catch (Exception ex) {
                                            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    } else {
                                        try {
                                            key = aes.encryptString(key, aes.getPassword());
                                            try {
                                                FileWriter writer = new FileWriter(file);
                                                writer.write(key);
                                                writer.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        } catch (Exception ex) {
                                            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        if (file1.exists()) {
                                            file1.delete();
                                        }
                                    }
                                    try {
                                        if (uid1.equals("sy01q5KeBdMnX1vpS2Lk86NcCdp1")) {
                                            home_admin home = new home_admin();
                                            String decrypted = aes.decryptString(key, aes.getPassword());
                                            home.updateLabelText(decrypted);
                                            home.setVisible(true);
                                            GlassPanePopup.closePopupLast();
                                            exit();
                                        } else {
                                            home home = new home();
                                            String decrypted = aes.decryptString(key, aes.getPassword());
                                            home.updateLabelText(decrypted);
                                            home.setVisible(true);
                                            GlassPanePopup.closePopupLast();
                                            exit();
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Error: Must valid format", "Error", ERROR_MESSAGE);
                                }
                                break;
                            }
                        }
                    } catch (FirebaseAuthException ex) {
                        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!authh) {
                        JOptionPane.showMessageDialog(null, "Error: Incorrect credentials", "Error", ERROR_MESSAGE);
                        GlassPanePopup.closePopupAll();
                        email.setText("");
                        pass.setText("");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Error: " + databaseError.getMessage());
                }
            });
        }

    }//GEN-LAST:event_myButtonborderless1ActionPerformed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            GlassPanePopup.showPopup(new loading());
            if (email.getText().toString().equals("") || pass.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                dbRef = FirebaseDatabase.getInstance().getReference("users");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            String loginn = "", key = "", passwd;
                            String email_address = email.getText();
                            char[] passwordChars = pass.getPassword();
                            String password = new String(passwordChars);
                            UserRecord userRecord = firebaseAuth.getUserByEmail(email_address);
                            String uid1 = userRecord.getUid();
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                String uiddb = child.child("uid").getValue(String.class);
                                String passdb = child.child("pass").getValue(String.class);
                                System.out.println("UID_DB = " + uiddb + ": UID = " + uid1);
                                System.out.println("PASS_DB = " + passdb + ": PASS = " + password);
                                if (uiddb.equals(uid1) && passdb.equals(password)) {

                                    GlassPanePopup.closePopupAll();
                                    authh = true;
                                    key = uiddb;
                                    loginn = "true";
                                    System.out.println(key + " " + loginn);

                                    if (validate.validate(email_address)) {
                                        if (selected) {
                                            try {
                                                System.out.println("Key: " + key);
                                                key = aes.encryptString(key, aes.getPassword());
                                                try {
                                                    FileWriter writer = new FileWriter(file);
                                                    FileWriter writer1 = new FileWriter(file1);
                                                    writer.write(key);
                                                    writer.close();
                                                    writer1.write("true");
                                                    writer1.close();

                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                            } catch (Exception ex) {
                                                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                                            }

                                        } else {
                                            try {
                                                key = aes.encryptString(key, aes.getPassword());
                                                try {
                                                    FileWriter writer = new FileWriter(file);
                                                    writer.write(key);
                                                    writer.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                            } catch (Exception ex) {
                                                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            if (file1.exists()) {
                                                file1.delete();
                                            }
                                        }
                                        try {
                                            if (uid1.equals("sy01q5KeBdMnX1vpS2Lk86NcCdp1")) {
                                                home_admin home = new home_admin();
                                                String decrypted = aes.decryptString(key, aes.getPassword());
                                                home.updateLabelText(decrypted);
                                                home.setVisible(true);
                                                GlassPanePopup.closePopupLast();
                                                exit();
                                            } else {
                                                home home = new home();
                                                String decrypted = aes.decryptString(key, aes.getPassword());
                                                home.updateLabelText(decrypted);
                                                home.setVisible(true);
                                                GlassPanePopup.closePopupLast();
                                                exit();
                                            }

                                        } catch (Exception ex) {
                                            Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error: Must valid format", "Error", ERROR_MESSAGE);
                                    }
                                    break;
                                }
                            }
                        } catch (FirebaseAuthException ex) {
                            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (!authh) {
                            JOptionPane.showMessageDialog(null, "Error: Incorrect credentials", "Error", ERROR_MESSAGE);
                            GlassPanePopup.closePopupAll();
                            email.setText("");
                            pass.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Error: " + databaseError.getMessage());
                    }
                });
            }

        }
    }//GEN-LAST:event_passKeyPressed

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

    public void exit() {
        this.setVisible(false);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel donthave;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailaddlabel;
    private javax.swing.JLabel here;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    private libratech.design.MyButtonborderless myButtonborderless1;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel pwdlabel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    public void initFont() {
        donthave.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        here.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        email.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        pass.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        emailaddlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        pwdlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButtonborderless1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        title.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        jLabel5.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        jLabel6.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        jLabel7.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        jLabel1.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        jLabel3.setFont(new Font("Poppins Regular", Font.PLAIN, 14));
        jCheckBox1.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
    }

    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
//        // Check if the email is from Gmail
//        if (!email.endsWith("@gmail.com")) {
//            return false;
//        }
        // Check if the email has a valid format
        String regex = "^[\\w-_.+]*[\\w-_.]@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
