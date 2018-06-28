package newgui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import newgui.Home;

/**
 *
 * @author PIWROCK
 * @author SOSWEET
 * @author LINGNOI
 */
public class ProgressBar extends javax.swing.JFrame {

    static final int MY_MINIMUM = 0;
    static final int MY_MAXIMUM = 100;
    protected int counter = 0;

    public ProgressBar() {
        initComponents();
        setIconImage();

        jProgressBar1.setMinimum(MY_MINIMUM);
        jProgressBar1.setMaximum(MY_MAXIMUM);
        jProgressBar1.setStringPainted(true);

        runProgress();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        progress_txt = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        progress_txt.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        progress_txt.setText("Downloading...");

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 153));
        jProgressBar1.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(progress_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(309, Short.MAX_VALUE))
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(progress_txt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 460, 50));

        jPanel1.setBackground(new java.awt.Color(0, 191, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/testgen.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("TH SarabunPSK", 1, 18)); // NOI18N
        jLabel2.setText("a Testing Generation Tool for Supporting  System and Acceptance Testing ");

        jLabel3.setFont(new java.awt.Font("TH SarabunPSK", 0, 18)); // NOI18N
        jLabel3.setText("version 2.0");

        jLabel4.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jLabel4.setText("last update 4/18/2018");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 220));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new ProgressBar().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel progress_txt;
    // End of variables declaration//GEN-END:variables

    private void runProgress() {
        Thread runner = new Thread() {
            public void run() {
                counter = MY_MINIMUM;
                while (counter <= MY_MAXIMUM) {
                    Runnable runme = new Runnable() {
                        public void run() {
                            jProgressBar1.setValue(counter);

                            if (counter == 90) {
                                progress_txt.setText("Complete...");
                            }
                            if (counter == 100) {
                                try {
                                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InstantiationException ex) {
                                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (UnsupportedLookAndFeelException ex) {
                                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                new Home().setVisible(true);
    
                                dispose();
                    }
                }
            }

            ;
            SwingUtilities.invokeLater (runme);
            counter

            
                ++;
                    try {
                        Thread.sleep(30);
            }
            catch (Exception ex

            
                ) {
                        System.out.println(ex.getMessage());
            }
        }
    }
};
runner.start();
    }

    private void setIconImage() {
        //setIconImage(new ImageIcon(getClass().getResource("C:\\Users\\5730213082\\Documents\\NetBeansProjects\\TestGenProject\\src\\testgenproject\\testgen.png")).getImage());
    }
}
