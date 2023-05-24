/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.dashboard;

import java.awt.Font;
import libratech.design.GlassPanePopup;
import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.EventAction;
import libratech.books.inshelf.StatusType;
import libratech.books.inshelf.TableStatus;
import libratech.design.ImageScaler;
import libratech.design.RoundedPanel;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.util.firebaseInit;

/**
 *
 * @author HBUSER
 */
public class books_menu extends javax.swing.JPanel {

    private List<Book> books;
    private DatabaseReference dbRef;
    private DatabaseReference dbRef1;
    private DatabaseReference dbRef2;
    DefaultTableModel mod;
    DefaultTableModel mod1;
    private String path = "analytics/" + new getUID().getUid() + "/";
    private DatabaseReference analytics = FirebaseDatabase.getInstance().getReference(path);
    private HashMap<String, Object> m;
    private pushValue v;
    ImageScaler scaler = new ImageScaler();
    boolean exist;

    public books_menu() {
        initComponents();
        initFont();
        this.mod = (DefaultTableModel) inshelfTable1.getModel();
        this.mod1 = (DefaultTableModel) inshelfTable2.getModel();
        new firebaseInit().initFirebase();
        inshelfTable1.fixTable(jScrollPane1);
        inshelfTable2.fixTable(jScrollPane2);
        scaler.scaleImage(notificationLabel1, "src\\main\\resources\\bookmark-line.png");
        notificationLabel1.setEnabled(false);
        checkTransaction();
        retrieveData();
        retrieveDataborrowed();

    }

