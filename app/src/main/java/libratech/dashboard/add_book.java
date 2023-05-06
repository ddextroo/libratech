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
 * @author HBUSER
 */
public class add_book extends javax.swing.JPanel {

    private String localFilePath;
    private String remoteFilePath;
    ImageScaler scaler = new ImageScaler();
    private DatabaseReference databaseReference;
    private HashMap<String, Object> m;
    private pushValue v;
    private retrieve r;

    public add_book() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        initComponents();
        initFont();
        new firebaseInit().initFirebase();

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
        jSeparator1 = new javax.swing.JSeparator();
        coverlabel = new javax.swing.JLabel();
        allowedtype = new javax.swing.JLabel();
        booktitlelabel = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(12, new Color(250,250,250));
        booktitle = new javax.swing.JTextField();
        photoCover1 = new libratech.design.PhotoCover();
        authorlabel = new javax.swing.JLabel();
        jPanel7 = new RoundedPanel(12, new Color(250,250,250));
        author = new javax.swing.JTextField();
        jPanel8 = new RoundedPanel(12, new Color(250,250,250));
        publisher = new javax.swing.JTextField();
        publisherlabel = new javax.swing.JLabel();
        genrelabel = new javax.swing.JLabel();
        jPanel9 = new RoundedPanel(12, new Color(250,250,250));
        genre = new javax.swing.JTextField();
        jPanel10 = new RoundedPanel(12, new Color(250,250,250));
        dewey = new javax.swing.JTextField();
        deweylabel = new javax.swing.JLabel();
        datelabel = new javax.swing.JLabel();
        jPanel11 = new RoundedPanel(12, new Color(250,250,250));
        date = new javax.swing.JTextField();
        quantitylabel = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(12, new Color(250,250,250));
        quantity = new javax.swing.JTextField();
        decklabel = new javax.swing.JLabel();
        jPanel13 = new RoundedPanel(12, new Color(250,250,250));
        deck = new javax.swing.JTextField();
        shelflabel = new javax.swing.JLabel();
        jPanel14 = new RoundedPanel(12, new Color(250,250,250));
        shelf = new javax.swing.JTextField();
        myButtonborderless1 = new libratech.design.MyButtonborderless();
        myButtonborderless2 = new libratech.design.MyButtonborderless();

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 58, 58));
        jLabel2.setText("Add book");

        coverlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        coverlabel.setForeground(new java.awt.Color(51, 51, 51));
        coverlabel.setText("Cover");

        allowedtype.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        allowedtype.setForeground(new java.awt.Color(158, 158, 158));
        allowedtype.setText("Allowed file types: png, jpeg, or jpg");

        booktitlelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        booktitlelabel.setForeground(new java.awt.Color(51, 51, 51));
        booktitlelabel.setText("Book Title");

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setOpaque(false);

        booktitle.setBackground(new java.awt.Color(250, 250, 250));
        booktitle.setBorder(null);
        booktitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booktitleActionPerformed(evt);
            }
        });
        booktitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                booktitleKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(booktitle, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(booktitle, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        photoCover1.setBackground(new java.awt.Color(158, 158, 158));
        photoCover1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                photoCover1MouseClicked(evt);
            }
        });

        authorlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        authorlabel.setForeground(new java.awt.Color(51, 51, 51));
        authorlabel.setText("Author");

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setOpaque(false);

        author.setBackground(new java.awt.Color(250, 250, 250));
        author.setBorder(null);
        author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorActionPerformed(evt);
            }
        });
        author.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                authorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(author, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setOpaque(false);

        publisher.setBackground(new java.awt.Color(250, 250, 250));
        publisher.setBorder(null);
        publisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publisherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(publisher, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(publisher, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        publisherlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        publisherlabel.setForeground(new java.awt.Color(51, 51, 51));
        publisherlabel.setText("Publisher");

        genrelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        genrelabel.setForeground(new java.awt.Color(51, 51, 51));
        genrelabel.setText("Genre");

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setOpaque(false);

        genre.setBackground(new java.awt.Color(250, 250, 250));
        genre.setBorder(null);
        genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreActionPerformed(evt);
            }
        });
        genre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                genreKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(genre, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setOpaque(false);

        dewey.setBackground(new java.awt.Color(250, 250, 250));
        dewey.setBorder(null);
        dewey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deweyActionPerformed(evt);
            }
        });
        dewey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                deweyKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dewey, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(598, 598, 598))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dewey, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        deweylabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deweylabel.setForeground(new java.awt.Color(51, 51, 51));
        deweylabel.setText("Dewey No.");

        datelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        datelabel.setForeground(new java.awt.Color(51, 51, 51));
        datelabel.setText("Date Publish");

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setOpaque(false);

        date.setBackground(new java.awt.Color(250, 250, 250));
        date.setBorder(null);
        date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateMouseClicked(evt);
            }
        });
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(554, 554, 554))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date)
                .addContainerGap())
        );

        quantitylabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quantitylabel.setForeground(new java.awt.Color(51, 51, 51));
        quantitylabel.setText("Quantity of Book");

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setOpaque(false);

        quantity.setBackground(new java.awt.Color(250, 250, 250));
        quantity.setBorder(null);
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantityKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(566, 566, 566))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        decklabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        decklabel.setForeground(new java.awt.Color(51, 51, 51));
        decklabel.setText("Deck No.");

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel13.setOpaque(false);

        deck.setBackground(new java.awt.Color(250, 250, 250));
        deck.setBorder(null);
        deck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckActionPerformed(evt);
            }
        });
        deck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                deckKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deck, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(566, 566, 566))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deck, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        shelflabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        shelflabel.setForeground(new java.awt.Color(51, 51, 51));
        shelflabel.setText("Shelf No.");

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.setOpaque(false);

        shelf.setBackground(new java.awt.Color(250, 250, 250));
        shelf.setBorder(null);
        shelf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shelfActionPerformed(evt);
            }
        });
        shelf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shelfKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(shelf, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(566, 566, 566))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shelf, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        myButtonborderless1.setForeground(new java.awt.Color(224, 224, 224));
        myButtonborderless1.setText("Cancel");
        myButtonborderless1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless1ActionPerformed(evt);
            }
        });

        myButtonborderless2.setForeground(new java.awt.Color(224, 224, 224));
        myButtonborderless2.setText("Add book");
        myButtonborderless2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(myButtonborderless1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(myButtonborderless2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(allowedtype)
                                .addComponent(coverlabel)
                                .addComponent(booktitlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(photoCover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(authorlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(publisherlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(datelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(genrelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(quantitylabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(deweylabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(14, 14, 14)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(shelflabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(decklabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coverlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(photoCover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allowedtype)
                .addGap(18, 18, 18)
                .addComponent(booktitlelabel)
                .addGap(4, 4, 4)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(authorlabel)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(publisherlabel)
                                .addGap(4, 4, 4)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(datelabel)
                                .addGap(4, 4, 4)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(genrelabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(quantitylabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(decklabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deweylabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(shelflabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myButtonborderless1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButtonborderless2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void booktitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booktitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_booktitleActionPerformed

    private void authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_authorActionPerformed

    private void publisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_publisherActionPerformed

    private void genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreActionPerformed

    private void deweyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deweyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deweyActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void deckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deckActionPerformed

    private void shelfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shelfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shelfActionPerformed

    private void myButtonborderless1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless1ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_myButtonborderless1ActionPerformed

    private void myButtonborderless2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless2ActionPerformed
        // TODO add your handling code here:
        String book_title = booktitle.getText();
        String book_author = author.getText();
        String publ = publisher.getText();
        String genr = genre.getText();
        String date1 = date.getText();
        String quan = quantity.getText();
        String dew = dewey.getText();
        String shelff = shelf.getText();
        String deckk = deck.getText();
        String downloadUrl = "";

        if (booktitle.getText().equals("") || author.getText().equals("") || publisher.getText().equals("") || genre.getText().equals("") || date.getText().equals("") || quantity.getText().equals("") || dewey.getText().equals("") || date.getText().equals("") || deck.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
            if (this.localFilePath.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Cover is empty", "Error", ERROR_MESSAGE);
            } else {
                storage uploader = new storage(this.localFilePath, this.remoteFilePath);
                try {
                    downloadUrl = uploader.upload();
                    JOptionPane.showMessageDialog(null, "Please wait", "Uploading", INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String getnow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            String key = databaseReference.push().getKey();
            v = new pushValue(databaseReference.push().getKey());
            m = new HashMap<>();
            m.put("booktitle", book_title);
            m.put("bookauthor", book_author);
            m.put("publisher", publ);
            m.put("dewey", dew);
            m.put("genre", genr);
            m.put("date", date1);
            m.put("quantity", quan);
            m.put("shelf", shelff);
            m.put("deck", deckk);
            m.put("key", key);
            m.put("status", "Available");
            m.put("timestamp", getnow);
            m.put("cover", downloadUrl);
            v.pushData("books/inshelf", m);
            JOptionPane.showMessageDialog(null, "Add book", "Book added Successfully", INFORMATION_MESSAGE);
            GlassPanePopup.closePopupLast();
        }
    }//GEN-LAST:event_myButtonborderless2ActionPerformed

    private void photoCover1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_photoCover1MouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.localFilePath = selectedFile.getAbsolutePath();
            this.remoteFilePath = "cover/" + selectedFile.getName();

            try {
                BufferedImage image = ImageIO.read(new File(selectedFile.getAbsolutePath()));
                photoCover1.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(add_book.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_photoCover1MouseClicked

    private void dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_dateMouseClicked

    private void booktitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_booktitleKeyReleased
        // TODO add your handling code here:
        String text = booktitle.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            booktitle.setText(text);
        }

        if (text.length() > 24) {
            str = text.substring(0, 24);
            booktitle.setText("");
        }

        if (text.length() == 0) {
            booktitle.setText(str);
            str = "";
        }
    }//GEN-LAST:event_booktitleKeyReleased

    private void quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_quantityKeyTyped

    private void deweyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deweyKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_deweyKeyTyped

    private void shelfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shelfKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_shelfKeyTyped

    private void deckKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deckKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_deckKeyTyped

    private void authorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_authorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == ' ' || c == '-' || c == '.' || c == ',' || c == '\'' || c == '\"'
           || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_authorKeyTyped

    private void genreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_genreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_genreKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allowedtype;
    private javax.swing.JTextField author;
    private javax.swing.JLabel authorlabel;
    private javax.swing.JTextField booktitle;
    private javax.swing.JLabel booktitlelabel;
    private javax.swing.JLabel coverlabel;
    private javax.swing.JTextField date;
    private javax.swing.JLabel datelabel;
    private javax.swing.JTextField deck;
    private javax.swing.JLabel decklabel;
    private javax.swing.JTextField dewey;
    private javax.swing.JLabel deweylabel;
    private javax.swing.JTextField genre;
    private javax.swing.JLabel genrelabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private libratech.design.MyButtonborderless myButtonborderless1;
    private libratech.design.MyButtonborderless myButtonborderless2;
    private libratech.design.PhotoCover photoCover1;
    private javax.swing.JTextField publisher;
    private javax.swing.JLabel publisherlabel;
    private javax.swing.JTextField quantity;
    private javax.swing.JLabel quantitylabel;
    private javax.swing.JTextField shelf;
    private javax.swing.JLabel shelflabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        jLabel2.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        coverlabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        allowedtype.setFont(new Font("Poppins Regular", Font.PLAIN, 10));
        booktitle.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        booktitlelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        author.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        authorlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        publisher.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        publisherlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        quantity.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        quantitylabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        shelf.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        shelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        shelf.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        shelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        date.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        datelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        deck.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        decklabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        dewey.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        deweylabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        genre.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        genrelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButtonborderless1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButtonborderless2.setFont(new Font("Poppins Regular", Font.BOLD, 12));

    }
}
