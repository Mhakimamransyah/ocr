/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Azhary Arliansyah
 */
public class Filters {
    
    private static final int LOWER_BOUND_PIXEL = 0;
    private static final int UPPER_BOUND_PIXEL = 255;
    
    private static int[][] pixels;
    private static int imgWidth;
    private static int imgHeight;
    private static ArrayList<List<Integer>> regionCoordinates;
    
    public static int[][] connected(int[][] pixels) {
        Filters.imgHeight = pixels.length;
        if (imgHeight <= 0) {
            throw new Error(""); 
        }
        Filters.regionCoordinates = new ArrayList<List<Integer>>();
        Filters.imgWidth = pixels[0].length;
        Filters.pixels = pixels;
        
        for (int i = 0; i < Filters.imgWidth; i++) {
            Filters.filter(i, 0);
            Filters.filter(i, Filters.imgHeight - 1);
        }
        
        for (int j = 0; j < Filters.imgHeight; j++) {
            Filters.filter(0, j);
            Filters.filter(Filters.imgWidth - 1, j);
        }
        
        Filters.noiseCleaning();
        return Filters.pixels;
    }
    
    private static void filter(int x, int y) {
        ArrayList<List<Integer>> neighbors = new ArrayList<>();
        neighbors.add(new ArrayList<Integer>(Arrays
                .asList(new Integer[]{x - 1, y - 1})));
        neighbors.add(new ArrayList<Integer>(Arrays
                .asList(new Integer[]{x, y - 1})));
        neighbors.add(new ArrayList<Integer>(Arrays
                .asList(new Integer[]{x + 1, y - 1})));
        neighbors.add(new ArrayList<Integer>(Arrays
                .asList(new Integer[]{x - 1, y + 1})));
        neighbors.add(new ArrayList<Integer>(Arrays
                .asList(new Integer[]{x, y + 1})));
        neighbors.add(new ArrayList<Integer>(Arrays
                .asList(new Integer[]{x + 1, y})));
        neighbors.add(new ArrayList<Integer>(Arrays
                .asList(new Integer[]{x + 1, y + 1})));
        
        for (List<Integer> xy : neighbors) {
            if (xy.get(0) < 0 || xy.get(1) < 0 || 
                    xy.get(0) >= Filters.imgWidth || xy.get(1) >= Filters.imgHeight) {
                continue;
            }
            if (Filters.pixels[xy.get(0)][xy.get(1)] == LOWER_BOUND_PIXEL) {
                if (!Filters.regionCoordinates.contains(xy)) {
                    Filters.regionCoordinates.add(xy);
                    Filters.filter(xy.get(0), xy.get(1));
                }
                
            }
        }
    }
    
    private static void noiseCleaning() {
        for (List<Integer> xy : Filters.regionCoordinates) {
            Filters.pixels[xy.get(0)][xy.get(1)] = UPPER_BOUND_PIXEL;
        } 
    }
}
