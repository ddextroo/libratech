/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.dashboard;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import libratech.auth.login;
import libratech.auth.signup;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.RoundedPanel;
import libratech.design.RoundedPanelBorderless;
import libratech.design.loading;
import libratech.models.ClassificationInfo;
import libratech.models.auth;
import libratech.models.getUID;
import libratech.models.getUserInfo;
import libratech.models.pushValue;
import libratech.models.retrieve;
import libratech.util.firebaseInit;
import libratech.util.storage;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author HBUSER
 */
public class add_book extends javax.swing.JPanel {

    private String localFilePath;
    private String remoteFilePath;
    private DatabaseReference databaseReference;
    private HashMap<String, Object> m;
    private pushValue v;
    private retrieve r;
    private String uid;
    ClassificationInfo info = new ClassificationInfo();
    private ChildEventListener booksuserinfo;
    private final String path_bookuserinfo = "users/";
    private final DatabaseReference bookuser = FirebaseDatabase.getInstance().getReference(path_bookuserinfo);
    String barcode_name;

    public add_book() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        initComponents();
        initFont();
        new firebaseInit().initFirebase();

        classification.setEditable(true);
        classification.setModel(new javax.swing.DefaultComboBoxModel(info.getClassification()));
        classification.getEditor().getEditorComponent().setBackground(new Color(250, 250, 250));
        userBookInfo();
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

        dateChooser1 = new libratech.design.DateChooser();
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
        classificationlabel = new javax.swing.JLabel();
        jPanel9 = new RoundedPanel(12, new Color(250,250,250));
        classification = new libratech.design.ComboBoxSuggestion();
        jPanel10 = new RoundedPanel(12, new Color(250,250,250));
        isbn = new javax.swing.JTextField();
        isbnlabel = new javax.swing.JLabel();
        datelabel = new javax.swing.JLabel();
        jPanel11 = new RoundedPanel(12, new Color(250,250,250));
        date = new javax.swing.JTextField();
        copieslabel = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(12, new Color(250,250,250));
        copies = new javax.swing.JTextField();
        decklabel = new javax.swing.JLabel();
        jPanel13 = new RoundedPanel(12, new Color(250,250,250));
        deck = new javax.swing.JTextField();
        shelflabel = new javax.swing.JLabel();
        jPanel14 = new RoundedPanel(12, new Color(250,250,250));
        shelf = new javax.swing.JTextField();
        myButtonborderless2 = new libratech.design.MyButtonborderless();
        myButtonborder1 = new libratech.design.MyButtonborder();
        jPanel15 = new RoundedPanel(12, new Color(250,250,250));
        edition = new javax.swing.JTextField();
        editionlabel = new javax.swing.JLabel();
        pricelabel = new javax.swing.JLabel();
        jPanel16 = new RoundedPanel(12, new Color(250,250,250));
        price = new javax.swing.JTextField();

