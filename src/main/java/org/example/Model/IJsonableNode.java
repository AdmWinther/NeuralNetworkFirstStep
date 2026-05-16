package org.example.Model;

import org.example.Model.ActivationFunction.IActivationFunction;
import org.example.Model.Tools.JsonableConnection;
import org.example.Model.Tools.JsonableNode;

import java.util.List;

public interface IJsonableNode {
    public JsonableNode setLayer(int layer);
    public int getLayer();

    public JsonableNode setIndex(int index);
    public int getIndex();

    public JsonableNode setBias(float weight);
    public float getBias();

    public JsonableNode setActivationFunction(IActivationFunction activationFunction);
    public IActivationFunction getActivationFunction();


    public JsonableNode addConnection(JsonableConnection connection);
    public List<JsonableConnection> getConnections();
}
