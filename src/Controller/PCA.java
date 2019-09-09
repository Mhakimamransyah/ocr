/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jama.Matrix;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class PCA {
    
    private ArrayList<double [][]> matriks;
    
    public PCA(){
        this.matriks = new ArrayList<double [][]>();
    }
    
    public double[][] do_pca(){
       double[][] vektor_kolom  = this.bentuk_vektor_kolom();
       double[][] zero_mean     = this.zero_mean(vektor_kolom);
       double[][] matriks_pca   = this.matriks_pca(zero_mean);
       double[][] matriks_bobot = this.transformasi(matriks_pca, zero_mean);
       return matriks_bobot;
    }
    
    private double[][] transformasi(double[][] pca,double[][] zero_mean){
        Matrix PCA =new Matrix(pca);
        Matrix ZM = new Matrix(zero_mean);
        Matrix bobot = PCA.transpose().times(ZM);
        return bobot.getArray();
    }
    
    private double[][] matriks_pca(double z[][]){
        Matrix zero_mean = new Matrix(z);
        Matrix kovarian =zero_mean.transpose().times(zero_mean);
        double eigen[][] = kovarian.eig().getV().getArray();
        Matrix PCA = zero_mean.times(new Matrix(eigen));
        return PCA.getArray();
    }
    
    private double[][] zero_mean(double m[][]){
        double vektor_rerata[][] = new double[m.length][m[0].length];
        double zero_mean[][] = new double[m.length][m[0].length];
        int jumlah_kolom = m[0].length;
        
        double rerata;
        double sum;
        for(int i=0;i<m.length;i++){
            sum = 0;
            for(int j=0;j<m[i].length;j++){
                sum = sum + m[i][j];
            }
            rerata = sum/jumlah_kolom;
            for(int j=0;j<jumlah_kolom;j++){
                vektor_rerata[i][j] = rerata;
            }
        }

        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[i].length;j++){
                zero_mean[i][j] = m[i][j] - vektor_rerata[i][j];
            }
        }
        
        return zero_mean;
    }
    
    public void cetakMatriks(double[][] M){
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[i].length;j++){
                System.out.print(M[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    private double[][] bentuk_vektor_kolom(){
        int baris = this.matriks.get(0).length * this.matriks.get(0)[0].length;
        int kolom = this.matriks.size();
        double[][] vektor_kolom = new double[baris][kolom];
        int index_baris = 0;
        int index_kolom = 0;
        int index_matriks = 0;
        for(int i=0;i<vektor_kolom.length;i++){
            index_matriks = 0;
            for(int j=0;j<vektor_kolom[i].length;j++){
                vektor_kolom[i][j] = this.matriks.get(index_matriks)[index_baris][index_kolom];
                index_matriks++;
            }
//            this.cetakMatriks(vektor_kolom);
//            System.out.println("");
            index_kolom++;
            if(index_kolom > this.matriks.get(0)[0].length-1){
                index_kolom = 0;
                index_baris++;
            }
        }
        return vektor_kolom;
     }
    
    
    public void tambah_matriks(double[][] A){
        this.matriks.add(A);
    }
    
    public ArrayList<double[][]> getMatriks() {
        return matriks;
    }

    public void setMatriks(ArrayList<double[][]> matriks) {
        this.matriks = matriks;
    }
    
    public void cetakMatriks(){
        int index = 1;
        for(double[][] m : this.matriks){
            System.out.println("Matriks ke "+index);
            for(int i=0;i<m.length;i++){
                for(int j=0;j<m[i].length;j++){
                    System.out.print(m[i][j]+" ");
                }
                System.out.println("");
            }
            index++;
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
    
    
}
