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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.EventAction;
import libratech.books.inshelf.StatusType;
import libratech.books.inshelf.TableStatus;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.RoundedPanel;
import libratech.design.loading;
import libratech.models.ClassificationInfo;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.models.pushValueExisting;
import libratech.user.students.EventActionStudent;
import libratech.user.students.StatusTypeStudent;
import libratech.user.students.Student;
import libratech.user.students.TableStatusStudent;
import libratech.util.firebaseInit;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author HBUSER
 */
public class select_user extends javax.swing.JPanel {

    private DatabaseReference dbRef;
    DefaultTableModel mod;
    private String key;
    private String title;
    private String parentKey;
    ImageScaler scaler = new ImageScaler();
    private String path = "books/" + new getUID().getUid() + "/";
    private DatabaseReference books = FirebaseDatabase.getInstance().getReference(path);
    private ChildEventListener booksinfo;
    int remaining_copies;
    ClassificationInfo info = new ClassificationInfo();
    private ChildEventListener accinfo;
    private final String path_selectuser = "students/" + new getUID().getUid() + "/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path_selectuser);
    private EventActionStudent eventAction;
    private HashMap<String, Object> m;
    private pushValueExisting v;
    private pushValue v2;
    private DatabaseReference dbRef1;
    String idnum;

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(graphics);
    }

    public select_user() {
        initComponents();
        new firebaseInit().initFirebase();
        studentTable1.fixTable(jScrollPane1);
        this.mod = (DefaultTableModel) studentTable1.getModel();
        retrieveData("");
        initFont();
        scaler.scaleImage(jLabel2, "src\\main\\resources\\arrow-left-line.png");
        setOpaque(false);
        borrow.setVisible(false);
    }

    public select_user(String key, String title) {
        initComponents();
        new firebaseInit().initFirebase();
        jLabel1.setText("Select user for book - " + key);
        this.parentKey = parentKey;
        this.key = key;
        this.title = title;
        studentTable1.fixTable(jScrollPane1);
        this.mod = (DefaultTableModel) studentTable1.getModel();
        retrieveData("");
        initFont();
        scaler.scaleImage(jLabel2, "src\\main\\resources\\arrow-left-line.png");
        scaler.scaleImage(searchicon, "src\\main\\resources\\search-line.png");
        setOpaque(false);
        dateborrowed.setEnabled(false);
        borrow.setVisible(false);
        checkLatestBorrower();

    }

    private void retrieveData(String idn) {
        // Fetch data from Firebase and create table
        eventAction = new EventActionStudent() {
            private String selectedIDNumber;

            @Override
            public void update(Student student) {
                //selectDate(student.getIDnumber());
                //System.out.println(student.getIDnumber());
                dbRef1 = FirebaseDatabase.getInstance().getReference("cart/" + new getUID().getUid() + "/borrower");
                dbRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() < 5) {
                            v = new pushValueExisting("borrower");
                            m = new HashMap<>();
                            m.put("idno", student.getIDnumber());
                            v.pushData("latest_borrower/" + new getUID().getUid(), m);
                            m.clear();
                            v2 = new pushValue(key);
                            m = new HashMap<>();
                            m.put("idno", student.getIDnumber());
                            m.put("book_title", title);
                            m.put("book_key", key);
                            m.put("due_date", duedate.getText());
                            m.put("borrowed_date", dateborrowed.getText());
                            v2.pushData("cart/" + new getUID().getUid() + "/borrower", m);
                            m.clear();
                            GlassPanePopup.closePopupAll();
                        } else {
                            GlassPanePopup.closePopupLast();
                            GlassPanePopup.showPopup(new max_limit());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Error: " + databaseError.getMessage());
                    }
                });
            }

            @Override
            public void selectIDNumber(String idNumber) {
                selectedIDNumber = idNumber;
            }

            @Override
            public String getSelectedIDNumber() {
                return selectedIDNumber;
            }
        };

        dbRef = FirebaseDatabase.getInstance().getReference("students/" + new getUID().getUid());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if ("Active".equals(child.child("status").getValue(String.class)) && child.child("idno").getValue(String.class).contains(idn)) {
                        String fullname = child.child("fullname").getValue(String.class);
                        String IDnumber = child.child("idno").getValue(String.class);

                        studentTable1.addRow(new Student(fullname, IDnumber).toRowTableSelectUser(eventAction));
                        eventAction.selectIDNumber(IDnumber);
                        mod.fireTableDataChanged();
                        studentTable1.repaint();
                        studentTable1.revalidate();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }

    private void checkLatestBorrower() {
        dbRef1 = FirebaseDatabase.getInstance().getReference("latest_borrower/" + new getUID().getUid());
        dbRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("borrower")) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            System.out.println(true);
                            idnum = child.child("idno").getValue(String.class);
                            borrow.setVisible(true);
                        }
                    } else {
                        borrow.setVisible(false);
                        System.out.println(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });
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
        dateChooser2 = new libratech.design.DateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel25 = new RoundedPanel(12, new Color(250,250,250));
        search = new javax.swing.JTextField();
        searchicon = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        dateborrowedlabel = new javax.swing.JLabel();
        jPanel24 = new RoundedPanel(12, new Color(250,250,250));
        dateborrowed = new javax.swing.JTextField();
        duedatelabel = new javax.swing.JLabel();
        jPanel26 = new RoundedPanel(12, new Color(250,250,250));
        duedate = new javax.swing.JTextField();
        borrow = new libratech.design.MyButtonborderless();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable1 = new libratech.user.students.studentTableSelect();

        dateChooser1.setDateFormat("MM-dd-yyyy");
        dateChooser1.setTextRefernce(dateborrowed);

        dateChooser2.setDateFormat("MM-dd-yyyy");
        dateChooser2.setTextRefernce(duedate);

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);

        jPanel8.setBackground(new java.awt.Color(250, 250, 250));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 58, 58));
        jLabel1.setText("Select user");

        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setPreferredSize(new java.awt.Dimension(25, 25));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jPanel25.setBackground(new java.awt.Color(0, 0, 0));
        jPanel25.setOpaque(false);

        search.setBackground(new java.awt.Color(250, 250, 250));
        search.setToolTipText("Search ID Number");
        search.setBorder(null);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });

        searchicon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchicon.setPreferredSize(new java.awt.Dimension(20, 20));
        searchicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchiconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchicon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchicon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        dateborrowedlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateborrowedlabel.setForeground(new java.awt.Color(51, 51, 51));
        dateborrowedlabel.setText("Date Borrowed");

        jPanel24.setBackground(new java.awt.Color(0, 0, 0));
        jPanel24.setOpaque(false);

        dateborrowed.setBackground(new java.awt.Color(250, 250, 250));
        dateborrowed.setBorder(null);
        dateborrowed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateborrowedMouseClicked(evt);
            }
        });
        dateborrowed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateborrowedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateborrowed, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(533, 533, 533))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateborrowed)
                .addContainerGap())
        );

        duedatelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        duedatelabel.setForeground(new java.awt.Color(51, 51, 51));
        duedatelabel.setText("Due Date");

        jPanel26.setBackground(new java.awt.Color(0, 0, 0));
        jPanel26.setOpaque(false);

        duedate.setBackground(new java.awt.Color(250, 250, 250));
        duedate.setBorder(null);
        duedate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                duedateMouseClicked(evt);
            }
        });
        duedate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duedateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(duedate, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(533, 533, 533))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(duedate)
                .addContainerGap())
        );

        borrow.setForeground(new java.awt.Color(250, 250, 250));
        borrow.setText("Select latest borrower");
        borrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrowMouseClicked(evt);
            }
        });
        borrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(dateborrowedlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(duedatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(716, 716, 716))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(duedatelabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dateborrowedlabel)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(borrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setBackground(new java.awt.Color(250, 250, 250));

        studentTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "ID Number", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(studentTable1);

        jPanel9.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_searchKeyTyped

    private void searchiconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchiconMouseClicked
        // TODO add your handling code here:
        retrieveData(search.getText());
        int row = studentTable1.rowAtPoint(evt.getPoint());
        String desiredIDNumber = eventAction.getSelectedIDNumber();
        //System.out.println(desiredIDNumber + " " + row);
    }//GEN-LAST:event_searchiconMouseClicked

    private void dateborrowedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateborrowedMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dateborrowedMouseClicked

    private void dateborrowedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateborrowedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateborrowedActionPerformed

    private void duedateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_duedateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_duedateMouseClicked

    private void duedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duedateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_duedateActionPerformed

    private void borrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_borrowMouseClicked

    private void borrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowActionPerformed
        // TODO add your handling code here:
        v = new pushValueExisting("borrower");
        m = new HashMap<>();
        m.put("idno", idnum);
        v.pushData("latest_borrower/" + new getUID().getUid(), m);
        m.clear();
        v2 = new pushValue(key);
        m = new HashMap<>();
        m.put("idno", idnum);
        m.put("book_title", title);
        m.put("book_key", key);
        m.put("due_date", duedate.getText());
        m.put("borrowed_date", dateborrowed.getText());
        v2.pushData("cart/" + new getUID().getUid() + "/borrower", m);
        m.clear();
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_borrowActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private libratech.design.MyButtonborderless borrow;
    private libratech.design.DateChooser dateChooser1;
    private libratech.design.DateChooser dateChooser2;
    private javax.swing.JTextField dateborrowed;
    private javax.swing.JLabel dateborrowedlabel;
    private javax.swing.JTextField duedate;
    private javax.swing.JLabel duedatelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField search;
    private javax.swing.JLabel searchicon;
    private libratech.user.students.studentTableSelect studentTable1;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        jLabel1.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        studentTable1.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        dateborrowed.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        dateborrowedlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        duedate.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        duedatelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        borrow.setFont(new Font("Poppins Regular", Font.BOLD, 12));

//        coverlabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
//        booktitle.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        booktitlelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        author.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        authorlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        publisher.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        publisherlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        copies.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        copieslabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        shelf.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        shelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        shelf.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        shelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        date.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        datelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        deck.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        decklabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        editionlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        edition.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        isbn.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        isbnlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        classification.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        classificationlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//
//        borrowername.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        borrowernamelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        borrowerid.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        borroweridlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        dateborrowed.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        dateborrowedlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//        controlnumber.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
//        controlnumberlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        search.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
    }
}
