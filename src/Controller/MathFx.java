/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Azhary Arliansyah
 */
public class MathFx {
    public static int countIntegerFrequency(List<Integer> data, int e) {
        return Collections.frequency(data, e);
    }
    
    public static int[] getColumn(int[][] array, int index){
        int[] column = new int[array[0].length]; // Here I assume a rectangular 2D array! 
        for(int i=0; i<column.length; i++){
           column[i] = array[i][index];
        }
        return column;
    }
    
    public static double getAvg(double[] data) {
        double avg = 0.0;
        for (int i = 0; i < data.length; i++) {
            avg += data[i];
        }
        avg = avg / data.length;
        return avg;
    }
    
    public static int sum(int[] list) {
        int total = 0;
        for (int x : list) {
            total += x;
        }
        return total;
    }
    
    public static int findSmallestNumberIndex(double[] data) {
        double min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
