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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import libratech.models.pushValueExisting;
import libratech.util.smtp;

/**
 *
 * @author HBUSER
 */
public class cart extends javax.swing.JPanel {

    ImageScaler scaler = new ImageScaler();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference transaction = FirebaseDatabase.getInstance().getReference("cart/" + new getUID().getUid() + "/borrower/");
    private ChildEventListener accinfo;
    private final String path_selectuser = "latest_borrower/" + new getUID().getUid() + "/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path_selectuser);

    private ChildEventListener studentinfo;
    private DatabaseReference.CompletionListener completionListener;
    private final String path_selectstudent = "students/" + new getUID().getUid() + "/";
    private final DatabaseReference student = FirebaseDatabase.getInstance().getReference(path_selectstudent);

    private ChildEventListener booksinfo;
    private final String path_book = "books/" + new getUID().getUid() + "/";
    private final DatabaseReference book = FirebaseDatabase.getInstance().getReference(path_book);

    private DatabaseReference dbRef;
    private DatabaseReference dbRef2;
    private DatabaseReference dbRef3;

    DefaultTableModel mod;
    String key = databaseReference.push().getKey();
    String email_add;
    String fname;
    String idnum;
    String bookTitle;
    String due_date;
    String borrowed_date;
    String barcode;
    String remaining_copies;
    private List<Object> columnData;
    private HashMap<String, Object> m;
    private pushValueExisting v;
    private pushValue v2;

    public cart() {
        initComponents();
        new firebaseInit().initFirebase();
        initFont();
        barcode(key);
        String getnow = new SimpleDateFormat("MMMM d, yyyy").format(Calendar.getInstance().getTime());
        jLabel4.setText(getnow);
        retrieveData();

        this.mod = (DefaultTableModel) inshelfTable1.getModel();
        inshelfTable1.fixTable(jScrollPane3);
        inshelfTable1.setBackground(new Color(250, 250, 250));

        completionListener = (DatabaseError error, DatabaseReference ref) -> {
            if (error != null) {
                System.out.println("Error removing value: " + error.getMessage());
            } else {
            }
        };

        retrieveDataBooks();
    }

    public void convertToPDF(String outputPath) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            PdfContentByte contentByte = writer.getDirectContent();
            contentByte.saveState();

            // Calculate the center position
            float pageWidth = document.getPageSize().getWidth();
            float pageHeight = document.getPageSize().getHeight();
            float panelWidth = jPanel3.getWidth();
            float panelHeight = jPanel3.getHeight();
            float x = (pageWidth - panelWidth) / 2;
            float y = (pageHeight - panelHeight) / 2;

            // Create a Graphics2D object from the contentByte
            Graphics2D graphics2D = contentByte.createGraphicsShapes((int) pageWidth, (int) pageHeight);
            graphics2D.translate(x, y);

            // Set the panel as the target for rendering
            jPanel3.print(graphics2D);

            // Dispose the graphics object
            graphics2D.dispose();

