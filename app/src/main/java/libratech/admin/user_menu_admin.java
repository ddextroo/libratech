/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.admin;

import libratech.dashboard.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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
public class user_menu_admin extends javax.swing.JPanel {

    private List<Student> students;
    private DatabaseReference dbRef;
    private DatabaseReference dbRef1;
    DefaultTableModel mod;
    DefaultTableModel mod1;
    private String path = "analytics/" + new getUID().getUid() + "/";
    private DatabaseReference analytics = FirebaseDatabase.getInstance().getReference(path);
    private HashMap<String, Object> m;
    private pushValue v;
    ImageScaler scaler = new ImageScaler();

    public user_menu_admin() {
        initComponents();
        initFont();
        this.mod = (DefaultTableModel) studentTable1.getModel();
        this.mod1 = (DefaultTableModel) studentTable2.getModel();
        new firebaseInit().initFirebase();
        studentTable1.fixTable(jScrollPane2);
        studentTable2.fixTable(jScrollPane3);
        retrieveDataApproved();
        retrieveDataPending();
    }

    private void retrieveDataPending() {
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
                GlassPanePopup.showPopup(new change_status_admin(student.getUID()), option, "change");
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

        dbRef = FirebaseDatabase.getInstance().getReference("users/");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.child("status").getValue(String.class).equals("Pending")) {
                        String key = child.child("uid").getValue(String.class);
                        String email = child.child("email").getValue(String.class);
                        String school_name = child.child("school_name").getValue(String.class);
                        String status = child.child("status").getValue(String.class);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

                        String date1 = child.child("limit_date").getValue(String.class);
                        Date dueDate = null;
                        try {
                            dueDate = dateFormat.parse(date1);
                        } catch (ParseException ex) {
                            Logger.getLogger(user_menu_admin.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Date date = new Date();

                        long differenceInMilliseconds = date.getTime() - dueDate.getTime();
                        long days_remaining = Math.abs(differenceInMilliseconds / (24 * 60 * 60 * 1000));

                        TableStatusStudent statust = new TableStatusStudent();

                        if (status.equals("Pending")) {
                            statust.setType(StatusTypeStudent.Pending);
                            days_remaining = 0;
                        } else if (status.equals("Approved")) {
                            statust.setType(StatusTypeStudent.Approved);
                        }

                        studentTable1.addRow(new Student(school_name, key, email, days_remaining, statust.getType()).toRowTablePromptUsers(eventAction));
                        new Student().setUID(key);
                        mod.fireTableDataChanged();
                        studentTable1.repaint();
                        studentTable1.revalidate();
                    }
                    v = new pushValue("pending");
                    m = new HashMap<>();
                    m.put("total", mod.getRowCount());
                    v.pushData(path, m);
                    m.clear();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }
    private void retrieveDataApproved() {
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

        dbRef1 = FirebaseDatabase.getInstance().getReference("users/");
        dbRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod1.setRowCount(0);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.child("status").getValue(String.class).equals("Approved")) {
                        String key = child.child("uid").getValue(String.class);
                        String email = child.child("email").getValue(String.class);
                        String school_name = child.child("school_name").getValue(String.class);
                        String status = child.child("status").getValue(String.class);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

                        String date1 = child.child("limit_date").getValue(String.class);
                        Date dueDate = null;
                        try {
                            dueDate = dateFormat.parse(date1);
                        } catch (ParseException ex) {
                            Logger.getLogger(user_menu_admin.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Date date = new Date();

                        long differenceInMilliseconds = date.getTime() - dueDate.getTime();
                        long days_remaining = Math.abs(differenceInMilliseconds / (24 * 60 * 60 * 1000));

                        TableStatusStudent statust = new TableStatusStudent();

                        if (status.equals("Pending")) {
                            statust.setType(StatusTypeStudent.Pending);
                            days_remaining = 0;
                        } else if (status.equals("Approved")) {
                            statust.setType(StatusTypeStudent.Approved);
                        }

                        studentTable2.addRow(new Student(school_name, key, email, days_remaining, statust.getType()).toRowTablePromptUsers(eventAction));
                        new Student().setUID(key);
                        mod1.fireTableDataChanged();
                        studentTable2.repaint();
                        studentTable2.revalidate();
                    }
                    v = new pushValue("approved");
                    m = new HashMap<>();
                    m.put("total", mod1.getRowCount());
                    v.pushData(path, m);
                    m.clear();
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
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new RoundedPanel(12, new Color(255,255,255));
        materialTabbed1 = new libratech.design.MaterialTabbed();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentTable1 = new libratech.user.students.adminUserTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentTable2 = new libratech.user.students.adminUserTable();
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(userslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1176, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(userslabel)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        materialTabbed1.setBackground(new java.awt.Color(250, 250, 250));

        studentTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "School Name", "UID", "Email Address", "Days Remaining", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(studentTable1);
        if (studentTable1.getColumnModel().getColumnCount() > 0) {
            studentTable1.getColumnModel().getColumn(0).setResizable(false);
            studentTable1.getColumnModel().getColumn(1).setResizable(false);
            studentTable1.getColumnModel().getColumn(2).setResizable(false);
            studentTable1.getColumnModel().getColumn(3).setResizable(false);
            studentTable1.getColumnModel().getColumn(4).setResizable(false);
            studentTable1.getColumnModel().getColumn(5).setResizable(false);
            studentTable1.getColumnModel().getColumn(5).setHeaderValue("Actions");
        }

        materialTabbed1.addTab("Pending", jScrollPane2);

        studentTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "School Name", "UID", "Email Address", "Days Remaining", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(studentTable2);
        if (studentTable2.getColumnModel().getColumnCount() > 0) {
            studentTable2.getColumnModel().getColumn(0).setResizable(false);
            studentTable2.getColumnModel().getColumn(1).setResizable(false);
            studentTable2.getColumnModel().getColumn(2).setResizable(false);
            studentTable2.getColumnModel().getColumn(3).setResizable(false);
            studentTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        materialTabbed1.addTab("Approved", jScrollPane3);

        jPanel2.add(materialTabbed1, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel2);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);
        jPanel1.add(filler1, java.awt.BorderLayout.LINE_START);
        jPanel1.add(filler2, java.awt.BorderLayout.LINE_END);
        jPanel1.add(filler3, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private libratech.design.MaterialTabbed materialTabbed1;
    private libratech.design.MaterialTabbed materialTabbed2;
    private libratech.user.students.adminUserTable studentTable1;
    private libratech.user.students.adminUserTable studentTable2;
    private javax.swing.JLabel userslabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        userslabel.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        materialTabbed1.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        materialTabbed2.setFont(new Font("Poppins Regular", Font.BOLD, 16));

    }

}
