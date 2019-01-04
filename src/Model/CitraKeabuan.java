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
public class CitraKeabuan extends Citra{
    
    private int p[][];
    
    public CitraKeabuan(BufferedImage img){
        this.img  = img;
        this.baris = this.img.getHeight();
        this.kolom = this.img.getWidth();
        this.p = new int[baris][kolom];
        this.isiArray();
    }
    
    public CitraKeabuan(int p[][]){
        this.baris = p.length;
        this.kolom = p[0].length;
        this.p = new int[baris][kolom];
        this.p = p;
        this.img = new BufferedImage(this.kolom,this.baris,BufferedImage.TYPE_BYTE_GRAY);
        this.bentukImage();
    }
    
    @Override
    protected void isiArray(){
        int p;
        for(int i=0;i<this.baris;i++){
            for(int j=0;j<this.kolom;j++){
                p = this.img.getRGB(j, i);
                this.p[i][j] = (p>>16)&0xff;
            }
        }
    }
    
    @Override
    protected void bentukImage(){
        int p;
        for(int i=0;i<this.baris;i++){
            for(int j=0;j<this.kolom;j++){
                p = (this.p[i][j]<<16) | (this.p[i][j]<<8) | this.p[i][j];
                this.img.setRGB(j, i, p);
            }
        }  
    }
    
    public void bentukCitraDariArray(int px[][]){
        int p;
        for(int i=0;i<baris;i++){
            for(int j=0;j<kolom;j++){
               p = (px[i][j]<<16) | (px[i][j]<<8) | px[i][j];
               this.p[i][j] = px[i][j];
               this.img.setRGB(j, i, p);
            }
        }
    }
    
    public void cetak_matriks(){
        for(int i=0;i<this.p.length;i++){
            for(int j=0;j<this.p[i].length;j++){
                System.out.print(this.p[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
    public int[][] getP() {
        return p;
    }

    public void setP(int[][] p) {
        this.p = p;
    }
    
    
}
