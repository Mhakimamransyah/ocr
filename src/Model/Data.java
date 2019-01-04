/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Data {
    
    private String plat_nomor;
    private CitraKeabuan citra;
    private ArrayList<CitraKeabuan> citra_segmentasi;
    
    public Data(){
        
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public CitraKeabuan getCitra() {
        return citra;
    }

    public void setCitra(CitraKeabuan citra) {
        this.citra = citra;
    }

    public ArrayList<CitraKeabuan> getCitra_segmentasi() {
        return citra_segmentasi;
    }

    public void setCitra_segmentasi(ArrayList<CitraKeabuan> citra_segmentasi) {
        this.citra_segmentasi = citra_segmentasi;
    }
    
   
}
