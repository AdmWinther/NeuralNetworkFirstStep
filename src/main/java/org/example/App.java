package org.example;

import org.example.Model.Layer;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        Layer layer = new Layer("layer01", 3, 2);

        System.out.println(Arrays.toString(layer.getBiases()));

        layer.printWeights();

        float[] input = new float[2];


        input[0] = 0.1F;
        input[1] = 0.2F;
        layer.verboseGetOutput(input);
    }
}