            contentByte.restoreState();
            document.close();
            new smtp().sendMail("Receipt for Book Borrowing - " + key, "Dear " + fname + ",\n\n"
                    + "We hope this email finds you well. We would like to thank you for utilizing our Library Management System and borrowing the books. As per your request, we have generated a PDF receipt for your borrowing transaction. Please find the attached PDF document, which contains the receipt details."
                    + "\n\nWe value your continued patronage and encourage you to explore the various resources available in our library. Should you have any questions or concerns, please do not hesitate to reach out to our dedicated support team."
                    + "\n\nThank you once again for choosing our Library Management System. We hope you enjoy your reading experience and look forward to serving you in the future."
                    + "\n\nBest regards,"
                    + "\n\nLibratech Team", email_add, outputPath);
            retrieveDataBooksInfo();
            storeTransaction();
            deleteTransaction();
            deleteLatestBorrower();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();

        } catch (Exception ex) {
            Logger.getLogger(cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteTransaction() {

        for (Object item : columnData) {
            transaction.child((String) item).removeValue(completionListener);
            GlassPanePopup.closePopupAll();
        }
    }

    private void retrieveDataBooksInfo() {

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
                        String remaining = _childValue.get("remaining_copies").toString();
                        int remain = Integer.parseInt(remaining);
                        System.out.println(remain);
                        v = new pushValueExisting(_childKey);
                        m = new HashMap<>();
                        m.put("remaining_copies", remain - 1);
                        v.pushData("books/" + new getUID().getUid(), m);
                        m.clear();
                        if (remain <= 1) {
                            v = new pushValueExisting(_childKey);
                            m = new HashMap<>();
                            m.put("status", "Borrowed");
                            v.pushData("books/" + new getUID().getUid(), m);
                            m.clear();
                        }
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

    private void deleteLatestBorrower() {
        dbRef3 = FirebaseDatabase.getInstance().getReference("latest_borrower/" + new getUID().getUid() + "/borrower");
        dbRef3.getRef().removeValue(completionListener);
    }

    private void retrieveDataBooks() {

        EventAction eventAction = new EventAction() {
            @Override
            public void update(Book book) {
                for (Object item : columnData) {
                    String bItem = (String) item;
                    if (bItem.equals(book.getBarcode())) {
                        transaction.child(bItem).removeValue(completionListener);
                        GlassPanePopup.closePopupAll();
                        break;
                    }
                }
            }
        };
        dbRef = FirebaseDatabase.getInstance().getReference("cart/" + new getUID().getUid() + "/borrower");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    bookTitle = child.child("book_title").getValue(String.class);
                    barcode = child.child("book_key").getValue(String.class);
                    due_date = child.child("due_date").getValue(String.class);
                    borrowed_date = child.child("borrowed_date").getValue(String.class);
                    inshelfTable1.addRow(new Book(bookTitle, barcode, due_date).toRowTableReceipt(eventAction));
                    columnData = inshelfTable1.getColumnData(1);
                    mod.fireTableDataChanged();
                    inshelfTable1.repaint();
                    inshelfTable1.revalidate();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }

    private void storeTransaction() {

        dbRef2 = FirebaseDatabase.getInstance().getReference("cart/" + new getUID().getUid() + "/borrower");
        dbRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v = new pushValueExisting(key + "/" + child.child("book_key").getValue(String.class));
                    m = new HashMap<>();
                    m.put("idno", idnum);
                    m.put("book_title", child.child("book_title").getValue(String.class));
                    m.put("book_key", child.child("book_key").getValue(String.class));
                    m.put("due_date", child.child("due_date").getValue(String.class));
                    m.put("borrowed_date", child.child("borrowed_date").getValue(String.class));
                    v.pushData("borrowerlist/" + new getUID().getUid(), m);
                    m.clear();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

    }

    private void barcode(String code) {
        try {
            Barcode barcode = BarcodeFactory.createCode128(code);
            barcode.setFont(new Font("Poppins Regular", Font.BOLD, 12));
            barcode.setBarHeight(60);
            barcode.setBarWidth(2);

            File file = File.createTempFile("barcode", ".png");
            BarcodeImageHandler.savePNG(barcode, file);
            scaler.scaleImage(jLabel7, file.getAbsolutePath());

        } catch (BarcodeException | OutputException | IOException ex) {
            Logger.getLogger(books_menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void retrieveData() {
        accinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (_childKey.equals("borrower")) {
                    retrieveDataStudentInfo(_childValue.get("idno").toString());
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
                if (_childKey.equals("borrower")) {
                    retrieveDataStudentInfo(_childValue.get("idno").toString());
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

    private void retrieveDataStudentInfo(String idno) {
        studentinfo = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = dataSnapshot.getKey();
                final HashMap<String, Object> _childValue = dataSnapshot.getValue(_ind);
                if (_childKey.equals(idno)) {
                    name.setText("Name: " + _childValue.get("fullname").toString());
                    address.setText("Address: " + _childValue.get("address").toString());
                    email.setText("Email Address: " + _childValue.get("email").toString());
                    email_add = _childValue.get("email").toString();
                    fname = _childValue.get("fullname").toString();
                    idnum = _childValue.get("idno").toString();
                    phone.setText("Phone Number: " + _childValue.get("phone").toString());
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
                if (_childKey.equals(idno)) {
                    name.setText("Name: " + _childValue.get("fullname").toString());
                    address.setText("Address: " + _childValue.get("address").toString());
                    email.setText("Email Address: " + _childValue.get("email").toString());
                    phone.setText("Phone Number: " + _childValue.get("phone").toString());
                    email_add = _childValue.get("email").toString();
                    fname = _childValue.get("fullname").toString();
                    idnum = _childValue.get("idno").toString();
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
        student.addChildEventListener(studentinfo);
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        userinfolabel = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        inshelfTable1 = new libratech.books.borrowlist.borrowTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        cancel = new libratech.design.MyButtonborder();
        borrow = new libratech.design.MyButtonborderless();
        borrow1 = new libratech.design.MyButtonborderless();

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);

        jPanel12.setBackground(new java.awt.Color(250, 250, 250));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("LIBRATECH");
        jPanel5.add(jLabel1);
        jPanel5.add(filler1);

        jPanel6.setBackground(new java.awt.Color(250, 250, 250));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Library System Solutions");
        jPanel6.add(jLabel2);

        jPanel7.setBackground(new java.awt.Color(250, 250, 250));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("RECEIPT");
        jPanel7.add(jLabel3);
        jPanel7.add(filler2);

        jPanel8.setBackground(new java.awt.Color(250, 250, 250));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel4.setText("hehe");
        jPanel8.add(jLabel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBackground(new java.awt.Color(250, 250, 250));

        userinfolabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        userinfolabel.setText("User Information");

        name.setText("Name:");

        address.setText("Address:");

        email.setText("Email Address: ");

        phone.setText("Phone Number: ");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setPreferredSize(new java.awt.Dimension(433, 52));

        inshelfTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Book Title", "Book Code", "Due Date", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(inshelfTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userinfolabel)
                            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phone, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userinfolabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(address)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);

        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(250, 250, 250));

        jPanel11.setBackground(new java.awt.Color(250, 250, 250));

        cancel.setForeground(new java.awt.Color(23, 23, 23));
        cancel.setText("Close");
        cancel.setPreferredSize(new java.awt.Dimension(102, 23));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        borrow.setForeground(new java.awt.Color(250, 250, 250));
        borrow.setText("Borrow");
        borrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrowMouseClicked(evt);
            }
        });
        borrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowActionPerformed(evt);
            }
        });

        borrow1.setForeground(new java.awt.Color(250, 250, 250));
        borrow1.setText("Remove all");
        borrow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrow1MouseClicked(evt);
            }
        });
        borrow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrow1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void borrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_borrowMouseClicked

    private void borrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowActionPerformed
        // TODO add your handling code here:
        convertToPDF("libratech_receipt.pdf");
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_borrowActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_cancelActionPerformed

    private void borrow1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrow1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_borrow1MouseClicked

    private void borrow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrow1ActionPerformed
        // TODO add your handling code here:
        deleteTransaction();
        deleteLatestBorrower();
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_borrow1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private libratech.design.MyButtonborderless borrow;
    private libratech.design.MyButtonborderless borrow1;
    private libratech.design.MyButtonborder cancel;
    private javax.swing.JLabel email;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private libratech.books.borrowlist.borrowTable inshelfTable1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel userinfolabel;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        jLabel1.setFont(new Font("Poppins Regular", Font.BOLD, 30));
        jLabel3.setFont(new Font("Poppins Regular", Font.BOLD, 30));
        userinfolabel.setFont(new Font("Poppins Regular", Font.BOLD, 18));
        jLabel2.setFont(new Font("Poppins Regular", Font.PLAIN, 14));
        jLabel4.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        name.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        email.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        address.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        phone.setFont(new Font("Poppins Regular", Font.PLAIN, 12));

        cancel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        borrow.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        borrow1.setFont(new Font("Poppins Regular", Font.BOLD, 12));
    }
}
