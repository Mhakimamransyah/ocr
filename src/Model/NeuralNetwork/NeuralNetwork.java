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
public class NeuralNetwork {
    
    private InputLayer input_layer;
    private ArrayList<HiddenLayer> n_hidden_layer;
    private OutputLayer output_layer;
    private double error;

    public NeuralNetwork(int hidden[],int n_input_layer,int n_output_layer){
        this.n_hidden_layer = new ArrayList<HiddenLayer>();
        this.inisialisasiNeuralNetwork(hidden,n_input_layer,n_output_layer);
        this.cetakBobot();
        System.out.println("");
        System.out.println("");
    //    this.cetakArsitektur();
    }
    
    private void inisialisasiNeuralNetwork(int hidden[],int n_input_layer, int n_output_layer){
        int jumlah_node = 0;
        HiddenLayer hidden_layer;
        this.input_layer = new InputLayer(n_input_layer,hidden[0]);
        for(int i=0;i<hidden.length;i++){
           if(i == hidden.length-1){
               hidden_layer = new HiddenLayer(hidden[i],n_output_layer); // hidden layer terakhir
           }else{
               hidden_layer = new HiddenLayer(hidden[i],hidden[i+1]);
           }
           this.n_hidden_layer.add(hidden_layer);
        }
        this.output_layer = new OutputLayer(n_output_layer);
    }
    
      public InputLayer getInput_layer() {
        return input_layer;
    }

    public void setInput_layer(InputLayer input_layer) {
        this.input_layer = input_layer;
    }

    public ArrayList<HiddenLayer> getN_hidden_layer() {
        return n_hidden_layer;
    }

    public void setN_hidden_layer(ArrayList<HiddenLayer> n_hidden_layer) {
        this.n_hidden_layer = n_hidden_layer;
    }

    public OutputLayer getOutput_layer() {
        return output_layer;
    }

    public void setOutput_layer(OutputLayer output_layer) {
        this.output_layer = output_layer;
    }
    
    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
    
    public void cetakBobot(){
        int hidden = 1;
        System.out.println("----------------------------------------------");
        System.out.println("Ke bawah jumlah neuronnya ke kanan jumlah neuron di atasnya");
        System.out.println("----------------------------------------------");
        System.out.println("Bobot lapisan input ke hidden 1");
        for(Neuron neuron : this.input_layer.getNeuron()){
            for(Double value : neuron.getBobot_keluar()){
                System.out.print(value+" ");
            }
            System.out.println("");
        }
        for(HiddenLayer hidden_layer : this.n_hidden_layer){
            System.out.print("Bobot hidden "+hidden);
            if(hidden == this.n_hidden_layer.size()){
                System.out.println(" ke lapisan output ");
            }else{
                System.out.println(" ke hidden "+(hidden+1));
            }
            for(Neuron neuron : hidden_layer.getNeuron()){
                for(Double value: neuron.getBobot_keluar()){
                    System.out.print(value+" ");
                }
                System.out.println("");
            }
            hidden++;
        }
    }
    
    public void cetakArsitektur(){
        int i=1;
        System.out.println("Lapisan Input");
        for(Neuron neuron : this.input_layer.getNeuron()){
            System.out.print("(Node"+i+") ");
            i++;
        }
        System.out.println("");
        int j=1;
        for(HiddenLayer hidden : this.n_hidden_layer){
            System.out.println("Lapisan Hidden("+j+")");
            i=1;
            for(Neuron neuron : hidden.getNeuron()){
                System.out.print("(Node"+i+") ");
                i++;
            }
            j++;
            System.out.println("");
        }
        System.out.println("Lapisan Output");
        i=1;
        for(Neuron neuron : this.output_layer.getNeuron()){
            System.out.print("(Node"+i+") ");
            i++;
        }
        System.out.println("");
    }
    
}
