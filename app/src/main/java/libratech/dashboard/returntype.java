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
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import libratech.design.DefaultOption;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.Option;
import libratech.design.RoundedPanel;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.models.pushValueExisting;
import libratech.util.firebaseInit;

/**
 *
 * @author Carocoy
 */
public class returntype extends javax.swing.JPanel {

    ImageScaler scaler = new ImageScaler();
    private HashMap<String, Object> m;
    private pushValueExisting v;
    private pushValue v2;
    private ChildEventListener booksinfo;
    private double fines;
    private String barcode;
    private final String path_book = "books/" + new getUID().getUid() + "/";
    private final DatabaseReference book = FirebaseDatabase.getInstance().getReference(path_book);
    private List<Object> columnData;
    private String key;
    private DatabaseReference.CompletionListener completionListener;
    private DatabaseReference transaction;
    private DatabaseReference dbRef;
    private ValueEventListener data;
    private String idnum;
    private int penalties;
    private double user_fines;
    private String added_overdue;

    private ChildEventListener borrow_added;
    private DatabaseReference borrow;

    public returntype(Object columnData, double fines, String key, String barcode, String idnum, int penalties, double user_fines) {
        this.fines = fines;
        this.key = key;
        this.barcode = barcode;
        this.idnum = idnum;
        this.penalties = penalties;
        this.user_fines = user_fines;
        System.out.println(key);

        this.transaction = FirebaseDatabase.getInstance().getReference("borrowerlist/" + new getUID().getUid() + "/" + key);
        this.borrow = FirebaseDatabase.getInstance().getReference("borrowerlist/" + new getUID().getUid() + "/" + key + "/");
        this.columnData = (List<Object>) columnData;
        initComponents();
        new firebaseInit().initFirebase();
        setOpaque(false);
        comboBoxSuggestion1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Inshelf", "Lost", "Damaged"}));
        comboBoxSuggestion1.getEditor().getEditorComponent().setBackground(new Color(250, 250, 250));
        initFont();
        checkBorrowerList();

        completionListener = (DatabaseError error, DatabaseReference ref) -> {
            if (error != null) {
                System.out.println("Error removing value: " + error.getMessage());
            } else {
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

    private void deleteTransaction() {

        transaction.child(barcode).removeValue(completionListener);
        home home = new home();
        home.setVisible(true);
        home.jPanel15.setBackground(Color.decode("#0E2C4A"));
        home.jPanel10.setBackground(Color.decode("#041C34"));
        home.jPanel18.setBackground(Color.decode("#041C34"));
        home.jPanel20.setBackground(Color.decode("#041C34"));
        scaler.scaleImage(home.jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(home.jLabel15, "src\\main\\resources\\book-fill.png");
        scaler.scaleImage(home.jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(home.jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) home.jPanel3.getLayout();
        cardLayout.show(home.jPanel3, "user");
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
        GlassPanePopup.showPopup(new cartreturn(key), option);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(returntype.this);
        frame.dispose();

    }

    private void checkBorrowerList() {
        borrow_added = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);

                if (_childKey.contains(barcode)) {
                    if (_childValue.containsKey("added_overdue")) {
                        added_overdue = _childValue.get("added_overdue").toString();
                    } else {
                        added_overdue = "false";
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
        borrow.addChildEventListener(borrow_added);
    }

    private void retrieveDataBooksInfoInshelf() {

        booksinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                for (Object item : columnData) {
                    String temp = (String) item;
                    if (temp.equals(_childKey)) {
                        int remaining = Integer.parseInt(_childValue.get("remaining_copies").toString());
                        int overdue_books = Integer.parseInt(_childValue.get("overdue_books").toString());
                        v = new pushValueExisting(_childKey);
                        m = new HashMap<>();
                        m.put("remaining_copies", remaining + 1);
                        if (added_overdue.equals("true")) {
                            m.put("overdue_books", overdue_books - 1);
                        }
                        if (overdue_books <= 0) {
                            m.put("status", "Available");
                        }
                        v.pushData("books/" + new getUID().getUid(), m);
                        m.clear();
                        v = new pushValueExisting(idnum);
                        m = new HashMap<>();
                        m.put("fines", fines + user_fines);
                        v.pushData("students/" + new getUID().getUid(), m);
                        m.clear();
                    }
                    break;
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
        book.addChildEventListener(booksinfo);

    }

    private void retrieveDataBooksInfoLost() {

        booksinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                for (Object item : columnData) {
                    String temp = (String) item;
                    if (temp.equals(_childKey)) {
                        int overdue_books = Integer.parseInt(_childValue.get("overdue_books").toString());
                        int lostbooks;
                        double price = 0f;
                        if (_childValue.containsKey("lost_books")) {
                            lostbooks = Integer.parseInt(_childValue.get("lost_books").toString());
                        } else {
                            lostbooks = 0;
                        }
                        if (_childValue.containsKey("price")) {
                            price = Double.parseDouble(_childValue.get("price").toString());
                        }
                        v = new pushValueExisting(_childKey);
                        m = new HashMap<>();
                        m.put("lost_books", lostbooks + 1);
                        if (added_overdue.equals("true")) {
                            m.put("overdue_books", overdue_books - 1);
                        }
                        overdue_books = Integer.parseInt(_childValue.get("overdue_books").toString());
                        if (overdue_books <= 0) {
                            m.put("status", "Available");
                        }
                        v.pushData("books/" + new getUID().getUid(), m);
                        m.clear();
                        v = new pushValueExisting(idnum);
                        m = new HashMap<>();
                        m.put("fines", fines + user_fines + price);
                        m.put("penalties", penalties + 1);
                        v.pushData("students/" + new getUID().getUid(), m);
                        m.clear();

                    }
                    break;
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
        book.addChildEventListener(booksinfo);

    }

    private void retrieveDataBooksInfoDamaged() {

        booksinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                for (Object item : columnData) {
                    String temp = (String) item;
                    if (temp.equals(_childKey)) {
                        int overdue_books = Integer.parseInt(_childValue.get("overdue_books").toString());
                        int damagedbooks;
                        double price = 0f;
                        if (_childValue.containsKey("damaged_books")) {
                            damagedbooks = Integer.parseInt(_childValue.get("damaged_books").toString());
                        } else {
                            damagedbooks = 0;
                        }
                        if (_childValue.containsKey("price")) {
                            price = Double.parseDouble(_childValue.get("price").toString());
                        }
                        v = new pushValueExisting(_childKey);
                        m = new HashMap<>();
                        m.put("damaged_books", damagedbooks + 1);
                        if (added_overdue.equals("true")) {
                            m.put("overdue_books", overdue_books - 1);
                        }
                        if (overdue_books <= 0) {
                            m.put("status", "Available");
                        }
                        v.pushData("books/" + new getUID().getUid(), m);
                        m.clear();
                        v = new pushValueExisting(idnum);
                        m = new HashMap<>();
                        m.put("fines", fines + user_fines + price);
                        m.put("penalties", penalties + 1);
                        v.pushData("students/" + new getUID().getUid(), m);
                        m.clear();
                    }
                    break;
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
        book.addChildEventListener(booksinfo);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmlabel = new javax.swing.JLabel();
        returnn = new libratech.design.MyButtonborderless();
        cancel = new libratech.design.MyButtonborder();
        jPanel10 = new RoundedPanel(12, new Color(250,250,250,0));
        comboBoxSuggestion1 = new libratech.design.ComboBoxSuggestion();

        confirmlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmlabel.setText("Return as");

        returnn.setForeground(new java.awt.Color(224, 224, 224));
        returnn.setText("Return");
        returnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnnActionPerformed(evt);
            }
        });

        cancel.setForeground(new java.awt.Color(23, 23, 23));
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setOpaque(false);

        comboBoxSuggestion1.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxSuggestion1.setEditable(false);
        comboBoxSuggestion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuggestion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(291, 291, 291))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(returnn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(confirmlabel)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cancelActionPerformed

    private void returnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnnActionPerformed
        // TODO add your handling code here:
        if (comboBoxSuggestion1.getSelectedItem().toString().equals("Inshelf")) {
            retrieveDataBooksInfoInshelf();
        } else if (comboBoxSuggestion1.getSelectedItem().toString().equals("Lost")) {
            retrieveDataBooksInfoLost();
        } else {
            retrieveDataBooksInfoDamaged();
        }

        deleteTransaction();
    }//GEN-LAST:event_returnnActionPerformed

    private void comboBoxSuggestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion1ActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, comboBoxSuggestion1, "Select", JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_comboBoxSuggestion1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private libratech.design.MyButtonborder cancel;
    private libratech.design.ComboBoxSuggestion comboBoxSuggestion1;
    private javax.swing.JLabel confirmlabel;
    private javax.swing.JPanel jPanel10;
    private libratech.design.MyButtonborderless returnn;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        confirmlabel.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        comboBoxSuggestion1.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        returnn.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        cancel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
    }
}
