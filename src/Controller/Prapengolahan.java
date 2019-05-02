/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import Model.CitraWarna;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import org.apache.commons.lang3.ArrayUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

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
    
//    public CitraKeabuan doBinerisasi(CitraKeabuan gambar, String title){
//       int baris = gambar.getBaris();
//       int kolom = gambar.getKolom();
//       int new_p[][] = new int[baris][kolom];
//       double[] hist = new double[baris * kolom];
//       int histIdx = 0;
//       for(int i=0;i<baris;i++){
//           for(int j=0;j<kolom;j++){
//             hist[histIdx++] = gambar.getP()[i][j];
//             new_p[i][j] = this.treshold(gambar.getP()[i][j]);
//           }
//       }
//       this.displayHistogram(hist, title);
//       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
//       return gambar_2;
//    }
    
    private double[] createHistogramData(int p[][]) {
        int baris = p.length;
        int kolom = p[0].length;
        double[] hist = new double[baris * kolom];
        int histIdx = 0;
        for(int i=0;i<baris;i++){
            for(int j=0;j<kolom;j++){
              hist[histIdx++] = p[i][j];
            }
        }
        return hist;
    }
    
    public CitraKeabuan doBinerisasi(CitraKeabuan gambar, String title){
       int baris = gambar.getBaris();
       int kolom = gambar.getKolom();
       int new_p[][] = new int[baris][kolom];
       int t = this.otsu(gambar.getP());
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
             new_p[i][j] = this.treshold(gambar.getP()[i][j], t);
           }
       }
       new_p = this.horizontalBlackRunlengthFilter(new_p);
       new_p = this.transposeMatrix(new_p);
       new_p = this.horizontalBlackRunlengthFilter(new_p);
       new_p = this.transposeMatrix(new_p);
//       this.displayBarChart(this.createHistogramDataFromBinary(new_p), title);
       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
       return gambar_2;
    }
    
    public int[][] transposeMatrix(int [][] m){
       int[][] temp = new int[m[0].length][m.length];
       for (int i = 0; i < m.length; i++)
           for (int j = 0; j < m[0].length; j++)
               temp[j][i] = m[i][j];
       return temp;
    }
    
    public int[][] horizontalBlackRunlengthFilter(int p[][]) {
        final int NUM_ROWS = p.length;
        final int NUM_COLS = p[0].length;
        final double MIN_RATIO = 0.05;
        final double MAX_RATIO = 0.85;
        
        for (int i = 0; i < NUM_ROWS; i++) {
            final int BLACK_PIXELS_NUM = 
                    MathFx.countIntegerFrequency(
                            Arrays.asList(ArrayUtils.toObject(p[i])), 0);
            final double BLACK_RATIO = (double)BLACK_PIXELS_NUM / (double)NUM_COLS;
            if (BLACK_RATIO < MIN_RATIO || BLACK_RATIO > MAX_RATIO) {
                for (int j = 0; j < NUM_COLS; j++) {
                    p[i][j] = 255;
                }
            }
        }
        
        return p;
    }
    
    private double[] createHistogramDataFromBinary(int p[][]) {
        double[] hist = new double[p.length];
        Arrays.fill(hist, 0);
        for (int i = 0; i < p.length; i++) {
            hist[i] = MathFx.countIntegerFrequency(Arrays.asList(ArrayUtils.toObject(p[i])), 0);
        }
        return hist;
    }
    
    public void displayBarChart(double[] data, String title) {
        double avg = MathFx.getAvg(data);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < data.length; i++) {
            dataset.addValue(data[i], "pixel", String.valueOf(i));
        }
        JFreeChart chart = ChartFactory.createBarChart(title, "Pixel", 
                "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
//        plot.addRangeMarker(new Marker(avg));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame f = new JFrame("HISTOGRAM");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(chartPanel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void displayHistogram(double[] data, String title) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Histogram", data, 256);
        String plotTitle = title;
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
    
    private int treshold(int x, int t){
        int n= 0;
        if(x <= t){
            n = 0;
        }else{
            n = 255;
        }
        return n;
    }
    
    private double calculateWithinClassVariance(int threshold, 
            int numPixels[], int TOTAL) {
        final int MID_VALUE = threshold;
        final int PIXEL_VALUE = 256;

        int foreground = 0;
        int background = 0;
        int[] foregroundPixels = new int[PIXEL_VALUE];
        int[] backgroundPixels = new int[PIXEL_VALUE];
        
        for (int i = 0; i < MID_VALUE; i++) {
            foreground += numPixels[i];
            foregroundPixels[i] = numPixels[i];
        }
        
        for (int i = MID_VALUE; i < PIXEL_VALUE; i++) {
            background += numPixels[i];
            backgroundPixels[i] = numPixels[i];
        }
        
        double foregroundWeight = (double)foreground / (double)TOTAL;
        double backgroundWeight = (double)background / (double)TOTAL;
        
        double foregroundMean = 0.0;
        double backgroundMean = 0.0;
        
        for (int i = 0; i < MID_VALUE; i++) {
            foregroundMean += (i * foregroundPixels[i]);
        }
        
        for (int i = MID_VALUE; i < PIXEL_VALUE; i++) {
            backgroundMean += (i * backgroundPixels[i]);
        }
        
        foregroundMean = foregroundMean / (double)foreground;
        backgroundMean = backgroundMean / (double)background;
        
        double foregroundVariance = 0.0;
        double backgroundVariance = 0.0;
        
        for (int i = 0; i < MID_VALUE; i++) {
            foregroundVariance += 
                    (Math.pow(i - foregroundMean, 2) * foregroundPixels[i]);
        }
        
        for (int i = MID_VALUE; i < PIXEL_VALUE; i++) {
            backgroundVariance += 
                    (Math.pow(i - backgroundMean, 2) * backgroundPixels[i]);
        }
        
        foregroundVariance = foregroundVariance / (double)foreground;
        backgroundVariance = backgroundVariance / (double)background;
        
        double variance = (foregroundVariance * foregroundWeight) + 
                                (backgroundVariance * backgroundWeight);
        return variance;
    }
    
    private int otsu(int p[][]) {
        final int PIXEL_VALUE = 256;
        int[] numPixels = new int[PIXEL_VALUE];
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                numPixels[p[i][j]]++;
            }
        }
        
        final int TOTAL = MathFx.sum(numPixels);
        
        // test all possible mid values from 0 to 255
        double[] classVariances = new double[PIXEL_VALUE];
        for (int i = 0; i < PIXEL_VALUE; i++) {
            classVariances[i] = this.calculateWithinClassVariance(i, numPixels, 
                    TOTAL);
        }
        
        return MathFx.findSmallestNumberIndex(classVariances);
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
