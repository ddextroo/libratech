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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import libratech.design.ImageScaler;
import libratech.design.RoundedPanelBorderless;
import libratech.models.Dashboard.*;
import libratech.util.firebaseInit;
import java.util.Calendar;
import javax.swing.SwingUtilities;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.StatusType;
import libratech.design.DefaultOption;
import libratech.design.GlassPanePopup;
import libratech.design.Option;
import libratech.design.loading;
import libratech.models.getUID;
import libratech.models.pushValue;
import libratech.models.pushValueExisting;
import org.quartz.*;

/**
 *
 * @author HB-user
 */
public class home extends javax.swing.JFrame {

    private retrieveInfo listener = new retrieveInfo();
    ImageScaler scaler = new ImageScaler();
    dashboard_menu dashboard_menu = new dashboard_menu();
    books_menu book_menu = new books_menu();
    user_menu user_menu = new user_menu();
    settingsmenu setting_menu = new settingsmenu();
    private String uid;
    private ChildEventListener accinfo;
    private final String path = "users/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path);
    private DatabaseReference dbRef;
    private DatabaseReference dbRef1;
    private DatabaseReference dbRef2;
    private DatabaseReference dbRef3;
    String durl = "";
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private HashMap<String, Object> m;
    private pushValueExisting v;
    private ValueEventListener data;
    private ValueEventListener data2;
    private ValueEventListener data3;
    private ValueEventListener data4;
    private int days_limit;
    private int overdue_books;
    private int penalties;
    private String childtemp;
    private String childtemp2;
    String tempID;

    public home() {
        initComponents();
        this.add(jPanel3);
        jPanel3.add(dashboard_menu, "dashboard");
        jPanel3.add(book_menu, "book");
        jPanel3.add(user_menu, "user");
        jPanel3.add(setting_menu, "setting");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "dashboard");
        jPanel10.setBackground(Color.decode("#0E2C4A"));
        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
        jPanel20.setBackground(Color.decode("#041C34"));
        ImageIcon icon1 = new ImageIcon("resources1/logo.png");
        this.setIconImage(icon1.getImage());
        new firebaseInit().initFirebase();
        GlassPanePopup.install(this);

