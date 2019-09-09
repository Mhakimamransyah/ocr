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
public abstract class Citra {
    
    protected int baris;
    protected int kolom;
    protected BufferedImage img;
    
    protected void isiArray(){};
    protected void bentukImage(){};

    public int getBaris() {
        return baris;
    }

    public void setBaris(int baris) {
        this.baris = baris;
    }

    public int getKolom() {
        return kolom;
    }

    public void setKolom(int kolom) {
        this.kolom = kolom;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
    
}
