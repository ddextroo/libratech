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
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import libratech.admin.planChecker;
import libratech.design.DefaultOption;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.Option;
import libratech.design.RoundedPanel;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.user.students.EventActionStudent;
import libratech.user.students.StatusTypeStudent;
import libratech.user.students.Student;
import libratech.user.students.TableStatusStudent;
import libratech.util.firebaseInit;

/**
 *
 * @author HBUSER
 */
public class user_menu extends javax.swing.JPanel {

    private List<Student> students;
    private DatabaseReference dbRef;
    private DatabaseReference dbRef2;
    private DatabaseReference dbRef3;
    DefaultTableModel mod;
    DefaultTableModel mod2;
    DefaultTableModel mod3;
    private String path = "analytics/" + new getUID().getUid() + "/";
    private DatabaseReference analytics = FirebaseDatabase.getInstance().getReference(path);
    private HashMap<String, Object> m;
    private pushValue v;
    ImageScaler scaler = new ImageScaler();
    int plan;
    private ChildEventListener accinfo;
    private final String path1 = "users/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path1);

    public user_menu() {
        initComponents();
        initFont();
        this.mod = (DefaultTableModel) studentTable1.getModel();
        this.mod2 = (DefaultTableModel) studentTable2.getModel();
        this.mod3 = (DefaultTableModel) transactionTable1.getModel();
        new firebaseInit().initFirebase();
        studentTable1.fixTable(jScrollPane2);
        studentTable2.fixTable(jScrollPane4);
        transactionTable1.fixTable(jScrollPane1);
        retrieveDataGeneral();
        retrieveDataRestricted();
        retrieveDataTransactions();
        scaler.scaleImage(scanner, "src\\main\\resources\\qr-scan-line.png");
        scaler.scaleImage(searchicon, "src\\main\\resources\\search-line.png");
        materialTabbed1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        accinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                System.out.println(_childKey);
                if (_childKey.equals(new getUID().getUid())) {
                    plan = Integer.parseInt(_childValue.get("plan").toString());

                    if (plan == 0) {
                        scanner.setVisible(false);
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
                if (_childKey.equals(new getUID().getUid())) {

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

    private void retrieveDataGeneral() {
        // Fetch data from Firebase and create table
        EventActionStudent eventAction = new EventActionStudent() {

            @Override
            public void update(Student student) {
                System.out.println("Ck: " + student.getIDnumber());
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
                GlassPanePopup.showPopup(new edit_user(student.getIDnumber()), option, "edit");
            }

            @Override
            public void selectIDNumber(String idNumber) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public String getSelectedIDNumber() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };

        dbRef = FirebaseDatabase.getInstance().getReference("students/" + new getUID().getUid());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.hasChild("penalties") && child.child("penalties").getValue(Integer.class) < 3) {
                        String key = child.child("idno").getValue(String.class);
                        String email = child.child("email").getValue(String.class);
                        String IDnumber = child.child("idno").getValue(String.class);

                        studentTable1.addRow(new Student(email, IDnumber, StatusTypeStudent.Active).toRowTable(eventAction));
                        new Student().setIDnumber(key);
                        mod.fireTableDataChanged();
                        studentTable1.repaint();
                        studentTable1.revalidate();
                    }
                }
                v = new pushValue("active");
                m = new HashMap<>();
                m.put("total", mod.getRowCount());
                v.pushData(path, m);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }

    private void retrieveDataRestricted() {
        // Fetch data from Firebase and create table
        EventActionStudent eventAction = new EventActionStudent() {

            @Override
            public void update(Student student) {
                System.out.println("Ck: " + student.getIDnumber());
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
                GlassPanePopup.showPopup(new clear_penalties(student.getIDnumber()), option, "clear");
            }

            @Override
            public void selectIDNumber(String idNumber) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public String getSelectedIDNumber() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };

        dbRef2 = FirebaseDatabase.getInstance().getReference("students/" + new getUID().getUid());
        dbRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod2.setRowCount(0);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.hasChild("penalties") && child.child("penalties").getValue(Integer.class) >= 3) {
                        String key = child.child("idno").getValue(String.class);
                        String email = child.child("email").getValue(String.class);
                        String IDnumber = child.child("idno").getValue(String.class);

                        studentTable2.addRow(new Student(email, IDnumber, StatusTypeStudent.Restricted).toRowTable(eventAction));
                        new Student().setIDnumber(key);
                        mod.fireTableDataChanged();
                        studentTable2.repaint();
                        studentTable2.revalidate();
                    }
                }
                v = new pushValue("restricted");
                m = new HashMap<>();
                m.put("total", mod2.getRowCount());
                v.pushData(path, m);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }

