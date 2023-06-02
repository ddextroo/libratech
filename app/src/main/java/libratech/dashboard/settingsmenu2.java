/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.dashboard;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import libratech.auth.signup;
import libratech.design.DefaultOption;
import libratech.design.GlassPanePopup;
import libratech.design.Option;
import libratech.design.RoundedPanel;
import libratech.design.ScrollBarCustom;
import libratech.design.loading;
import libratech.models.getUID;
import libratech.models.pushValueExisting;
import libratech.util.firebaseInit;
import libratech.util.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import libratech.models.pushValue;

/**
 *
 * @author HBUSER
 */
public class settingsmenu2 extends javax.swing.JPanel {

    private String localFilePath;
    private String remoteFilePath;
    private String uid = new getUID().getUid();
    private ChildEventListener accinfo;
    private final String path = "users/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path);
    private HashMap<String, Object> m;
    private Map<String, Object> mtest;
    private pushValueExisting v;
    String downloadUrl = "";
    boolean upload = false;
    private String passwd;

    public settingsmenu2() {
        initComponents();
        setOpaque(false);
        libratech.setBackground(new Color(0, 0, 0, 0));
        libratech.setOpaque(false);
        libratech.setEditable(false);
        goals.setBackground(new Color(0, 0, 0, 0));
        goals.setOpaque(false);
        goals.setEditable(false);
        initFont();
        new firebaseInit().initFirebase();

        StyledDocument doc = libratech.getStyledDocument();
        libratech.setStyledDocument(doc);

        SimpleAttributeSet paragraphAttributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(paragraphAttributes, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), paragraphAttributes, false);

//        try {
//            doc.insertString(0, libratech.getText(), null);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }

        StyledDocument docmission = mission.getStyledDocument();
        mission.setStyledDocument(docmission);

        SimpleAttributeSet paragraphAttributesmission = new SimpleAttributeSet();
        StyleConstants.setAlignment(paragraphAttributesmission, StyleConstants.ALIGN_JUSTIFIED);
        docmission.setParagraphAttributes(0, docmission.getLength(), paragraphAttributesmission, false);

//        try {
//            docmission.insertString(0, mission.getText(), null);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }

        StyledDocument docvision = vision.getStyledDocument();
        vision.setStyledDocument(docvision);

        SimpleAttributeSet paragraphAttributesvision = new SimpleAttributeSet();
        StyleConstants.setAlignment(paragraphAttributesvision, StyleConstants.ALIGN_JUSTIFIED);
        docvision.setParagraphAttributes(0, docvision.getLength(), paragraphAttributesvision, false);

//        try {
//            docmission.insertString(0, vision.getText(), null);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }

        StyledDocument docgoals = goals.getStyledDocument();
        goals.setStyledDocument(docgoals);

