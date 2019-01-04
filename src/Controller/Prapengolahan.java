/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import Model.CitraWarna;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Prapengolahan {
    
   
    
    public Prapengolahan(){
   
    }
    
    public CitraKeabuan doGrayScale(CitraWarna gambar){
       int baris = gambar.getBaris();
       int kolom = gambar.getKolom();     
       int new_p[][] = new int[baris][kolom];
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
              new_p[i][j] = (gambar.getR()[i][j] + gambar.getG()[i][j] + gambar.getB()[i][j])/3;
           }
       }
       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
       return gambar_2;
    }
    
    public CitraKeabuan doBinerisasi(CitraKeabuan gambar){
       int baris = gambar.getBaris();
       int kolom = gambar.getKolom();
       int new_p[][] = new int[baris][kolom];
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
             new_p[i][j] = this.treshold(gambar.getP()[i][j]);
           }
       }
       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
       return gambar_2;
    }
    
    public CitraKeabuan doInvers(CitraKeabuan gambar){
       int baris = gambar.getBaris();
       int kolom = gambar.getKolom();
       int new_p[][] = new int[baris][kolom];
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
             new_p[i][j] = 255-gambar.getP()[i][j]; //or G or B
           }
       }
       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
       return gambar_2;
    }
    
    
    
    private int treshold(int x){
        int n= 0;
        if(x <= 128){
            n = 0;
        }else{
            n = 255;
        }
        return n;
    }
    
 
    public double[][] normalisasi_citra(int citra[][]){
        double norm_citra[][] = new double[citra.length][citra[0].length];
        double max  = 255;
        double min  = 0;
        
        for(int i=0;i<citra.length;i++){
            for(int j=0;j<citra[i].length;j++){
                norm_citra[i][j] = (citra[i][j] - min)/(max-min);
            }
        }
        
        return norm_citra;
    }
}