    private void retrieveDataTransactions() {
        // Fetch data from Firebase and create table
        EventActionStudent eventAction = new EventActionStudent() {

            @Override
            public void update(Student student) {
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
                GlassPanePopup.showPopup(new cartreturn(student.getTransactionKey()), option, "transaction");
            }

            @Override
            public void selectIDNumber(String idNumber) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public String getSelectedIDNumber() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };

        dbRef3 = FirebaseDatabase.getInstance().getReference("borrowerlist/" + new getUID().getUid());
        dbRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod3.setRowCount(0);

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    int booksBorrowed = (int) child.getChildrenCount() - 1;
                    String key = child.getKey();
                    String IDnumber = child.child("idno").getValue(String.class);

                    transactionTable1.addRow(new Student(key, IDnumber, booksBorrowed, StatusTypeStudent.Pending).toRowTableSelectTransaction(eventAction));
                    new Student().setIDnumber(key);
                    mod3.fireTableDataChanged();
                    transactionTable1.repaint();
                    transactionTable1.revalidate();
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

        materialTabbed2 = new libratech.design.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        userslabel = new javax.swing.JLabel();
        myButtonborderless1 = new libratech.design.MyButtonborderless();
        scanner = new javax.swing.JLabel();
        jPanel10 = new RoundedPanel(12, new Color(245,245,245,0));
        searchbar = new javax.swing.JTextField();
        searchicon = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        materialTabbed1 = new libratech.design.MaterialTabbed();
        jPanel2 = new RoundedPanel(12, new Color(255,255,255));
        jScrollPane2 = new javax.swing.JScrollPane();
        studentTable1 = new libratech.user.students.studentTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        studentTable2 = new libratech.user.students.studentTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        transactionTable1 = new libratech.user.students.transactionTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(224, 224, 224));
        jPanel1.setLayout(new java.awt.BorderLayout(30, 10));

        jPanel8.setBackground(new java.awt.Color(224, 224, 224));

        userslabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        userslabel.setForeground(new java.awt.Color(58, 58, 58));
        userslabel.setText("Users");

