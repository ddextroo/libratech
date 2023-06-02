package libratech.admin;

import libratech.dashboard.*;
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
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import libratech.auth.login;
import libratech.auth.splash;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.StatusType;
import libratech.design.ImageScaler;
import libratech.design.RoundedPanelBorderless;
import libratech.models.Dashboard.*;
import libratech.util.firebaseInit;
import libratech.dashboard.dashboard_menu;
import libratech.dashboard.books_menu;
import libratech.dashboard.user_menu;
//import libratech.dashboard.settings_menu2;
import libratech.design.DefaultOption;
import libratech.design.GlassPanePopup;
import libratech.design.Option;
import libratech.design.loading;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.models.pushValueExisting;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author HB-user
 */
public class home_admin extends javax.swing.JFrame {

    private retrieveInfo listener = new retrieveInfo();
    ImageScaler scaler = new ImageScaler();
    dashboard_menu_admin dashboard_menu = new dashboard_menu_admin();
    user_menu_admin user_menu = new user_menu_admin();
    private String uid;
    private ChildEventListener accinfo;
    private final String path = "users/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private DatabaseReference dbRef;
    private DatabaseReference dbRef1;
    String durl = "";
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private HashMap<String, Object> m;
    private pushValueExisting v;
    private int days_limit;

    public home_admin() {
        initComponents();
        this.add(jPanel3);
        jPanel3.add(dashboard_menu, "dashboard");
//        jPanel3.add(book_menu, "book");
        jPanel3.add(user_menu, "user");
//        jPanel3.add(setting_menu, "setting");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "dashboard");
        jPanel10.setBackground(Color.decode("#0E2C4A"));
//        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
//        jPanel20.setBackground(Color.decode("#041C34"));
        ImageIcon icon1 = new ImageIcon("resources1/logo.png");
        this.setIconImage(icon1.getImage());
        new firebaseInit().initFirebase();
        GlassPanePopup.install(this);

        scaler.scaleImage(jLabel3, "src\\main\\resources\\logo.png");
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-fill.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initFont();
        updateInfo();
    }

    private void updateInfo() {
        accinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (_childKey.equals(new getUID().getUid())) {
                    if (_childValue.containsKey("status")) {
                        school_n.setText(_childValue.get("school_name").toString());
                        idnum.setText(_childValue.get("school_id").toString());
                        durl = _childValue.get("url").toString();
                        //Timer timer = new Timer(500, e -> {
                        try {
                            GlassPanePopup.showPopup(new loading());
                            URL url1 = new URL(durl);
                            BufferedImage image1 = ImageIO.read(url1);
                            ImageIcon icon = new ImageIcon(image1);
                            imageAvatar1.setIcon(icon);
                            GlassPanePopup.closePopupLast();
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        });
//                        timer.setRepeats(false);
//                        timer.start();
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
                    school_n.setText(_childValue.get("school_name").toString());
                    idnum.setText(_childValue.get("school_id").toString());
                    durl = _childValue.get("url").toString();

                    Timer timer = new Timer(500, e -> {
                        try {
                            GlassPanePopup.showPopup(new loading());
                            URL url1 = new URL(durl);
                            BufferedImage image1 = ImageIO.read(url1);
                            ImageIcon icon = new ImageIcon(image1);
                            imageAvatar1.setIcon(icon);
                            GlassPanePopup.closePopupLast();
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();

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

    public void updateLabelText(String text) {
        this.uid = text;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new RoundedPanelBorderless(12, new Color(0, 4, 36, 0));
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new RoundedPanelBorderless(12, new java.awt.Color(4,28,52,0));
        jPanel14 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel18 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        jPanel19 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        imageAvatar1 = new libratech.design.ImageAvatar();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel4 = new javax.swing.JPanel();
        school_n = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        idnum = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel9 = new javax.swing.JPanel();
        myButton1 = new libratech.design.MyButton();
        jPanel3 = new javax.swing.JPanel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(4, 28, 52));

        jPanel7.setBackground(new java.awt.Color(129, 14, 26));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("X");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel5, new java.awt.GridBagConstraints());

        jLabel3.setPreferredSize(new java.awt.Dimension(50, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(250, 250, 250));
        jLabel8.setText("LIBRATECH");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(250, 250, 250));
        jLabel9.setText("Library System Solution");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 750, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(4, 28, 52));

        jPanel10.setBackground(new java.awt.Color(4,28,52,0));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(41,182,246, 65));
        jPanel14.setOpaque(false);
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel14.add(filler8);

        jLabel10.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel14.add(jLabel10);

        jLabel11.setText("       ");
        jPanel14.add(jLabel11);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(250, 250, 250));
        jLabel14.setText("Dashboard");
        jPanel14.add(jLabel14);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel18.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(41,182,246, 65));
        jPanel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel19.setOpaque(false);
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel19.add(filler10);

