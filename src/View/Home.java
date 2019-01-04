/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Main;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Home extends javax.swing.JFrame {

    private Main main;
    
    public Home(Main main) {
        initComponents();
        this.main = main;
    }

    private Home() {
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_home = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panel_pelatihan = new javax.swing.JPanel();
        data_management = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        progres_muat_citra = new javax.swing.JProgressBar();
        pilih_folder = new javax.swing.JPanel();
        folder_chooser = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lokasi_direktori = new javax.swing.JLabel();
        image_panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_image = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        train_management = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        profil_proy_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        training_result = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label_mse = new javax.swing.JLabel();
        label_waktu = new javax.swing.JLabel();
        prog_bar = new javax.swing.JPanel();
        progress_bar_pelatihan = new javax.swing.JProgressBar();
        train_konfig = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        epoch1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        label_input_epcoh = new javax.swing.JTextField();
        epoch2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        label_input_learning_rate = new javax.swing.JTextField();
        epoch3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        label_input_hidden_layer = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        do_pelatihan = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Optical Character Recognition");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel13.setText("Pengenalan Karakter Optik");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Backpropagation");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("- Profile Projection");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel_homeLayout = new javax.swing.GroupLayout(panel_home);
        panel_home.setLayout(panel_homeLayout);
        panel_homeLayout.setHorizontalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_homeLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_homeLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        panel_homeLayout.setVerticalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_homeLayout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel18))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Home", panel_home);

        panel_pelatihan.setLayout(new javax.swing.BoxLayout(panel_pelatihan, javax.swing.BoxLayout.LINE_AXIS));

        data_management.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        data_management.setPreferredSize(new java.awt.Dimension(200, 473));
        data_management.setLayout(new javax.swing.BoxLayout(data_management, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel11.setMaximumSize(new java.awt.Dimension(32767, 10));
        jPanel11.setMinimumSize(new java.awt.Dimension(0, 10));
        jPanel11.setPreferredSize(new java.awt.Dimension(356, 10));
        jPanel11.setLayout(new java.awt.BorderLayout());

        progres_muat_citra.setForeground(new java.awt.Color(0, 255, 51));
        progres_muat_citra.setToolTipText("");
        jPanel11.add(progres_muat_citra, java.awt.BorderLayout.CENTER);

        data_management.add(jPanel11);

        pilih_folder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pilih_folder.setPreferredSize(new java.awt.Dimension(301, 10));
        pilih_folder.setLayout(new java.awt.BorderLayout());

        folder_chooser.setText("Pilih Folder");
        folder_chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folder_chooserActionPerformed(evt);
            }
        });
        pilih_folder.add(folder_chooser, java.awt.BorderLayout.LINE_START);

        jPanel3.setMaximumSize(new java.awt.Dimension(156, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(156, 100));

        lokasi_direktori.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        lokasi_direktori.setText("direktori");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lokasi_direktori)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lokasi_direktori, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );

        pilih_folder.add(jPanel3, java.awt.BorderLayout.CENTER);

        data_management.add(pilih_folder);

        image_panel.setPreferredSize(new java.awt.Dimension(301, 400));
        image_panel.setLayout(new java.awt.BorderLayout());

        list_image.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_imageMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(list_image);

        image_panel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        data_management.add(image_panel);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        data_management.add(jPanel4);

        panel_pelatihan.add(data_management);

        train_management.setBackground(new java.awt.Color(51, 102, 0));
        train_management.setPreferredSize(new java.awt.Dimension(400, 570));
        train_management.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        profil_proy_panel.setPreferredSize(new java.awt.Dimension(453, 15));
        profil_proy_panel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1 ", "Item 2" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jList2.setSelectedIndex(1);
        jList2.setVisibleRowCount(1);
        jScrollPane1.setViewportView(jList2);

        profil_proy_panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(profil_proy_panel);

        training_result.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        training_result.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        training_result.setPreferredSize(new java.awt.Dimension(453, 100));
        training_result.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hasil Pelatihan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel2.setText("MSE");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel3.setText("Waktu");

        label_mse.setText("jLabel7");

        label_waktu.setText("not finished yet");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_mse, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(label_waktu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(label_mse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(label_waktu))
                .addContainerGap())
        );

        training_result.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.add(training_result);

        prog_bar.setMaximumSize(new java.awt.Dimension(32767, 20));
        prog_bar.setMinimumSize(new java.awt.Dimension(0, 20));
        prog_bar.setPreferredSize(new java.awt.Dimension(446, 20));
        prog_bar.setLayout(new java.awt.BorderLayout());

        progress_bar_pelatihan.setToolTipText("proses");
        progress_bar_pelatihan.setName("epoch"); // NOI18N
        progress_bar_pelatihan.setString("");
        progress_bar_pelatihan.setStringPainted(true);
        prog_bar.add(progress_bar_pelatihan, java.awt.BorderLayout.CENTER);

        jPanel1.add(prog_bar);

        train_konfig.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        train_konfig.setPreferredSize(new java.awt.Dimension(453, 50));
        train_konfig.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        title.setPreferredSize(new java.awt.Dimension(400, 50));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel12.setText("Konfigurasi Parameter Backpropagation");

        javax.swing.GroupLayout titleLayout = new javax.swing.GroupLayout(title);
        title.setLayout(titleLayout);
        titleLayout.setHorizontalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel12)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        titleLayout.setVerticalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.add(title);

        epoch1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        epoch1.setPreferredSize(new java.awt.Dimension(400, 50));
        epoch1.setLayout(new java.awt.BorderLayout());

        jPanel8.setMaximumSize(new java.awt.Dimension(101, 32767));
        jPanel8.setMinimumSize(new java.awt.Dimension(101, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(101, 46));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel14.setText("Epoch");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel14)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        epoch1.add(jPanel8, java.awt.BorderLayout.LINE_START);

        label_input_epcoh.setMaximumSize(new java.awt.Dimension(6, 2147483647));
        epoch1.add(label_input_epcoh, java.awt.BorderLayout.CENTER);

        jPanel5.add(epoch1);

        epoch2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        epoch2.setPreferredSize(new java.awt.Dimension(400, 50));
        epoch2.setLayout(new java.awt.BorderLayout());

        jPanel9.setMaximumSize(new java.awt.Dimension(101, 32767));
        jPanel9.setMinimumSize(new java.awt.Dimension(101, 0));
        jPanel9.setPreferredSize(new java.awt.Dimension(101, 46));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel16.setText("Learning Rate");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        epoch2.add(jPanel9, java.awt.BorderLayout.LINE_START);

        label_input_learning_rate.setMaximumSize(new java.awt.Dimension(6, 2147483647));
        epoch2.add(label_input_learning_rate, java.awt.BorderLayout.CENTER);

        jPanel5.add(epoch2);

        epoch3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        epoch3.setPreferredSize(new java.awt.Dimension(400, 50));
        epoch3.setLayout(new java.awt.BorderLayout());

        jPanel12.setMaximumSize(new java.awt.Dimension(101, 32767));
        jPanel12.setMinimumSize(new java.awt.Dimension(101, 0));
        jPanel12.setPreferredSize(new java.awt.Dimension(101, 46));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel17.setText("Hidden Layer");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        epoch3.add(jPanel12, java.awt.BorderLayout.LINE_START);

        label_input_hidden_layer.setMaximumSize(new java.awt.Dimension(6, 2147483647));
        epoch3.add(label_input_hidden_layer, java.awt.BorderLayout.CENTER);

        jPanel5.add(epoch3);

        jPanel10.setPreferredSize(new java.awt.Dimension(400, 28));
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jPanel7.setLayout(new java.awt.BorderLayout());

        do_pelatihan.setText("Mulai Pelatihan");
        do_pelatihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                do_pelatihanActionPerformed(evt);
            }
        });
        jPanel7.add(do_pelatihan, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel7);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jButton2.setText("Mulai Pengujian");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel6);

        jPanel5.add(jPanel10);

        train_konfig.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.add(train_konfig);

        train_management.add(jPanel1, java.awt.BorderLayout.CENTER);

        panel_pelatihan.add(train_management);

        jTabbedPane1.addTab("Dashboard Pelatihan", panel_pelatihan);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void folder_chooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folder_chooserActionPerformed
        try {
            this.main.muatCitra("Data Latih",this.panel_pelatihan,this.progres_muat_citra,this.lokasi_direktori,this.list_image);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_folder_chooserActionPerformed

    private void list_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_imageMouseClicked
       
    }//GEN-LAST:event_list_imageMouseClicked

    private void do_pelatihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_do_pelatihanActionPerformed
        HashMap<String, String> konfig = new HashMap<String, String>();
        konfig.put("Epoch", this.label_input_epcoh.getText());
        konfig.put("Learning_rate", this.label_input_learning_rate.getText());
        konfig.put("Hidden_layer", this.label_input_hidden_layer.getText());
        this.main.mulai_pelatihan(konfig,this.progress_bar_pelatihan, this.label_mse, this.label_waktu, this.do_pelatihan);
    }//GEN-LAST:event_do_pelatihanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel data_management;
    private javax.swing.JButton do_pelatihan;
    private javax.swing.JPanel epoch1;
    private javax.swing.JPanel epoch2;
    private javax.swing.JPanel epoch3;
    private javax.swing.JButton folder_chooser;
    private javax.swing.JPanel image_panel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList2;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField label_input_epcoh;
    private javax.swing.JTextField label_input_hidden_layer;
    private javax.swing.JTextField label_input_learning_rate;
    private javax.swing.JLabel label_mse;
    private javax.swing.JLabel label_waktu;
    private javax.swing.JList<String> list_image;
    private javax.swing.JLabel lokasi_direktori;
    private javax.swing.JPanel panel_home;
    private javax.swing.JPanel panel_pelatihan;
    private javax.swing.JPanel pilih_folder;
    private javax.swing.JPanel profil_proy_panel;
    private javax.swing.JPanel prog_bar;
    private javax.swing.JProgressBar progres_muat_citra;
    private javax.swing.JProgressBar progress_bar_pelatihan;
    private javax.swing.JPanel title;
    private javax.swing.JPanel train_konfig;
    private javax.swing.JPanel train_management;
    private javax.swing.JPanel training_result;
    // End of variables declaration//GEN-END:variables
}
