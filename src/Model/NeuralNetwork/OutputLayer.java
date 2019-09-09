/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.NeuralNetwork;

import Model.NeuralNetwork.Neuron;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class OutputLayer {
    
    private ArrayList<Neuron> neuron;
    private int nneuron;

    public OutputLayer(int nneuron){
        this.nneuron = nneuron;
        this.neuron = new ArrayList<Neuron>(this.nneuron);
        this.inisialisasiNeuron();
    }
    
    private void inisialisasiNeuron(){
       Neuron neuron;
       for(int i=0;i<this.nneuron;i++){
           neuron = new Neuron(0);
           this.neuron.add(neuron);
       }
    }
    
    public ArrayList<Neuron> getNeuron() {
        return neuron;
    }

    public void setNeuron(ArrayList<Neuron> neuron) {
        this.neuron = neuron;
    }

    public int getNneuron() {
        return nneuron;
    }

    public void setNneuron(int nneuron) {
        this.nneuron = nneuron;
    }
    
}