        dateChooser1.setDateFormat("yyyy");
        dateChooser1.setTextRefernce(date);

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
                .addComponent(booktitle, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(317, 317, 317))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(booktitle, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        photoCover1.setBackground(new java.awt.Color(158, 158, 158));
        photoCover1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
                .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
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

        classificationlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        classificationlabel.setForeground(new java.awt.Color(51, 51, 51));
        classificationlabel.setText("Classification");

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setOpaque(false);

        classification.setBackground(new java.awt.Color(255, 255, 255));
        classification.setEditable(false);
        classification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classificationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classification, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(classification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setOpaque(false);

        isbn.setBackground(new java.awt.Color(250, 250, 250));
        isbn.setBorder(null);
        isbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnActionPerformed(evt);
            }
        });
        isbn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isbnKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(598, 598, 598))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(isbn, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        isbnlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        isbnlabel.setForeground(new java.awt.Color(51, 51, 51));
        isbnlabel.setText("ISBN Number");

        datelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        datelabel.setForeground(new java.awt.Color(51, 51, 51));
        datelabel.setText("Copyright Year");

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

        copieslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        copieslabel.setForeground(new java.awt.Color(51, 51, 51));
        copieslabel.setText("Number of Copies");

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setOpaque(false);

        copies.setBackground(new java.awt.Color(250, 250, 250));
        copies.setBorder(null);
        copies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiesActionPerformed(evt);
            }
        });
        copies.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                copiesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(copies, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(566, 566, 566))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(copies, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        decklabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        decklabel.setForeground(new java.awt.Color(51, 51, 51));
        decklabel.setText("Deck Number");

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
        shelflabel.setText("Shelf Number");

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
                .addComponent(shelf, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(569, 569, 569))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shelf, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        myButtonborderless2.setForeground(new java.awt.Color(224, 224, 224));
        myButtonborderless2.setText("Add book");
        myButtonborderless2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless2ActionPerformed(evt);
            }
        });

        myButtonborder1.setForeground(new java.awt.Color(23, 23, 23));
        myButtonborder1.setText("Cancel");
        myButtonborder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborder1ActionPerformed(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel15.setOpaque(false);

        edition.setBackground(new java.awt.Color(250, 250, 250));
        edition.setBorder(null);
        edition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editionActionPerformed(evt);
            }
        });
        edition.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edition, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(546, 546, 546))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edition, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        editionlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editionlabel.setForeground(new java.awt.Color(51, 51, 51));
        editionlabel.setText("Book Edition");

        pricelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pricelabel.setForeground(new java.awt.Color(51, 51, 51));
        pricelabel.setText("Price");

        jPanel16.setBackground(new java.awt.Color(0, 0, 0));
        jPanel16.setOpaque(false);

        price.setBackground(new java.awt.Color(250, 250, 250));
        price.setBorder(null);
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(546, 546, 546))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButtonborder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myButtonborderless2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(coverlabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(classificationlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(copieslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 137, Short.MAX_VALUE))
                                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(isbnlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(shelflabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(decklabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE))
                                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(publisherlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(authorlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(datelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editionlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGap(10, 10, 10)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(allowedtype)
                                    .addComponent(booktitlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(photoCover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(364, 364, 364)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pricelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)))))
                .addContainerGap(25, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(booktitlelabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pricelabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(authorlabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editionlabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(publisherlabel)
                                .addGap(4, 4, 4)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(datelabel)
                                .addGap(4, 4, 4)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(classificationlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(copieslabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(decklabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(isbnlabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(shelflabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myButtonborderless2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButtonborder1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void isbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isbnActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void copiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_copiesActionPerformed

    private void shelfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shelfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shelfActionPerformed

    private void barcode(String code) {
        try {
            Barcode barcode = BarcodeFactory.createCode128(code);
            barcode.setFont(new Font("Poppins Regular", Font.BOLD, 12));
            barcode.setBarHeight(60);
            barcode.setBarWidth(2);

            // Create a temporary file to save the barcode image
            File file = File.createTempFile("barcode", ".png");
            BarcodeImageHandler.savePNG(barcode, file);

        } catch (BarcodeException | OutputException | IOException ex) {
            Logger.getLogger(books_menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void userBookInfo() {
        booksuserinfo = new ChildEventListener() {
            
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (new getUID().getUid().equals(_childKey)) {
                    if (_childValue.containsKey("barcode_name")) {
                        barcode_name = _childValue.get("barcode_name").toString();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String string) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = ds.getKey();
                final HashMap<String, Object> _childValue = ds.getValue(_ind);
                if (new getUID().getUid().equals(_childKey)) {
                    if (_childValue.containsKey("barcode_name")) {
                        barcode_name = _childValue.get("barcode_name").toString();
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void onChildMoved(DataSnapshot ds, String string) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        bookuser.addChildEventListener(booksuserinfo);
    }

    private void myButtonborderless2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless2ActionPerformed
        // TODO add your handling code here:
        String book_title = booktitle.getText();
        String book_author = author.getText();
        String publ = publisher.getText();
        String book_isbn = isbn.getText();
        String genr = info.getDeweyNo()[classification.getSelectedIndex()];
        String date1 = date.getText();
        String book_copies = copies.getText();
        String book_edition = edition.getText();
        String shelff = shelf.getText();
        String deckk = deck.getText();
        double prices = Double.parseDouble(price.getText());
        String downloadUrl = "";

        if (booktitle.getText().equals("") || author.getText().equals("") || publisher.getText().equals("") || classification.getSelectedItem().toString().equals("") || date.getText().equals("") || copies.getText().equals("") || isbn.getText().equals("") || date.getText().equals("") || deck.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
            if (this.localFilePath.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Cover is empty", "Error", ERROR_MESSAGE);
            } else {
                storage uploader = new storage(this.localFilePath, this.remoteFilePath);
                try {
                    downloadUrl = uploader.upload();
                    GlassPanePopup.showPopup(new loading());
                } catch (IOException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String getnow = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
            String uidpath = new getUID().getUid();
            String call_no = genr + "-" + shelff + deckk + "-" + date1;
            //String key = databaseReference.push().getKey();
            String bcode = barcode_name + genr + shelff + deckk + String.format("%04d", Integer.valueOf(book_copies)) + book_edition;
            String key = bcode;

            v = new pushValue(key);
            m = new HashMap<>();
            m.put("booktitle", book_title);
            m.put("bookauthor", book_author);
            m.put("publisher", publ);
            m.put("isbn", book_isbn);
            m.put("classification_code", genr);
            m.put("barcode", bcode);
            m.put("classification_pos", classification.getSelectedIndex());
            m.put("classification", classification.getSelectedItem().toString());
            m.put("date", date1);
            m.put("copies", book_copies);
            m.put("edition", book_edition);
            m.put("shelf", shelff);
            m.put("deck", deckk);
            m.put("key", key);
            m.put("status", "Available");
            m.put("timestamp", getnow);
            m.put("remaining_copies", Integer.valueOf(book_copies));
            m.put("cover", downloadUrl);
            m.put("borrowed_books", 0);
            m.put("price", prices);
            m.put("overdue_books", 0);
            m.put("lost_books", 0);
            m.put("damaged_books", 0);
            v.pushData("books/" + uidpath, m);
            m.clear();
            GlassPanePopup.closePopupAll();
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

        if (text.length() > 100) {
            str = text.substring(0, 24);
            booktitle.setText("");
        }

        if (text.length() == 0) {
            booktitle.setText(str);
            str = "";
        }
    }//GEN-LAST:event_booktitleKeyReleased

    private void copiesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_copiesKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_copiesKeyTyped

    private void isbnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isbnKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_isbnKeyTyped

    private void shelfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shelfKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_shelfKeyTyped

    private void authorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_authorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == ' ' || c == '-' || c == '.' || c == ',' || c == '\'' || c == '\"'
                || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_authorKeyTyped

    private void myButtonborder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborder1ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_myButtonborder1ActionPerformed

    private void editionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editionActionPerformed

    private void editionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_editionKeyTyped

    private void deckKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deckKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_deckKeyTyped

    private void deckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deckActionPerformed

    private void classificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classificationActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, comboBoxSuggestion1, "Select", JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_classificationActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_priceKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allowedtype;
    private javax.swing.JTextField author;
    private javax.swing.JLabel authorlabel;
    private javax.swing.JTextField booktitle;
    private javax.swing.JLabel booktitlelabel;
    private libratech.design.ComboBoxSuggestion classification;
    private javax.swing.JLabel classificationlabel;
    private javax.swing.JTextField copies;
    private javax.swing.JLabel copieslabel;
    private javax.swing.JLabel coverlabel;
    private javax.swing.JTextField date;
    private libratech.design.DateChooser dateChooser1;
    private javax.swing.JLabel datelabel;
    private javax.swing.JTextField deck;
    private javax.swing.JLabel decklabel;
    private javax.swing.JTextField edition;
    private javax.swing.JLabel editionlabel;
    private javax.swing.JTextField isbn;
    private javax.swing.JLabel isbnlabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private libratech.design.MyButtonborder myButtonborder1;
    private libratech.design.MyButtonborderless myButtonborderless2;
    private libratech.design.PhotoCover photoCover1;
    private javax.swing.JTextField price;
    private javax.swing.JLabel pricelabel;
    private javax.swing.JTextField publisher;
    private javax.swing.JLabel publisherlabel;
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
        copies.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        copieslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        shelf.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        shelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        shelf.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        shelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        date.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        datelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        deck.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        decklabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        editionlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        edition.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        isbn.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        isbnlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        classification.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        classificationlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        price.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        pricelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButtonborder1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButtonborderless2.setFont(new Font("Poppins Regular", Font.BOLD, 12));
    }
}
