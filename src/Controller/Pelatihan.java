/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.NeuralNetwork_Learning_Algorithm.Backpropagation;
import Model.CitraKeabuan;
import Model.CitraWarna;
import Model.Data;
import Model.NeuralNetwork.NeuralNetwork;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
    private JLabel mse,waktu;
    private JButton do_learn;
    private HashMap<String, String> konfigurasi_nn;
    boolean inTraining = false;
    private Prapengolahan pra_proses = new Prapengolahan();
    
    public NeuralNetwork getNn() {
        return nn;
    }
    
    public void setNeuralNetwork(HashMap<String, String> konf){
          this.konfigurasi_nn = konf;
    }
    
    public void setProgressBar(JProgressBar p){
        this.progress = p;
    }
    
    public void setDataLatih(ArrayList<Data> data){
        this.data_latih = data;
    }
    
    public void setLabel(JLabel mse,JLabel waktu,JButton do_learn){
        this.mse = mse;
        this.waktu = waktu;
        this.do_learn = do_learn;
    }
    
    private int[] getHiddenLayerNumber(String hidden_layer_label){
        String[] temp_hidden_layer = hidden_layer_label.split(",");
        int[] hidden_layer         = new int[temp_hidden_layer.length]; 
        for(int i=0;i<hidden_layer.length;i++){
            hidden_layer[i] = Integer.parseInt(temp_hidden_layer[i]);
        }
        return hidden_layer;
    }

    @Override
    protected Object doInBackground() throws Exception {
        long waktu_eksekusi   = System.currentTimeMillis();
        this.inTraining = true;
        this.nn               = new NeuralNetwork(this.getHiddenLayerNumber(this.konfigurasi_nn.get("Hidden_layer")),Integer.parseInt(this.konfigurasi_nn.get("Input_layer")),36);
        int epoch             = Integer.parseInt(this.konfigurasi_nn.get("Epoch"));
        double learning_rate  = Double.parseDouble(this.konfigurasi_nn.get("Learning_rate"));
        this.progress.setMaximum(epoch-1);
        this.do_learn.setText("Proses Sedang Berjalan");
        this.waktu.setText(" meng-eksekusi...");
        this.do_learn.setEnabled(false);
        this.progress.setValue(0);
        this.mse.setText("0");

        Backpropagation bp    = new Backpropagation(this.nn,learning_rate); 
       
        int iter = 1;
        double jumlah_segmen = 0;
        double error;
        int index_file;
        double mse = 0;
        while(iter <= epoch){
            this.progress.setString(iter+" epoch");
            error = 0;
            jumlah_segmen = 0;
            for(Data data : this.data_latih){
                this.segmentasiTidakDitemukan(data.getPlat_nomor());
                index_file = 0;
                File direktori = new File(new File("").getAbsolutePath()+"\\segments\\"+data.getPlat_nomor()+".JPG\\");
                int banyaknya_segmentasi = direktori.listFiles().length;
                this.jumlahSegmentasiTerlaluBesar(banyaknya_segmentasi, data.getPlat_nomor());
                for(File f : direktori.listFiles()){
                    try {
                        CitraKeabuan citra = pra_proses.doBinerisasi(pra_proses.doInvers(pra_proses
                        .doGrayScale(new CitraWarna(ImageIO
                                .read(f.getAbsoluteFile())))), f.getName());
                          double[][] input = this.normalisasiCitra(this.resizeImage(citra).getPixelDecimal());
                          double input_vektor[] = this.getImageVektor(input);
                          bp.learn(input_vektor, this.getTargetValue(data, index_file));                     
                          error = error + bp.getNn().getError();                        
                    } catch (IOException ex) {
                        Logger.getLogger(Pelatihan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jumlah_segmen++;
                    index_file++;
                }
            }
            mse = error/(this.data_latih.size()*jumlah_segmen);
//            System.out.println("MSE : "+mse);
            this.mse.setText(mse+"");
            
//            mse = (double)iter/(double)epoch;
            this.progress.setValue(iter);
            iter++;
        }
        Main.write_bobot(this.nn, mse+"");
        this.waktu.setText(((double)(System.currentTimeMillis()-waktu_eksekusi)/1000)+" detik");
        return null;
    }
    
    
    private void jumlahSegmentasiTerlaluBesar(int panjang_segmen, String karakter){    
        int panjang_plat_aktual = karakter.toCharArray().length;
        if(panjang_segmen > panjang_plat_aktual){
            System.out.println("ERROR--->Segmentasi karakter "+karakter+" terlalu banyak");
        }
    }
    
    private void segmentasiTidakDitemukan(String karakter){
        boolean  isThere = false;
        File[] directories = new File(new File("").getAbsolutePath()+"\\segments\\").listFiles(File::isDirectory);
        for(File f : directories){
            if(f.getName().split("\\.")[0].equalsIgnoreCase(karakter)){
                isThere = true;
                break;
            }
        } 
        if(isThere == false){
            System.out.println("ERROR-->Plat Nomor "+karakter+" GAGAL DI SEGMENTASI");
        }
    }
    
    private CitraKeabuan resizeImage(CitraKeabuan citra){
       CitraKeabuan citra_baru;
       BufferedImage img = citra.getImg();
       Image tmp = img.getScaledInstance(5,5, Image.SCALE_SMOOTH);
       BufferedImage dimg = new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g2d = dimg.createGraphics();
       g2d.drawImage(tmp, 0, 0, null);
       g2d.dispose();
       citra_baru = new CitraKeabuan(dimg);
       return citra_baru;
    }
    
    private void oprekNN(){
        double target[] = {1,0,0,0};
        double input[][] = {{1,1},{1,0},{0,1},{0,0}};
         Backpropagation bp    = new Backpropagation(this.nn,0.2); 
        int epoch = 1000;
        int iter = 1;
        int index;
        double error;
        while(iter <= 1000){
            index = 0;
            error = 0;
            for(double in[] : input){
                bp.learn(in, new double[]{target[index]});
                error = error + bp.getNn().getError();
                index++;
            }
            System.out.println("MSE "+(error/4));
            iter++;
        }
        
        for(double in[] : input){
            double res[] = bp.propagasiMaju(in, null);
            System.out.println(in[0]+" "+in[1]+" => "+Math.round(res[0]));
        }
        
        System.out.println("test manual 0 0 => "+Math.round(bp.propagasiMaju(new double[]{1,1}, null)[0]));
    }
    
    private double[] getImageVektor(double[][] V){
        double vektor[] = new double[(V.length*V[0].length)+1];
        
        int index_vektor = 1;
        vektor[0] = 1;
        for(int i=0;i<V.length;i++){
            for(int j=0;j<V[i].length;j++){
                vektor[index_vektor] = V[i][j];
                index_vektor++;
            }
        }
//        System.out.println("Vektor");
//        for(int i=0;i<vektor.length;i++){
//            System.out.print(vektor[i]+",");
//        }

        
        return vektor;
    }
    
   private double[][] normalisasiCitra(double input[][]){
        double new_input[][] = new double[input.length][input[0].length];
        double max = 255,min = 0;
        
        for(int i=0;i<input.length;i++){
           for(int j=0;j<input[i].length;j++){
               new_input[i][j] = (input[i][j]-min)/(max-min);
           }
        }
        return new_input;
    }
    
    private double[] normalisasi(double input[]){
        double new_input[] = new double[input.length];
        double max = input[0];
        double min = input[0];
        
        for(double value : input){
            if(max < value){
                max = value;
            }
            
            if(min > value){
                min = value;
            }
        }
        
        for(int i=0;i<input.length;i++){
            new_input[i] = (input[i]-min)/(max-min);
        }
        
        return new_input;
    }
    
    private void oprekPCA(){
//        EIGEN VEKTOR BERBEDA-BEDA,LIBRARY( JAMA, OCTAVE, DLL) MENGHASILKAN EIGEN VEKTOR YANG BERBEDA-BEDA
        PCA pca = new PCA();
        double x1[][] = {{2,3,4},{1,3,7},{6,4,9}};
        double x2[][] = {{5,3,7},{1,1,7},{0,4,2}};
        double x3[][] = {{2,3,4},{1,4,5},{4,4,7}};
        pca.tambah_matriks(x1);
        pca.tambah_matriks(x2);
        pca.tambah_matriks(x3);
        pca.cetakMatriks(pca.do_pca());
    }
    
    private double[] getTargetValue(Data data, int index){
      double value[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
      char[] plat_nomor = data.getPlat_nomor().toCharArray();
//    System.out.println("karakter : "+plat_nomor[index]+" ");
      if(data.getPlat_nomor().toCharArray().length > index){
//          System.out.println("karakter : "+plat_nomor[index]);
          switch(plat_nomor[index]){
          
          case '0' :  value[0]=1; break;
          case '1' :  value[1]=1; break;
          case '2' :  value[2]=1; break;
          case '3' :  value[3]=1; break; 
          case '4' :  value[4]=1; break;
          case '5' :  value[5]=1; break;
          case '6' :  value[6]=1; break;
          case '7' :  value[7]=1; break;
          case '8' :  value[8]=1; break;
          case '9' :  value[9]=1; break;
          case 'A' :  value[10]=1; break;
          case 'B' :  value[11]=1; break;
          case 'C' :  value[12]=1; break;
          case 'D' :  value[13]=1; break;
          case 'E' :  value[14]=1; break;
          case 'F' :  value[15]=1; break;
          case 'G' :  value[16]=1; break;
          case 'H' :  value[17]=1; break;
          case 'I' :  value[18]=1; break;
          case 'J' :  value[19]=1; break;
          case 'K' :  value[20]=1; break;
          case 'L' :  value[21]=1; break;
          case 'M' :  value[22]=1; break;
          case 'N' :  value[23]=1; break;
          case 'O' :  value[24]=1; break;
          case 'P' :  value[25]=1; break;
          case 'Q' :  value[26]=1; break;
          case 'R' :  value[27]=1; break;
          case 'S' :  value[28]=1; break;
          case 'T' :  value[29]=1; break;
          case 'U' :  value[30]=1; break;
          case 'V' :  value[31]=1; break;
          case 'W' :  value[32]=1; break;
          case 'X' :  value[33]=1; break;
          case 'Y' :  value[34]=1; break;
          case 'Z' :  value[35]=1; break;
        }   
      }
//      else{
//          System.out.println("Error!, Jumlah segmentasi lebih banyak dibandingkan jumlah karakter sebenarnya");
//      }
//      for(int i=0;i<value.length;i++){
//          System.out.print(value[i]+" ");
//      }
//        System.out.println("");
      return value;
      
    }
    
    
    @Override
    protected void done(){
        this.inTraining = false;
        this.do_learn.setEnabled(true);
        this.do_learn.setText("Mulai Pelatihan");
        System.out.println("selesai");
    }
}
