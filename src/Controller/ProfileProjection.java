/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Azhary Arliansyah
 */
public class ProfileProjection {
    
    private final int LOWER_BOUND_PIXEL = 0;
    private final int UPPER_BOUND_PIXEL = 255;
    private final int MAX_CHARACTER_NUM = 9;
    private final double MIN_HEIGHT_SCALE = 0.05;
    
    private BufferedImage img;
    private int imgWidth;
    private int imgHeight;
    private int characterWidth;
    private int characterHeight;
    private CitraKeabuan citra;
    private String filename;
    private double foregroundThreshold = 0.0;
    
    public ProfileProjection(CitraKeabuan citra) {
        this.citra = citra;
        this.img = citra.getImg();
        this.imgWidth = this.img.getWidth();
        this.imgHeight = this.img.getHeight();
        this.characterWidth = this.imgWidth / (MAX_CHARACTER_NUM);
        this.characterHeight = (int)(this.imgHeight * MIN_HEIGHT_SCALE);
        
//        String BASE_DIR = System.getProperty("user.dir");
//        File dir = new File(BASE_DIR + "/segments");
//        this.deleteDirectory(dir);
//        dir.mkdir();
    }
    
    public ProfileProjection(CitraKeabuan citra, String filename, double foregroundThreshold) {
        this.filename = filename;
        this.citra = citra;
        this.img = citra.getImg();
        this.imgWidth = this.img.getWidth();
        this.imgHeight = this.img.getHeight();
        this.characterWidth = this.imgWidth / (MAX_CHARACTER_NUM);
        this.characterHeight = (int)(this.imgHeight * MIN_HEIGHT_SCALE);
        this.foregroundThreshold = foregroundThreshold;
        
        String BASE_DIR = System.getProperty("user.dir");
        File dir = new File(BASE_DIR + "/segments/" + this.filename);
        this.deleteDirectory(dir);
//        dir.mkdir();
    }
    
    public static void main(String args[]) {
        int[][] pixels = new int[][] {
            {0, 255, 0},
            {255, 255, 0},
            {0, 255, 255}
        };
        for (int[] x : Filters.connected(pixels)) {
            System.out.println(Arrays.toString(x));
        }
        CitraKeabuan c = new CitraKeabuan(Filters.connected(pixels));
        ProfileProjection p = new ProfileProjection(c);
        p.getProjectedImage();
    }
    
    public CitraKeabuan getProjectedImage() {
        System.out.println("PROJECTING IMAGE");
        
        int[][] newPixels = this.citra.getPixel();
        newPixels = this.transposeMatrix(newPixels);
        
        newPixels = this.connectedFilter(newPixels);
        
        int[] histogram = this.verticalProjection(newPixels);
//        List<Integer> segmentIndices = this.cut(histogram, (int)(newPixels.length * 0.1));
        List<Integer> segmentIndices = this.cut(histogram);
        for (int i = 0; i < newPixels.length; i++) {
            for (int j : segmentIndices) {
                newPixels[i][j] = 0;
            }
        }
        
        if (segmentIndices.size() > 1) {
            int startIndex = 0;
            int endIndex = 0;
            int gap = 0;
            
            for (int i = 0; i < segmentIndices.size() - 1; i++) {
                int segmentGap = segmentIndices.get(i + 1) - 
                        segmentIndices.get(i);
                if (gap < segmentGap) {
                    gap = segmentGap;
                    startIndex = segmentIndices.get(i) + 1;
                    endIndex = segmentIndices.get(i + 1) - 1;
                }
            }
            
            int[][] pixelSegment = 
                    new int[newPixels.length][endIndex - startIndex];
            for (int i = 0; i < newPixels.length; i++) {
                for (int j = startIndex; j < endIndex; j++) {
                    pixelSegment[i][j - startIndex] = newPixels[i][j];
                }
            }
            
            newPixels = pixelSegment;
        }
        
        newPixels = this.transposeMatrix(newPixels);

        histogram = this.verticalProjection(newPixels);
        segmentIndices = this.cut(histogram);
        
        if (segmentIndices.size() > 1) {
            segmentIndices = this.segmentCleaning(segmentIndices);
        }
        
        for (int i = 0; i < newPixels.length; i++) {
            for (int j : segmentIndices) {
                newPixels[i][j] = 0;
            }
        }
        
        this.createSegment(newPixels, segmentIndices);
        
        CitraKeabuan projectedImage = new CitraKeabuan(newPixels);
        return projectedImage;
    }
    
