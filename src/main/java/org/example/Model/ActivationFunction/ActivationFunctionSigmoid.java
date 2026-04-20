package org.example.Model.ActivationFunction;

public class ActivationFunctionSigmoid implements IActivationFunction {
    @Override
    public String getName() {
        return "Sigmoid";
    }

    @Override
    public float getValue(float input) {
        return (float) (1.0f / (1.0f + Math.exp(-input)));
    }

    @Override
    public float derivative(float input) {
        float value = getValue(input);
        return value * (1.0f - value);
    }
}
