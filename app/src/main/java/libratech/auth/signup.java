/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package libratech.auth;

import libratech.util.firebaseInit;
import libratech.util.storage;
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
//import static libratech.auth.login.validateGmail;
import libratech.design.RoundedPanel;
import libratech.design.RoundedPanelBorderless;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.loading;
import libratech.models.EmailValidation;
import libratech.models.SchoolInfo;

/**
 *
 * @author DEXTER GWAPO
 */
public class signup extends javax.swing.JFrame {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    private DatabaseReference user = _firebase.getReference("user");
    private RoundedPanel cornerRadius;
    int posX = 0, posY = 0;
    private String localFilePath;
    private String remoteFilePath;
    SchoolInfo info = new SchoolInfo();
    EmailValidation validate = new EmailValidation();

    public signup() {
        ImageIcon icon = new ImageIcon("resources1/logo.png");
        this.setIconImage(icon.getImage());

        ImageScaler scaler = new ImageScaler();

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
            }
        });
        setLocationRelativeTo(null);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        initComponents();
        initFont();
        schoolname.setEditable(true);
        schoolname.setModel(new javax.swing.DefaultComboBoxModel(info.getSchoolName()));
        schoolname.getEditor().getEditorComponent().setBackground(new Color(245, 245, 245));
        schoolid.setEditable(false);
        schoolname.setFocusable(false);

        GlassPanePopup.install(this);
        new firebaseInit().initFirebase();
        scaler.scaleImage(jLabel4, "src\\main\\resources\\logo.png");
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12));
        schoolid.setText(String.format("%d", info.getSchoolID()[schoolname.getSelectedIndex()]));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        schoolidlabel = new javax.swing.JLabel();
        confirmpasslabel = new javax.swing.JLabel();
        jPanel13 = new RoundedPanel(12, new Color(245,245,245));
        confirmpassword = new javax.swing.JPasswordField();
        jPanel14 = new RoundedPanel(12, new Color(245,245,245));
        schoolid = new javax.swing.JTextField();
        schoolnamelabel = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(12, new Color(245,245,245));
        schoolname = new libratech.design.ComboBoxSuggestion();
        jPanel4 = new RoundedPanelBorderless(12, new Color(245, 245, 245));
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        donthave = new javax.swing.JLabel();
        here = new javax.swing.JLabel();
        jPanel8 = new RoundedPanelBorderless(12, new Color(245, 245, 245));
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        filepath = new javax.swing.JLabel();
        myButtonborderless2 = new libratech.design.MyButtonborderless();
        myButtonborder1 = new libratech.design.MyButtonborder();

        jButton1.setText("jButton1");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jPanel2.setBackground(new java.awt.Color(4, 28, 52));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(245, 245, 245));
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
                        .addGap(77, 77, 77)
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel2.add(jPanel9, java.awt.BorderLayout.CENTER);

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(51, 51, 51));
        title.setText("SIGN UP");

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setLayout(null);

        emailaddlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emailaddlabel.setForeground(new java.awt.Color(51, 51, 51));
        emailaddlabel.setText("Email Address");
        jPanel3.add(emailaddlabel);
        emailaddlabel.setBounds(10, 120, 90, 16);

        pwdlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pwdlabel.setForeground(new java.awt.Color(51, 51, 51));
        pwdlabel.setText("Password");
        jPanel3.add(pwdlabel);
        pwdlabel.setBounds(10, 180, 70, 16);

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
                .addContainerGap(14, Short.MAX_VALUE)
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
        jPanel6.setBounds(10, 140, 310, 33);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        pass.setBackground(new java.awt.Color(245, 245, 245));
        pass.setBorder(null);
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
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
        jPanel7.setBounds(10, 200, 310, 33);

        schoolidlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        schoolidlabel.setForeground(new java.awt.Color(51, 51, 51));
        schoolidlabel.setText("School ID Number");
        jPanel3.add(schoolidlabel);
        schoolidlabel.setBounds(10, 60, 130, 16);

        confirmpasslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        confirmpasslabel.setForeground(new java.awt.Color(51, 51, 51));
        confirmpasslabel.setText("Confirm Password");
        jPanel3.add(confirmpasslabel);
        confirmpasslabel.setBounds(10, 240, 140, 16);

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel13.setOpaque(false);

        confirmpassword.setBackground(new java.awt.Color(245, 245, 245));
        confirmpassword.setBorder(null);
        confirmpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmpasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(confirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel13);
        jPanel13.setBounds(10, 260, 310, 33);

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.setOpaque(false);

        schoolid.setBackground(new java.awt.Color(245, 245, 245));
        schoolid.setBorder(null);
        schoolid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolidActionPerformed(evt);
            }
        });
        schoolid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                schoolidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                schoolidKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                schoolidKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(schoolid, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolid, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel14);
        jPanel14.setBounds(10, 80, 310, 33);

        schoolnamelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        schoolnamelabel.setForeground(new java.awt.Color(51, 51, 51));
        schoolnamelabel.setText("School Name");
        jPanel3.add(schoolnamelabel);
        schoolnamelabel.setBounds(10, 0, 190, 20);

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setOpaque(false);

        schoolname.setPreferredSize(new java.awt.Dimension(291, 21));
        schoolname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolnameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(schoolname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel12);
        jPanel12.setBounds(10, 20, 310, 33);

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

        donthave.setText("Already have an account? Click");
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

        jPanel8.setOpaque(false);
        jPanel8.setBackground(new java.awt.Color(250, 250, 250));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(4, 28, 52));
        jLabel6.setText("-");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel6, new java.awt.GridBagConstraints());

        filepath.setForeground(new java.awt.Color(58, 58, 58));
        jScrollPane1.setViewportView(filepath);

        myButtonborderless2.setForeground(new java.awt.Color(250, 250, 250));
        myButtonborderless2.setText("Sign up");
        myButtonborderless2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless2ActionPerformed(evt);
            }
        });

        myButtonborder1.setText("Upload");
        myButtonborder1.setPreferredSize(new java.awt.Dimension(76, 29));
        myButtonborder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborder1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(myButtonborderless2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(myButtonborder1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(myButtonborderless2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(myButtonborder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(874, 558));
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
        login login = new login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_hereMouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void confirmpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmpasswordActionPerformed

    private void schoolidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolidActionPerformed

    private void schoolidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolidKeyReleased
        // TODO add your handling code here:
        String text = schoolid.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            schoolid.setText(text);
        }

        if (text.length() > 15) {
            str = text.substring(0, 24);
            schoolid.setText("");
        }

        if (text.length() == 0) {
            schoolid.setText(str);
            str = "";
        }
    }//GEN-LAST:event_schoolidKeyReleased

    private void schoolidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolidKeyPressed

    private void myButtonborderless2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless2ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new loading());
        String email_address = email.getText();
        String school_name = schoolname.getSelectedItem().toString();
        String school_id = schoolid.getText();
        String file_path = filepath.getText();
        char[] passwordChars = pass.getPassword();
        String password = new String(passwordChars);
        char[] confirmpasswordChars = confirmpassword.getPassword();
        String conpassword = new String(confirmpasswordChars);
        boolean authenticated = false;
        String downloadUrl = "";

        if (file_path.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            GlassPanePopup.closePopupLast();
        } else {
            if (email.getText().equals("") || pass.getPassword().length == 0 || schoolid.getText().equals("") || schoolname.getSelectedItem().toString().equals("") || filepath.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                if (validate.validate(email_address)) {
                    if (conpassword.equals(password)) {
                        storage uploader = new storage(this.localFilePath, this.remoteFilePath);
                        try {
                            downloadUrl = uploader.upload();
                            GlassPanePopup.showPopup(new loading());
                        } catch (IOException ex) {
                            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            auth auth = new auth(email_address, password);
                            authenticated = auth.signUp(school_name, school_id, downloadUrl);

                            if (authenticated) {
                                login login = new login();
                                login.setVisible(true);
                                GlassPanePopup.closePopupLast();
                                this.dispose();
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FirebaseAuthException ex) {
                            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        GlassPanePopup.closePopupLast();
                        JOptionPane.showMessageDialog(null, "Password doesn't match", "Error", ERROR_MESSAGE);
                    }
                } else {
                    GlassPanePopup.closePopupLast();
                    JOptionPane.showMessageDialog(null, "Error: Invalid format", "Error", ERROR_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_myButtonborderless2ActionPerformed

    private void myButtonborder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborder1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.localFilePath = selectedFile.getAbsolutePath();
            this.remoteFilePath = "logo/" + selectedFile.getName();
            filepath.setText(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_myButtonborder1ActionPerformed

    private void schoolidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolidKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_schoolidKeyTyped

    private void schoolnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolnameActionPerformed
        // TODO add your handling code here:
        schoolid.setText(String.format("%d", info.getSchoolID()[schoolname.getSelectedIndex()]));
    }//GEN-LAST:event_schoolnameActionPerformed
    public void ScaleImage() {
        ImageIcon icon = new ImageIcon("resources1\\logo.png");
        Image img = icon.getImage().getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        int iconWidth = scaledIcon.getIconWidth();
        int iconHeight = scaledIcon.getIconHeight();

        int x = (jPanel2.getWidth() - iconWidth) / 2;
        int y = (jPanel2.getHeight() - iconHeight) / 2;

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
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel confirmpasslabel;
    private javax.swing.JPasswordField confirmpassword;
    private javax.swing.JLabel donthave;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailaddlabel;
    private javax.swing.JLabel filepath;
    private javax.swing.JLabel here;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private libratech.design.MyButtonborder myButtonborder1;
    private libratech.design.MyButtonborderless myButtonborderless2;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel pwdlabel;
    private javax.swing.JTextField schoolid;
    private javax.swing.JLabel schoolidlabel;
    private libratech.design.ComboBoxSuggestion schoolname;
    private javax.swing.JLabel schoolnamelabel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    public void initFont() {
        donthave.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        here.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        email.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        pass.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        emailaddlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        pwdlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButtonborderless2.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        title.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        jLabel5.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        jLabel1.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        jLabel3.setFont(new Font("Poppins Regular", Font.PLAIN, 14));
        jLabel6.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        myButtonborder1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        schoolidlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        schoolid.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        schoolnamelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        schoolname.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        filepath.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        schoolid.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        confirmpasslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        confirmpassword.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
    }
}
