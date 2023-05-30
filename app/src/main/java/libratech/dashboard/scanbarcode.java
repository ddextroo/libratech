package libratech.dashboard;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import libratech.design.DefaultOption;
import libratech.design.GlassPanePopup;
import libratech.design.Option;
import libratech.design.RoundedPanel;
import libratech.design.RoundedWebcamPanel;

public class scanbarcode extends javax.swing.JPanel implements Runnable {

    /**
     * Creates new form scan barcode
     */
    private Webcam webcam = null;

    public scanbarcode() {
        initComponents();
        setOpaque(false);
        startCameraPreview();

    }

    private void startCameraPreview() {
        int webcamCount = Webcam.getWebcams().size();
        String[] webcams = new String[webcamCount];

        for (int wc = 0; wc < webcamCount; wc++) {
            webcams[wc] = Webcam.getWebcams().toString();
        }
        comboBoxSuggestion1.setModel(new javax.swing.DefaultComboBoxModel(webcams));
        comboBoxSuggestion1.getEditor().getEditorComponent().setBackground(new Color(250, 250, 250,0));
        //webcam = Webcam.getWebcams().get(comboBoxSuggestion1.getSelectedIndex());
                webcam = Webcam.getWebcams().get(1);


        Dimension size = WebcamResolution.VGA.getSize();
        webcam.setViewSize(size);
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(false);

        jPanel3.add(panel, BorderLayout.CENTER);
        
        
        new Thread(this).start();
    }

    private void processBarcode(BufferedImage image) {

        try {
//            image = ImageIO.read(new File("src\\main\\resources\\file.png"));
            //jLabel1.setIcon(new ImageIcon(image));
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = new MultiFormatReader().decode(bitmap);

            String barcodeText = result.getText();
            //resultLabel.setText("Barcode: " + barcodeText);
            webcam.close();
            GlassPanePopup.closePopupLast();
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
            GlassPanePopup.showPopup(new cartreturn(barcodeText), option, "return");

        } catch (Exception e) {
            //resultLabel.setText("No barcode found");
        }
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                processBarcode(image);
            }
        } while (true);

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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new RoundedPanel(12, new Color(250,250,250,0));
        comboBoxSuggestion1 = new libratech.design.ComboBoxSuggestion();

        setBackground(new java.awt.Color(33, 33, 33));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

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

        jPanel1.add(jPanel10);

        jPanel3.add(jPanel1, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxSuggestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion1ActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, comboBoxSuggestion1, "Select", JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_comboBoxSuggestion1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private libratech.design.ComboBoxSuggestion comboBoxSuggestion1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}
