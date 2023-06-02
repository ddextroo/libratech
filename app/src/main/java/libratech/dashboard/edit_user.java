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
import com.google.firebase.database.ValueEventListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.EventAction;
import libratech.books.inshelf.StatusType;
import libratech.books.inshelf.TableStatus;
import libratech.design.DefaultOption;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.Option;
import libratech.design.RoundedPanel;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.models.pushValueExisting;
import libratech.user.students.EventActionStudent;
import libratech.user.students.StatusTypeStudent;
import libratech.user.students.Student;
import libratech.user.students.TableStatusStudent;
import libratech.util.firebaseInit;
import libratech.util.storage;

/**
 *
 * @author Admin
 */
public class edit_user extends javax.swing.JPanel {

    private HashMap<String, Object> m;
    private pushValueExisting v;
    private DatabaseReference databaseReference;
    private ChildEventListener studentinfo;
    private DatabaseReference.CompletionListener completionListener;
    private String path;
    private DatabaseReference student;
    private String idnum;
    private DatabaseReference books2;
    ImageScaler scaler = new ImageScaler();

    public edit_user(String idnum) {
        initComponents();
        initFont();
        new firebaseInit().initFirebase();
        setOpaque(false);

        this.idnum = idnum;
        sex.setEditable(false);
        sex.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Male", "Female", "I prefer not to say"}));

        path = "students/" + new getUID().getUid();
        student = FirebaseDatabase.getInstance().getReference(path);
        this.books2 = FirebaseDatabase.getInstance().getReference(path + "/" + idnum);

        studentinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (_childKey.equals(idnum)) {
                    fullname.setText(_childValue.get("fullname").toString());
                    email.setText(_childValue.get("email").toString());
                    dateofbirth.setText(_childValue.get("dateofbirth").toString());
                    coursegrade.setText(_childValue.get("coursegrade").toString());
                    idno.setText(_childValue.get("idno").toString());
                    address.setText(_childValue.get("address").toString());
                    sex.setSelectedItem(_childValue.get("sex").toString());
                    phone.setText(_childValue.get("phone").toString());
                    if (_childValue.containsKey("penalties")) {
                        penalties.setText(_childValue.get("penalties").toString());
                    }
                    if (_childValue.containsKey("fines")) {
                        fines.setText(_childValue.get("fines").toString());
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
                if (_childKey.equals(idnum)) {
                    fullname.setText(_childValue.get("fullname").toString());
                    email.setText(_childValue.get("email").toString());
                    dateofbirth.setText(_childValue.get("dateofbirth").toString());
                    coursegrade.setText(_childValue.get("coursegrade").toString());
                    idno.setText(_childValue.get("idno").toString());
                    address.setText(_childValue.get("address").toString());
                    sex.setSelectedItem(_childValue.get("sex").toString());
                    phone.setText(_childValue.get("phone").toString());
                    if (_childValue.containsKey("penalties")) {
                        penalties.setText(_childValue.get("penalties").toString());
                    }
                    if (_childValue.containsKey("fines")) {
                        fines.setText(_childValue.get("fines").toString());
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
        student.addChildEventListener(studentinfo);

        completionListener = (DatabaseError error, DatabaseReference ref) -> {
            if (error != null) {
                System.out.println("Error removing value: " + error.getMessage());
            } else {
                System.out.println("Value removed successfully.");
            }
        };
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

        dateChooser1 = new libratech.design.DateChooser();
        adduserlabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        fullnamelabel = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(12, new Color(250,250,250,0));
        fullname = new javax.swing.JTextField();
        idnolabel = new javax.swing.JLabel();
        jPanel7 = new RoundedPanel(12, new Color(250,250,250,0));
        idno = new javax.swing.JTextField();
        jPanel8 = new RoundedPanel(12, new Color(250,250,250,0));
        dateofbirth = new javax.swing.JTextField();
        dateofbirthlabel = new javax.swing.JLabel();
        emaillabel = new javax.swing.JLabel();
        jPanel9 = new RoundedPanel(12, new Color(250,250,250,0));
        email = new javax.swing.JTextField();
        jPanel10 = new RoundedPanel(12, new Color(250,250,250,0));
        sex = new libratech.design.ComboBoxSuggestion();
        coursegradelabel = new javax.swing.JLabel();
        contactnumberlabel = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(12, new Color(250,250,250,0));
        coursegrade = new javax.swing.JTextField();
        addresslabel = new javax.swing.JLabel();
        jPanel13 = new RoundedPanel(12, new Color(250,250,250,0));
        phone = new javax.swing.JTextField();
        sexlabel = new javax.swing.JLabel();
        jPanel14 = new RoundedPanel(12, new Color(250,250,250,0));
        address = new javax.swing.JTextField();
        addbutton = new libratech.design.MyButtonborderless();
        cancelbutton = new libratech.design.MyButtonborder();
        delete = new libratech.design.MyButtonborder();
        penaltieslabel = new javax.swing.JLabel();
        jPanel11 = new RoundedPanel(12, new Color(250,250,250,0));
        penalties = new javax.swing.JTextField();
        fineslabel = new javax.swing.JLabel();
        jPanel15 = new RoundedPanel(12, new Color(250,250,250,0));
        fines = new javax.swing.JTextField();

        dateChooser1.setDateFormat("MM-dd-yyyy");
        dateChooser1.setTextRefernce(dateofbirth);

        setBackground(new java.awt.Color(255, 255, 255));

        adduserlabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        adduserlabel.setForeground(new java.awt.Color(58, 58, 58));
        adduserlabel.setText("Edit user");

        fullnamelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fullnamelabel.setForeground(new java.awt.Color(51, 51, 51));
        fullnamelabel.setText("Full Name: ");

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setOpaque(false);

        fullname.setBackground(new java.awt.Color(250, 250, 250,0));
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(536, 536, 536))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fullname, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        idnolabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idnolabel.setForeground(new java.awt.Color(51, 51, 51));
        idnolabel.setText("ID No.");

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setOpaque(false);

        idno.setBackground(new java.awt.Color(250, 250, 250,0));
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idno, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(536, 536, 536))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idno, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setOpaque(false);

        dateofbirth.setBackground(new java.awt.Color(250, 250, 250,0));
        dateofbirth.setBorder(null);
        dateofbirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateofbirthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateofbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(536, 536, 536))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateofbirth, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        dateofbirthlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateofbirthlabel.setForeground(new java.awt.Color(51, 51, 51));
        dateofbirthlabel.setText("Date of Birth:");

        emaillabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emaillabel.setForeground(new java.awt.Color(51, 51, 51));
        emaillabel.setText("Email: ");

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setOpaque(false);

        email.setBackground(new java.awt.Color(250, 250, 250,0));
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(536, 536, 536))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setOpaque(false);

        sex.setBackground(new java.awt.Color(255, 255, 255));
        sex.setEditable(false);
        sex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sex, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        coursegradelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        coursegradelabel.setForeground(new java.awt.Color(51, 51, 51));
        coursegradelabel.setText("Course/Grade: ");

        contactnumberlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        contactnumberlabel.setForeground(new java.awt.Color(51, 51, 51));
        contactnumberlabel.setText("Contact Number: ");

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setOpaque(false);

        coursegrade.setBackground(new java.awt.Color(250, 250, 250,0));
        coursegrade.setBorder(null);
        coursegrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursegradeActionPerformed(evt);
            }
        });
        coursegrade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coursegradeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                coursegradeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(coursegrade, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(535, 535, 535))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coursegrade, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        addresslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addresslabel.setForeground(new java.awt.Color(51, 51, 51));
        addresslabel.setText("Address: ");

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel13.setOpaque(false);

        phone.setBackground(new java.awt.Color(250, 250, 250,0));
        phone.setBorder(null);
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phone, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        sexlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sexlabel.setForeground(new java.awt.Color(51, 51, 51));
        sexlabel.setText("Sex: ");

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.setOpaque(false);

        address.setBackground(new java.awt.Color(250, 250, 250,0));
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

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(address)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        addbutton.setForeground(new java.awt.Color(224, 224, 224));
        addbutton.setText("Save changes");
        addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbuttonActionPerformed(evt);
            }
        });

        cancelbutton.setForeground(new java.awt.Color(23, 23, 23));
        cancelbutton.setText("Cancel");
        cancelbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbuttonActionPerformed(evt);
            }
        });

        delete.setForeground(new java.awt.Color(23, 23, 23));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        penaltieslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        penaltieslabel.setForeground(new java.awt.Color(51, 51, 51));
        penaltieslabel.setText("Penalties");

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setOpaque(false);

        penalties.setEditable(false);
        penalties.setBackground(new java.awt.Color(250, 250, 250,0));
        penalties.setBorder(null);
        penalties.setEnabled(false);
        penalties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penaltiesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(penalties, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(536, 536, 536))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(penalties, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        fineslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fineslabel.setForeground(new java.awt.Color(51, 51, 51));
        fineslabel.setText("Fines");

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel15.setOpaque(false);

        fines.setBackground(new java.awt.Color(250, 250, 250,0));
        fines.setBorder(null);
        fines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fines, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(536, 536, 536))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fines, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(adduserlabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fullnamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emaillabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(coursegradelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(contactnumberlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(addresslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sexlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fineslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cancelbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(147, 147, 147))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateofbirthlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(penaltieslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 145, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(adduserlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fullnamelabel)
                            .addComponent(coursegradelabel))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idnolabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(dateofbirthlabel)
                                                .addGap(4, 4, 4)
                                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(emaillabel))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(addresslabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sexlabel)
                                                .addGap(3, 3, 3)))
                                        .addGap(4, 4, 4)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(contactnumberlabel))
                        .addGap(18, 18, 18)
                        .addComponent(penaltieslabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fineslabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullnameActionPerformed

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

    private void idnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idnoActionPerformed

    private void idnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idnoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_idnoKeyTyped

    private void dateofbirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateofbirthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateofbirthActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_emailKeyTyped

    private void coursegradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursegradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coursegradeActionPerformed

    private void coursegradeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coursegradeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_coursegradeKeyTyped

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void addressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_addressKeyTyped

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
        // TODO add your handling code here:
        String name = fullname.getText();
        String idnumss = idno.getText();
        String datebirth = dateofbirth.getText();
        String emailaddress = email.getText();
        String course = coursegrade.getText();
        String number = phone.getText();
        String user_address = address.getText();
        String fine = fines.getText();
        String user_sex = sex.getSelectedItem().toString();

        if (fullname.getText().equals("") || idno.getText().equals("") || dateofbirth.getText().equals("") || email.getText().equals("") || coursegrade.getText().equals("") || phone.getText().equals("") || address.getText().equals("") || sex.getSelectedItem().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Error: Field is empty", "Error", ERROR_MESSAGE);
        } else {
            String getnow = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            String uidpath = new getUID().getUid();

            v = new pushValueExisting(idnum);
            m = new HashMap<>();
            m.put("fullname", name);
            m.put("idno", idnum);
            m.put("dateofbirth", datebirth);
            m.put("email", emailaddress);
            m.put("coursegrade", course);
            m.put("phone", number);
            m.put("address", user_address);
            m.put("sex", user_sex);
            m.put("fines", Double.valueOf(fine));
            m.put("key", idnum);
            m.put("timestamp", getnow);
            v.pushData("students/" + uidpath, m);
            m.clear();
            GlassPanePopup.closePopupAll();
        }
    }//GEN-LAST:event_addbuttonActionPerformed

    private void cancelbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbuttonActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cancelbuttonActionPerformed

    private void fullnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fullnameKeyTyped
        // TODO add your handling code here
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            evt.consume();
        }

    }//GEN-LAST:event_fullnameKeyTyped

    private void coursegradeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coursegradeKeyReleased
        // TODO add your handling code here:
        String text = coursegrade.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            coursegrade.setText(text);
        }

        if (text.length() > 24) {
            str = text.substring(0, 24);
            coursegrade.setText("");
        }

        if (text.length() == 0) {
            coursegrade.setText(str);
            str = "";
        }
    }//GEN-LAST:event_coursegradeKeyReleased

    private void idnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idnoKeyReleased
        // TODO add your handling code here:
        String text = idno.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            idno.setText(text);
        }

        if (text.length() > 7) {
            str = text.substring(0, 7);
            idno.setText("");
        }

        if (text.length() == 0) {
            idno.setText(str);
            str = "";
        }
    }//GEN-LAST:event_idnoKeyReleased

    private void sexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, comboBoxSuggestion1, "Select", JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_sexActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void phoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneKeyReleased

    private void phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneKeyTyped

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        books2.getRef().removeValue(completionListener);
        home home = new home();
        home.setVisible(true);
        home.jPanel15.setBackground(Color.decode("#041C34"));
        home.jPanel10.setBackground(Color.decode("#041C34"));
        home.jPanel18.setBackground(Color.decode("#0E2C4A"));
        home.jPanel20.setBackground(Color.decode("#041C34"));
        scaler.scaleImage(home.jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(home.jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(home.jLabel18, "src\\main\\resources\\user-fill.png");
        scaler.scaleImage(home.jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) home.jPanel3.getLayout();
        cardLayout.show(home.jPanel3, "user");
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(edit_user.this);
        frame.dispose();
    }//GEN-LAST:event_deleteActionPerformed

    private void penaltiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penaltiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_penaltiesActionPerformed

    private void finesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_finesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private libratech.design.MyButtonborderless addbutton;
    private javax.swing.JTextField address;
    private javax.swing.JLabel addresslabel;
    private javax.swing.JLabel adduserlabel;
    private libratech.design.MyButtonborder cancelbutton;
    private javax.swing.JLabel contactnumberlabel;
    private javax.swing.JTextField coursegrade;
    private javax.swing.JLabel coursegradelabel;
    private libratech.design.DateChooser dateChooser1;
    private javax.swing.JTextField dateofbirth;
    private javax.swing.JLabel dateofbirthlabel;
    private libratech.design.MyButtonborder delete;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emaillabel;
    private javax.swing.JTextField fines;
    private javax.swing.JLabel fineslabel;
    private javax.swing.JTextField fullname;
    private javax.swing.JLabel fullnamelabel;
    private javax.swing.JTextField idno;
    private javax.swing.JLabel idnolabel;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField penalties;
    private javax.swing.JLabel penaltieslabel;
    private javax.swing.JTextField phone;
    private libratech.design.ComboBoxSuggestion sex;
    private javax.swing.JLabel sexlabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        fullnamelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        fullname.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        idnolabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        idno.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        coursegradelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        coursegrade.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        sexlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        sex.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        dateofbirthlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        dateofbirth.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        emaillabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        email.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        addresslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        address.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        contactnumberlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        phone.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        cancelbutton.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        delete.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        addbutton.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        adduserlabel.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        penaltieslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        penalties.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        fineslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        fines.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
    }

}
