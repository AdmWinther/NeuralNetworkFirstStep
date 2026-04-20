package org.example.Model;

import org.example.Model.ActivationFunction.IActivationFunction;
import org.example.Model.Interfaces.IConnection;
import org.example.Model.Interfaces.INode;

public class Node implements INode {
    private float value;
    private float bias;
    private final NodeCoordinate coordinate;
    private IConnection[] connections;
    private IActivationFunction activationFunction;

    // netInput is summation of the input*weight + bias. i.e. the activation function is not applied to it.
    private float netInput;

    // delta is the difference of the ideal value of the node and the actual value. It is used for backpropagation and training of the model.
    private float delta;

    public Node(NodeCoordinate coordinate, float initialBias, IActivationFunction activationFunction){
        this.coordinate = coordinate;
        this.bias = initialBias;
        this.activationFunction = activationFunction;
        this.delta = 0;
    }

    @Override
    public float getValue() {
        return this.value;
    }

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public void addToValue(float value) {
        this.value += value;
    }

    @Override
    public float getBias() {
        return this.bias;
    }

    @Override
    public void setBias(float bias) {
        this.bias = bias;
    }

    @Override
    public IConnection[] getConnections() {
        return connections;
    }

    @Override
    public void setConnections(IConnection[] connections) {
        this.connections = connections;
    }

    @Override
    public NodeCoordinate getCoordinate() {
        return this.coordinate;
    }

    @Override
    public void setActivationFunction(IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    @Override
    public IActivationFunction getActivationFunction() {
        if(activationFunction == null){
            throw new RuntimeException("activation function cannot be null");
        }
        else {
            return activationFunction;
        }
    }

    @Override
    public float getNetInput() {
        return netInput;
    }

    @Override
    public void setNetInput(float netInput) {
        this.netInput = netInput;
    }

    @Override
    public float getDelta() {
        return delta;
    }

    @Override
    public void setDelta(float delta) {
        this.delta = delta;
    }

    @Override
    public void addToDelta(float delta) {
        this.delta += delta;
    }
}
