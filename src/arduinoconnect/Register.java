package arduinoconnect;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps =null;
    ResultSet res = null;
    String UserReg = null, PassReg = null, PassConf = null;
    public Register() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        RegLogo = new javax.swing.JLabel();
        RegClose = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        TfUserReg = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BtnReg = new javax.swing.JButton();
        TfPassReg = new javax.swing.JPasswordField();
        TfPassConf = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.85F);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setOpaque(false);

        RegLogo.setBackground(new java.awt.Color(255, 255, 255));
        RegLogo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        RegLogo.setForeground(new java.awt.Color(255, 255, 255));
        RegLogo.setText("Register");

        RegClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arduinoconnect/close.png"))); // NOI18N
        RegClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegCloseMouseExited(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username");

        TfUserReg.setBackground(new java.awt.Color(255, 255, 255));
        TfUserReg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TfUserRegFocusLost(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Confirm Password");

        BtnReg.setText("Register");
        BtnReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
            }
        });

        TfPassReg.setBackground(new java.awt.Color(255, 255, 255));
        TfPassReg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TfPassRegFocusLost(evt);
            }
        });

        TfPassConf.setBackground(new java.awt.Color(255, 255, 255));
        TfPassConf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TfPassConfFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(RegLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RegClose))
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(BtnReg))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(TfUserReg)
                            .addComponent(TfPassReg)
                            .addComponent(TfPassConf, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RegClose, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(RegLogo)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TfUserReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TfPassReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TfPassConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(BtnReg)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void RegCloseMouseClicked(java.awt.event.MouseEvent evt) {                                      
        this.dispose();
        Login log =new Login();
        log.setLocationRelativeTo(null);
        log.getContentPane().setBackground(Color.black);
        log.setVisible(true);
    }                                     

    private void TfUserRegFocusLost(java.awt.event.FocusEvent evt) {                                    
        UserReg = TfUserReg.getText();
        if(UserReg.length()<1)
            UserReg=null;
        System.out.println(UserReg);
    }                                   

    private void TfPassRegFocusLost(java.awt.event.FocusEvent evt) {                                    
        char temp[] = TfPassReg.getPassword();
        PassReg = new String(temp);
        if(PassReg.length()<1)
            PassReg=null;
        System.out.println(PassReg);
    }                                   

    private void TfPassConfFocusLost(java.awt.event.FocusEvent evt) {                                     
        char temp[] = TfPassConf.getPassword();
        PassConf = new String(temp);
        if(PassConf.length()<1)
            PassConf=null;
        System.out.println(PassConf);
    }                                    

    private void RegCloseMouseEntered(java.awt.event.MouseEvent evt) {                                      
        RegClose.setOpaque(true);
        RegClose.setBackground(Color.DARK_GRAY);
    }                                     

    private void RegCloseMouseExited(java.awt.event.MouseEvent evt) {                                     
        RegClose.setOpaque(false);
        RegClose.setBackground(Color.red);
    }                                    

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {                                       
        if(UserReg!=null && PassReg!=null && PassConf!=null)
        {
            if(PassReg.equals(PassConf))
            {
                try {
                    String user1 =null, pass1 = null;
                    String sql = "INSERT into LoginData (name, pass)" 
                                +"VALUES ('" +UserReg+ "','" +PassReg+ "');";
                    System.out.println(sql);
                    String url = "jdbc:sqlite:C:/sqlite/LoginInfo.db";
                    Class.forName("org.sqlite.JDBC");
                    // create a connection to the database
                    conn = DriverManager.getConnection(url);
                    stmt = conn.createStatement();
                    stmt.execute(sql);
                    this.dispose();
                    
                    Login log = new Login();
                    log.setLocationRelativeTo(null);
                    log.getContentPane().setBackground(Color.black);
                    log.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JFrame jf = new JFrame();
                JOptionPane.showMessageDialog(jf,"Passwords do not match!");
            }
        }
        else{
            JFrame jf = new JFrame();
            JOptionPane.showMessageDialog(jf,"Fill all details!");
        }
    }                                      

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Register reg = new Register();
                reg.setLocationRelativeTo(null);
                reg.getContentPane().setBackground(Color.black);
                reg.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton BtnReg;
    private javax.swing.JLabel RegClose;
    private javax.swing.JLabel RegLogo;
    private javax.swing.JPasswordField TfPassConf;
    private javax.swing.JPasswordField TfPassReg;
    private javax.swing.JTextField TfUserReg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration                   
}
