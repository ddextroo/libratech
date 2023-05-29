/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.admin;

import libratech.dashboard.*;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.StatusType;
import libratech.design.ModelPieChart;
import libratech.design.PieChart;
import libratech.design.RoundedPanel;
import libratech.design.RoundedPanelBorderless;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.models.pushValueExisting;
import libratech.util.firebaseInit;

/**
 *
 * @author HBUSER
 */
public class dashboard_menu_admin extends javax.swing.JPanel {

//    private ChildEventListener inshelf_total;
//    private ChildEventListener borrow_total;
//    private ChildEventListener overdue_total;
//    private ChildEventListener lost_total;
//    private ChildEventListener damaged_total;
    private ChildEventListener student_total;

//    private final String inshelf_path = "analytics/" + new getUID().getUid() + "/";
//    private final DatabaseReference inshelf_db = FirebaseDatabase.getInstance().getReference(inshelf_path);
//    private final DatabaseReference borrow_db = FirebaseDatabase.getInstance().getReference(inshelf_path);
//    private final DatabaseReference overdue_db = FirebaseDatabase.getInstance().getReference(inshelf_path);
//    private final DatabaseReference lost_db = FirebaseDatabase.getInstance().getReference(inshelf_path);
//    private final DatabaseReference damaged_db = FirebaseDatabase.getInstance().getReference(inshelf_path);
    private DatabaseReference dbRef;
    private final String student_path = "users/";
    private final DatabaseReference student_db = FirebaseDatabase.getInstance().getReference(student_path);
    private List<ModelPieChart> pieChartDataList = new ArrayList<>();
    private HashMap<String, Object> m;
    private pushValueExisting v;

