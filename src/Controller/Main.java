/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import View.Home;
import View.Panel_pengujian;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    public void tampilkanPanelPengujian(){
        if(this.learner != null){
            if(this.learner.inTraining == false){
                Panel_pengujian panel = new Panel_pengujian(this);
                panel.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Pelatihan Sedang dilakukan"," Sabar Beb",JOptionPane.ERROR_MESSAGE);
            }          
        }else{
            JOptionPane.showMessageDialog(null,"Lakukan Pelatihan Terlebih Dahulu"," Ooops",JOptionPane.ERROR_MESSAGE);
        }
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
   
    public void muatCitra(String tipe,javax.swing.JPanel parent_panel, JProgressBar progress, JLabel label_dir,
            JList list_image,JLabel label_jumlah_data) throws IOException{ 
        String dir = this.pilihDirektori(parent_panel);
        label_dir.setText(dir);
        CitraLoader memuat_citra = new CitraLoader(new File(dir),progress, label_jumlah_data);
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
    
    public void mulai_pengujian(JTable tabel,JLabel label){
        if(this.data_uji.size() > 0){
           Pengujian pengujian = new Pengujian(this.data_uji,this.learner.getNn(),tabel,label);
           pengujian.doPengujian();
        }else{
           JOptionPane.showMessageDialog(null,"Data Uji tidak ada"," Ooops",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