        jLabel18.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel19.add(jLabel18);

        jLabel19.setText("       ");
        jPanel19.add(jLabel19);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(224, 224, 224));
        jLabel20.setText("User");
        jPanel19.add(jLabel20);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(4, 28, 52));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));
        jPanel5.add(filler1);
        jPanel5.add(imageAvatar1);
        jPanel5.add(filler4);

        jPanel4.setBackground(new java.awt.Color(4, 28, 52));
        jPanel4.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        school_n.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        school_n.setForeground(new java.awt.Color(250, 250, 250));
        school_n.setText("School Name");
        jPanel4.add(school_n);

        jPanel5.add(jPanel4);

        jPanel6.setBackground(new java.awt.Color(4, 28, 52));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        idnum.setBackground(new java.awt.Color(250, 250, 250));
        idnum.setForeground(new java.awt.Color(250, 250, 250));
        idnum.setText("ID Number");
        jPanel6.add(idnum);

        jPanel5.add(jPanel6);
        jPanel5.add(filler2);

        jPanel9.setBackground(new java.awt.Color(4, 28, 52));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        myButton1.setForeground(new java.awt.Color(250, 250, 250));
        myButton1.setText("Log out");
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(myButton1);

        jPanel5.add(jPanel9);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 623, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setLayout(new java.awt.CardLayout());
        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        /*String filePath1 = "remember.txt";
        String filePath = "uid.txt";
        File file1 = new File(filePath1);
        File file = new File(filePath);
        if (file1.exists()) {

        } else {
            file.delete();
        }
        System.exit(0);*/
        GlassPanePopup.showPopup(new exit_dialog());
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        /*String filePath1 = "remember.txt";
        String filePath = "uid.txt";
        File file1 = new File(filePath1);
        File file = new File(filePath);
        if (file1.exists()) {

        } else {
            file.delete();
        }
        System.exit(0);*/
        GlassPanePopup.showPopup(new exit_dialog());
    }//GEN-LAST:event_jPanel7MouseClicked

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new logout_admin_dialog());
//        splash splash = new splash();
//        String filePath = "uid.txt";
//        File file = new File(filePath);
//        String filePath1 = "remember.txt";
//        File file1 = new File(filePath1);
//
//        if (file.exists()) {
//            boolean deleted = file.delete();
//            if (deleted) {
//                if (file1.exists()) {
//                    file1.delete();
//                }
//                splash.setVisible(true);
//                setVisible(false);
//                this.dispose();
//            }
//        } else {
//            splash.setVisible(true);
//            setVisible(false);
//            this.dispose();
//        }
//        if (file1.exists()) {
//
//        } else {
//            file.delete();
//            splash.setVisible(true);
//            setVisible(false);
//            this.dispose();
//        }

    }//GEN-LAST:event_myButton1ActionPerformed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        jPanel10.setBackground(Color.decode("#0E2C4A"));
//        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
//        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel14.setFont(new Font("Poppins Regular", Font.BOLD, 16));
//        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
//        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-fill.png");
//        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
//        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "dashboard");
        updateInfo();
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here:
        jPanel18.setBackground(Color.decode("#0E2C4A"));
//        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel10.setBackground(Color.decode("#041C34"));
//        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel20.setFont(new Font("Poppins Regular", Font.BOLD, 16));
//        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
//        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
//        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-fill.png");
//        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "user");
        updateInfo();
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
        jPanel10.setBackground(Color.decode("#0E2C4A"));
//        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
//        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel14.setFont(new Font("Poppins Regular", Font.BOLD, 16));
//        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
//        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-fill.png");
//        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
//        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "dashboard");
        updateInfo();
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
        jPanel18.setBackground(Color.decode("#0E2C4A"));
//        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel10.setBackground(Color.decode("#041C34"));
//        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel20.setFont(new Font("Poppins Regular", Font.BOLD, 16));
//        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
//        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
//        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-fill.png");
//        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "user");
        updateInfo();
    }//GEN-LAST:event_jPanel19MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JLabel idnum;
    private libratech.design.ImageAvatar imageAvatar1;
    public javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    public javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel18;
    public javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private libratech.design.MyButton myButton1;
    private javax.swing.JLabel school_n;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        jLabel14.setFont(new Font("Poppins Regular", Font.BOLD, 16));
//        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
//        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel8.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        jLabel9.setFont(new Font("Poppins Regular", Font.PLAIN, 14));
        jLabel5.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        school_n.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        idnum.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButton1.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
    }
}
