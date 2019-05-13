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
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    public void setLabel(JLabel mse,JButton do_learn){
        this.mse = mse;
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
        
        this.inTraining = true;
        this.nn               = new NeuralNetwork(this.getHiddenLayerNumber(this.konfigurasi_nn.get("Hidden_layer")),Integer.parseInt(this.konfigurasi_nn.get("Input_layer")),36);
        int epoch             = Integer.parseInt(this.konfigurasi_nn.get("Epoch"));
        double learning_rate  = Double.parseDouble(this.konfigurasi_nn.get("Learning_rate"));
        this.progress.setMaximum(epoch-1);
        this.do_learn.setText("Proses Sedang Berjalan");
        this.do_learn.setEnabled(false);
        this.progress.setValue(0);
        this.mse.setText("0");

        Backpropagation bp    = new Backpropagation(this.nn,learning_rate); 
       
        int iter = 1;
        double jumlah_segmen = 0;
        double error;
        double mse = 0;
        while(iter <= epoch){
            this.progress.setString(iter+" epoch");
            PCA pca;
            error = 0;
            jumlah_segmen = 0;
            for(Data data : this.data_latih){
                pca = new PCA();
                File direktori = new File(new File("").getAbsolutePath()+"\\segments\\"+data.getPlat_nomor()+".JPG\\");
                for(File f : direktori.listFiles()){
                    try {
                        CitraKeabuan citra = pra_proses.doBinerisasi(pra_proses.doInvers(pra_proses
                        .doGrayScale(new CitraWarna(ImageIO
                                .read(f.getAbsoluteFile())))), f.getName());
//                        citra = pra_proses.doInvers(citra);
                        pca.tambah_matriks(this.normalisasiCitra(citra.getPixelDecimal()));
                       
                    } catch (IOException ex) {
                        Logger.getLogger(Pelatihan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jumlah_segmen++;
                }
                double hasil_pca[][] = pca.do_pca();
               
                
                double temp_input[];
                for(int i=0;i<hasil_pca[0].length;i++){
                    temp_input = new double[hasil_pca.length];
                    for(int j=0;j<hasil_pca.length;j++){
                        temp_input[j] = hasil_pca[j][i];
                        
                    }
                  
                    bp.learn(temp_input, this.getTargetValue(data, i));
                    error = error + bp.getNn().getError();
                }
            }
            mse = error/(this.data_latih.size()*jumlah_segmen);
//            System.out.println("MSE : "+mse);
            this.mse.setText(mse+"");
            mse = (double)iter/(double)epoch;
            this.progress.setValue(iter);
            iter++;
        }
        
        return null;
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
    
    private void getImageVektor(int[][] V){
        double vektor[] = new double[V.length];
        
        int index_vektor = 0;
        for(int i=0;i<V.length;i++){
            for(int j=0;j<V[i].length;j++){
                vektor[index_vektor] = V[i][j];
                System.out.print(vektor[index_vektor]+" ");
                index_vektor++;
            }
        }
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
      }else{
          System.out.println("Error!, Jumlah segmentasi lebih banyak dibandingkan jumlah karakter sebenarnya");
      }
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
