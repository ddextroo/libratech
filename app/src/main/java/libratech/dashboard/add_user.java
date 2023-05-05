/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.dashboard;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.filechooser.FileNameExtensionFilter;
import libratech.auth.login;
import libratech.auth.signup;
import static libratech.auth.signup.validateGmail;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.RoundedPanel;
import libratech.design.RoundedPanelBorderless;
import libratech.models.auth;
import libratech.models.pushValue;
import libratech.models.retrieve;
import libratech.util.firebaseInit;
import libratech.util.storage;

/**
 *
 * @author noemi
 */
public class add_user extends javax.swing.JPanel {

    private String localFilePath;
    private String remoteFilePath;
    ImageScaler scaler = new ImageScaler();
    private DatabaseReference databaseReference;
    private HashMap<String, Object> m;
    private pushValue v;
    private retrieve r;

    public add_user() {
        initComponents();
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        new firebaseInit().initFirebase();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        fullnamelabel = new javax.swing.JLabel();
        idnolabel = new javax.swing.JLabel();
        cancellabel = new libratech.design.MyButtonborderless();
        addbooklabel = new libratech.design.MyButtonborderless();
        dateofbirthlabel = new javax.swing.JLabel();
        emaillabel = new javax.swing.JLabel();
        phonelabel = new javax.swing.JLabel();
        addresslabel = new javax.swing.JLabel();
        sexlabel = new javax.swing.JLabel();
        coursegradelabel = new javax.swing.JLabel();
        jPanel15 = new RoundedPanel(12, new Color(250,250,250));
        address = new javax.swing.JTextField();
        jPanel19 = new RoundedPanel(12, new Color(250,250,250));
        email = new javax.swing.JTextField();
        jPanel23 = new RoundedPanel(12, new Color(250,250,250));
        phone3 = new javax.swing.JTextField();
        jPanel24 = new RoundedPanel(12, new Color(250,250,250));
        coursegrade = new javax.swing.JTextField();
        jPanel17 = new RoundedPanel(12, new Color(250,250,250));
        sex = new javax.swing.JTextField();
        jPanel25 = new RoundedPanel(12, new Color(250,250,250));
        fullname = new javax.swing.JTextField();
        paneldate = new RoundedPanel(12, new Color(250,250,250));
        dateofbirth = new javax.swing.JTextField();
        jPanel28 = new RoundedPanel(12, new Color(250,250,250));
        idno = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 791));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 58, 58));
        jLabel2.setText("Add user");

        fullnamelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fullnamelabel.setForeground(new java.awt.Color(51, 51, 51));
        fullnamelabel.setText("Full Name:");

        idnolabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idnolabel.setForeground(new java.awt.Color(51, 51, 51));
        idnolabel.setText("ID no:");

        cancellabel.setForeground(new java.awt.Color(224, 224, 224));
        cancellabel.setText("Cancel");
        cancellabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellabelActionPerformed(evt);
            }
        });

        addbooklabel.setForeground(new java.awt.Color(224, 224, 224));
        addbooklabel.setText("Add book");
        addbooklabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbooklabelActionPerformed(evt);
            }
        });

        dateofbirthlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateofbirthlabel.setForeground(new java.awt.Color(51, 51, 51));
        dateofbirthlabel.setText("Date of birth:");

        emaillabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emaillabel.setForeground(new java.awt.Color(51, 51, 51));
        emaillabel.setText("Email:");

        phonelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        phonelabel.setForeground(new java.awt.Color(51, 51, 51));
        phonelabel.setText("Phone:");

        addresslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addresslabel.setForeground(new java.awt.Color(51, 51, 51));
        addresslabel.setText("Address:");

        sexlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sexlabel.setForeground(new java.awt.Color(51, 51, 51));
        sexlabel.setText("Sex:");

        coursegradelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        coursegradelabel.setForeground(new java.awt.Color(51, 51, 51));
        coursegradelabel.setText("Course/Grade:");

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel15.setOpaque(false);

        address.setBackground(new java.awt.Color(250, 250, 250));
        address.setBorder(null);
        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });
        address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addressKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(0, 0, 0));
        jPanel19.setOpaque(false);
        jPanel19.setPreferredSize(new java.awt.Dimension(374, 36));

        email.setBackground(new java.awt.Color(250, 250, 250));
        email.setBorder(null);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(0, 0, 0));
        jPanel23.setOpaque(false);

        phone3.setBackground(new java.awt.Color(250, 250, 250));
        phone3.setBorder(null);
        phone3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone3ActionPerformed(evt);
            }
        });
        phone3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phone3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phone3KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(phone3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phone3, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(0, 0, 0));
        jPanel24.setOpaque(false);

        coursegrade.setBackground(new java.awt.Color(250, 250, 250));
        coursegrade.setBorder(null);
        coursegrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursegradeActionPerformed(evt);
            }
        });
        coursegrade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                coursegradeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(coursegrade, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coursegrade, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(0, 0, 0));
        jPanel17.setOpaque(false);

        sex.setBackground(new java.awt.Color(250, 250, 250));
        sex.setBorder(null);
        sex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexActionPerformed(evt);
            }
        });
        sex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sexKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sex, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.setOpaque(false);

        fullname.setBackground(new java.awt.Color(250, 250, 250));
        fullname.setBorder(null);
        fullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullnameActionPerformed(evt);
            }
        });
        fullname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fullnameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fullnameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fullname, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        paneldate.setBackground(new java.awt.Color(0, 0, 0));
        paneldate.setOpaque(false);

        dateofbirth.setBackground(new java.awt.Color(250, 250, 250));
        dateofbirth.setBorder(null);
        dateofbirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateofbirthActionPerformed(evt);
            }
        });
        dateofbirth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dateofbirthKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout paneldateLayout = new javax.swing.GroupLayout(paneldate);
        paneldate.setLayout(paneldateLayout);
        paneldateLayout.setHorizontalGroup(
            paneldateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneldateLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(dateofbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        paneldateLayout.setVerticalGroup(
            paneldateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateofbirth, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(0, 0, 0));
        jPanel28.setOpaque(false);

        idno.setBackground(new java.awt.Color(250, 250, 250));
        idno.setBorder(null);
        idno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idnoActionPerformed(evt);
            }
        });
        idno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idnoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(idno, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idno, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emaillabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fullnamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateofbirthlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(337, 337, 337)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(coursegradelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cancellabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addbooklabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(idnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(paneldate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(53, 53, 53))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(47, 47, 47)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(phonelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addresslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sexlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel2)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fullnamelabel)
                            .addComponent(coursegradelabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idnolabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(phonelabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateofbirthlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paneldate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addresslabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emaillabel)
                    .addComponent(sexlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancellabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addbooklabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addbooklabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbooklabelActionPerformed
        // TODO add your handling code here:
        String full_name = fullname.getText();
        String id_no = idno.getText();
        String course_grade = sex.getText();
        String date_of_birth = dateofbirth.getText();
        String sex1 = sex.getText();
        String email1 = email.getText();
        String address1 = address.getText();
        String phone1 = address.getText();

        if (full_name.equals("") || id_no.equals("") || course_grade.equals("") || date_of_birth.equals("") || sex1.equals("") || email1.equals("") || address1.equals("") || phone1.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
//            String getnow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
//            String key = databaseReference.push().getKey();
//            v = new pushValue(databaseReference.push().getKey());
//            m = new HashMap<>();
//            m.put("booktitle", book_title);
//            m.put("bookauthor", book_author);
//            m.put("publisher", publ);
//            m.put("dewey", dew);
//            m.put("genre", genr);
//            m.put("date", date1);
//            m.put("quantity", quan);
//            m.put("shelf", shelff);
//            m.put("deck", deckk);
//            m.put("key", key);
//            m.put("status", "Available");
//            m.put("timestamp", getnow);
//            m.put("cover", downloadUrl);
//            v.pushData("books/inshelf", m);
//            JOptionPane.showMessageDialog(null, "Add book", "Book added Successfully", INFORMATION_MESSAGE);
            GlassPanePopup.closePopupLast();
        }
    }//GEN-LAST:event_addbooklabelActionPerformed

    private void cancellabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellabelActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cancellabelActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void addressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_addressKeyTyped

    private void phone3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone3ActionPerformed

    private void phone3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phone3KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_phone3KeyTyped

    private void coursegradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursegradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coursegradeActionPerformed

    private void coursegradeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coursegradeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_coursegradeKeyTyped

    private void sexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexActionPerformed

    private void sexKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sexKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sexKeyTyped

    private void fullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullnameActionPerformed

    private void fullnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fullnameKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fullnameKeyTyped

    private void dateofbirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateofbirthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateofbirthActionPerformed

    private void dateofbirthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateofbirthKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dateofbirthKeyTyped

    private void idnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idnoActionPerformed

    private void idnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idnoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_idnoKeyTyped

    private void emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_emailKeyTyped

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void fullnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fullnameKeyReleased
        // TODO add your handling code here:
        String text = fullname.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            fullname.setText(text);
        }

        if (text.length() > 50) {
            str = text.substring(0, 50);
            fullname.setText("");
        }

        if (text.length() == 0) {
            fullname.setText(str);
            str = "";
        }
    }//GEN-LAST:event_fullnameKeyReleased

    private void idnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idnoKeyReleased
        // TODO add your handling code here:
        String text = idno.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            idno.setText(text);
        }

        if (text.length() > 50) {
            str = text.substring(0, 50);
            idno.setText("");
        }

        if (text.length() == 0) {
            idno.setText(str);
            str = "";
        }
    }//GEN-LAST:event_idnoKeyReleased

    private void phone3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phone3KeyReleased
        // TODO add your handling code here:
        String text = phone3.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            phone3.setText(text);
        }

        if (text.length() > 11) {
            str = text.substring(0, 11);
            phone3.setText("");
        }

        if (text.length() == 0) {
            phone3.setText(str);
            str = "";
        }
    }//GEN-LAST:event_phone3KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private libratech.design.MyButtonborderless addbooklabel;
    private javax.swing.JTextField address;
    private javax.swing.JLabel addresslabel;
    private libratech.design.MyButtonborderless cancellabel;
    private javax.swing.JTextField coursegrade;
    private javax.swing.JLabel coursegradelabel;
    private javax.swing.JTextField dateofbirth;
    private javax.swing.JLabel dateofbirthlabel;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emaillabel;
    private javax.swing.JTextField fullname;
    private javax.swing.JLabel fullnamelabel;
    private javax.swing.JTextField idno;
    private javax.swing.JLabel idnolabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel paneldate;
    private javax.swing.JTextField phone3;
    private javax.swing.JLabel phonelabel;
    private javax.swing.JTextField sex;
    private javax.swing.JLabel sexlabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        fullnamelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        fullname.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        idnolabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        idno.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        coursegradelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        sex.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        dateofbirthlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        paneldate.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        sexlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        sex.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        emaillabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        email.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        addresslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        address.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        phonelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        address.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        jLabel2.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        cancellabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        addbooklabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));

    }
}
