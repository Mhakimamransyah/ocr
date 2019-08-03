/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Main;
import Model.NeuralNetwork.NeuralNetwork;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Panel_pengujian extends javax.swing.JFrame {

    private Main main;
    private NeuralNetwork nn;
    
    public Panel_pengujian(Main main) {
        this.main = main;
        initComponents();
    }

    private Panel_pengujian() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jLabel2 = new javax.swing.JLabel();
        label_jumlah_data = new javax.swing.JLabel();
        testing_management = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        hasil_pengujian = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_plat_nomor = new javax.swing.JTable();
        akurasi = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        rerata_akurasi = new javax.swing.JLabel();
        btn_mulai_pengujian = new javax.swing.JPanel();
        do_pengujian = new javax.swing.JButton();
        load_bobot = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel Pengujian");
        setPreferredSize(new java.awt.Dimension(700, 609));

        jPanel1.setLayout(new java.awt.BorderLayout());

        panel_pelatihan.setLayout(new javax.swing.BoxLayout(panel_pelatihan, javax.swing.BoxLayout.LINE_AXIS));

        data_management.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        data_management.setPreferredSize(new java.awt.Dimension(150, 473));
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

        folder_chooser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/browse.png"))); // NOI18N
        folder_chooser.setText(" Data");
        folder_chooser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder_chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folder_chooserActionPerformed(evt);
            }
        });
        pilih_folder.add(folder_chooser, java.awt.BorderLayout.LINE_START);

        jPanel3.setMaximumSize(new java.awt.Dimension(156, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(156, 100));

        lokasi_direktori.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        lokasi_direktori.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lokasi_direktori)
                .addContainerGap(319, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/data_icon.png"))); // NOI18N

        label_jumlah_data.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        label_jumlah_data.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_jumlah_data, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(264, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(label_jumlah_data))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        data_management.add(jPanel4);

        panel_pelatihan.add(data_management);

        testing_management.setBackground(new java.awt.Color(51, 102, 0));
        testing_management.setPreferredSize(new java.awt.Dimension(200, 570));
        testing_management.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        hasil_pengujian.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Identifikasi Plat Nomor Kendaraan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        hasil_pengujian.setPreferredSize(new java.awt.Dimension(453, 420));
        hasil_pengujian.setLayout(new java.awt.BorderLayout());

        tabel_plat_nomor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_plat_nomor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plat Nomor", "Identifikasi Neural Network", "Akurasi"
            }
        ));
        tabel_plat_nomor.setRowHeight(20);
        jScrollPane1.setViewportView(tabel_plat_nomor);

        hasil_pengujian.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(hasil_pengujian);

        akurasi.setPreferredSize(new java.awt.Dimension(460, 30));
        akurasi.setLayout(new javax.swing.BoxLayout(akurasi, javax.swing.BoxLayout.LINE_AXIS));

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Rata-rata akurasi");
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 14));
        jPanel6.add(jLabel1, java.awt.BorderLayout.CENTER);

        akurasi.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setLayout(new java.awt.BorderLayout());

        rerata_akurasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rerata_akurasi.setText("0%");
        rerata_akurasi.setPreferredSize(new java.awt.Dimension(50, 14));
        jPanel5.add(rerata_akurasi, java.awt.BorderLayout.CENTER);

        akurasi.add(jPanel5);

        jPanel2.add(akurasi);

        btn_mulai_pengujian.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_mulai_pengujian.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        btn_mulai_pengujian.setPreferredSize(new java.awt.Dimension(453, 50));
        btn_mulai_pengujian.setLayout(new java.awt.BorderLayout());

        do_pengujian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/start.png"))); // NOI18N
        do_pengujian.setText("Uji Neural Network");
        do_pengujian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        do_pengujian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                do_pengujianActionPerformed(evt);
            }
        });
        btn_mulai_pengujian.add(do_pengujian, java.awt.BorderLayout.CENTER);

        load_bobot.setText("Load Bobot");
        load_bobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                load_bobotActionPerformed(evt);
            }
        });
        btn_mulai_pengujian.add(load_bobot, java.awt.BorderLayout.LINE_START);

        jPanel2.add(btn_mulai_pengujian);

        testing_management.add(jPanel2, java.awt.BorderLayout.CENTER);

        panel_pelatihan.add(testing_management);

        jPanel1.add(panel_pelatihan, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void folder_chooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folder_chooserActionPerformed
        try {
            this.main.muatCitra("Data Uji",this.panel_pelatihan,this.progres_muat_citra,this.lokasi_direktori,this.list_image, this.label_jumlah_data);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_folder_chooserActionPerformed

    private void list_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_imageMouseClicked

    }//GEN-LAST:event_list_imageMouseClicked

    private void do_pengujianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_do_pengujianActionPerformed
       if(this.nn != null){
          this.main.mulai_pengujian(this.tabel_plat_nomor,this.rerata_akurasi,this.nn);   
       }else{
           JOptionPane.showMessageDialog(null,"Tidak ada bobot ditemukan"," Ooops!!",JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_do_pengujianActionPerformed

    private void load_bobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_load_bobotActionPerformed
        // TODO add your handling code here:
        this.nn = this.main.load_bobot();
    }//GEN-LAST:event_load_bobotActionPerformed

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
            java.util.logging.Logger.getLogger(Panel_pengujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel_pengujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel_pengujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel_pengujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel_pengujian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel akurasi;
    private javax.swing.JPanel btn_mulai_pengujian;
    private javax.swing.JPanel data_management;
    private javax.swing.JButton do_pengujian;
    private javax.swing.JButton folder_chooser;
    private javax.swing.JPanel hasil_pengujian;
    private javax.swing.JPanel image_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_jumlah_data;
    private javax.swing.JList<String> list_image;
    private javax.swing.JButton load_bobot;
    private javax.swing.JLabel lokasi_direktori;
    private javax.swing.JPanel panel_pelatihan;
    private javax.swing.JPanel pilih_folder;
    private javax.swing.JProgressBar progres_muat_citra;
    private javax.swing.JLabel rerata_akurasi;
    private javax.swing.JTable tabel_plat_nomor;
    private javax.swing.JPanel testing_management;
    // End of variables declaration//GEN-END:variables
}