    private void checkTransaction() {
        dbRef1 = FirebaseDatabase.getInstance().getReference("cart/" + new getUID().getUid() + "/borrower");
        dbRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    exist = true;
                    notificationLabel1.showBadge();
                } else {
                    exist = false;
                    notificationLabel1.hideBadge();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });
    }

    private void retrieveData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void update(Book book) {
                System.out.println("Ck: " + book.getChildKey());
                GlassPanePopup.showPopup(new edit_book(book.getChildKey(), inshelfTable1));
            }
        };

        dbRef = FirebaseDatabase.getInstance().getReference("books/" + new getUID().getUid());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if ("Available".equals(child.child("status").getValue(String.class))) {
                        String key = child.child("key").getValue(String.class);
                        String bookCoverUrl = child.child("cover").getValue(String.class);
                        String bookTitle = child.child("booktitle").getValue(String.class);
                        String publisher = child.child("publisher").getValue(String.class);
                        String barcode = child.child("barcode").getValue(String.class);
                        String classification = child.child("classification").getValue(String.class);
                        String author = child.child("bookauthor").getValue(String.class);
                        String dewey = child.child("isbn").getValue(String.class);
                        String call_no = child.child("call_number").getValue(String.class);
                        String copies = child.child("copies").getValue(String.class);
                        String deck = child.child("deck").getValue(String.class);
                        String shelf = child.child("shelf").getValue(String.class);
                        String date = child.child("date").getValue(String.class);
                        String status = child.child("status").getValue(String.class);

                        TableStatus statust = new TableStatus();

                        if (status.equals("Available")) {
                            statust.setType(StatusType.Available);
                        } else if (status.equals("Borrowed")) {
                            statust.setType(StatusType.Borrowed);
                        } else if (status.equals("Lost")) {
                            statust.setType(StatusType.Lost);
                        } else if (status.equals("Damaged")) {
                            statust.setType(StatusType.Damaged);
                        } else {
                            statust.setType(StatusType.Returned);
                        }
                        inshelfTable1.addRow(new Book(bookTitle, publisher, classification, author, barcode, copies, statust.getType(), key).toRowTable(eventAction));
                        new Book().setChildKey(key);
                        mod.fireTableDataChanged();
                        inshelfTable1.repaint();
                        inshelfTable1.revalidate();
                    }
                }
                v = new pushValue("inshelf");
                m = new HashMap<>();
                m.put("total", mod.getRowCount());
                v.pushData(path, m);
                m.clear();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }
    private void retrieveDataborrowed() {
        EventAction eventAction = new EventAction() {
            @Override
            public void update(Book book) {
                System.out.println("Ck: " + book.getChildKey());
                GlassPanePopup.showPopup(new edit_book(book.getChildKey(), inshelfTable2));
            }
        };

        dbRef2 = FirebaseDatabase.getInstance().getReference("books/" + new getUID().getUid());
        dbRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod1.setRowCount(0);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if ("Borrowed".equals(child.child("status").getValue(String.class))) {
                        String key = child.child("key").getValue(String.class);
                        String bookTitle = child.child("booktitle").getValue(String.class);
                        String publisher = child.child("publisher").getValue(String.class);
                        String barcode = child.child("barcode").getValue(String.class);
                        String classification = child.child("classification").getValue(String.class);
                        String author = child.child("bookauthor").getValue(String.class);
                        String copies = child.child("copies").getValue(String.class);
                        String status = child.child("status").getValue(String.class);

                        TableStatus statust = new TableStatus();

                        if (status.equals("Available")) {
                            statust.setType(StatusType.Available);
                        } else if (status.equals("Borrowed")) {
                            statust.setType(StatusType.Borrowed);
                        } else if (status.equals("Lost")) {
                            statust.setType(StatusType.Lost);
                        } else if (status.equals("Damaged")) {
                            statust.setType(StatusType.Damaged);
                        } else {
                            statust.setType(StatusType.Returned);
                        }
                        inshelfTable2.addRow(new Book(bookTitle, publisher, classification, author, barcode, copies, statust.getType(), key).toRowTable(eventAction));
                        new Book().setChildKey(key);
                        mod1.fireTableDataChanged();
                        inshelfTable2.repaint();
                        inshelfTable2.revalidate();
                    }
                }
                v = new pushValue("borrowed");
                m = new HashMap<>();
                m.put("total", mod1.getRowCount());
                v.pushData(path, m);
                m.clear();

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

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        myButtonborderless1 = new libratech.design.MyButtonborderless();
        notificationLabel1 = new libratech.design.NotificationLabel();
        jPanel9 = new javax.swing.JPanel();
        materialTabbed1 = new libratech.design.MaterialTabbed();
        jPanel2 = new RoundedPanel(12, new Color(255,255,255));
        jScrollPane1 = new javax.swing.JScrollPane();
        inshelfTable1 = new libratech.books.inshelf.InshelfTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inshelfTable2 = new libratech.books.inshelf.InshelfTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jLabel1.setText("Books");

        myButtonborderless1.setForeground(new java.awt.Color(250, 250, 250));
        myButtonborderless1.setText("Add book");
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

        notificationLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        notificationLabel1.setPreferredSize(new java.awt.Dimension(25, 25));
        notificationLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1160, Short.MAX_VALUE)
                .addComponent(notificationLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myButtonborderless1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myButtonborderless1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(notificationLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        materialTabbed1.setBackground(new java.awt.Color(250, 250, 250));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        inshelfTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Title", "Book Publisher", "Classification", "Book Author", "Book Code", "Number of Copies", "Book Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(inshelfTable1);
        if (inshelfTable1.getColumnModel().getColumnCount() > 0) {
            inshelfTable1.getColumnModel().getColumn(7).setResizable(false);
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        materialTabbed1.addTab("In-Shelf", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        inshelfTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Title", "Book Publisher", "Classification", "Book Author", "Book Code", "Number of Copies", "Book Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(inshelfTable2);
        if (inshelfTable2.getColumnModel().getColumnCount() > 0) {
            inshelfTable2.getColumnModel().getColumn(7).setResizable(false);
        }

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        materialTabbed1.addTab("Borrowed", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1497, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Overdue", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1497, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Lost", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1497, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Damaged", jPanel7);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(856, 856, 856)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(521, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(640, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Test", jPanel3);

        jPanel9.add(materialTabbed1);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);
        jPanel1.add(filler1, java.awt.BorderLayout.LINE_START);
        jPanel1.add(filler2, java.awt.BorderLayout.LINE_END);
        jPanel1.add(filler3, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void myButtonborderless1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myButtonborderless1MouseClicked
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new add_book());

    }//GEN-LAST:event_myButtonborderless1MouseClicked

    private void myButtonborderless1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButtonborderless1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButtonborderless1ActionPerformed

    private void notificationLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationLabel1MouseClicked
        // TODO add your handling code here:
        if (exist) {
            GlassPanePopup.showPopup(new cart());
        }
    }//GEN-LAST:event_notificationLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    public libratech.books.inshelf.InshelfTable inshelfTable1;
    public libratech.books.inshelf.InshelfTable inshelfTable2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private libratech.design.MaterialTabbed materialTabbed1;
    private libratech.design.MyButtonborderless myButtonborderless1;
    private libratech.design.NotificationLabel notificationLabel1;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        materialTabbed1.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel1.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        myButtonborderless1.setFont(new Font("Poppins Regular", Font.BOLD, 14));
    }

}