        SimpleAttributeSet paragraphAttributesgoals = new SimpleAttributeSet();
        StyleConstants.setAlignment(paragraphAttributesgoals, StyleConstants.ALIGN_JUSTIFIED);
        docgoals.setParagraphAttributes(0, docgoals.getLength(), paragraphAttributesgoals, false);

//        try {
//            docmission.insertString(0, goals.getText(), null);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }

        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setPreferredSize(new Dimension(12, 50));
        jScrollPane1.setVerticalScrollBar(sb);
        jScrollPane6.setVerticalScrollBar(sb);
        ScrollBarCustom sbH = new ScrollBarCustom();
        sbH.setOrientation(JScrollBar.HORIZONTAL);
        sbH.setPreferredSize(new Dimension(12, 12));
        jScrollPane1.setHorizontalScrollBar(sbH);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setHorizontalScrollBar(sbH);
        jScrollPane6.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        accinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                System.out.println(_childKey);
                if (_childKey.equals(uid)) {
                    try {
                        if (_childValue.containsKey("barcode_name")) {
                            barcodename.setText(_childValue.get("barcode_name").toString());
                        }
                        if (_childValue.containsKey("days_limit")) {
                            limit.setText(_childValue.get("days_limit").toString());
                        }
                        if (_childValue.containsKey("overdue_fines")) {
                            overduefines.setText(_childValue.get("overdue_fines").toString());
                        }
                        schoolname.setText(_childValue.get("school_name").toString());
                        passwd = _childValue.get("pass").toString();
                        schoolid.setText(_childValue.get("school_id").toString());
                        email.setText(_childValue.get("email").toString());
                        URL url = new URL(_childValue.get("url").toString());
                        BufferedImage image = ImageIO.read(url);
                        photoCover1.setImage(image);
                        downloadUrl = _childValue.get("url").toString();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
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
                if (_childKey.equals(uid)) {
                    try {
                        if (_childValue.containsKey("barcode_name")) {
                            barcodename.setText(_childValue.get("barcode_name").toString());
                        }
                        if (_childValue.containsKey("days_limit")) {
                            limit.setText(_childValue.get("days_limit").toString());
                        }
                        if (_childValue.containsKey("overdue_fines")) {
                            overduefines.setText(_childValue.get("overdue_fines").toString());
                        }
                        schoolname.setText(_childValue.get("school_name").toString());
                        schoolid.setText(_childValue.get("school_id").toString());
                        email.setText(_childValue.get("email").toString());
                        URL url = new URL(_childValue.get("url").toString());
                        BufferedImage image = ImageIO.read(url);
                        photoCover1.setImage(image);
                        downloadUrl = _childValue.get("url").toString();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
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
        acc.addChildEventListener(accinfo);
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
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        materialTabbed1 = new libratech.design.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new RoundedPanel(12, new Color(255,255,255));
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        photoCover1 = new libratech.design.PhotoCover();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        pwdlabel = new javax.swing.JLabel();
        jPanel7 = new RoundedPanel(12, new Color(245,245,245, 0));
        pass = new javax.swing.JPasswordField();
        pwdlabel1 = new javax.swing.JLabel();
        jPanel10 = new RoundedPanel(12, new Color(245,245,245, 0));
        pass1 = new javax.swing.JPasswordField();
        pwdlabel2 = new javax.swing.JLabel();
        jPanel11 = new RoundedPanel(12, new Color(245,245,245, 0));
        pass2 = new javax.swing.JPasswordField();
        jPanel23 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        schoolnamelabel = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(12, new Color(245,245,245,0));
        schoolname = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        schoolidlabel = new javax.swing.JLabel();
        jPanel14 = new RoundedPanel(12, new Color(245,245,245,0));
        schoolid = new javax.swing.JTextField();
        emailaddlabel = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(12, new Color(245,245,245,0));
        email = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        editprofile = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel32 = new javax.swing.JPanel();
        passwrodsecuritylabel = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        myButtonborderless4 = new libratech.design.MyButtonborderless();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel33 = new javax.swing.JPanel();
        books = new javax.swing.JLabel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        barcodenamelabel = new javax.swing.JLabel();
        jPanel20 = new RoundedPanel(12, new Color(245,245,245, 0));
        barcodename = new javax.swing.JTextField();
        limitlabel = new javax.swing.JLabel();
        jPanel21 = new RoundedPanel(12, new Color(245,245,245, 0));
        limit = new javax.swing.JTextField();
        overduefineslabel = new javax.swing.JLabel();
        jPanel26 = new RoundedPanel(12, new Color(245,245,245, 0));
        overduefines = new javax.swing.JTextField();
        myButtonborderless5 = new libratech.design.MyButtonborderless();
        cancel = new libratech.design.MyButtonborder();
        jSeparator3 = new javax.swing.JSeparator();
        schoolnamelabel1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        libratechlabel = new javax.swing.JLabel();
        libratech = new javax.swing.JTextPane();
        visionlabel = new javax.swing.JLabel();
        mission = new javax.swing.JTextPane();
        goalslabel = new javax.swing.JLabel();
        goals = new javax.swing.JTextPane();
        missionlabel = new javax.swing.JLabel();
        vision = new javax.swing.JTextPane();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setBackground(new java.awt.Color(255, 153, 153));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(224, 224, 224));
        jPanel1.setLayout(new java.awt.BorderLayout(30, 10));

        jPanel8.setBackground(new java.awt.Color(224, 224, 224));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 58, 58));
        jLabel1.setText("Settings");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1468, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        materialTabbed1.setBackground(new java.awt.Color(250, 250, 250));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255,0));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setOpaque(false);

        photoCover1.setBackground(new java.awt.Color(158, 158, 158));
        photoCover1.setPreferredSize(new java.awt.Dimension(200, 200));
        photoCover1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        photoCover1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                photoCover1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photoCover1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photoCover1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(jPanel17);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setOpaque(false);
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setOpaque(false);

        pwdlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pwdlabel.setForeground(new java.awt.Color(51, 51, 51));
        pwdlabel.setText("Old Password");

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        pass.setBackground(new java.awt.Color(250, 250, 250));
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pass)
                .addContainerGap())
        );

        jPanel7.setOpaque(false);

        pwdlabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pwdlabel1.setForeground(new java.awt.Color(51, 51, 51));
        pwdlabel1.setText("New Password");

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        pass1.setBackground(new java.awt.Color(250, 250, 250));
        pass1.setBorder(null);
        pass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass1ActionPerformed(evt);
            }
        });
        pass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pass1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pass1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pass1)
                .addContainerGap())
        );

        jPanel10.setOpaque(false);

        pwdlabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pwdlabel2.setForeground(new java.awt.Color(51, 51, 51));
        pwdlabel2.setText("Confirm Password");

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        pass2.setBackground(new java.awt.Color(250, 250, 250));
        pass2.setBorder(null);
        pass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass2ActionPerformed(evt);
            }
        });
        pass2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pass2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pass2)
                .addContainerGap())
        );

        jPanel11.setOpaque(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwdlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwdlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwdlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(pwdlabel)
                .addGap(4, 4, 4)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pwdlabel1)
                .addGap(4, 4, 4)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pwdlabel2)
                .addGap(4, 4, 4)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel15.add(jPanel16);

        jPanel23.setBackground(new java.awt.Color(250, 250, 250));

        jPanel22.setBackground(new java.awt.Color(250, 250, 250));

        schoolnamelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        schoolnamelabel.setForeground(new java.awt.Color(51, 51, 51));
        schoolnamelabel.setText("School Name");

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setOpaque(false);

        schoolname.setBackground(new java.awt.Color(250, 250, 250));
        schoolname.setBorder(null);
        schoolname.setOpaque(true);
        schoolname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolnameActionPerformed(evt);
            }
        });
        schoolname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                schoolnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                schoolnameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                schoolnameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(schoolname, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolname)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schoolnamelabel)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(schoolnamelabel)
                .addGap(0, 0, 0)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel24.setBackground(new java.awt.Color(250, 250, 250));

        schoolidlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        schoolidlabel.setForeground(new java.awt.Color(51, 51, 51));
        schoolidlabel.setText("School ID Number");

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.setOpaque(false);

        schoolid.setEditable(false);
        schoolid.setBackground(new java.awt.Color(250, 250, 250));
        schoolid.setBorder(null);
        schoolid.setEnabled(false);
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
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolid)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolid, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        emailaddlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emailaddlabel.setForeground(new java.awt.Color(51, 51, 51));
        emailaddlabel.setText("Email Address");

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        email.setEditable(false);
        email.setBackground(new java.awt.Color(250, 250, 250));
        email.setBorder(null);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(email)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(email)
                .addContainerGap())
        );

        jPanel6.setOpaque(false);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schoolidlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailaddlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(schoolidlabel)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(emailaddlabel)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(250, 250, 250));
        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        editprofile.setBackground(new java.awt.Color(250, 250, 250));
        editprofile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        editprofile.setText("Edit Profile");
        jPanel25.add(editprofile);
        jPanel25.add(filler4);

        jPanel32.setBackground(new java.awt.Color(250, 250, 250));
        jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        passwrodsecuritylabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        passwrodsecuritylabel.setText("Password & Security");
        jPanel32.add(passwrodsecuritylabel);
        jPanel32.add(filler6);

        myButtonborderless4.setForeground(new java.awt.Color(224, 224, 224));
        myButtonborderless4.setText("Save Password");
        myButtonborderless4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless4ActionPerformed(evt);
            }
        });
        jPanel32.add(myButtonborderless4);

        jPanel33.setBackground(new java.awt.Color(250, 250, 250));
        jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        books.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        books.setText("Books");
        jPanel33.add(books);
        jPanel33.add(filler7);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setOpaque(false);
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setOpaque(false);

        barcodenamelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        barcodenamelabel.setForeground(new java.awt.Color(51, 51, 51));
        barcodenamelabel.setText("Barcode Name");

        jPanel20.setBackground(new java.awt.Color(0, 0, 0));
        jPanel20.setOpaque(false);

        barcodename.setBackground(new java.awt.Color(250, 250, 250));
        barcodename.setBorder(null);
        barcodename.setOpaque(true);
        barcodename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcodenameActionPerformed(evt);
            }
        });
        barcodename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodenameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                barcodenameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barcodenameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(barcodename, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(383, 383, 383))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barcodename)
                .addContainerGap())
        );

        jPanel7.setOpaque(false);

        limitlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        limitlabel.setForeground(new java.awt.Color(51, 51, 51));
        limitlabel.setText("Days Limit");

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel21.setOpaque(false);

        limit.setBackground(new java.awt.Color(250, 250, 250));
        limit.setBorder(null);
        limit.setOpaque(true);
        limit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitActionPerformed(evt);
            }
        });
        limit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                limitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                limitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                limitKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(limit, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(383, 383, 383))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(limit)
                .addContainerGap())
        );

        jPanel10.setOpaque(false);

        overduefineslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        overduefineslabel.setForeground(new java.awt.Color(51, 51, 51));
        overduefineslabel.setText("Overdue Fines");

        jPanel26.setBackground(new java.awt.Color(0, 0, 0));
        jPanel26.setOpaque(false);

        overduefines.setBackground(new java.awt.Color(250, 250, 250));
        overduefines.setBorder(null);
        overduefines.setOpaque(true);
        overduefines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overduefinesActionPerformed(evt);
            }
        });
        overduefines.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                overduefinesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                overduefinesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                overduefinesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(overduefines, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(383, 383, 383))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overduefines)
                .addContainerGap())
        );

        jPanel11.setOpaque(false);

        myButtonborderless5.setForeground(new java.awt.Color(224, 224, 224));
        myButtonborderless5.setText("Import books");
        myButtonborderless5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless5ActionPerformed(evt);
            }
        });

        cancel.setForeground(new java.awt.Color(255, 51, 51));
        cancel.setText("DELETE ALL BOOKS");
        cancel.setPreferredSize(new java.awt.Dimension(102, 23));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(overduefineslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limitlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barcodenamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myButtonborderless5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(barcodenamelabel)
                .addGap(4, 4, 4)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limitlabel)
                .addGap(4, 4, 4)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(overduefineslabel)
                .addGap(4, 4, 4)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myButtonborderless5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel18.add(jPanel19);

        schoolnamelabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        schoolnamelabel1.setForeground(new java.awt.Color(51, 51, 51));
        schoolnamelabel1.setText("School Avatar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator3)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schoolnamelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 570, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(schoolnamelabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel2);

        materialTabbed1.addTab("Account", jScrollPane1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        libratechlabel.setText("Libratech");

        libratech.setEditable(false);
        libratech.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        libratech.setText("LibraTech, a modern solution for efficient library management that is an ultimate destination for a vast collection of knowledge and information. As a cutting-edge e-library, we are dedicated to providing convenient access to an extensive range of digital resources, making learning and exploration accessible to all.\n\nAt LibraTech, we understand the importance of embracing the digital age and harnessing technology to revolutionize the way we engage with literature and research. Our platform brings together a diverse selection of e-books, academic journals, magazines, research papers, and multimedia content, all at your fingertips.\n\nOur user-friendly interface and intuitive search capabilities can easily navigate through our extensive catalog and discover a wealth of knowledge across various subjects and disciplines. Our e-library provides an immense set of knowledge that is designed to your interest and academic goals for students, teachers, researchers or even just simply for an avid reader. With the collaboration with renowned publishers, institutions, and authors worldwide to build a comprehensive collection that spans both classic and contemporary works. From literary masterpieces to scientific breakthroughs, our very own e-library houses a treasure trove of resources that cater to the needs of learners and enthusiasts from all walks of life.\n\nIn addition to our wide collection, we are committed to providing a seamless and personalized user experience. Our platform allows you to create your own virtual bookshelves, bookmark favorite titles, and highlight important passages. You can also customize your reading preferences, such as font size and background color, for enhanced comfort and accessibility.\n\nAt LibraTech, we believe in fostering a vibrant and inclusive community of learners. Through our interactive forums and discussion boards, you can connect with fellow enthusiasts, exchange ideas, and engage in intellectual discourse. Our commitment to collaboration extends to partnerships with educational institutions, enabling seamless integration with academic curricula and research initiatives.\n\nWe are constantly evolving and expanding our collection to ensure that our e-library remains a dynamic and enriching resource for our users. Our dedicated team of librarians and technology experts work tirelessly to enhance the user experience and incorporate the latest advancements in digital library services.");
        libratech.setOpaque(false);

        visionlabel.setText("Vision");

        mission.setEditable(false);
        mission.setText("Empower individuals with convenient and comprehensive access to knowledge, strengthening a lifelong love for learning and intellectual growth. We strive to revolutionize the way people engage with literature and research by providing a digital platform that transcends physical limitations and expands the horizons of knowledge.");
        mission.setOpaque(false);

        goalslabel.setText("Goals");

        goals.setEditable(false);
        goals.setText("1. Curate a Vast and Diverse Collection: Continuously expand and curate our collection to offer a comprehensive range of e-books, academic journals, research papers, and multimedia content across various subjects and disciplines. Strive to include both classic and contemporary works that cater to the evolving needs and interests of our users.\n\n2. Enhance User Experience: Continually improve our platform's user interface, search capabilities, and personalization features to ensure a seamless and engaging experience for our users. Enable easy navigation, customizable reading preferences, virtual bookshelves, and interactive features that promote collaboration and knowledge sharing.\n\n3. Foster a Learning Community: Facilitate intellectual discourse and foster a vibrant community of learners through interactive forums, discussion boards, and virtual events. Encourage users to connect, exchange ideas, and engage in meaningful conversations that enrich their learning journey.\n\n4. Promote Accessibility and Inclusivity: Prioritize accessibility and inclusivity by implementing features such as adjustable font sizes, screen reader compatibility, and translations to cater to individuals with diverse needs and language preferences. Strive to break down barriers and ensure that knowledge is accessible to everyone.\n\n5. Collaborate with Publishers and Institutions: Forge partnerships with renowned publishers, educational institutions, and authors to expand our collection, enhance content quality, and enable seamless integration with academic curricula and research initiatives. Collaborate to bring exclusive resources and facilitate scholarly engagement.\n\n6. Embrace Technological Advancements: Embrace emerging technologies and trends in digital library services to stay at the forefront of innovation. Leverage artificial intelligence, machine learning, and data analytics to enhance search algorithms, recommend personalized content, and provide valuable insights for users and institutions.\n\n7. Measure and Improve Impact: Continuously evaluate and measure our impact on users' learning outcomes, satisfaction levels, and engagement. Utilize user feedback, data analytics, and performance indicators to identify areas for improvement and enhance the effectiveness of our services.\n\nBy alienating our mission, vision, and goals, we aim to create a transformative and inclusive digital library experience that empowers individuals, expands knowledge horizons, and fosters a lifelong passion for learning.");
        goals.setOpaque(false);

        missionlabel.setText("Mission");

        vision.setEditable(false);
        vision.setText("To be the premier online digital library, steering to be recognized globally for its exceptional collection, user-friendly interface, and commitment to innovation. We aim to inspire and educate millions of users or readers, regardless of their geographical location or socioeconomic status, by delivering a thorough and diverse collection of digital resources and fostering a thriving community of learners.");
        vision.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(libratechlabel)
                            .addComponent(missionlabel)
                            .addComponent(visionlabel)
                            .addComponent(goalslabel)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(libratech, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(mission, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(vision, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(goals, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(libratechlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(libratech, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(missionlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(visionlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goalslabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goals, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(949, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel4, java.awt.BorderLayout.CENTER);

        jScrollPane6.setViewportView(jPanel5);

        materialTabbed1.addTab("About", jScrollPane6);

        jPanel9.add(materialTabbed1);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);
        jPanel1.add(filler1, java.awt.BorderLayout.LINE_START);
        jPanel1.add(filler2, java.awt.BorderLayout.LINE_END);
        jPanel1.add(filler3, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

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
                upload = true;
                if (upload) {
                    storage uploader = new storage(this.localFilePath, this.remoteFilePath);
                    try {
                        downloadUrl = uploader.upload();
                        v = new pushValueExisting(uid);
                        m = new HashMap<>();
                        m.put("url", downloadUrl);
                        v.pushData("users/", m);
                        GlassPanePopup.showPopup(new loading());
                        JOptionPane.showMessageDialog(null, "Profile Information Sucessfully Changed", "Success", INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(add_book.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_photoCover1MouseClicked

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyReleased
        // TODO add your handling code here:
        char[] opasswordChars = pass.getPassword();
        String opassword = new String(opasswordChars);
        String text = opassword;
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            pass.setText(text);
        }

        if (text.length() > 50) {
            str = text.substring(0, 50);
            pass.setText("");
        }

        if (text.length() == 0) {
            pass.setText(str);
            str = "";
        }
    }//GEN-LAST:event_passKeyReleased

    private void pass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass1ActionPerformed

    private void pass1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass1KeyReleased
        // TODO add your handling code here:
        char[] opasswordChars = pass1.getPassword();
        String opassword = new String(opasswordChars);
        String text = opassword;
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            pass1.setText(text);
        }

        if (text.length() > 50) {
            str = text.substring(0, 50);
            pass1.setText("");
        }

        if (text.length() == 0) {
            pass1.setText(str);
            str = "";
        }
    }//GEN-LAST:event_pass1KeyReleased

    private void pass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass2ActionPerformed

    private void pass2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass2KeyReleased
        // TODO add your handling code here:
        char[] opasswordChars = pass2.getPassword();
        String opassword = new String(opasswordChars);
        String text = opassword;
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            pass2.setText(text);
        }

        if (text.length() > 50) {
            str = text.substring(0, 50);
            pass2.setText("");
        }

        if (text.length() == 0) {
            pass2.setText(str);
            str = "";
        }
    }//GEN-LAST:event_pass2KeyReleased

    private void schoolnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolnameActionPerformed

    private void schoolnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolnameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (schoolname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                String getnow = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                String key = uid;
                String uidpath = new getUID().getUid();

                v = new pushValueExisting(key);
                m = new HashMap<>();
                m.put("school_name", schoolname.getText());
                //                m.put("school_id", schoolid.getText());
                //                m.put("timestamp", getnow);
                //                m.put("url", downloadUrl);
                //                m.put("pass", passwd);
                //                m.put("email", email.getText());
                //                m.put("uid", uid);
                v.pushData("users/", m);
                JOptionPane.showMessageDialog(null, "Profile Information Sucessfully Changed", "Success", INFORMATION_MESSAGE);

            }
        }
    }//GEN-LAST:event_schoolnameKeyPressed

    private void schoolnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolnameKeyReleased
        // TODO add your handling code here:
        String text = schoolname.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            schoolname.setText(text);
        }

        if (text.length() > 24) {
            str = text.substring(0, 24);
            schoolname.setText("");
        }

        if (text.length() == 0) {
            schoolname.setText(str);
            str = "";
        }
    }//GEN-LAST:event_schoolnameKeyReleased

    private void schoolnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolnameKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == ' ' || c == '-' || c == '.' || c == ',' || c == '\'' || c == '\"'
                || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_schoolnameKeyTyped

    private void schoolidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolidActionPerformed

    private void schoolidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolidKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (schoolname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                String getnow = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                String key = uid;
                String uidpath = new getUID().getUid();

                v = new pushValueExisting(key);
                m = new HashMap<>();
                //                m.put("school_name", schoolname.getText());
                m.put("school_id", schoolid.getText());
                //                m.put("timestamp", getnow);
                //                m.put("url", downloadUrl);
                //                m.put("pass", passwd);
                //                m.put("email", email.getText());
                //                m.put("uid", uid);
                v.pushData("users/", m);
                JOptionPane.showMessageDialog(null, "Profile Information Sucessfully Changed", "Success", INFORMATION_MESSAGE);

            }
        }
    }//GEN-LAST:event_schoolidKeyPressed

    private void schoolidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolidKeyReleased
        // TODO add your handling code here:
        String text = schoolid.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            schoolid.setText(text);
        }

        if (text.length() > 50) {
            str = text.substring(0, 50);
            schoolid.setText("");
        }

        if (text.length() == 0) {
            schoolid.setText(str);
            str = "";
        }
    }//GEN-LAST:event_schoolidKeyReleased

    private void schoolidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolidKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_schoolidKeyTyped

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (schoolname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                String getnow = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                String key = uid;
                String uidpath = new getUID().getUid();

                v = new pushValueExisting(key);
                m = new HashMap<>();
                //                m.put("school_name", schoolname.getText());
                //                m.put("school_id", schoolid.getText());
                //                m.put("timestamp", getnow);
                //                m.put("url", downloadUrl);
                //                m.put("pass", passwd);
                m.put("email", email.getText());
                //                m.put("uid", uid);
                v.pushData("users/", m);
                JOptionPane.showMessageDialog(null, "Profile Information Sucessfully Changed", "Success", INFORMATION_MESSAGE);

            }
        }
    }//GEN-LAST:event_emailKeyPressed

    private void myButtonborderless4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless4ActionPerformed
        // TODO add your handling code here:

        char[] oldpasswordChars = pass.getPassword();
        String oldpassword = new String(oldpasswordChars);
        char[] newpasswordChars = pass1.getPassword();
        String newpassword = new String(newpasswordChars);
        char[] confirmpasswordChars = pass2.getPassword();
        String conpassword = new String(confirmpasswordChars);

        if (pass.getPassword().length == 0 || pass1.getPassword().length == 0 || pass2.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
            if (oldpassword.equals(passwd) && newpassword.equals(conpassword)) {

                String getnow = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                String key = uid;

                v = new pushValueExisting(key);
                m = new HashMap<>();
                m.put("school_name", schoolname.getText());
                m.put("school_id", schoolid.getText());
                m.put("timestamp", getnow);
                m.put("url", downloadUrl);
                m.put("pass", newpassword);
                m.put("email", email.getText());
                m.put("uid", uid);
                v.pushData("users/", m);
                JOptionPane.showMessageDialog(null, "Password Sucessfully changed", "Error", INFORMATION_MESSAGE);
                GlassPanePopup.closePopupAll();
            } else {
                JOptionPane.showMessageDialog(null, "Password doesn't match", "Error", ERROR_MESSAGE);
                pass.setText("");
                pass1.setText("");
                pass2.setText("");
            }
        }
    }//GEN-LAST:event_myButtonborderless4ActionPerformed

    private void barcodenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcodenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodenameActionPerformed

    private void barcodenameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodenameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (barcodename.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                String key = uid;
                v = new pushValueExisting(key);
                m = new HashMap<>();
                m.put("barcode_name", barcodename.getText());
                v.pushData("users/", m);
                JOptionPane.showMessageDialog(null, "Book Settings Sucessfully Changed", "Success", INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_barcodenameKeyPressed

    private void barcodenameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodenameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodenameKeyReleased

    private void barcodenameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodenameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodenameKeyTyped

    private void limitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_limitActionPerformed

    private void limitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limitKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (limit.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                String key = uid;
                v = new pushValueExisting(key);
                m = new HashMap<>();
                m.put("days_limit", Integer.valueOf(limit.getText()));
                v.pushData("users/", m);
                JOptionPane.showMessageDialog(null, "Book Settings Sucessfully Changed", "Success", INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_limitKeyPressed

    private void limitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limitKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_limitKeyReleased

    private void limitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_limitKeyTyped

    private void overduefinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overduefinesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_overduefinesActionPerformed

    private void overduefinesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_overduefinesKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (overduefines.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
            } else {
                String key = uid;
                v = new pushValueExisting(key);
                m = new HashMap<>();
                m.put("overdue_fines", Integer.valueOf(overduefines.getText()));
                v.pushData("users/", m);
                JOptionPane.showMessageDialog(null, "Book Settings Sucessfully Changed", "Success", INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_overduefinesKeyPressed

    private void overduefinesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_overduefinesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_overduefinesKeyReleased

    private void overduefinesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_overduefinesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_overduefinesKeyTyped

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_passKeyPressed


    private void myButtonborderless5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless5ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            Option option = new DefaultOption() {
                @Override
                public float opacity() {
                    return 0.6f;
                }

                @Override
                public boolean closeWhenClickOutside() {
                    return false;
                }

                @Override
                public Color background() {
                    return new Color(33, 33, 33);
                }

            };
            GlassPanePopup.showPopup(new loading(), option);
            File selectedFile = fileChooser.getSelectedFile();
            this.localFilePath = selectedFile.getAbsolutePath();
            this.remoteFilePath = "cover/" + selectedFile.getName();
            StringBuilder jsonContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Map<String, Object>>>() {
            }.getType();
            List<Map<String, Object>> data = gson.fromJson(jsonContent.toString(), listType);

            System.out.println(new Gson().toJson(data));

            for (int count = 0; count < data.size(); count++) {
                mtest = data.get(count);
                String bcode = (String) mtest.get("barcode");
                v = new pushValueExisting(bcode);
                m = new HashMap<>();
                m.put("booktitle", mtest.get("booktitle"));
                m.put("bookauthor", mtest.get("bookauthor"));
                m.put("publisher", mtest.get("publisher"));
                m.put("isbn", mtest.get("isbn"));
                m.put("classification_code", mtest.get("classification_code"));
                m.put("barcode", mtest.get("barcode"));
                m.put("classification_pos", Integer.valueOf((String) mtest.get("classification_pos")));
                m.put("classification", mtest.get("classification"));
                m.put("date", mtest.get("date"));
                m.put("copies", Integer.valueOf((String) mtest.get("copies")));
                m.put("edition", mtest.get("edition"));
                m.put("shelf", mtest.get("shelf"));
                m.put("deck", mtest.get("deck"));
                m.put("key", mtest.get("key"));
                m.put("status", mtest.get("status"));
                m.put("timestamp", mtest.get("timestamp"));
                m.put("remaining_copies", Integer.valueOf((String) mtest.get("remaining_copies")));
                m.put("price", Integer.valueOf((String) mtest.get("price")));
                m.put("cover", mtest.get("cover"));
                m.put("borrowed_books", Integer.valueOf((String) mtest.get("borrowed_books")));
                m.put("overdue_books", Integer.valueOf((String) mtest.get("overdue_books")));
                m.put("lost_books", Integer.valueOf((String) mtest.get("lost_books")));
                m.put("damaged_books", Integer.valueOf((String) mtest.get("damaged_books")));
                v.pushData("books/" + new getUID().getUid(), m);
                m.clear();

                try {
                    TimeUnit.SECONDS.sleep(1); // Delay for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GlassPanePopup.closePopupLast();
            GlassPanePopup.showPopup(new done_import());
        }
    }//GEN-LAST:event_myButtonborderless5ActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        Option option = new DefaultOption() {
            @Override
            public float opacity() {
                return 0.6f;
            }

            @Override
            public boolean closeWhenClickOutside() {
                return false;
            }

            @Override
            public Color background() {
                return new Color(33, 33, 33);
            }

        };
        GlassPanePopup.showPopup(new delete_all(), option, "delete");
    }//GEN-LAST:event_cancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcodename;
    private javax.swing.JLabel barcodenamelabel;
    private javax.swing.JLabel books;
    private libratech.design.MyButtonborder cancel;
    private javax.swing.JLabel editprofile;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailaddlabel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JTextPane goals;
    private javax.swing.JLabel goalslabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextPane libratech;
    private javax.swing.JLabel libratechlabel;
    private javax.swing.JTextField limit;
    private javax.swing.JLabel limitlabel;
    private libratech.design.MaterialTabbed materialTabbed1;
    private javax.swing.JTextPane mission;
    private javax.swing.JLabel missionlabel;
    private libratech.design.MyButtonborderless myButtonborderless4;
    private libratech.design.MyButtonborderless myButtonborderless5;
    private javax.swing.JTextField overduefines;
    private javax.swing.JLabel overduefineslabel;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPasswordField pass1;
    private javax.swing.JPasswordField pass2;
    private javax.swing.JLabel passwrodsecuritylabel;
    private libratech.design.PhotoCover photoCover1;
    private javax.swing.JLabel pwdlabel;
    private javax.swing.JLabel pwdlabel1;
    private javax.swing.JLabel pwdlabel2;
    private javax.swing.JTextField schoolid;
    private javax.swing.JLabel schoolidlabel;
    private javax.swing.JTextField schoolname;
    private javax.swing.JLabel schoolnamelabel;
    private javax.swing.JLabel schoolnamelabel1;
    private javax.swing.JTextPane vision;
    private javax.swing.JLabel visionlabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        materialTabbed1.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel1.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        editprofile.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        passwrodsecuritylabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        myButtonborderless4.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButtonborderless5.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        cancel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        schoolidlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        schoolnamelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        schoolnamelabel1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        schoolname.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        schoolid.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        pwdlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        pwdlabel1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        pwdlabel2.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        emailaddlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        email.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        pass.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        pass1.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        pass2.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        books.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        barcodenamelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        barcodename.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        overduefineslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        overduefines.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        limitlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        limit.setFont(new Font("Poppins Regular", Font.PLAIN, 12));

        libratechlabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        missionlabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        visionlabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        goalslabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        //dataprivacylabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        //termslabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));        
        libratech.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        mission.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        vision.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        goals.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        //dataprivacy.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        //terms.setFont(new Font("Poppins Regular", Font.PLAIN, 12));

    }

}
