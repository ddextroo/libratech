/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.user.subscription;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import libratech.dashboard.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.RoundedPanel;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.models.pushValueExisting;
import libratech.util.firebaseInit;
import libratech.util.smtp;

/**
 *
 * @author Carocoy
 */
public class payment extends javax.swing.JPanel {

    private String plan_name;
    private String plan_price;
    private int choose;
    private HashMap<String, Object> m;
    private pushValueExisting v;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy; hh:mm a");
    private ChildEventListener userinfo;
    private final String path_user = "users";
    private final DatabaseReference user = FirebaseDatabase.getInstance().getReference(path_user);
    private String school_name;
    private String school_id;
    private String email_add;
    LocalDate currentDate = LocalDate.now();
    String randomString;

    public payment(String plan_name, String plan_price, int choose) {
        initComponents();

        Random random = new Random();
        initComponents();

        new firebaseInit().initFirebase();
        this.plan_name = plan_name;
        this.plan_price = plan_price;
        this.choose = choose;
        String characters = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        randomString = sb.toString();
        System.out.println(new getUID().getUid() + "qwqw 1 1 1 1");
        setOpaque(false);
        initFont();
        ImageScaler scaler = new ImageScaler();
        planstandardlabel1.setText("Plan - " + plan_name + " " + plan_price);
        retrieveDataBooksInfo();
        //scaler.scaleImage(jLabel1, "src\\main\\resources\\arrow-left-line.png");
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

    private void retrieveDataBooksInfo() {

        userinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (new getUID().getUid().equals(_childKey)) {
                    school_name = _childValue.get("school_name").toString();
                    school_id = _childValue.get("school_id").toString();
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

        jPanel1 = new javax.swing.JPanel();
        paymentoptionslabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        planstandardlabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        accountnumberlabel = new javax.swing.JLabel();
        accountnamelabel = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(12, new Color(245,245,245));
        accountnumber = new javax.swing.JTextField();
        jPanel7 = new RoundedPanel(12, new Color(245,245,245));
        accountname = new javax.swing.JTextField();
        pay = new libratech.design.MyButtonborderless();

        paymentoptionslabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        paymentoptionslabel.setText("Payment Options: GCASH");
        jPanel1.add(paymentoptionslabel);

        planstandardlabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        planstandardlabel1.setForeground(new java.awt.Color(158, 158, 158));
        planstandardlabel1.setText("Plan - Standard PHP 149.00/month");
        jPanel3.add(planstandardlabel1);

        jPanel4.setLayout(null);

        accountnumberlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        accountnumberlabel.setForeground(new java.awt.Color(51, 51, 51));
        accountnumberlabel.setText("Account Number");
        jPanel4.add(accountnumberlabel);
        accountnumberlabel.setBounds(10, 10, 290, 16);

        accountnamelabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        accountnamelabel.setForeground(new java.awt.Color(51, 51, 51));
        accountnamelabel.setText("Account Name");
        jPanel4.add(accountnamelabel);
        accountnamelabel.setBounds(10, 70, 260, 16);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        accountnumber.setBackground(new java.awt.Color(245, 245, 245));
        accountnumber.setBorder(null);
        accountnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountnumberActionPerformed(evt);
            }
        });
        accountnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountnumberKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                accountnumberKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountnumber, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setOpaque(false);

        jPanel4.add(jPanel6);
        jPanel6.setBounds(10, 30, 310, 33);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        accountname.setBackground(new java.awt.Color(245, 245, 245));
        accountname.setBorder(null);
        accountname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountnameActionPerformed(evt);
            }
        });
        accountname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountnameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                accountnameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountname, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountname, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setOpaque(false);

        jPanel4.add(jPanel7);
        jPanel7.setBounds(10, 90, 310, 33);

        pay.setForeground(new java.awt.Color(224, 224, 224));
        pay.setText("Pay Now");
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });
        jPanel4.add(pay);
        pay.setBounds(220, 140, 107, 36);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
        try {
            // TODO add your handling code here:
            //GlassPanePopup.closePopupLast();
            
            v = new pushValueExisting(randomString);
            m = new HashMap<>();
            m.put("plan", choose);
            m.put("account_number", accountnumber.getText());
            m.put("account_name", accountname.getText());
            m.put("reference_no", randomString);
            m.put("transaction_date", currentDate.format(formatter));
            m.put("uid", new getUID().getUid());
            v.pushData("admin_reference", m);
            m.clear();
            v = new pushValueExisting(new getUID().getUid());
            m = new HashMap<>();
            m.put("plan", choose);
            v.pushData("users", m);
            m.clear();
            new smtp().sendMail("Waiting for Approval", "Dear" + school_name + ",\n\n" +
                    "Thank you for choosing LibraTech. This is regarding your recent online transaction you made. As of now, the transaction is still pending and we are actively working on processing it.\n\n" +
                    "Plan Transaction Details:\n" +
                    "Reference Number:\t" + randomString + "\n" +
                    "School Name:\t" + school_name + "\n" +
                    "Student ID:\t" + school_id + "\n" +
                    "Transaction Date/Time:\t" + currentDate.format(formatter) + "\n" +
                    "Subscription:\t" + plan_name + "\n" +
                    "Payment Amount:\t" + plan_name + "\n\n" +
                    "Our team is currently working diligently to ensure that your payment is processed successfully. We apologize for any delay this may cause and appreciate your patience throughout this process.\n\n" +
                    "Rest assured, we are giving this transaction our utmost attention, and we will notify you promptly once it has been successfully processed. If you have any questions or require further information, please feel free to reach out to our customer support team at LibraCare. We are here to assist you.\n\n" +
                    "Thank you for your understanding and cooperation in this matter. We value your business and are committed to resolving this pending transaction as soon as possible.\n\n" +
                    "Best regards,\n" +
                    "LibraTech Team", email_add);
            GlassPanePopup.closePopupLast();
            GlassPanePopup.showPopup(new approval_dialog());
        } catch (Exception ex) {
            Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_payActionPerformed

    private void accountnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountnumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountnumberActionPerformed

    private void accountnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountnameActionPerformed

    private void accountnumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountnumberKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_accountnumberKeyTyped

    private void accountnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountnameKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_accountnameKeyTyped

    private void accountnumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountnumberKeyReleased
        // TODO add your handling code here:
        String text = accountnumber.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            accountnumber.setText(text);
        }

        if (text.length() > 11) {
            str = text.substring(0, 11);
            accountnumber.setText("");
        }

        if (text.length() == 0) {
            accountnumber.setText(str);
            str = "";
        }
    }//GEN-LAST:event_accountnumberKeyReleased

    private void accountnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountnameKeyReleased
        // TODO add your handling code here:
        String text = accountname.getText();
        String str = "";
        // Capitalize the first letter of the text
        if (text.length() > 0) {
            text = Character.toUpperCase(text.charAt(0)) + text.substring(1);
            accountname.setText(text);
        }

        if (text.length() > 50) {
            str = text.substring(0, 50);
            accountname.setText("");
        }

        if (text.length() == 0) {
            accountname.setText(str);
            str = "";
        }
    }//GEN-LAST:event_accountnameKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountname;
    private javax.swing.JLabel accountnamelabel;
    private javax.swing.JTextField accountnumber;
    private javax.swing.JLabel accountnumberlabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private libratech.design.MyButtonborderless pay;
    private javax.swing.JLabel paymentoptionslabel;
    private javax.swing.JLabel planstandardlabel1;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        paymentoptionslabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        pay.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        accountnamelabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        accountnumberlabel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        planstandardlabel1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        accountnumber.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        accountname.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
    }
}
