/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import Model.CitraWarna;
import Model.Data;
import View.Home;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Main {

    private ArrayList<Data> data_latih;
    private ArrayList<Data> data_uji;
    Pelatihan learner;
    
    public Main(){
      this.data_latih = new ArrayList<Data>();
      this.data_uji = new ArrayList<Data>();
    }
    
    
    public static void main(String[] args) {
      Main main = new Main();
      main.tampilkanFrameUtama();
    }
    
    public void tampilkanFrameUtama(){
       Home h = new Home(this);
       h.setVisible(true);  
    }
    
    public void mulai_pelatihan(HashMap<String, String> konf, JProgressBar p, JLabel label_mse, JLabel label_waktu,JButton do_learn){
        if(this.data_latih.size() > 0){
           if(this.validasi_input(konf)){
              this.learner = new Pelatihan();
              this.learner.setDataLatih(this.data_latih);
              this.learner.setNeuralNetwork(konf);
              this.learner.setLabel(label_mse, label_waktu, do_learn);
              this.learner.setProgressBar(p);
              this.learner.execute();
              
           }else{
               JOptionPane.showMessageDialog(null,"Parameter input tidak valid"," Ooops",JOptionPane.ERROR_MESSAGE);
           }
        }else{
            JOptionPane.showMessageDialog(null,"Data latih tidak ada"," Ooops",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validasi_input(HashMap<String, String> konf){
        boolean valid = false;
        try{
            int epoch            = Integer.parseInt(konf.get("Epoch"));
            double learning_rate = Double.parseDouble(konf.get("Learning_rate"));
            String hidden[]      = konf.get("Hidden_layer").split(",");
            if(!(hidden.length == 1 && hidden[0].equalsIgnoreCase(""))){
               valid = true;   
            }
        }
        catch(Exception ex){
            valid = false;
        }
        return valid;
    }
   
    public void muatCitra(String tipe,javax.swing.JPanel parent_panel, JProgressBar progress, JLabel label_dir,JList list_image) throws IOException{ 
        String dir = this.pilihDirektori(parent_panel);
        label_dir.setText(dir);
        WorkerMuatCitra memuat_citra = new WorkerMuatCitra(new File(dir),progress);
        memuat_citra.setPanel_image(list_image);
        if(tipe.equalsIgnoreCase("Data Latih")){
            this.data_latih.clear();
            memuat_citra.setData(this.data_latih);
        }else{
            this.data_uji.clear();
            memuat_citra.setData(this.data_uji);
        }
        list_image.removeAll();
        memuat_citra.execute();     
    }
    
    class WorkerMuatCitra extends SwingWorker{
        private File file;
        private JProgressBar progress;
        private ArrayList<Data> kumpulan_data;
        private JList list_image;
        private Prapengolahan pra_proses = new Prapengolahan();
        
        public WorkerMuatCitra(File f, JProgressBar p){
            this.file = f;
            this.progress = p;
        }
        
        public void setData(ArrayList<Data> data){
            this.kumpulan_data = data;
        }
        
        public void setPanel_image(JList list_image){
            this.list_image = list_image;
        }
        
        @Override
        protected Object doInBackground() throws Exception {
            Data data;
            CitraKeabuan citra;
            int index = 0;
            this.progress.setVisible(true);
            this.progress.setMaximum(this.file.listFiles().length-1);
            System.out.println("JUMLAH FILE: " + file.listFiles().length);
            for(File f : file.listFiles()){
                System.out.println(f.getName());
                this.progress.setValue(index);
                data = new Data();
                data.setPlat_nomor(f.getName());
                citra = pra_proses.doBinerisasi(pra_proses.doInvers(pra_proses
                        .doGrayScale(new CitraWarna(ImageIO
                                .read(f.getAbsoluteFile())))));
                ProfileProjection projector = new ProfileProjection(citra);
                citra = projector.getProjectedImage();
                this.progress.setString((index/100*this.file.listFiles().length)+"%");
                data.setCitra(citra);
                kumpulan_data.add(data);
                index++;
            }     
            return null;
        }
        
        @Override
        protected void done(){
          JOptionPane.showMessageDialog(null,"Prapengolahan citra selesai"," Selesai!!",JOptionPane.INFORMATION_MESSAGE);
          DefaultListModel list_model = new DefaultListModel();
          int no = 0;
          ImageIcon icon,new_icon;
          Image img;
          for(Data data : this.kumpulan_data){
               icon = new ImageIcon(data.getCitra().getImg());
               img = icon.getImage().getScaledInstance(340, 200,java.awt.Image.SCALE_SMOOTH);
               new_icon = new ImageIcon(img);
               list_model.add(no, new_icon);
               no++;
          }
          this.list_image.setModel(list_model);
          this.list_image.setVisibleRowCount(0);
        }
       
    }
    
    private String pilihDirektori(javax.swing.JPanel parent_panel){
        String direktori = "null";   
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setCurrentDirectory(new File(Paths.get(".").toAbsolutePath().normalize().toString()));
        file_chooser.setToolTipText("Pilih folder citra latih");
        file_chooser.setDialogTitle("Pilih direktori citra latih");
        file_chooser.setFileFilter(new FileNameExtensionFilter("Pilih Folder Citra Data Latih", "jpg","png","bmp"));
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(file_chooser.showOpenDialog(parent_panel) == JFileChooser.APPROVE_OPTION){
           direktori = file_chooser.getSelectedFile().toString();   
        }
        return direktori;
    }
    
}
