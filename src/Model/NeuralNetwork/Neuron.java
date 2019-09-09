/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.NeuralNetwork;

import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Neuron {
  
    private ArrayList<Double> bobot_keluar;
    private int nbobot_keluar;
    private double input;
    private double output;
    private double data_input;
    private double delta;

  
    public Neuron(int nbobot_keluar){
        this.nbobot_keluar = nbobot_keluar;
        this.bobot_keluar = new ArrayList<Double>(this.nbobot_keluar);
        this.inisialisasiBobotKeluar();
    }
    
    private void inisialisasiBobotKeluar(){
        for(int i=0;i<this.nbobot_keluar;i++){
            this.bobot_keluar.add(Math.random());
        }
    }
    
    public double getData_input() {
        return data_input;
    }

    public void setData_input(double data_input) {
        this.data_input = data_input;
    }
    
    public ArrayList<Double> getBobot_keluar() {
        return bobot_keluar;
    }

    public void setBobot_keluar(ArrayList<Double> bobot_keluar) {
        this.bobot_keluar = bobot_keluar;
    }
    
    public void setInput(double input) {
       this.output = 1/(1+Math.exp(-input));
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }
    
     public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
    
}
