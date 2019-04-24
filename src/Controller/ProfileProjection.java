/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Azhary Arliansyah
 */
public class ProfileProjection {
    
    private final int LOWER_BOUND_PIXEL = 0;
    private final int UPPER_BOUND_PIXEL = 255;
    private final int MAX_CHARACTER_NUM = 9;
    private final double MIN_HEIGHT_SCALE = 0.3;
    
    private BufferedImage img;
    private int imgWidth;
    private int imgHeight;
    private int characterWidth;
    private int characterHeight;
    private CitraKeabuan citra;
    
    public ProfileProjection(CitraKeabuan citra) {
        this.citra = citra;
        this.img = citra.getImg();
        this.imgWidth = this.img.getWidth();
        this.imgHeight = this.img.getHeight();
        this.characterWidth = this.imgWidth / MAX_CHARACTER_NUM;
        this.characterHeight = (int)(this.imgHeight * MIN_HEIGHT_SCALE);
    
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
        int[] histogram = this.verticalProjection();
        List<Integer> segmentIndices = this.cut(histogram);
        System.out.println(segmentIndices);
//        int[][] newPixels = Filters.connected(this.citra.getP());
//        for (int[] x : newPixels) {
//            System.out.println(Arrays.toString(x));
//        }
        int[][] newPixels = this.citra.getP();
        for (int i = 0; i < this.imgHeight; i++) {
            for (int j : segmentIndices) {
                newPixels[i][j] = 0;
            }
        }
        CitraKeabuan projectedImage = new CitraKeabuan(newPixels);
        return projectedImage;
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
    
    private int binarize(int pixel) {
        return pixel <= 128 ? LOWER_BOUND_PIXEL : UPPER_BOUND_PIXEL;
    }
    
    
}
