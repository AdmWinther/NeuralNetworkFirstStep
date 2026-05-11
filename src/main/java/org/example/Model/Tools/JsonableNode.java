package org.example.Model.Tools;

import org.example.Model.ActivationFunction.IActivationFunction;
import org.example.Model.Interfaces.IJsonableNode;

import java.util.ArrayList;
import java.util.List;

public class JsonableNode implements IJsonableNode {
    int layer;
    int index;
    float bias;
    List<JsonableConnection> connections;
    IActivationFunction activationFunction;


    public JsonableNode() {
        this.connections = new ArrayList<JsonableConnection>();
    }

    @Override
    public JsonableNode setLayer(int layer) {
        this.layer = layer;
        return this;
    }

    @Override
    public int getLayer() {
        return this.layer;
    }

    @Override
    public JsonableNode setIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public JsonableNode setBias(float bias) {
        this.bias = bias;
        return this;
    }

    @Override
    public float getBias() {
        return this.bias;
    }

    @Override
    public JsonableNode setActivationFunction(IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        return this;
    }

    @Override
    public IActivationFunction getActivationFunction() {
        return this.activationFunction;
    }

    @Override
    public JsonableNode addConnection(JsonableConnection connection) {
        if(this.connections == null) {
            this.connections = new ArrayList<>();
        }
        this.connections.add(connection);
        return this;
    }

    @Override
    public List<JsonableConnection> getConnections() {
        return this.connections;
    }
}
