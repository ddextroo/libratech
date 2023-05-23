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
//        controlnumber.setText(key);
//        retrieveBooksInfo();
        //barcode(key);

    }

//    private void barcode(String code) {
//        try {
//            Barcode barcode = BarcodeFactory.createCode128(code);
//            barcode.setFont(new Font("Poppins Regular", Font.BOLD, 12));
//            barcode.setBarHeight(60);
//            barcode.setBarWidth(2);
//
//            // Create a temporary file to save the barcode image
//            File file = File.createTempFile("barcode", ".png");
//            BarcodeImageHandler.savePNG(barcode, file);
//            scaler.scaleImage(jLabel3, file.getAbsolutePath());
//
//        } catch (BarcodeException | OutputException | IOException ex) {
//            Logger.getLogger(books_menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    private void retrieveBooksInfo() {
//
//        classification.setModel(new javax.swing.DefaultComboBoxModel(info.getClassification()));
//        classification.getEditor().getEditorComponent().setBackground(new Color(250, 250, 250));
//        booksinfo = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = dataSnapshot.getKey();
//                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
//                if (_childKey.equals(parentKey)) {
//                    try {
//                        booktitle.setText(_childValue.get("booktitle").toString());
//                        author.setText(_childValue.get("bookauthor").toString());
//                        publisher.setText(_childValue.get("publisher").toString());
//                        classification.setSelectedIndex(Integer.parseInt(_childValue.get("classification_pos").toString()));
//                        date.setText(_childValue.get("date").toString());
//                        copies.setText(_childValue.get("copies").toString());
//                        isbn.setText(_childValue.get("isbn").toString());
//                        shelf.setText(_childValue.get("shelf").toString());
//                        deck.setText(_childValue.get("deck").toString());
//                        edition.setText(_childValue.get("edition").toString());
//                        URL url = new URL(_childValue.get("cover").toString());
//                        BufferedImage image = ImageIO.read(url);
//                        photoCover1.setImage(image);
//                        remaining_copies = Integer.parseInt(_childValue.get("remaining_copies").toString());
//                    } catch (MalformedURLException ex) {
//                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IOException ex) {
//                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
//                    }
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
//                if (_childKey.equals(parentKey)) {
//                    try {
//                        booktitle.setText(_childValue.get("booktitle").toString());
//                        author.setText(_childValue.get("bookauthor").toString());
//                        publisher.setText(_childValue.get("publisher").toString());
//                        classification.setSelectedIndex(Integer.parseInt(_childValue.get("classification_pos").toString()));
//                        date.setText(_childValue.get("date").toString());
//                        copies.setText(_childValue.get("copies").toString());
//                        isbn.setText(_childValue.get("isbn").toString());
//                        shelf.setText(_childValue.get("shelf").toString());
//                        deck.setText(_childValue.get("deck").toString());
//                        edition.setText(_childValue.get("edition").toString());
//                        URL url = new URL(_childValue.get("cover").toString());
//                        BufferedImage image = ImageIO.read(url);
//                        photoCover1.setImage(image);
//                        remaining_copies = Integer.parseInt(_childValue.get("remaining_copies").toString());
//                    } catch (MalformedURLException ex) {
//                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IOException ex) {
//                        Logger.getLogger(edit_book.class.getName()).log(Level.SEVERE, null, ex);
//                    }
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
//        books.addChildEventListener(booksinfo);
//    }
//    private void selectDate(String id) {
//        accinfo = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
//                };
//                final String _childKey = dataSnapshot.getKey();
//                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
//                System.out.println(_childKey);
//                if (_childKey.equals(id)) {
//                    borrowername.setText(_childValue.get("fullname").toString());
//                    borrowerid.setText(_childValue.get("idno").toString());
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
//                if (_childKey.equals(id)) {
//                    borrowername.setText(_childValue.get("fullname").toString());
//                    borrowerid.setText(_childValue.get("idno").toString());
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
//        acc.addChildEventListener(accinfo);
//    }
    private void retrieveData(String idn) {
        // Fetch data from Firebase and create table
        eventAction = new EventActionStudent() {
            private String selectedIDNumber;

            @Override
            public void update(Student student) {
                //selectDate(student.getIDnumber());
                //System.out.println(student.getIDnumber());
                v = new pushValueExisting("borrower");
                m = new HashMap<>();
                m.put("idno", student.getIDnumber());
                v.pushData("cart/" + new getUID().getUid(), m);
                m.clear();
                v = new pushValueExisting("borrower/books/" + key);
                m = new HashMap<>();
                m.put("idno", student.getIDnumber());
                m.put("book_title", title);
                m.put("book_key", key);
                m.put("due_date", duedate1.getText());
                v.pushData("cart/" + new getUID().getUid(), m);
                m.clear();
                GlassPanePopup.closePopupAll();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new libratech.design.DateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel25 = new RoundedPanel(12, new Color(250,250,250));
        search = new javax.swing.JTextField();
        searchicon = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        duedatelabel = new javax.swing.JLabel();
        jPanel24 = new RoundedPanel(12, new Color(250,250,250));
        duedate1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable1 = new libratech.user.students.studentTableSelect();

        dateChooser1.setTextRefernce(duedate1);

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
                .addContainerGap(7, Short.MAX_VALUE))
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

        duedatelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        duedatelabel.setForeground(new java.awt.Color(51, 51, 51));
        duedatelabel.setText("Due Date");

        jPanel24.setBackground(new java.awt.Color(0, 0, 0));
        jPanel24.setOpaque(false);

        duedate1.setBackground(new java.awt.Color(250, 250, 250));
        duedate1.setBorder(null);
        duedate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                duedate1MouseClicked(evt);
            }
        });
        duedate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duedate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(duedate1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(533, 533, 533))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(duedate1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(duedatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1133, 1133, 1133))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(duedatelabel)
                .addGap(4, 4, 4)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void duedate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_duedate1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_duedate1MouseClicked

    private void duedate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duedate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_duedate1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private libratech.design.DateChooser dateChooser1;
    private javax.swing.JTextField duedate;
    private javax.swing.JTextField duedate1;
    private javax.swing.JLabel duedatelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
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
        duedate1.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        duedatelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));

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
