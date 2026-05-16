package org.example.Model;

import org.example.Model.Data.IData;

public interface IAI {
    public void train(IData[] trainingData);
    public int classify(IData input);
    public INode getNode(NodeCoordinate coordinate);
    public INode getNode(int i, int j);
    public int[] getLayers();
    public String getName();
    public void setName(String name);
    public boolean isTrained();
    public void setIsTrained(boolean trained);
}
