package libratech.auth;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import libratech.auth.login;
import libratech.dashboard.home;
import libratech.design.RoundedPanelBorderless;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import libratech.admin.home_admin;
import libratech.design.GlassPanePopup;
import libratech.design.ImageScaler;
import libratech.design.loading;
import libratech.models.aes;

public class splash extends javax.swing.JFrame {

    Timer t;
    int duration = 5000;
    Color startColor = new java.awt.Color(224, 224, 224);
    long startTime = System.currentTimeMillis();
    private File file;
    private File file1;
    String fileContent = "";
    String fileContent1 = "";
    aes aes = new aes();

    public splash() {
        this.file = new File("uid.txt");
        this.file1 = new File("remember.txt");
        if (file1.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                fileContent1 = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileContent1 = "";
        }
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                fileContent = sb.toString();
                try {
                    fileContent = aes.decryptString(fileContent, aes.getPassword());
                } catch (Exception ex) {
                    Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ImageIcon icon = new ImageIcon("resources1/logo.png");
        this.setIconImage(icon.getImage());

        initComponents();
        initFont();
        timerTonext();
        ImageScaler scaler = new ImageScaler();
        scaler.scaleImage(jLabel1, "src\\main\\resources\\logo.png");
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jProgressBar1.setForeground(new java.awt.Color(4, 28, 52));
        getContentPane().add(jProgressBar1, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBackground(new java.awt.Color(4, 28, 52));
        jPanel1.setForeground(new java.awt.Color(41, 182, 246));

        jPanel3.setBackground(new java.awt.Color(4, 28, 52));
        jPanel3.setPreferredSize(new java.awt.Dimension(900, 426));

        title.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title.setForeground(new java.awt.Color(245, 245, 245));
        title.setText("LIBRATECH");

        description.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        description.setForeground(new java.awt.Color(245, 245, 245));
        description.setText("The Modern Solution for Efficient Library Management");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
            .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        jLabel1.setText("   ");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new splash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
 public void initFont() {
        title.setFont(new Font("Poppins Regular", Font.BOLD, 36));
        description.setFont(new Font("Poppins Regular", Font.PLAIN, 24));

    }

    public static boolean isInternetConnected() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setConnectTimeout(5000);
            urlConnect.getContent();
            urlConnect.disconnect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void timerTonext() {
        jProgressBar1.setValue(0);
        jProgressBar1.setString(" ");
        jProgressBar1.setStringPainted(true);
        t = new Timer(50, (ActionEvent e) -> {
            long now = System.currentTimeMillis();
            long elapsedTime = now - startTime;
            int val = jProgressBar1.getValue();
            if (elapsedTime > duration) {
                t.stop();
                if (fileContent1.equals("")) {
                    login login = new login();
                    login.setVisible(true);
                    setVisible(false);
                    this.dispose();
                } else if (file.exists() && file1.exists()) {
                    if (fileContent.equals("sy01q5KeBdMnX1vpS2Lk86NcCdp1")) {
                        home_admin home = new home_admin();
                        home.updateLabelText(fileContent);
                        home.setVisible(true);
                        this.dispose();
                    } else {
                        home home = new home();
                        home.updateLabelText(fileContent);
                        home.setVisible(true);
                        this.dispose();
                    }

                } else if (!file.exists() && !file1.exists()) {
                    login login = new login();
                    login.setVisible(true);
                    setVisible(false);
                    this.dispose();
                } else if (file1.exists()) {
                    home home = new home();
                    home.updateLabelText(fileContent);
                    home.setVisible(true);
                    this.dispose();
                } else {
                    login login = new login();
                    login.setVisible(true);
                    setVisible(false);
                    this.dispose();
                }
            } else {
                jProgressBar1.setValue(++val + 20);
                if (val > 50) {
                    if (!isInternetConnected()) {
                        JOptionPane.showMessageDialog(null, "No internet connection available : Closing the app", "Error", JOptionPane.ERROR_MESSAGE);
                        Timer timer1 = new Timer(1000, ee -> {
                            System.exit(0);
                        });
                        timer1.setRepeats(false);
                        timer1.start();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        t.start();
    }
}
