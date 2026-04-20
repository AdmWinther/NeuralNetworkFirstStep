package org.example.Model.ActivationFunction;

public class ActivationFunctionFactory implements  IActivationFunctionFactory {

    @Override
    public IActivationFunction build(EnumActivationFunction activationFunction) {
        switch (activationFunction) {
            case ReLU -> {
                return new ActivationFunctionReLU();
            }
            case TANH -> {
                return  new ActivationFunctionTanh();
            }
            case SIGMOID ->  {
                return new ActivationFunctionSigmoid();
            }
            case Step -> {
                return new ActivationFunctionStep();
            }
            case null -> {
                throw new RuntimeException("activation function cannot be null");
            }
        }
    }
}
