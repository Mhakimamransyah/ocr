/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.NeuralNetwork.HiddenLayer;
import Model.NeuralNetwork.NeuralNetwork;
import Model.NeuralNetwork.Neuron;
import View.Home;
import View.Panel_Pelatihan;
import View.Panel_pengujian;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
/**
 *
 * @author M.Hakim Amransyah
 */
public class Main {

    private ArrayList<Data> data_latih;
    private ArrayList<Data> data_uji;
    private Pelatihan learner;
    
    public Main(){
      this.data_latih = new ArrayList<Data>();
      this.data_uji = new ArrayList<Data>();
    }
    
    public void panel_pelatihan(){
        Panel_Pelatihan pelatihan = new Panel_Pelatihan(this);
        pelatihan.setVisible(true);
    }
    
   
    public static void write_bobot(NeuralNetwork nn,String file_name){
        PrintWriter writer = null;
        try {
            try {
                writer = new PrintWriter(new File(Paths.get(".").toAbsolutePath().normalize().toString()+"/"+file_name+".txt"),"UTF-8");
                writer.println(nn.getN_hidden_layer().get(0).getNeuron().size());
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int hidden = 1;
            for(Neuron neuron : nn.getInput_layer().getNeuron()){
                for(Double value : neuron.getBobot_keluar()){
                  writer.print(value+" ");   
                }
                writer.println("");
            }
       
            for(HiddenLayer hidden_layer : nn.getN_hidden_layer()){
                for(Neuron neuron : hidden_layer.getNeuron()){
                    for(Double value: neuron.getBobot_keluar()){
                        writer.print(value+" ");
                    }
                    writer.println("");
                }
                hidden++;
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
//        JOptionPane.showMessageDialog(null,"Bobot Telah Tersimpan","Berhasil",JOptionPane.OK_OPTION);
        JOptionPane.showMessageDialog(null,"Pelatihan Selesai dan Bobot Telah Tersimpan","  It's Works!!",JOptionPane.INFORMATION_MESSAGE);
//        nn.cetakBobot();
    }
    
    public NeuralNetwork load_bobot(String file_bobot){
        
        NeuralNetwork nn = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(Paths.get(".").toAbsolutePath().normalize().toString()+"\\"+file_bobot)));
            int hidden_layer[] = {Integer.parseInt(br.readLine())};
            nn = new NeuralNetwork(hidden_layer,26,36);
            
            String value,temp[];
            int iter = 1;
            ArrayList<Neuron> temp_neuron = new ArrayList<Neuron>();
            Neuron neuron;
            while ((value = br.readLine()) != null){
                temp = value.split(" ");
                ArrayList<Double> bobot_keluar = new ArrayList<Double>();
                for(String v : temp){
                    bobot_keluar.add(Double.parseDouble(v));
                }
                
                if(iter<=26){
                  neuron = new Neuron(hidden_layer[0]);   
                  neuron.setBobot_keluar(bobot_keluar);
                  temp_neuron.add(neuron);
                }else{
                  neuron = new Neuron(36);   
                  neuron.setBobot_keluar(bobot_keluar);
                  temp_neuron.add(neuron);
                }
                
                if(iter == 26){
                  nn.getInput_layer().setNeuron(temp_neuron);    
                  temp_neuron = new ArrayList<Neuron>();
                }else if(iter == (26 + hidden_layer[0])){
                    nn.getN_hidden_layer().get(0).setNeuron(temp_neuron);
                }
                
                iter++;
            }
//            nn.cetakBobot();
              JOptionPane.showMessageDialog(null,"Bobot Telah Di Muat","  It's Works!!",JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Tidak ada bobot ditemukan, Periksa file bobot.txt"," Ooops!!",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nn;
        
    }
    
    public static void main(String[] args) {
      Main main = new Main();
      main.tampilkanFrameUtama();
    }
    
    public void displayHistogram(double[] data) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Histogram", data, 256);
        String plotTitle = "Histogram";
        String xaxis = "Pixel";
        String yaxis = "Count";
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        boolean show = true;
        boolean toolTips = true;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis, 
                dataset, orientation, show, toolTips, urls);
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame f = new JFrame("HISTOGRAM");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(chartPanel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
   
    
    public void tampilkanFrameUtama(){
       Home h = new Home(this);
       h.setVisible(true);  
    }
    
    public void mulai_pelatihan(HashMap<String, String> konf, JProgressBar p, JLabel label_mse,JLabel label_waktu, JButton do_learn){
        if(this.data_latih.size() > 0){
           if(this.validasi_input(konf)){
              this.learner = new Pelatihan();
              this.learner.setDataLatih(this.data_latih);
              this.learner.setNeuralNetwork(konf);
              this.learner.setLabel(label_mse, label_waktu, do_learn);
              this.learner.setProgressBar(p);
              this.learner.execute();
              
           }else{
               JOptionPane.showMessageDialog(null,"Parameter input tidak valid"," Ooops",JOptionPane.ERROR_MESSAGE);
           }
        }else{
            JOptionPane.showMessageDialog(null,"Data latih tidak ada"," Ooops",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void panel_pengujian(){
        Panel_pengujian panel = new Panel_pengujian(this);
        panel.setVisible(true);
    }
    
    private boolean validasi_input(HashMap<String, String> konf){
        boolean valid = false;
        try{
            int epoch            = Integer.parseInt(konf.get("Epoch"));
            double learning_rate = Double.parseDouble(konf.get("Learning_rate"));
            String hidden[]      = konf.get("Hidden_layer").split(",");
            if(!(hidden.length == 1 && hidden[0].equalsIgnoreCase(""))){
               valid = true;   
            }
        }
        catch(Exception ex){
            valid = false;
        }
        return valid;
    }
   
    public void muatCitra(String tipe,javax.swing.JPanel parent_panel, 
            JProgressBar progress, JLabel label_dir,
            JList list_image,JLabel label_jumlah_data, double foregroundThreshold) throws IOException{ 
        String dir = this.pilihDirektori(parent_panel);
        label_dir.setText(dir);
        CitraLoader memuat_citra = new CitraLoader(new File(dir),progress, 
                label_jumlah_data, foregroundThreshold);
        memuat_citra.setPanel_image(list_image);
        if(tipe.equalsIgnoreCase("Data Latih")){
            this.data_latih.clear();
            memuat_citra.setData(this.data_latih);
        }else{
            this.data_uji.clear();
            memuat_citra.setData(this.data_uji);
        }
        list_image.removeAll();
        memuat_citra.execute();     
    }
        
    private String pilihDirektori(javax.swing.JPanel parent_panel){
        String direktori = "null";   
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setCurrentDirectory(new File(Paths.get(".").toAbsolutePath().normalize().toString()));
        file_chooser.setToolTipText("Pilih folder citra latih");
        file_chooser.setDialogTitle("Pilih direktori citra latih");
        file_chooser.setFileFilter(new FileNameExtensionFilter("Pilih Folder Citra Data Latih", "jpg","png","bmp"));
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(file_chooser.showOpenDialog(parent_panel) == JFileChooser.APPROVE_OPTION){
           direktori = file_chooser.getSelectedFile().toString();   
        }
        return direktori;
    }
    
    public void mulai_pengujian(JTable tabel,JLabel label,NeuralNetwork nn){
        if(this.data_uji.size() > 0){
           Pengujian pengujian = new Pengujian(this.data_uji,nn,tabel,label);
           pengujian.doPengujian();
        }else{
           JOptionPane.showMessageDialog(null,"Data Uji tidak ada"," Ooops",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void releaseData(String data){
        if(data.equalsIgnoreCase("Uji")){
            this.data_uji.clear();
        }else if(data.equalsIgnoreCase("Latih")){
            this.data_latih.clear();
        }
    }
    
}
