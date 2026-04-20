package org.example.Model.ActivationFunction;

public interface IActivationFunction {
    public String getName();
    public float getValue(float input);
    public float derivative(float input);
}
