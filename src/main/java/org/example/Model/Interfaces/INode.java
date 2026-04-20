package org.example.Model.Interfaces;

import org.example.Model.ActivationFunction.IActivationFunction;
import org.example.Model.NodeCoordinate;

public interface INode {
    public float getValue();
    public void setValue(float value);
    public void addToValue(float value);
    public float getBias();
    public void setBias(float bias);
    public IConnection[] getConnections();
    public void setConnections(IConnection[] connections);
    public NodeCoordinate getCoordinate();
    public void setActivationFunction(IActivationFunction activationFunction);
    public IActivationFunction getActivationFunction();
    public float getNetInput();
    public void setNetInput(float netInput);
    public float getDelta();
    public void setDelta(float delta);
    public void addToDelta(float delta);
}
