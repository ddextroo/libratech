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
import libratech.design.ModelPieChart;
import libratech.design.PieChart;
import libratech.design.RoundedPanel;
import libratech.design.RoundedPanelBorderless;
import libratech.models.getUID;

/**
 *
 * @author HBUSER
 */
public class dashboard_menu extends javax.swing.JPanel {

    private ChildEventListener inshelf_total;
    private ChildEventListener borrow_total;
    private ChildEventListener student_total;

    private final String inshelf_path = "analytics/" + new getUID().getUid() + "/";
    private final DatabaseReference inshelf_db = FirebaseDatabase.getInstance().getReference(inshelf_path);
    
    private final String borrow_path = "analytics/" + new getUID().getUid() + "/";
    private final DatabaseReference borrow_db = FirebaseDatabase.getInstance().getReference(inshelf_path);

    private final String student_path = "analytics/" + new getUID().getUid() + "/";
    private final DatabaseReference student_db = FirebaseDatabase.getInstance().getReference(student_path);
    private List<ModelPieChart> pieChartDataList = new ArrayList<>();

    public dashboard_menu() {
        initComponents();
        initFont();
        totalInshelf();
        totalBorrow();
        user_chart();
    }

    private void totalInshelf() {
        inshelf_total = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (_childKey.contains("inshelf")) {
                    inshelf.setText(_childValue.get("total").toString());
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
                if (_childKey.contains("inshelf")) {
                    inshelf.setText(_childValue.get("total").toString());
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
        inshelf_db.addChildEventListener(inshelf_total);
    }
    private void totalBorrow() {
        borrow_total = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (_childKey.contains("borrowed")) {
                    borrowedbooks.setText(_childValue.get("total").toString());
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
                if (_childKey.contains("borrowed")) {
                    borrowedbooks.setText(_childValue.get("total").toString());
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
        borrow_db.addChildEventListener(borrow_total);
    }

    private void user_chart() {
        pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);
        student_total = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (_childKey.contains("active")) {
                    pieChartDataList.add(new ModelPieChart("Active", Integer.parseInt(_childValue.get("total").toString()), new Color(0, 128, 0)));
                }
                if (_childKey.contains("restricted")) {
                    pieChartDataList.add(new ModelPieChart("Restricted", Integer.parseInt(_childValue.get("total").toString()), new Color(221, 65, 65)));
                }
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
                if (_childKey.contains("active")) {
                    for (ModelPieChart data : pieChartDataList) {
                        if (data.getName().equals("Active")) {
                            data.setValues(Integer.parseInt(_childValue.get("total").toString()));
                            break;
                        }
                    }
                }
                if (_childKey.contains("restricted")) {
                    // Find the existing "Restricted" data in the list and update it
                    for (ModelPieChart data : pieChartDataList) {
                        if (data.getName().equals("Restricted")) {
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        coloredRoundedPanel1 = new libratech.design.ColoredRoundedPanel();
        jPanel22 = new RoundedPanelBorderless(13, new Color(4,28,52, 0));
        inshelflabel = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        inshelf = new javax.swing.JLabel();
        coloredRoundedPanel2 = new libratech.design.ColoredRoundedPanel();
        jPanel24 = new RoundedPanelBorderless(13, new Color(4,28,52,0));
        borrowedbookslabel = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        borrowedbooks = new javax.swing.JLabel();
        coloredRoundedPanel3 = new libratech.design.ColoredRoundedPanel();
        jPanel34 = new RoundedPanelBorderless(13, new Color(4,28,52,0));
        overduebookslabel = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        overduebooks = new javax.swing.JLabel();
        coloredRoundedPanel4 = new libratech.design.ColoredRoundedPanel();
        jPanel38 = new RoundedPanelBorderless(13, new Color(4,28,52,0));
        lostbookslabel = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        lostbooks = new javax.swing.JLabel();
        coloredRoundedPanel5 = new libratech.design.ColoredRoundedPanel();
        jPanel25 = new RoundedPanelBorderless(13, new Color(4,28,52,0));
        borrowedbookslabel5 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        damagedbooks = new javax.swing.JLabel();
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("BOOK STATISTICS");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel2);

        jPanel15.setBackground(new java.awt.Color(255, 153, 0));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 16, 0));

        coloredRoundedPanel1.setBackground(new java.awt.Color(238, 238, 238));

        jPanel22.setBackground(new java.awt.Color(4, 28, 52));
        jPanel22.setOpaque(false);

        inshelflabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inshelflabel.setForeground(new java.awt.Color(250, 250, 250));
        inshelflabel.setText("In-Shelf");
        jPanel22.add(inshelflabel);

        jPanel23.setBackground(new java.awt.Color(238, 238, 238));

        inshelf.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        inshelf.setText("0");
        jPanel23.add(inshelf);

        javax.swing.GroupLayout coloredRoundedPanel1Layout = new javax.swing.GroupLayout(coloredRoundedPanel1);
        coloredRoundedPanel1.setLayout(coloredRoundedPanel1Layout);
        coloredRoundedPanel1Layout.setHorizontalGroup(
            coloredRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coloredRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        coloredRoundedPanel1Layout.setVerticalGroup(
            coloredRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel4.add(coloredRoundedPanel1);

        coloredRoundedPanel2.setBackground(new java.awt.Color(238, 238, 238));

        jPanel24.setBackground(new java.awt.Color(4, 28, 52));
        jPanel24.setOpaque(false);

        borrowedbookslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        borrowedbookslabel.setForeground(new java.awt.Color(250, 250, 250));
        borrowedbookslabel.setText("Borrowed Books");
        jPanel24.add(borrowedbookslabel);

        jPanel33.setBackground(new java.awt.Color(238, 238, 238));

        borrowedbooks.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        borrowedbooks.setText("0");
        jPanel33.add(borrowedbooks);

        javax.swing.GroupLayout coloredRoundedPanel2Layout = new javax.swing.GroupLayout(coloredRoundedPanel2);
        coloredRoundedPanel2.setLayout(coloredRoundedPanel2Layout);
        coloredRoundedPanel2Layout.setHorizontalGroup(
            coloredRoundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coloredRoundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        coloredRoundedPanel2Layout.setVerticalGroup(
            coloredRoundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel4.add(coloredRoundedPanel2);

        coloredRoundedPanel3.setBackground(new java.awt.Color(238, 238, 238));
        coloredRoundedPanel3.setOpaque(false);

        jPanel34.setBackground(new java.awt.Color(4, 28, 52));
        jPanel34.setOpaque(false);

        overduebookslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        overduebookslabel.setForeground(new java.awt.Color(250, 250, 250));
        overduebookslabel.setText("Overdue Books");
        jPanel34.add(overduebookslabel);

        jPanel37.setBackground(new java.awt.Color(238, 238, 238));

        overduebooks.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        overduebooks.setText("0");
        jPanel37.add(overduebooks);

        javax.swing.GroupLayout coloredRoundedPanel3Layout = new javax.swing.GroupLayout(coloredRoundedPanel3);
        coloredRoundedPanel3.setLayout(coloredRoundedPanel3Layout);
        coloredRoundedPanel3Layout.setHorizontalGroup(
            coloredRoundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coloredRoundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        coloredRoundedPanel3Layout.setVerticalGroup(
            coloredRoundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel4.add(coloredRoundedPanel3);

        coloredRoundedPanel4.setBackground(new java.awt.Color(238, 238, 238));

        jPanel38.setBackground(new java.awt.Color(4, 28, 52));
        jPanel38.setOpaque(false);

        lostbookslabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lostbookslabel.setForeground(new java.awt.Color(250, 250, 250));
        lostbookslabel.setText("Lost Books");
        jPanel38.add(lostbookslabel);

        jPanel39.setBackground(new java.awt.Color(238, 238, 238));

        lostbooks.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lostbooks.setText("0");
        jPanel39.add(lostbooks);

        javax.swing.GroupLayout coloredRoundedPanel4Layout = new javax.swing.GroupLayout(coloredRoundedPanel4);
        coloredRoundedPanel4.setLayout(coloredRoundedPanel4Layout);
        coloredRoundedPanel4Layout.setHorizontalGroup(
            coloredRoundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coloredRoundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        coloredRoundedPanel4Layout.setVerticalGroup(
            coloredRoundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel4.add(coloredRoundedPanel4);

        coloredRoundedPanel5.setBackground(new java.awt.Color(238, 238, 238));

        jPanel25.setBackground(new java.awt.Color(4, 28, 52));
        jPanel25.setOpaque(false);

        borrowedbookslabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        borrowedbookslabel5.setForeground(new java.awt.Color(250, 250, 250));
        borrowedbookslabel5.setText("Damaged Books");
        jPanel25.add(borrowedbookslabel5);

        jPanel35.setBackground(new java.awt.Color(238, 238, 238));

        damagedbooks.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        damagedbooks.setText("0");
        jPanel35.add(damagedbooks);

        javax.swing.GroupLayout coloredRoundedPanel5Layout = new javax.swing.GroupLayout(coloredRoundedPanel5);
        coloredRoundedPanel5.setLayout(coloredRoundedPanel5Layout);
        coloredRoundedPanel5Layout.setHorizontalGroup(
            coloredRoundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coloredRoundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        coloredRoundedPanel5Layout.setVerticalGroup(
            coloredRoundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coloredRoundedPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel4.add(coloredRoundedPanel5);

        jPanel15.add(jPanel4);

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
        jLabel1.setText("Active - Green | Restricted - Red");
        jPanel5.add(jLabel1, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel5);

        coloredRoundedPanel6.add(jPanel29, java.awt.BorderLayout.PAGE_START);
        coloredRoundedPanel6.add(pieChart1, java.awt.BorderLayout.CENTER);

        jPanel31.add(coloredRoundedPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
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
    private javax.swing.JLabel borrowedbooks;
    private javax.swing.JLabel borrowedbookslabel;
    private javax.swing.JLabel borrowedbookslabel5;
    private javax.swing.JLabel borrowedbookslabel6;
    private libratech.design.ColoredRoundedPanel coloredRoundedPanel1;
    private libratech.design.ColoredRoundedPanel coloredRoundedPanel2;
    private libratech.design.ColoredRoundedPanel coloredRoundedPanel3;
    private libratech.design.ColoredRoundedPanel coloredRoundedPanel4;
    private libratech.design.ColoredRoundedPanel coloredRoundedPanel5;
    private libratech.design.ColoredRoundedPanel coloredRoundedPanel6;
    private javax.swing.JLabel damagedbooks;
    private javax.swing.JLabel inshelf;
    private javax.swing.JLabel inshelflabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lostbooks;
    private javax.swing.JLabel lostbookslabel;
    private javax.swing.JLabel overduebooks;
    private javax.swing.JLabel overduebookslabel;
    private libratech.design.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
public void initFont() {
        jLabel2.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        inshelf.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        inshelflabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        lostbookslabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        borrowedbookslabel5.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        overduebookslabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        borrowedbookslabel6.setFont(new Font("Poppins Regular", Font.BOLD, 36));
        jLabel1.setFont(new Font("Poppins Regular", Font.PLAIN, 20));
        borrowedbookslabel.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        borrowedbooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        overduebooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        lostbooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        damagedbooks.setFont(new Font("Poppins Regular", Font.BOLD, 20));
    }

}
