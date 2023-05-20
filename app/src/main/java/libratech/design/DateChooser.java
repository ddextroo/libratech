
package libratech.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class DateChooser extends javax.swing.JPanel {

    public JTextField getTextRefernce() {
        return textRefernce;
    }

    public void addEventDateChooser(EventDateChooser event) {
        events.add(event);
    }

    private JTextField textRefernce;
    private final String MONTH_ENGLISH[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String dateFormat = "dd-MM-yyyy";
    private int MONTH = 1;
    private int YEAR = 2021;
    private int DAY = 1;
    private int STATUS = 1;   //  1 is day    2 is month  3 is year
    private int startYear;
    private SelectedDate selectedDate = new SelectedDate();
    private List<EventDateChooser> events;

    /**
     * Creates new form DateChooser
     */
    public DateChooser() {
        initComponents();
        execute();
        initFont();
        setOpaque(false);
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

    private void execute() {
        setForeground(new Color(14, 44, 74));
        events = new ArrayList<>();
        popup.add(this);
        toDay(false);
    }

    public void setTextRefernce(JTextField txt) {
        this.textRefernce = txt;
        this.textRefernce.setEditable(false);
        this.textRefernce.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (textRefernce.isEnabled()) {
                    showPopup();
                }
            }
        });
        setText(false, 0);
    }

    private void setText(boolean runEvent, int act) {
        if (textRefernce != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date date = df.parse(DAY + "-" + MONTH + "-" + YEAR);
                textRefernce.setText(new SimpleDateFormat(dateFormat).format(date));
            } catch (ParseException e) {
                System.err.println(e);
            }
        }
        if (runEvent) {
            runEvent(act);
        }
    }

    private void runEvent(int act) {
        SelectedAction action = new SelectedAction() {
            @Override
            public int getAction() {
                return act;
            }
        };
        for (EventDateChooser event : events) {
            event.dateSelected(action, selectedDate);
        }
    }

    private Event getEventDay(Dates dates) {
        return (MouseEvent evt, int num) -> {
            dates.clearSelected();
            dates.setSelected(num);
            DAY = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 1);
            if (evt != null && evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                popup.setVisible(false);
            }
        };
    }

    private Event getEventMonth() {
        return (MouseEvent evt, int num) -> {
            MONTH = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 2);
            Dates d = new Dates();
            d.setForeground(getForeground());
            d.setEvent(getEventDay(d));
            d.showDate(MONTH, YEAR, selectedDate);
            if (slider2.slideToDown(d)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 1;
            }
        };
    }

    private Event getEventYear() {
        return (MouseEvent evt, int num) -> {
            YEAR = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 3);
            Months d = new Months();
            d.setEvent(getEventMonth());
            if (slider2.slideToDown(d)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 2;
            }
        };
    }

    private void toDay(boolean runEvent) {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        dates.showDate(MONTH, YEAR, selectedDate);
        slider2.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(runEvent, 0);
    }

    public void toDay() {
        toDay(true);
    }

    private void setDateNext() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slider2.slideToLeft(dates)) {
            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setDateBack() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slider2.slideToRight(dates)) {
            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setYearNext() {
        Years years = new Years();
        years.setEvent(getEventYear());
        startYear = years.next(startYear);
        slider2.slideToLeft(years);
    }

    private void setYearBack() {
        if (startYear >= 1000) {
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.back(startYear);
            slider2.slideToLeft(years);
        }
    }

    public void showPopup(Component com, int x, int y) {
        popup.show(com, x, y);
    }

    public void showPopup() {
        popup.show(textRefernce, 0, textRefernce.getHeight());
    }

    public void hidePopup() {
        popup.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup =  new javax.swing.JPopupMenu(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(114, 113, 113));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(Color.WHITE);
                grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            }
        };
        header = new javax.swing.JPanel();
        cmdForward = new libratech.design.Button();
        MY = new javax.swing.JLayeredPane();
        cmdPrevious = new libratech.design.Button();
        jPanel1 = new javax.swing.JPanel();
        cmdMonth = new libratech.design.Button();
        lb = new javax.swing.JLabel();
        cmdYear = new libratech.design.Button();
        slider2 = new libratech.design.Slider();

        setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(4, 28, 52));
        header.setMaximumSize(new java.awt.Dimension(262, 40));

        cmdForward.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/forward.png"))); // NOI18N
        cmdForward.setFocusable(true);
        cmdForward.setPaintBackground(false);
        cmdForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdForwardActionPerformed(evt);
            }
        });

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0);
        flowLayout1.setAlignOnBaseline(true);
        MY.setLayout(flowLayout1);

        cmdPrevious.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/previous.png"))); // NOI18N
        cmdPrevious.setFocusable(true);
        cmdPrevious.setPaintBackground(false);
        cmdPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPreviousActionPerformed(evt);
            }
        });
        cmdPrevious.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdPreviousKeyPressed(evt);
            }
        });

        jPanel1.setOpaque(false);

        cmdMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdMonth.setForeground(new java.awt.Color(255, 255, 255));
        cmdMonth.setText("January");
        cmdMonth.setFocusPainted(false);
        cmdMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmdMonth.setPaintBackground(false);
        cmdMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMonthActionPerformed(evt);
            }
        });
        jPanel1.add(cmdMonth);

        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("-");
        lb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(lb);

        cmdYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdYear.setForeground(new java.awt.Color(255, 255, 255));
        cmdYear.setText("2018");
        cmdYear.setFocusPainted(false);
        cmdYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmdYear.setPaintBackground(false);
        cmdYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdYearActionPerformed(evt);
            }
        });
        jPanel1.add(cmdYear);

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(MY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdPrevious, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdForward, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        slider2.setLayout(new javax.swing.BoxLayout(slider2, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreviousActionPerformed
        if (STATUS == 1) {   //  Date
            if (MONTH == 1) {
                MONTH = 12;
                YEAR--;
            } else {
                MONTH--;
            }
            setDateBack();
        } else if (STATUS == 3) {    //  Year
            setYearBack();
        } else {
            if (YEAR >= 1000) {
                YEAR--;
                Months months = new Months();
                months.setEvent(getEventMonth());
                slider2.slideToLeft(months);
                cmdYear.setText(YEAR + "");
            }
        }
    }//GEN-LAST:event_cmdPreviousActionPerformed

    private void cmdForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdForwardActionPerformed
        if (STATUS == 1) {   //  Date
            if (MONTH == 12) {
                MONTH = 1;
                YEAR++;
            } else {
                MONTH++;
            }
            setDateNext();
        } else if (STATUS == 3) {    //  Year
            setYearNext();
        } else {
            YEAR++;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slider2.slideToLeft(months);
            cmdYear.setText(YEAR + "");
        }
    }//GEN-LAST:event_cmdForwardActionPerformed

    private void cmdYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdYearActionPerformed
        if (STATUS != 3) {
            STATUS = 3;
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.showYear(YEAR);
            slider2.slideToDown(years);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slider2.slideToDown(dates);
            STATUS = 1;
        }
    }//GEN-LAST:event_cmdYearActionPerformed

    private void cmdMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMonthActionPerformed
        // TODO add your handling code here:
        if (STATUS != 2) {
            STATUS = 2;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slider2.slideToDown(months);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slider2.slideToDown(dates);
            STATUS = 1;
        }
    }//GEN-LAST:event_cmdMonthActionPerformed

    private void cmdPreviousKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdPreviousKeyPressed
        // TODO add your handling code here:
        if (STATUS != 2) {
            STATUS = 2;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slider2.slideToDown(months);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slider2.slideToDown(dates);
            STATUS = 1;
        }
    }//GEN-LAST:event_cmdPreviousKeyPressed

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setSelectedDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String d = df.format(date);
        DAY = Integer.valueOf(d.split("-")[0]);
        MONTH = Integer.valueOf(d.split("-")[1]);
        YEAR = Integer.valueOf(d.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slider2.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane MY;
    private libratech.design.Button cmdForward;
    private libratech.design.Button cmdMonth;
    private libratech.design.Button cmdPrevious;
    private libratech.design.Button cmdYear;
    private javax.swing.JPanel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb;
    private javax.swing.JPopupMenu popup;
    private libratech.design.Slider slider2;
    // End of variables declaration//GEN-END:variables
    public SelectedDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(SelectedDate selectedDate) {
        this.selectedDate = selectedDate;
        DAY = selectedDate.getDay();
        MONTH = selectedDate.getMonth();
        YEAR = selectedDate.getYear();
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slider2.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        if (header != null) {
            header.setBackground(new Color(4, 28, 52));
            toDay(false);
        }
    }

    public void initFont() {
        cmdMonth.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        lb.setFont(new Font("Poppins Regular", Font.BOLD, 14));
        cmdYear.setFont(new Font("Poppins Regular", Font.BOLD, 14));
    }
}
