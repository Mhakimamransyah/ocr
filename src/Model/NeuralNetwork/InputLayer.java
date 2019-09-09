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
public class InputLayer {
    
    private ArrayList<Neuron> neuron;
    private int nneuron;
    private int n_bobot_keluar;
    
    public InputLayer(int nneuron,int nbobot_keluar){
        this.nneuron = nneuron;
        this.n_bobot_keluar = nbobot_keluar;
        this.neuron = new ArrayList<Neuron>(this.nneuron);
        this.inisialisasiNeuron();
    }
    
    private void inisialisasiNeuron(){
       Neuron neuron;
       for(int i=0;i<this.nneuron;i++){
           neuron = new Neuron(this.n_bobot_keluar);
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

    public int getN_bobot_keluar() {
        return n_bobot_keluar;
    }

    public void setN_bobot_keluar(int n_bobot_keluar) {
        this.n_bobot_keluar = n_bobot_keluar;
    }
    
    
    
}
