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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Pengujian {
    
    private ArrayList<Data> data_uji;
    private NeuralNetwork nn;
    private double rerata_akurasi = 0;
    private Prapengolahan pra_proses = new Prapengolahan();
    private JTable tabel_identifikasi;
    private JLabel label_rerata_akurasi;
    
    public Pengujian(ArrayList<Data> data, NeuralNetwork nn, JTable tabel,JLabel label){
        this.data_uji = data;
        this.label_rerata_akurasi = label;
        this.nn = nn;
        this.tabel_identifikasi = tabel;
        this.prepareTabel();
    }
    
    private void prepareTabel(){
        int jumlah_data = this.data_uji.size();
        DefaultTableModel tabel = (DefaultTableModel) this.tabel_identifikasi.getModel();
        tabel.setRowCount(jumlah_data);
    }
    
    public void doPengujian(){
        PCA pca;
        Backpropagation bp    = new Backpropagation(this.nn,0.0); 
        int index_data_ke=0;
        for(Data data : this.data_uji){
//            pca = new PCA();
            File direktori = new File(new File("").getAbsolutePath()+"\\segments\\"+data.getPlat_nomor()+".JPG\\");
            String plat = "";
            for(File f : direktori.listFiles()){
               try {
                   CitraKeabuan citra = pra_proses.doBinerisasi(pra_proses.doInvers(pra_proses
                        .doGrayScale(new CitraWarna(ImageIO
                                .read(f.getAbsoluteFile())))), f.getName());
//                   pca.tambah_matriks(this.normalisasiCitra(citra.getPixelDecimal()));
                   double[][] input = this.normalisasiCitra(this.resizeImage(citra).getPixelDecimal());
                    double input_vektor[] = this.getImageVektor(input);
                    plat = plat + this.getCharacter(bp.propagasiMaju(input_vektor, null));
                } catch (IOException ex) {
                    Logger.getLogger(Pelatihan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.setTabelIdentifikasi(data.getPlat_nomor(), plat, index_data_ke);
            index_data_ke++;
        }
        this.label_rerata_akurasi.setText(new DecimalFormat("#.##").format((this.rerata_akurasi/this.data_uji.size()))+" %");
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
    
     private double[][] normalisasiCitra(double input[][]){
        double new_input[][] = new double[input.length][input[0].length];
        double max = 255,min = 0;
        
        for(int i=0;i<input.length;i++){
           for(int j=0;j<input[i].length;j++){
               new_input[i][j] = (input[i][j]-min)/(max-min);
//               System.out.print(new_input[i][j]+" ");
           }
//            System.out.println("");
        }
//         System.out.println("");
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
     
      private double[] getImageVektor(double [][] V){
        double vektor[] = new double[(V.length*V[0].length)+1];
        vektor[0] = 1;
        int index_vektor = 1;
        for(int i=0;i<V.length;i++){
            for(int j=0;j<V[i].length;j++){
                vektor[index_vektor] = V[i][j];
                index_vektor++;
            }
        }
        
//        for(int i=0;i<vektor.length;i++)
//        {
//            System.out.print(vektor[i]+",");    
//        }
//          System.out.println("");
        return vektor;
    }
    
    private void setTabelIdentifikasi(String aktual, String output,int index){
         double akurasi = this.calculateAccuracy(aktual, output);
         this.rerata_akurasi = this.rerata_akurasi + akurasi;
         DefaultTableModel tabel = (DefaultTableModel) this.tabel_identifikasi.getModel();
         tabel.setValueAt(aktual, index, 0);
         tabel.setValueAt(output, index, 1);
         tabel.setValueAt(akurasi+" %", index, 2);
    }
    
    
    private double calculateAccuracy(String aktual,String output){
        double accuracy = 0;
        char plat_nomor_aktual[] = aktual.toCharArray();
        char plat_nomor_output[] = output.toCharArray();
        double jumlah_data = plat_nomor_aktual.length;
        
        double true_count = 0;
        for(int i=0;i<plat_nomor_output.length;i++){
            if(i < plat_nomor_output.length && i < plat_nomor_aktual.length){
               if(plat_nomor_output[i] == plat_nomor_aktual[i]){
                 true_count++; 
               }    
            }else{
                System.out.println("Error Pengujian, Jumlah Karakter Segmentasi Lebih Banyak daripada Karakter Aslinya");
            }
        }
        
        DecimalFormat df = new DecimalFormat("#.##");
        accuracy = true_count/jumlah_data;
        accuracy = Double.parseDouble(df.format(accuracy).replaceAll(",","."));
        
        return accuracy*100;       
    }
    
    private String getCharacter(double output[]){
       String character = "";
//        System.out.println("Output");
//        for(int i=0;i<output.length;i++){
//            System.out.print(output[i]+" ");
//        }
//        System.out.println("");
       double max = output[0];
       int index=0;
       for(int i=0;i<output.length;i++){
           if(output[i] > max){
               max = output[i];
               index = i;
           }
       }
//       System.out.println("Index terbesar : "+index);
       for(int i=0;i<output.length;i++){
           if(i == index){
               output[i] = 1;
           }else{
               output[i] = 0;
           }
       }
       switch(index){
           case 0: character = "0"; break; 
           case 1: character = "1"; break; 
           case 2: character = "2"; break; 
           case 3: character = "3"; break; 
           case 4: character = "4"; break; 
           case 5: character = "5"; break; 
           case 6: character = "6"; break; 
           case 7: character = "7"; break; 
           case 8: character = "8"; break; 
           case 9: character = "9"; break; 
           case 10: character = "A"; break; 
           case 11: character = "B"; break; 
           case 12: character = "C"; break; 
           case 13: character = "D"; break; 
           case 14: character = "E"; break; 
           case 15: character = "F"; break; 
           case 16: character = "G"; break; 
           case 17: character = "H"; break; 
           case 18: character = "I"; break; 
           case 19: character = "J"; break; 
           case 20: character = "K"; break; 
           case 21: character = "L"; break; 
           case 22: character = "M"; break; 
           case 23: character = "N"; break; 
           case 24: character = "O"; break; 
           case 25: character = "P"; break; 
           case 26: character = "Q"; break; 
           case 27: character = "R"; break; 
           case 28: character = "S"; break; 
           case 29: character = "T"; break; 
           case 30: character = "U"; break; 
           case 31: character = "V"; break; 
           case 32: character = "W"; break; 
           case 33: character = "X"; break; 
           case 34: character = "Y"; break; 
           case 35: character = "Z"; break; 
       }
//        System.out.println("Karakter : "+character);
       return character;
    }
    
}