    private void createSegment(int p[][], List<Integer> segmentIndices) {
        final int minCharWidth = this.imgWidth / (MAX_CHARACTER_NUM + 13);
        final int maxCharWidth = this.imgWidth / (MAX_CHARACTER_NUM - 1);
        int startSegment = -1;
        int endSegment = -1;
        
        int segmentNumber = 0;
        
        for (int index : segmentIndices) {
            if (startSegment == -1) {
                startSegment = index;
                continue;
            }
            
            if (index - startSegment >= minCharWidth) {
                if (index - startSegment >= maxCharWidth) {
                    startSegment = index;
                    continue;
                }
                
                endSegment = index;
            }
            
            if (startSegment != -1 && endSegment != -1) {
                int[][] pixelSegment = 
                        new int[p.length][endSegment - startSegment];
                for (int i = 0; i < p.length; i++) {
                    for (int j = startSegment; j < endSegment; j++) {
                        pixelSegment[i][j - startSegment] = p[i][j];
                    }
                }
                
                int width = pixelSegment[0].length;
                int height = pixelSegment.length;
                
                int matrixSize = width * height;
                int foregroundSize = 0;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (pixelSegment[i][j] == LOWER_BOUND_PIXEL) {
                            foregroundSize++;
                        }
                    }
                }

                double threshold = (double)foregroundSize / (double)matrixSize;
                System.out.println("FT: " + threshold + ", " + this.foregroundThreshold);
                segmentNumber++;
                if (threshold < this.foregroundThreshold) {
                    startSegment = -1;
                    endSegment = -1;
                    continue;
                }
                
                this.createSegmentImage(pixelSegment, this.filename, 
                        "res-" + (++segmentNumber) + ".jpg");
                startSegment = -1;
                endSegment = -1;
            }
        }
    }
    
    private void createSegmentImage(int p[][], String dirname, 
            String filename) {
        final int width = p[0].length;
        final int height = p.length;
        final String BASE_DIR = System.getProperty("user.dir");
        
        BufferedImage img = new CitraKeabuan(p).getImg();
        
        File directory = new File(BASE_DIR + "/segments/" + dirname);
        
        if (!directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        
        
        File f = new File(BASE_DIR + "/segments/" + dirname + "/" + filename);
        try {
            System.out.println("WRITE IMAGE " + p[0].length + ", " + p.length);
            ImageIO.write(img, "jpg", f);
        } catch (IOException ex) {
            Logger.getLogger(ProfileProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
    
    private int[][] connectedFilter(int p[][]) {
        for (int i = 0; i < p.length; i++) {
            p = this.filters(p, i, 1);
            p = this.filters(p, i, p[0].length - 2);
        }
        
        for (int i = 0; i < p[0].length; i++) {
            // 0, p.length - 1
            p = this.filters(p, 1, i);
            p = this.filters(p, p.length - 2, i);
        }
        
        return p;
    }
    
    private int[][] filters(int p[][], int x, int y) {
        if (x < 0 || y < 0 || x >= p.length || y >= p[0].length) {
//            System.out.println("OUT OF BOUND " + x + ", " + y);
            return p;
        }
//        System.out.println("FILTER " + x + ", " + y);
        if (p[x][y] == LOWER_BOUND_PIXEL) {
            p[x][y] = UPPER_BOUND_PIXEL;
            
            p = this.filters(p, x - 1, y - 1);
            p = this.filters(p, x - 1, y);
            p = this.filters(p, x - 1, y + 1);
            p = this.filters(p, x, y - 1);
            p = this.filters(p, x, y + 1);
            p = this.filters(p, x + 1, y - 1);
            p = this.filters(p, x + 1, y);
            p = this.filters(p, x + 1, y + 1);
        }

        return p;
    }
    
    private List<Integer> segmentCleaning(List<Integer> segmentIndices) {
        List<Integer> newSegmentIndices = new ArrayList<>();
        newSegmentIndices.add(segmentIndices.get(0));
        final int MIN_GAP = 10;
        
        int previousIndex = segmentIndices.get(0);
        int currentIndex = -1;
        
        for (int i = 1; i < segmentIndices.size(); i++) {
            
            if (previousIndex == -1) {
                newSegmentIndices.add(segmentIndices.get(i));
                continue;
            }
            
            previousIndex = segmentIndices.get(i - 1);
            currentIndex = segmentIndices.get(i);
            
            if (currentIndex - previousIndex > MIN_GAP) {
                newSegmentIndices.add(currentIndex);
                previousIndex = -1;
            }
            
        }
        return newSegmentIndices;
    }
    
    private int[] verticalProjection() {
        int[] histogram = new int[this.imgWidth];
        
        for (int i = 0; i < this.imgWidth; i++) {
            for (int j = 0; j < this.imgHeight; j++) {
                int p = (this.img.getRGB(i, j) >> 16) & 0xff;
                if (this.binarize(p) == LOWER_BOUND_PIXEL) {
                    histogram[i]++;
                }
            }
        }
    
        return histogram;
    }
    
    private int[] verticalProjection(int p[][]) {
        int[] histogram = new int[p[0].length];
        
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                if (this.binarize(p[i][j]) == LOWER_BOUND_PIXEL) {
                    histogram[j]++;
                }
            }
        }
    
        return histogram;
    }
    
    private int[] horizontalProjection() {
        int[] histogram = new int[this.imgHeight];
        
        for (int i = 0; i < this.imgHeight; i++) {
            for (int j = 0; j < this.imgWidth; j++) {
                if (this.binarize(this.img.getRGB(i, j)) == LOWER_BOUND_PIXEL) {
                    histogram[i]++;
                }
            }
        }
        
        return histogram;
    }
    
    private List<Integer> cut(int[] histogram) {
        List<Integer> segmentIndices = new ArrayList<>();
        boolean startCutting = false;
        boolean gapCounting = true;
        int widthCount = 0;
        int gapCount = 0;
        int widthGap = 0;

        for (int i = 0; i < histogram.length; i++) {
            gapCount++;
            if (histogram[i] >= this.characterHeight) {
                if (!startCutting) {
                    
                    // define gap between characters
                    // by counting the length between the left border and
                    // the first character
                    if (gapCounting) {
                        widthGap = gapCount;
                        gapCounting = false;
                    }
                    
                    startCutting = true;
                    widthCount = 0;
                    segmentIndices.add(i);
                }
                widthCount++;
                gapCount = 0;
            } else {
                if (startCutting) {
                    if (gapCount < widthGap) {
                        startCutting = false;
                        segmentIndices.add(i);
                    }
                }
            }
        }
        return segmentIndices;
    }
    
    private List<Integer> cut(int[] histogram, int characterHeight) {
        List<Integer> segmentIndices = new ArrayList<>();
        boolean startCutting = false;
        boolean gapCounting = true;
        int widthCount = 0;
        int gapCount = 0;
        int widthGap = 0;

        for (int i = 0; i < histogram.length; i++) {
            gapCount++;
            if (histogram[i] >= characterHeight) {
                if (!startCutting) {
                    
                    // define gap between characters
                    // by counting the length between the left border and
                    // the first character
                    if (gapCounting) {
                        widthGap = gapCount;
                        gapCounting = false;
                    }
                    
                    startCutting = true;
                    widthCount = 0;
                    segmentIndices.add(i);
                }
                widthCount++;
                gapCount = 0;
            } else {
                if (startCutting) {
                    if (gapCount < widthGap) {
                        startCutting = false;
                        segmentIndices.add(i);
                    }
                }
            }
        }
        return segmentIndices;
    }
     
    private int binarize(int pixel) {
        return pixel <= 128 ? LOWER_BOUND_PIXEL : UPPER_BOUND_PIXEL;
    }
    
    public int[][] transposeMatrix(int [][] m){
       int[][] temp = new int[m[0].length][m.length];
       for (int i = 0; i < m.length; i++)
           for (int j = 0; j < m[0].length; j++)
               temp[j][i] = m[i][j];
       return temp;
    }
}
