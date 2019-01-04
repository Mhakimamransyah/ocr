/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.BufferedImage;

/**
 *
 * @author M.Hakim Amransyah
 */
public class CitraWarna extends Citra{
    
    private int r[][];
    private int g[][];
    private int b[][];
    
    public CitraWarna(BufferedImage img){
        this.img  = img;
        this.baris = this.img.getHeight();
        this.kolom = this.img.getWidth();
        this.r = new int[baris][kolom];
        this.g = new int[baris][kolom];
        this.b = new int[baris][kolom];
        this.isiArray();
    }
    
    public CitraWarna(int r[][],int g[][],int b[][]){
        this.r = r;
        this.g = g;
        this.b = b;
        this.bentukImage();
    }
    
    @Override
    protected void isiArray(){
        int p;
        for(int i=0;i<this.baris;i++){
            for(int j=0;j<this.kolom;j++){
                p = this.img.getRGB(j, i);
                this.r[i][j] = (p>>16)&0xff;
                this.g[i][j] = (p>>8)&0xff;
                this.b[i][j] = (p>>0)&0xff;
            }
        }
    }
    
    @Override
    protected void bentukImage(){
        int p;
        for(int i=0;i<this.baris;i++){
            for(int j=0;j<this.kolom;j++){
                p = (r[i][j]<<16) | (g[i][j]<<8) | b[i][j];
                this.img.setRGB(j, i, p);
            }
        }
    }
    
    public void bentukCitraDariArray(int r[][], int g[][], int b[][]){
        int p;
        for(int i=0;i<baris;i++){
            for(int j=0;j<kolom;j++){
               p = (r[i][j]<<16) | (g[i][j]<<8) | b[i][j];
               this.r[i][j] = r[i][j];
               this.g[i][j] = g[i][j];
               this.b[i][j] = b[i][j];
               this.img.setRGB(j, i, p);
            }
        }
    }
    
    public int[][] getR() {
        return r;
    }

    public void setR(int[][] r) {
        this.r = r;
    }

    public int[][] getG() {
        return g;
    }

    public void setG(int[][] g) {
        this.g = g;
    }

    public int[][] getB() {
        return b;
    }

    public void setB(int[][] b) {
        this.b = b;
    }
    
   
}
