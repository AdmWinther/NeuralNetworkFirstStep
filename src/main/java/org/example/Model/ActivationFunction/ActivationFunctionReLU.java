package org.example.Model.ActivationFunction;

public class ActivationFunctionReLU implements IActivationFunction {
    @Override
    public String getName() {
        return "ReLU";
    }

    @Override
    public float getValue(float input) {
        return Math.max(0.0f, input);
    }

    @Override
    public float derivative(float input) {
        return input > 0 ? 1 : 0;
    }
}
