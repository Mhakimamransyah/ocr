/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import Model.CitraWarna;
import Model.Data;
import Model.NeuralNetwork.NeuralNetwork;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Pelatihan extends SwingWorker{
    
    private NeuralNetwork nn;
    private PCA pca;
    private ArrayList<Data> data_latih;
    private JProgressBar progress;
    private JLabel mse;
    private JLabel waktu;
    private JButton do_learn;
    private HashMap<String, String> konfigurasi_nn;
    
    
    public void setNeuralNetwork(HashMap<String, String> konf){
          this.konfigurasi_nn = konf;
    }
    
    public void setProgressBar(JProgressBar p){
        this.progress = p;
    }
    
    public void setDataLatih(ArrayList<Data> data){
        this.data_latih = data;
    }
    
    public void setLabel(JLabel mse, JLabel waktu,JButton do_learn){
        this.mse = mse;
        this.waktu = waktu;
        this.do_learn = do_learn;
    }

    @Override
    protected Object doInBackground() throws Exception {
        
        int epoch             = Integer.parseInt(this.konfigurasi_nn.get("Epoch"));
        double learning_rate  = Double.parseDouble(this.konfigurasi_nn.get("Learning_rate"));
        this.progress.setMaximum(epoch-1);
        this.do_learn.setText("Proses Sedang Berjalan");
        this.do_learn.setEnabled(false);
        this.progress.setValue(0);
        this.mse.setText("0");
       
     
        int iter = 0;
        double mse = 0;
        while(iter <= epoch){
            Thread.sleep(50);
            this.progress.setString(iter+" epoch");
            PCA pca;
            for(Data data : this.data_latih){
                pca = new PCA();
                File direktori = new File(new File("").getAbsolutePath()+"\\segments\\"+data.getPlat_nomor()+".JPG\\");
                for(File f : direktori.listFiles()){
                    try {
                        CitraKeabuan citra = new Prapengolahan().doGrayScale(new CitraWarna(ImageIO.read(f.getAbsoluteFile())));
//                        System.out.println("panjang : "+citra.getPixelDecimal().length);
//                        System.out.println("lebar : "+citra.getPixelDecimal()[0].length);
                        pca.tambah_matriks(citra.getPixelDecimal());
                    } catch (IOException ex) {
                        Logger.getLogger(Pelatihan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("");
                }
                double hasil_pca[][] = pca.do_pca();
            }
            this.mse.setText(mse+"");
            mse = (double)iter/(double)epoch;
            this.progress.setValue(iter);
            iter++;
        }
        
        return null;
    }
    
    private void setPCAmatriks(){
       PCA pca;
       for(Data data : this.data_latih){
           pca = new PCA();
           File direktori = new File(new File("").getAbsolutePath()+"\\segments\\"+data.getPlat_nomor()+".JPG\\");
           for(File f : direktori.listFiles()){
               try {
                   CitraKeabuan citra = new Prapengolahan().doGrayScale(new CitraWarna(ImageIO.read(f.getAbsoluteFile())));
                   System.out.println("panjang : "+citra.getPixelDecimal().length);
                   System.out.println("lebar : "+citra.getPixelDecimal()[0].length);
                   pca.tambah_matriks(citra.getPixelDecimal());
               } catch (IOException ex) {
                   Logger.getLogger(Pelatihan.class.getName()).log(Level.SEVERE, null, ex);
               }
               System.out.println("");
           }
           double hasil_pca[][] = pca.do_pca();
           pca.cetakMatriks(hasil_pca);
       }
    }
    
    
    @Override
    protected void done(){
        this.do_learn.setEnabled(true);
        this.do_learn.setText("Mulai Pelatihan");
        System.out.println("selesai");
    }
}
