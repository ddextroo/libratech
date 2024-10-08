/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.admin;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.loading;
import libratech.models.getUID;
import libratech.models.pushValueExisting;
import libratech.util.firebaseInit;
import libratech.util.smtp;

/**
 *
 * @author Carocoy
 */
public class change_status_admin extends javax.swing.JPanel {

    private String UID;
    private HashMap<String, Object> m;
    private pushValueExisting v;
    private ChildEventListener accinfo;
    private final String path = "users/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    LocalDate currentDate = LocalDate.now();
    private String transactionKey;
    ImageScaler scaler = new ImageScaler();
    private DatabaseReference.CompletionListener completionListener;
    private DatabaseReference users2;
    private ChildEventListener userinfo;
    private final String path_user = "users";
    private final DatabaseReference user = FirebaseDatabase.getInstance().getReference(path_user);
    private String email_add;

    public change_status_admin(String UID, String transactionKey) {
        initComponents();
        this.UID = UID;
        this.transactionKey = transactionKey;
        new firebaseInit().initFirebase();
        this.UID = UID;
        setOpaque(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
        txt.setEditable(false);
        initFont();
        users2 = FirebaseDatabase.getInstance().getReference("admin_reference" + "/" + transactionKey);
        retrieveDataUserInfo();

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
    
    private void retrieveDataUserInfo() {

        userinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (UID.equals(_childKey)) {
                    email_add = _childValue.get("email").toString();
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
        user.addChildEventListener(userinfo);

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
        exit = new libratech.design.MyButtonborderless();
        txt = new javax.swing.JTextPane();
        cancel = new libratech.design.MyButtonborder();

        confirmlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmlabel.setText("Change Status");

        exit.setForeground(new java.awt.Color(224, 224, 224));
        exit.setText("Proceed");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        txt.setText("Are you sure to change this user Pending to Approved?");
        txt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cancel.setForeground(new java.awt.Color(23, 23, 23));
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(271, 271, 271)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(confirmlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cancelActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        try {
            // TODO add your handling code here:
            LocalDate newDate = currentDate.plusMonths(1);
            String newDateString = newDate.format(formatter);
            v = new pushValueExisting(UID);
            m = new HashMap<>();
            m.put("status", "Approved");
            m.put("limit_date", newDateString);
            v.pushData("users", m);
            m.clear();
            users2.getRef().removeValue(completionListener);
            new smtp().sendMail("Account Approved", """
                                                    Hi,
                                                    
                                                    We are pleased to inform you that your account with LibraTech has been approved and is now ready for use. We appreciate your interest in our services and are delighted to have you as a valued member of our community.
                                                    
                                                    With your newly approved account, you can now access all the features and benefits associated with our platform. We assure you that our team is committed to providing you with the best possible experience.
                                                    
                                                    We are excited about the possibilities that lie ahead and are confident that our platform will meet and exceed your expectations. Thank you for choosing LibtraTech as your library system solution. We look forward to serving you and ensuring your success.
                                                    
                                                    
                                                    Best regards,
                                                    LibraTech Admin""", email_add);
            GlassPanePopup.closePopupAll();
        } catch (Exception ex) {
            Logger.getLogger(change_status_admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_exitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private libratech.design.MyButtonborder cancel;
    private javax.swing.JLabel confirmlabel;
    private libratech.design.MyButtonborderless exit;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        confirmlabel.setFont(new Font("Poppins Regular", Font.BOLD, 20));
        txt.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        exit.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        cancel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
    }
}
