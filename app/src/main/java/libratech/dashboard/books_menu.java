/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package libratech.dashboard;

import java.awt.Font;
import libratech.design.GlassPanePopup;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import libratech.models.Book;
import libratech.util.firebaseInit;

/**
 *
 * @author HBUSER
 */
public class books_menu extends javax.swing.JPanel {

    private DefaultTableModel model;
    private List<Book> books;
    private DatabaseReference dbRef;

    public books_menu() {
        initComponents();
        initFont();
        new firebaseInit().initFirebase();

        // Fetch data from Firebase and create table
        dbRef = FirebaseDatabase.getInstance().getReference("books/inshelf");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CustomTableModel model = new CustomTableModel();
                model.addColumn("Book Title");
                model.addColumn("Book Publisher");
                model.addColumn("Book Genre");
                model.addColumn("Book Author");
                model.addColumn("Book Control Number");
                model.addColumn("Book Quantity");
                model.addColumn("Book Issued");
                model.addColumn("Book Status");
                model.addColumn("Actions");

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String bookCoverUrl = child.child("cover").getValue(String.class);
                    String bookTitle = child.child("booktitle").getValue(String.class);
                    String publisher = child.child("publisher").getValue(String.class);
                    String genre = child.child("genre").getValue(String.class);
                    String author = child.child("bookauthor").getValue(String.class);
                    String dewey = child.child("dewey").getValue(String.class);
                    String quantity = child.child("quantity").getValue(String.class);
                    String deck = child.child("deck").getValue(String.class);
                    String shelf = child.child("shelf").getValue(String.class);
                    String date = child.child("date").getValue(String.class);
                    String status = child.child("status").getValue(String.class);
                    Book book = new Book(bookCoverUrl, author, bookTitle, date, deck, genre, dewey, publisher, quantity, shelf, status);

                    myButtonborderless1 = new libratech.design.MyButtonborderless();
                    myButtonborderless1.setForeground(new java.awt.Color(224, 224, 224));
                    myButtonborderless1.setText("Cancel");
                    myButtonborderless1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    myButtonborderless1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            myButtonborderless1MouseClicked(evt);

                            JOptionPane.showMessageDialog(null, "test");
                        }
                    });

                    model.addRow(new Object[]{book.getTitle(), book.getPublisher(), book.getGenre(), book.getAuthor(), book.getDewey(), book.getQuantity(), deck + "-" + book.getDeck(), book.getStatus(), myButtonborderless1});
                }

                table.setModel(model);
                table.setDefaultRenderer(Object.class, new CustomCellRenderer());
                table.setRowHeight(50);
                table.setCellSelectionEnabled(false);

                table.setFocusable(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getMessage());
            }
        });
        
    }

    class CustomTableModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Edit button column
        }


    }

    class CustomCellRenderer extends DefaultTableCellRenderer {

        private static final int GAP = 20;
        private static final int PADDING = 10;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == 8) {
                myButtonborderless1.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
                myButtonborderless1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        myButtonborderless1MouseClicked(evt);
                        System.out.print("gwapo");
                        JOptionPane.showMessageDialog(null, "test");
                    }
                });
                c = myButtonborderless1;
            }

            // Center all cells
            if (c instanceof JLabel) {
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
            }
            if (c instanceof JLabel) {
                ((JLabel) c).setVerticalAlignment(SwingConstants.CENTER);
            }

            return c;
        }
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
        jPanel9 = new javax.swing.JPanel();
        materialTabbed1 = new libratech.design.MaterialTabbed();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 995, Short.MAX_VALUE)
                .addComponent(myButtonborderless1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(myButtonborderless1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(new java.awt.BorderLayout());

        materialTabbed1.setBackground(new java.awt.Color(250, 250, 250));

        jPanel2.setLayout(new java.awt.BorderLayout());

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        materialTabbed1.addTab("In-Shelf", jPanel2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1329, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Borrowed", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1329, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Overdue", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1329, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Lost", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1299, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Damaged", jPanel7);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1299, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Returned", jPanel10);

        jPanel9.add(materialTabbed1, java.awt.BorderLayout.PAGE_START);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private libratech.design.MaterialTabbed materialTabbed1;
    private libratech.design.MyButtonborderless myButtonborderless1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    public void initFont() {
        materialTabbed1.setFont(new Font("Poppins Regular", Font.BOLD, 16));
        jLabel1.setFont(new Font("Poppins Regular", Font.BOLD, 24));
        myButtonborderless1.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        table.setFont(new Font("Poppins Regular", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Poppins Regular", Font.PLAIN, 16));
    }
}