    public dashboard_menu_admin() {
        initComponents();
        initFont();
        new firebaseInit().initFirebase();
//        totalInshelf();
//        totalBorrow();
//        totalOverdue();
//        totalLost();
//        totalDamaged();
        user_chart();
        totalUsers();
    }

//    private void totalInshelf() {
//        inshelf_total = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = dataSnapshot.getKey();
//                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
//                if (_childKey.contains("inshelf")) {
////                    inshelf.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot ds, String string) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = ds.getKey();
//                final HashMap<String, Object> _childValue = ds.getValue(_ind);
//                if (_childKey.contains("inshelf")) {
//                    inshelf.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot ds) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot ds, String string) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        };
//        inshelf_db.addChildEventListener(inshelf_total);
//    }
//
//    private void totalBorrow() {
//        borrow_total = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = dataSnapshot.getKey();
//                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
//                if (_childKey.contains("borrowed")) {
//                    borrowedbooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot ds, String string) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = ds.getKey();
//                final HashMap<String, Object> _childValue = ds.getValue(_ind);
//                if (_childKey.contains("borrowed")) {
//                    borrowedbooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot ds) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot ds, String string) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        };
//        borrow_db.addChildEventListener(borrow_total);
//    }
//
//    private void totalOverdue() {
//        overdue_total = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = dataSnapshot.getKey();
//                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
//                if (_childKey.contains("overdue")) {
//                    overduebooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot ds, String string) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = ds.getKey();
//                final HashMap<String, Object> _childValue = ds.getValue(_ind);
//                if (_childKey.contains("overdue")) {
//                    overduebooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot ds) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot ds, String string) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        };
//        overdue_db.addChildEventListener(overdue_total);
//    }
//
//    private void totalLost() {
//        lost_total = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = dataSnapshot.getKey();
//                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
//                if (_childKey.contains("lost")) {
//                    lostbooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot ds, String string) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = ds.getKey();
//                final HashMap<String, Object> _childValue = ds.getValue(_ind);
//                if (_childKey.contains("lost")) {
//                    lostbooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot ds) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot ds, String string) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        };
//        lost_db.addChildEventListener(lost_total);
//    }
//
//    private void totalDamaged() {
//        damaged_total = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = dataSnapshot.getKey();
//                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
//                if (_childKey.contains("damaged")) {
//                    damagedbooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot ds, String string) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = ds.getKey();
//                final HashMap<String, Object> _childValue = ds.getValue(_ind);
//                if (_childKey.contains("damaged")) {
//                    damagedbooks.setText(_childValue.get("total").toString());
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot ds) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot ds, String string) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        };
//        damaged_db.addChildEventListener(damaged_total);
//    }
    private void totalUsers() {
        dbRef = FirebaseDatabase.getInstance().getReference("users/");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v = new pushValueExisting("approved");
                    m = new HashMap<>();
                    m.put("total", dataSnapshot.getChildren());
                    v.pushData("analytics/" + new getUID().getUid() + "/", m);
                    m.clear();
                    if (child.hasChild("remaining_copies") && child.child("remaining_copies").getValue(Integer.class) > 0) {

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }

    private void user_chart() {
        pieChart1.setChartType(PieChart.PeiChartType.DEFAULT);
        student_total = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                int count = 0;
                System.out.println(count);
                count++;
                pieChartDataList.add(new ModelPieChart("Approved", Integer.parseInt(_childValue.get("total").toString()), new Color(0, 128, 0)));
                pieChartDataList.add(new ModelPieChart("Pending", Integer.parseInt(_childValue.get("total").toString()), new Color(221, 65, 65)));
                pieChart1.setData(pieChartDataList);
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
                if (_childKey.contains("Approved")) {
                    for (ModelPieChart data : pieChartDataList) {
                        if (data.getName().equals("Approved")) {
                            data.setValues(Integer.parseInt(_childValue.get("total").toString()));
                            break;
                        }
                    }
                }
                if (_childKey.contains("Pending")) {
                    // Find the existing "Restricted" data in the list and update it
                    for (ModelPieChart data : pieChartDataList) {
                        if (data.getName().equals("Pending")) {
                            data.setValues(Integer.parseInt(_childValue.get("total").toString()));
                            break;
                        }
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
        student_db.addChildEventListener(student_total);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        coloredRoundedPanel6 = new libratech.design.ColoredRoundedPanel();
        jPanel29 = new RoundedPanelBorderless(13, new Color(4,28,52));
        jPanel5 = new javax.swing.JPanel();
        borrowedbookslabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pieChart1 = new libratech.design.PieChart();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        jPanel15.setBackground(new java.awt.Color(255, 153, 0));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.LINE_AXIS));

        jPanel31.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        coloredRoundedPanel6.setBackground(new java.awt.Color(238, 238, 238));
        coloredRoundedPanel6.setLayout(new java.awt.BorderLayout());

        jPanel29.setBackground(new java.awt.Color(4, 28, 52));
        jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 50));

        jPanel5.setBackground(new java.awt.Color(4, 28, 52));
        jPanel5.setLayout(new java.awt.BorderLayout());

        borrowedbookslabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        borrowedbookslabel6.setForeground(new java.awt.Color(250, 250, 250));
        borrowedbookslabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        borrowedbookslabel6.setText("User Statistics:");
        jPanel5.add(borrowedbookslabel6, java.awt.BorderLayout.CENTER);

        jLabel1.setForeground(new java.awt.Color(224, 224, 224));
        jLabel1.setText("Approved - Green | Pending - Yellow");
        jPanel5.add(jLabel1, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel5);

        coloredRoundedPanel6.add(jPanel29, java.awt.BorderLayout.PAGE_START);
        coloredRoundedPanel6.add(pieChart1, java.awt.BorderLayout.CENTER);

        jPanel31.add(coloredRoundedPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel borrowedbookslabel6;
    private libratech.design.ColoredRoundedPanel coloredRoundedPanel6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private libratech.design.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
public void initFont() {
//        jLabel2.setFont(new Font("Poppins Regular", Font.BOLD, 20));
//        inshelf.setFont(new Font("Poppins Regular", Font.BOLD, 20));
//        inshelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
//        lostbookslabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
//        borrowedbookslabel5.setFont(new Font("Poppins Regular", Font.BOLD, 14));
//        overduebookslabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        borrowedbookslabel6.setFont(new Font("Poppins Regular", Font.BOLD, 36));
        jLabel1.setFont(new Font("Poppins Regular", Font.PLAIN, 20));
//        borrowedbookslabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
//        borrowedbooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
//        overduebooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
//        lostbooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
//        damagedbooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
    }

}
