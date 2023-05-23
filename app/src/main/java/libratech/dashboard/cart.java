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
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author HBUSER
 */
public class cart extends javax.swing.JPanel {

    ImageScaler scaler = new ImageScaler();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String ID_NUMBER;
    private String BOOK_KEY;
    private ChildEventListener accinfo;
    private final String path_selectuser = "cart/" + new getUID().getUid() + "/";
    private final DatabaseReference acc = FirebaseDatabase.getInstance().getReference(path_selectuser);

    private ChildEventListener studentinfo;
    private final String path_selectstudent = "students/" + new getUID().getUid() + "/";
    private final DatabaseReference student = FirebaseDatabase.getInstance().getReference(path_selectstudent);

    private DatabaseReference dbRef;
    DefaultTableModel mod;

    public cart() {
        initComponents();
        new firebaseInit().initFirebase();
        initFont();
        String key = databaseReference.push().getKey();
        barcode(key);
        String getnow = new SimpleDateFormat("MMMM d, yyyy").format(Calendar.getInstance().getTime());
        jLabel4.setText(getnow);
        retrieveData();

        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setPreferredSize(new Dimension(12, 70));
        jScrollPane1.setVerticalScrollBar(sb);
        ScrollBarCustom sbH = new ScrollBarCustom();
        sbH.setOrientation(JScrollBar.HORIZONTAL);
        sbH.setPreferredSize(new Dimension(12, 12));
        jScrollPane1.setHorizontalScrollBar(sbH);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.mod = (DefaultTableModel) inshelfTable1.getModel();
        inshelfTable1.fixTable(jScrollPane3);
        inshelfTable1.setBackground(new Color(250, 250, 250));
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
            float panelWidth = this.getWidth();
            float panelHeight = this.getHeight();
            float x = (pageWidth - panelWidth) / 2;
            float y = (pageHeight - panelHeight) / 2;

            // Create a Graphics2D object from the contentByte
            Graphics2D graphics2D = contentByte.createGraphicsShapes((int) pageWidth, (int) pageHeight);
            graphics2D.translate(x, y);

            // Set the panel as the target for rendering
            this.print(graphics2D);

            // Dispose the graphics object
            graphics2D.dispose();

            contentByte.restoreState();
            document.close();

            sendEmail(outputPath);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail(String filePath) {
        // Sender's email configuration
        String senderEmail = "dexter.inguito@ctu.edu.ph";
        String senderPassword = "CTUInguito2021";

        // Recipient's email address
        String recipientEmail = "lourdjonas.torrejos@ctu.edu.ph";

        // Email subject and content
        String emailSubject = "PDF File";
        String emailContent = "Please find the PDF file attached.";

        // SMTP server configuration
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a multipart message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(emailSubject);

            // Create the message body part for the email content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailContent);

            // Create the message body part for the PDF attachment
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(new File(filePath).getName());

            // Create a multipart message and add the parts
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Set the multipart message as the content of the email
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void retrieveDataBooks() {

        EventAction eventAction = new EventAction() {
            @Override
            public void update(Book book) {

            }
        };
        dbRef = FirebaseDatabase.getInstance().getReference("cart/" + new getUID().getUid() + "/borrower/books");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mod.setRowCount(0);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String bookTitle = child.child("book_title").getValue(String.class);
                    String barcode = child.child("book_key").getValue(String.class);
                    inshelfTable1.addRow(new Book(bookTitle, barcode).toRowTableReceipt(eventAction));
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

    private void barcode(String code) {
        try {
            Barcode barcode = BarcodeFactory.createCode128(code);
            barcode.setFont(new Font("Poppins Regular", Font.BOLD, 12));
            barcode.setBarHeight(60);
            barcode.setBarWidth(2);

            // Create a temporary file to save the barcode image
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
                    ID_NUMBER = _childValue.get("idno").toString();
                    BOOK_KEY = _childValue.get("idno").toString();
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
                    ID_NUMBER = _childValue.get("idno").toString();
                    BOOK_KEY = _childValue.get("idno").toString();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        borrow = new libratech.design.MyButtonborderless();
        pdf = new libratech.design.MyButtonborder();
        cancel = new libratech.design.MyButtonborder();
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
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        inshelfTable1 = new libratech.books.inshelf.InshelfTable();

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jScrollPane1.setBackground(new java.awt.Color(250, 250, 250));

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(250, 250, 250));

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

        pdf.setForeground(new java.awt.Color(23, 23, 23));
        pdf.setText("Email Receipt");
        pdf.setPreferredSize(new java.awt.Dimension(102, 23));
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });

        cancel.setForeground(new java.awt.Color(23, 23, 23));
        cancel.setText("Close");
        cancel.setPreferredSize(new java.awt.Dimension(102, 23));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.add(jPanel10, java.awt.BorderLayout.PAGE_END);

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
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
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

        jPanel9.setBackground(new java.awt.Color(250, 250, 250));

        inshelfTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Book Title", "Book Key"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inshelfTable1.setPreferredSize(new java.awt.Dimension(150, 100));
        jScrollPane3.setViewportView(inshelfTable1);
        if (inshelfTable1.getColumnModel().getColumnCount() > 0) {
            inshelfTable1.getColumnModel().getColumn(0).setResizable(false);
            inshelfTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel9.add(jScrollPane3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userinfolabel)
                            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phone, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userinfolabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(address)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4);

        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel3);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void borrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowMouseClicked
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new add_book());
    }//GEN-LAST:event_borrowMouseClicked

    private void borrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_borrowActionPerformed

    private void pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActionPerformed
        // TODO add your handling code here:
        convertToPDF("output.pdf");
    }//GEN-LAST:event_pdfActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_cancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private libratech.design.MyButtonborderless borrow;
    private libratech.design.MyButtonborder cancel;
    private javax.swing.JLabel email;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private libratech.books.inshelf.InshelfTable inshelfTable1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel name;
    private libratech.design.MyButtonborder pdf;
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

        pdf.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        cancel.setFont(new Font("Poppins Regular", Font.BOLD, 12));
        borrow.setFont(new Font("Poppins Regular", Font.BOLD, 12));
    }
}
