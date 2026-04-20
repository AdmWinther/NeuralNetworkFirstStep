package org.example.Model.Interfaces;

public interface IClassifierAIFactory {
    public IClassifierAIFactory setInitialWeight(float initialWeight);
    public IClassifierAIFactory setInitialBias(float initialNodeBias);
    public IAI build();
}
