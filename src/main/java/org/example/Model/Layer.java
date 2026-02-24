package org.example.Model;

import java.util.Arrays;
import java.util.Random;

public class Layer {
    private String name;
    private final int layerNeuronsCount;
    private int layerInputSize;         //size of the upstream layer
    private float[] biases;
    private float[][] weights;

    public Layer(String name, int layerNeuronsCount, int layerInputSize) {
        this.name = name;
        this.layerNeuronsCount = layerNeuronsCount;
        this.layerInputSize = layerInputSize;
        this.biases = new float[layerNeuronsCount];

        this.weights = new float[layerNeuronsCount][layerInputSize];

        Arrays.fill(biases, 0.001F);

        Random random = new Random();
        for(int i=0; i < layerNeuronsCount; i++){
            for(int j=0; j < layerInputSize; j++){
                weights[i][j] = random.nextFloat() * 2.0F - 1.0F;
            }
        }
    }

    public String getName() { return this.name; }
    public Layer setName(String name) { this.name = name; return this; }

    public  int getLayerNeuronsCount() { return this.layerNeuronsCount; }
    public int getLayerInputSize() { return this.layerInputSize; }

    public float[] getBiases() {
        return biases;
    }

    public  float[][] getWeights() { return weights; }

    public void printWeights() {
        System.out.println("Layer Weights: [");
        for(int i = 0; i < layerNeuronsCount; i++){
            System.out.print("[ ");
            for(int j = 0; j < layerInputSize; j++){
                System.out.print(weights[i][j] + "  ");
            }
            System.out.println(" ]");
        }
        System.out.println(" ]");
    }

    public float[] getOutput(float [] inputs) {
        if(inputs.length != layerInputSize){
            throw new RuntimeException("the length of the input vector is different than the layer's expected input size.");
        }
        float[] output = new float[layerNeuronsCount];
        Arrays.fill(output, 0.0F);
        for (int i = 0; i < layerNeuronsCount; i++) {
            for (int j = 0; j < layerInputSize; j++) {
                output[i] +=
                        inputs[j] * weights[i][j];
            }
            output[i] += biases[i];
        }
        return output;
    }

    public float[] verboseGetOutput(float[] inputs) {
        System.out.print("input : [ ");
        for (float input : inputs) {
            System.out.print(input + " ");
        }
        System.out.println(" ] ");

        this.printWeights();


        System.out.print("Biases : [ ");
        for (float bias : biases) {
            System.out.print(bias + " ");
        }
        System.out.println(" ] ");

        float[] outputs = getOutput(inputs);
        System.out.print("output : [ ");
        for(int i = 0; i < layerNeuronsCount; i++) {
            System.out.print(outputs[i] + " ");
        }
        System.out.print(" ] ");

        return outputs;
    }
}
