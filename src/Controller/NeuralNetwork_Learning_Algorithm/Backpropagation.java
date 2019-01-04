/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.NeuralNetwork_Learning_Algorithm;

import Model.NeuralNetwork.HiddenLayer;
import Model.NeuralNetwork.NeuralNetwork;
import Model.NeuralNetwork.Neuron;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Backpropagation {
    
    private NeuralNetwork nn;
    private double learning_rate;
  
    public Backpropagation(NeuralNetwork nn, double lr){
        this.nn            = nn;
        this.learning_rate = lr;
    }
    
    public void learn(double data[], double[] target){
        this.propagasiMaju(data,target);
        this.propagasiBalik(target);
        this.perubahanBobot();
    }
    
    public void propagasiMaju(double data[], double[] target){
        double temp = 0;
        for(int i=0;i<data.length;i++){
            this.nn.getInput_layer().getNeuron().get(i).setData_input(data[i]);
        }
      
        int index_hidden = 0;
        for(HiddenLayer hidden : this.nn.getN_hidden_layer()){
            if(index_hidden == 0){
              int index_bobot = 0;
              for(Neuron neuron_hidden : hidden.getNeuron()){
                temp = 0;
                for(Neuron neuron_input : this.nn.getInput_layer().getNeuron()){
                   temp = temp + (neuron_input.getData_input()*neuron_input.getBobot_keluar().get(index_bobot));
                }
                neuron_hidden.setInput(temp);
                index_bobot++;
              }
            }else{
                HiddenLayer hidden_layer_dibawah = this.nn.getN_hidden_layer().get(index_hidden-1);
                int index_bobot = 0;
                for(Neuron neuron : hidden.getNeuron()){
                    temp = 0;
                    for(Neuron h_neuron : hidden_layer_dibawah.getNeuron()){
                        temp = temp + (h_neuron.getBobot_keluar().get(index_bobot)*h_neuron.getOutput());
                    }
                    neuron.setInput(temp);
                    index_bobot++;
                }
            }
            index_hidden++;
        }
        
        HiddenLayer hidden_layer_terakhir = this.nn.getN_hidden_layer().get(index_hidden-1);
        int index_bobot = 0;
        for(Neuron neuron_output : this.nn.getOutput_layer().getNeuron()){
            temp = 0;
            for(Neuron neuron_hidden : hidden_layer_terakhir.getNeuron()){
                temp = temp + (neuron_hidden.getBobot_keluar().get(index_bobot)*neuron_hidden.getOutput());
            }
            neuron_output.setInput(temp);
            index_bobot++;
        }
        if(target != null){
            int index_target = 0;
            double sum_error = 0,error;
            for(Neuron neuron_output : this.nn.getOutput_layer().getNeuron()){
              sum_error = sum_error + Math.pow(target[index_target]-neuron_output.getOutput(), 2);
              index_target++;
            }
            error = sum_error/this.nn.getOutput_layer().getNneuron();
            this.nn.setError(sum_error);   
        }else{
            for(Neuron neuron_output : this.nn.getOutput_layer().getNeuron()){
              
                System.out.print(Math.round(neuron_output.getOutput())+" ");
            }
            System.out.println("");
        }
    }
    
    private void propagasiBalik(double[] target){
        double delta;
        int index_hidden_layer = this.nn.getN_hidden_layer().size()-1;
        int index_target = 0;
        for(Neuron neuron_output : this.nn.getOutput_layer().getNeuron()){
               delta = (target[index_target]-neuron_output.getOutput())*(neuron_output.getOutput())*(1-neuron_output.getOutput());
               neuron_output.setDelta(delta);
               index_target++;
        }        
        double sum = 0;
        for(int i=index_hidden_layer;i>=0;i--){
            if(i == index_hidden_layer){ //hidden layer sesudah output layer
               
                for(Neuron neuron_hidden : this.nn.getN_hidden_layer().get(i).getNeuron()){
                    int index_bobot = 0;
                    delta = 0;
                    for(Neuron neuron_output  : this.nn.getOutput_layer().getNeuron()){
                        sum = sum +(neuron_output.getDelta()*neuron_hidden.getBobot_keluar().get(index_bobot));
                        index_bobot++;
                    }
                    delta = sum * (neuron_hidden.getOutput())*(1-neuron_hidden.getOutput());
                    neuron_hidden.setDelta(delta);
                }
            }else{ // hidden layer selanjutnya
         
                sum = 0;
                int index_bobot;
                for(Neuron neuron_hidden : this.nn.getN_hidden_layer().get(i).getNeuron()){
                    index_bobot = 0;
                    delta = 0;
                    for(Neuron neuron_hidden_atas : this.nn.getN_hidden_layer().get(i+1).getNeuron()){
                        sum = sum + (neuron_hidden_atas.getDelta()*neuron_hidden.getBobot_keluar().get(index_bobot));
                        index_bobot++;
                    }
                    delta = sum * (neuron_hidden.getOutput()) * (1-neuron_hidden.getOutput());
                    neuron_hidden.setDelta(delta);
                }
            }
        } 
    }
    
    private void perubahanBobot(){
        ArrayList<Double> bobot_baru;
        double temp = 0;
        int index_bobot = 0;
        
        //perubahan bobot input ke hidden
        for(Neuron  neuron_input : this.nn.getInput_layer().getNeuron()){
            index_bobot = 0;
            bobot_baru = new ArrayList<Double>();
            for(Neuron neuron_hidden : this.nn.getN_hidden_layer().get(0).getNeuron()){
                temp = neuron_input.getBobot_keluar().get(index_bobot)+(this.learning_rate*neuron_input.getData_input()*
                               neuron_hidden.getDelta());
                bobot_baru.add(temp);
                index_bobot++;
            }
            neuron_input.setBobot_keluar(bobot_baru);
        }
        
        for(int i=0;i<this.nn.getN_hidden_layer().size();i++){
            if(i == (this.nn.getN_hidden_layer().size()-1)){ // hidden layer terakhir
               for(Neuron neuron_hidden : this.nn.getN_hidden_layer().get(i).getNeuron()){
                   bobot_baru = new ArrayList<Double>();
                   index_bobot = 0;
                   for(Neuron neuron_output : this.nn.getOutput_layer().getNeuron()){
                       temp = neuron_hidden.getBobot_keluar().get(index_bobot)+(this.learning_rate*neuron_hidden.getOutput()*
                               neuron_output.getDelta());
                       bobot_baru.add(temp);
                       index_bobot++;
                   }
                   neuron_hidden.setBobot_keluar(bobot_baru);
               }               
            }else{
                for(Neuron neuron_bawah : this.nn.getN_hidden_layer().get(i).getNeuron()){
                    index_bobot = 0;
                    bobot_baru = new ArrayList<Double>();
                    for(Neuron neuron : this.nn.getN_hidden_layer().get(i+1).getNeuron()){
                       temp = neuron_bawah.getBobot_keluar().get(index_bobot)+(this.learning_rate*neuron_bawah.getOutput()*
                               neuron.getDelta());
                       bobot_baru.add(temp);
                       index_bobot++;
                    }
                    neuron_bawah.setBobot_keluar(bobot_baru);
                }
            }
        }
    }
    
    
    public NeuralNetwork getNn() {
        return nn;
    }
    
    
    
}
