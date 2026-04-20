package org.example.Model.ActivationFunction;

public class ActivationFunctionStep implements IActivationFunction {
    @Override
    public String getName() {
        return "Step";
    }

    @Override
    public float getValue(float input)
    {
        if(input > 0)
        {
            return 1.0f;
        }
        else
        {
            return 0.0f;
        }
    }

    @Override
    public float derivative(float input) {
        return 0;
    }
}
