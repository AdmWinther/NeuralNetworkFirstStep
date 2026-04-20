package org.example.Model.ActivationFunction;

public class ActivationFunctionTanh implements IActivationFunction {
    @Override
    public String getName() {
        return "Tanh";
    }

    @Override
    public float getValue(float input) {
        return (float) Math.tanh((double) input);
    }

    @Override
    public float derivative(float input) {
        // return the derivative of Tanh function
        return (float) (1.0f - Math.pow(Math.tanh((double) input), 2));
    }
}
