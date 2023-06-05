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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.EventAction;
import libratech.books.inshelf.StatusType;
import libratech.books.inshelf.TableStatus;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.ScrollBarCustom;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.util.firebaseInit;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import libratech.design.DefaultOption;
import libratech.design.Option;

import libratech.models.pushValueExisting;
import libratech.user.students.EventActionStudent;
import libratech.user.students.Student;
import libratech.util.smtp;

/**
 *
 * @author HBUSER
 */
public class reference_list extends javax.swing.JPanel {

    ImageScaler scaler = new ImageScaler();
    private ChildEventListener accinfo;

    private ChildEventListener studentinfo;
    private DatabaseReference.CompletionListener completionListener;

    private ChildEventListener booksinfo;
    private final String path_book = "admin_reference/";
    private final DatabaseReference book = FirebaseDatabase.getInstance().getReference(path_book);
    private ValueEventListener data;

    private DatabaseReference dbRef;

    DefaultTableModel mod;
    private DatabaseReference dbRef1;
    private List<Object> columnData;
    private HashMap<String, Object> m;
    private pushValueExisting v;
    private pushValue v2;

    public reference_list() {
        Random random = new Random();
        initComponents();

        new firebaseInit().initFirebase();
        initFont();
        scaler.scaleImage(jLabel1, "src\\main\\resources\\arrow-left-line.png");
        this.mod = (DefaultTableModel) studentTable1.getModel();
        studentTable1.fixTable(jScrollPane3);
        studentTable1.setBackground(new Color(250, 250, 250));

        completionListener = (DatabaseError error, DatabaseReference ref) -> {
            if (error != null) {
                System.out.println("Error removing value: " + error.getMessage());
            } else {
            }
        };

        retrieveDataUsers();
    }

    private void retrieveDataUsers() {

        EventActionStudent eventAction = new EventActionStudent() {
            @Override
            public void update(Student Student) {

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
                GlassPanePopup.showPopup(new change_status_admin(Student.getUID(), Student.getTransactionKey()), option);
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
        dbRef = FirebaseDatabase.getInstance().getReference("admin_reference");
        dbRef.addValueEventListener(data = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String account_name = child.child("account_name").getValue(String.class) + " - " + child.child("account_number").getValue(String.class);
                    String uid = child.child("uid").getValue(String.class);
                    String key = child.child("reference_no").getValue(String.class);
                    int plan = child.child("plan").getValue(Integer.class);
                    String planString = "";
                    if (plan == 0) {
                        planString = "Standard";
                    } else {
                        planString = "Premium";
                    }

                    studentTable1.addRow(new Student(account_name, uid, planString, key).toRowTablePayment(eventAction));
                    mod.fireTableDataChanged();
                    studentTable1.repaint();
                    studentTable1.revalidate();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

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

        jPanel12 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        userinfolabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentTable1 = new libratech.user.students.studentTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);

        jPanel12.setBackground(new java.awt.Color(250, 250, 250));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel12.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        userinfolabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        userinfolabel.setText("Pending Payment List");

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
                {null, null, null, null}
            },
            new String [] {
                "Account Name", "UID", "Plan", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(studentTable1);

        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setPreferredSize(new java.awt.Dimension(25, 25));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userinfolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userinfolabel)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private libratech.user.students.studentTable studentTable1;
    private javax.swing.JLabel userinfolabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        userinfolabel.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        studentTable1.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
    }
}