        scaler.scaleImage(jLabel3, "src\\main\\resources\\logo.png");
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-fill.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initFont();
        updateInfo();
        updateBooks();
    }

    private void updateBooks() {

        dbRef = FirebaseDatabase.getInstance().getReference("borrowerlist/" + new getUID().getUid());
        dbRef.addValueEventListener(data3 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    childtemp2 = child.getKey();
                    dbRef1 = FirebaseDatabase.getInstance().getReference("borrowerlist/" + new getUID().getUid() + "/" + child.getKey());
                    dbRef1.addValueEventListener(data = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                childtemp = child.getKey();
                                dbRef2 = FirebaseDatabase.getInstance().getReference("books/" + new getUID().getUid());
                                dbRef2.addValueEventListener(data2 = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                                            if (child.getKey().equals(childtemp)) {
                                                overdue_books = child.child("overdue_books").getValue(Integer.class);
                                                break;
                                            }
                                            dbRef2.removeEventListener(data2);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        System.out.println("Error: " + databaseError.getMessage());
                                    }
                                });

                                if (child.hasChild("borrowed_date") && child.hasChild("due_date")) {
                                    try {
                                        String borrowed_date = child.child("borrowed_date").getValue(String.class);
                                        String due_date = child.child("due_date").getValue(String.class);

                                        Date borrowed = dateFormat.parse(borrowed_date);
                                        Date due = dateFormat.parse(due_date);

                                        if (borrowed.after(due)) {
                                            if (child.hasChild("added_overdue")) {

                                            } else {
                                                v = new pushValueExisting(child.getKey());
                                                m = new HashMap<>();
                                                m.put("overdue_books", overdue_books + 1);
                                                m.put("status", "Overdue");
                                                v.pushData("books/" + new getUID().getUid(), m);
                                                m.clear();
                                                v = new pushValueExisting(child.getKey());
                                                m = new HashMap<>();
                                                m.put("added_overdue", "true");
                                                v.pushData("borrowerlist/" + new getUID().getUid() + "/" + childtemp2, m);
                                                m.clear();
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.setTime(due);
                                                calendar.add(Calendar.DAY_OF_YEAR, days_limit);
                                                Date DaysLater = calendar.getTime();

                                                tempID = child.child("idno").getValue(String.class);

                                                dbRef3 = FirebaseDatabase.getInstance().getReference("students/" + new getUID().getUid());
                                                dbRef3.addValueEventListener(data4 = new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                            System.out.println(tempID);
                                                            System.out.println(child.getKey());
                                                            if (child.getKey().equals(tempID)) {
                                                                System.out.println(child.child("penalties").getValue(Integer.class));
                                                                penalties = child.child("penalties").getValue(Integer.class);
                                                                break;
                                                            }
                                                        }
                                                        dbRef3.removeEventListener(data4);
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {
                                                        System.out.println("Error: " + databaseError.getMessage());
                                                    }
                                                });

                                                if (borrowed.after(DaysLater)) {
                                                    v = new pushValueExisting(child.child("idno").getValue(String.class));
                                                    m = new HashMap<>();
                                                    m.put("penalties", penalties + 1);
                                                    v.pushData("students/" + new getUID().getUid(), m);
                                                    m.clear();
                                                }
                                            }

                                        }
                                    } catch (ParseException ex) {
                                        Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("Error: " + databaseError.getMessage());
                        }
                    });
                }
                dbRef.removeEventListener(data);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });
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
                        days_limit = Integer.parseInt(_childValue.get("days_limit").toString());

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
                        if (_childValue.get("status").toString().equals("Pending")) {
                            GlassPanePopup.showPopup(new subscription("Subscription Payment Required", "Welcome to Libratech! To continue enjoying our premium features and exclusive content, a subscription payment is required. Don't miss out on the full potential of Libratech; unlock all the benefits today!"), option);
                        }

                        String limitDateString = _childValue.get("limit_date").toString();

                        //LocalDate limitDate = LocalDate.parse(limitDateString);
                        LocalDate limitDate = LocalDate.parse(limitDateString, formatter);

                        LocalDate currentDate = LocalDate.now();

                        if (currentDate.isEqual(limitDate) || currentDate.isAfter(limitDate)) {
                            v = new pushValueExisting(new getUID().getUid());
                            m = new HashMap<>();
                            m.put("status", "Pending");
                            v.pushData("users", m);
                            m.clear();
                            if (_childValue.get("status").toString().equals("Pending")) {
                                GlassPanePopup.showPopup(new subscription("Subscription Payment Required", "Welcome to Libratech! To continue enjoying our premium features and exclusive content, a subscription payment is required. Don't miss out on the full potential of Libratech; unlock all the benefits today!"), option);
                            } else if (_childValue.get("status").toString().equals("Approved")) {
                                LocalDate newDate = currentDate.plusMonths(1);
                                String newDateString = newDate.format(formatter);
                                v = new pushValueExisting(_childKey);
                                m = new HashMap<>();
                                m.put("limit_date", newDateString);
                                v.pushData("users", m);
                                m.clear();
                                home home = new home();
                                home.setVisible(true);
                                home.jPanel15.setBackground(Color.decode("#041C34"));
                                home.jPanel10.setBackground(Color.decode("#0E2C4A"));
                                home.jPanel18.setBackground(Color.decode("#041C34"));
                                home.jPanel20.setBackground(Color.decode("#041C34"));
                                scaler.scaleImage(home.jLabel10, "src\\main\\resources\\dashboard-fill.png");
                                scaler.scaleImage(home.jLabel15, "src\\main\\resources\\book-line.png");
                                scaler.scaleImage(home.jLabel18, "src\\main\\resources\\user-line.png");
                                scaler.scaleImage(home.jLabel21, "src\\main\\resources\\settings-line.png");
                                CardLayout cardLayout = (CardLayout) home.jPanel3.getLayout();
                                cardLayout.show(home.jPanel3, "dashboard");
                                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(home.this);
                                frame.dispose();
                            }
                        }
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
                    days_limit = Integer.parseInt(_childValue.get("days_limit").toString());

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

                    if (_childValue.get("status").toString().equals("Pending")) {
                        GlassPanePopup.showPopup(new subscription("Subscription Payment Required", "Welcome to Libratech! To continue enjoying our premium features and exclusive content, a subscription payment is required. Don't miss out on the full potential of Libratech; unlock all the benefits today!"), option);
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
        jPanel15 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        jPanel16 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel18 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        jPanel19 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel20 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        jPanel21 = new RoundedPanelBorderless(12, new java.awt.Color(41,182,246, 0));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
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
                .addGap(17, 17, 17)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel16.setOpaque(false);
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel16.add(filler9);

        jLabel15.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel16.add(jLabel15);

        jLabel16.setText("       ");
        jPanel16.add(jLabel16);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(224, 224, 224));
        jLabel17.setText("Books");
        jPanel16.add(jLabel17);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(41,182,246, 0));
        jPanel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel20.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(41,182,246, 65));
        jPanel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel21.setOpaque(false);
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
        });
        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));
        jPanel21.add(filler11);

        jLabel21.setPreferredSize(new java.awt.Dimension(27, 27));
        jPanel21.add(jLabel21);

        jLabel22.setText("       ");
        jPanel21.add(jLabel22);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(224, 224, 224));
        jLabel23.setText("Settings");
        jPanel21.add(jLabel23);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 493, Short.MAX_VALUE)
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

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        // TODO add your handling code here:
        jPanel15.setBackground(Color.decode("#0E2C4A"));
        jPanel10.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel17.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-fill.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "book");
    }//GEN-LAST:event_jPanel16MouseClicked

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
        GlassPanePopup.showPopup(new logout_dialog());
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
        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel14.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-fill.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "dashboard");
        updateInfo();
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        // TODO add your handling code here:
        jPanel15.setBackground(Color.decode("#0E2C4A"));
        jPanel10.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel17.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-fill.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "book");
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here:
        jPanel18.setBackground(Color.decode("#0E2C4A"));
        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel10.setBackground(Color.decode("#041C34"));
        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel20.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-fill.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "user");
        updateInfo();
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        // TODO add your handling code here:
        jPanel20.setBackground(Color.decode("#0E2C4A"));
        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
        jPanel10.setBackground(Color.decode("#041C34"));
        jLabel23.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-fill.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "setting");
        updateInfo();
    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
        jPanel10.setBackground(Color.decode("#0E2C4A"));
        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel14.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-fill.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "dashboard");
        updateInfo();
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
        jPanel18.setBackground(Color.decode("#0E2C4A"));
        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel10.setBackground(Color.decode("#041C34"));
        jPanel20.setBackground(Color.decode("#041C34"));
        jLabel20.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-fill.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-line.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "user");
        updateInfo();
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:
        jPanel20.setBackground(Color.decode("#0E2C4A"));
        jPanel15.setBackground(Color.decode("#041C34"));
        jPanel18.setBackground(Color.decode("#041C34"));
        jPanel10.setBackground(Color.decode("#041C34"));
        jLabel23.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel14.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        scaler.scaleImage(jLabel10, "src\\main\\resources\\dashboard-line.png");
        scaler.scaleImage(jLabel15, "src\\main\\resources\\book-line.png");
        scaler.scaleImage(jLabel18, "src\\main\\resources\\user-line.png");
        scaler.scaleImage(jLabel21, "src\\main\\resources\\settings-fill.png");
        CardLayout cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "setting");
        updateInfo();
    }//GEN-LAST:event_jPanel21MouseClicked

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel idnum;
    private libratech.design.ImageAvatar imageAvatar1;
    public javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    public javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel15;
    public javax.swing.JPanel jPanel16;
    public javax.swing.JPanel jPanel18;
    public javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel20;
    public javax.swing.JPanel jPanel21;
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
        jLabel17.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel20.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel23.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
        jLabel8.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        jLabel9.setFont(new Font("Poppins Regular", Font.PLAIN, 14));
        jLabel5.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        school_n.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        idnum.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        myButton1.setFont(new Font("Poppins Regular", Font.PLAIN, 16));
    }
}
