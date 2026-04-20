package org.example.Model.ActivationFunction;

public class ActivationFunctionSoftSign implements IActivationFunction {
    @Override
    public String getName() {
        return "SoftSign";
    }

    @Override
    public float getValue(float input) {
        return (input / (1 + Math.abs(input)));
    }

    @Override
    public float derivative(float input) {
        return (1 / (1 + Math.abs(input)));
    }
}