        myButtonborderless1.setForeground(new java.awt.Color(250, 250, 250));
        myButtonborderless1.setText("Add User");
        myButtonborderless1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myButtonborderless1MouseClicked(evt);
            }
        });
        myButtonborderless1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButtonborderless1ActionPerformed(evt);
            }
        });

        scanner.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scanner.setPreferredSize(new java.awt.Dimension(25, 25));
        scanner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scannerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                scannerMouseExited(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setOpaque(false);

        searchbar.setBackground(new java.awt.Color(250, 250, 250, 0));
        searchbar.setBorder(null);
        searchbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbarActionPerformed(evt);
            }
        });
        searchbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchbarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchbarKeyTyped(evt);
            }
        });

        searchicon.setPreferredSize(new java.awt.Dimension(20, 20));
        searchicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchiconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchicon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchbar)
                    .addComponent(searchicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel10.setOpaque(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(userslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 654, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myButtonborderless1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userslabel)
                    .addComponent(myButtonborderless1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scanner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        materialTabbed1.setBackground(new java.awt.Color(250, 250, 250));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        studentTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Email", "ID Number", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(studentTable1);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        materialTabbed1.addTab("General", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        studentTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Email", "ID Number", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(studentTable2);

        jPanel5.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        materialTabbed1.addTab("Restricted User", jPanel5);

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setLayout(new java.awt.BorderLayout());

        transactionTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Transaction Key", "ID Number", "Books Borrowed", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(transactionTable1);
        if (transactionTable1.getColumnModel().getColumnCount() > 0) {
            transactionTable1.getColumnModel().getColumn(0).setResizable(false);
            transactionTable1.getColumnModel().getColumn(1).setResizable(false);
            transactionTable1.getColumnModel().getColumn(2).setResizable(false);
            transactionTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        materialTabbed1.addTab("Transaction", jPanel3);

        jPanel9.add(materialTabbed1);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);
        jPanel1.add(filler1, java.awt.BorderLayout.LINE_START);
        jPanel1.add(filler2, java.awt.BorderLayout.LINE_END);
        jPanel1.add(filler3, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void myButtonborderless1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myButtonborderless1MouseClicked
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
        GlassPanePopup.showPopup(new add_user(), option, "add");
    }//GEN-LAST:event_myButtonborderless1MouseClicked

    private void myButtonborderless1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButtonborderless1ActionPerformed

    private void scannerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scannerMouseClicked
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
        GlassPanePopup.showPopup(new scanbarcode(), option, "scan");
    }//GEN-LAST:event_scannerMouseClicked

    private void scannerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scannerMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_scannerMouseExited

    private void searchbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbarActionPerformed

    private void searchbarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_searchbarKeyPressed

    private void searchbarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbarKeyTyped
        // TODO add your handling code here:
        EventActionStudent eventAction = new EventActionStudent() {

            @Override
            public void update(Student student) {
                System.out.println("Ck: " + student.getIDnumber());
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
                GlassPanePopup.showPopup(new edit_user(student.getIDnumber()), option, "edit");
            }

            @Override
            public void selectIDNumber(String idNumber) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public String getSelectedIDNumber() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };

        dbRef = FirebaseDatabase.getInstance().getReference("students/" + new getUID().getUid());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.hasChild("penalties") && child.child("penalties").getValue(Integer.class) < 3) {
                        if (child.child("idno").getValue(String.class).toLowerCase().contains(searchbar.getText())) {
                            String key = child.child("idno").getValue(String.class);
                            String email = child.child("email").getValue(String.class);
                            String IDnumber = child.child("idno").getValue(String.class);

                            studentTable1.addRow(new Student(email, IDnumber, StatusTypeStudent.Active).toRowTable(eventAction));
                            new Student().setIDnumber(key);
                            mod.fireTableDataChanged();
                            studentTable1.repaint();
                            studentTable1.revalidate();
                        }
                    }
                }
                v = new pushValue("active");
                m = new HashMap<>();
                m.put("total", mod.getRowCount());
                v.pushData(path, m);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });
    }//GEN-LAST:event_searchbarKeyTyped

    private void searchiconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchiconMouseClicked
        // TODO add your handling code here:
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
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(user_menu.this);
        frame.dispose();
    }//GEN-LAST:event_searchiconMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public libratech.design.MaterialTabbed materialTabbed1;
    private libratech.design.MaterialTabbed materialTabbed2;
    private libratech.design.MyButtonborderless myButtonborderless1;
    private javax.swing.JLabel scanner;
    private javax.swing.JTextField searchbar;
    private javax.swing.JLabel searchicon;
    private libratech.user.students.studentTable studentTable1;
    private libratech.user.students.studentTable studentTable2;
    private libratech.user.students.transactionTable transactionTable1;
    private javax.swing.JLabel userslabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        materialTabbed1.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        userslabel.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        myButtonborderless1.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        searchbar.setFont(new Font("Poppins Regular", Font.PLAIN, 12));

    }

}
