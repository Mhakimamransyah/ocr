/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.NeuralNetwork.NeuralNetwork;
import java.util.ArrayList;
import java.util.HashMap;
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
       
        
        this.progress.setString("Processing Profile Projection...");
        // profile projection
        Thread.sleep(1500);
        //do profile proyection
        
        this.progress.setString("Processing PCA...");
        // pca
        Thread.sleep(1500);
        // do pca
        
        
        int iter = 0;
        double mse = 0;
        while(iter <= epoch){
            Thread.sleep(50);
            this.progress.setString(iter+" epoch");
            this.mse.setText(mse+"");
            mse = (double)iter/(double)epoch;
            this.progress.setValue(iter);
            iter++;
        }
        return null;
    }
    
    
    @Override
    protected void done(){
        this.do_learn.setEnabled(true);
        this.do_learn.setText("Mulai Pelatihan");
        System.out.println("selesai");
    }
}
