package org.example.Model.Interfaces;

import org.example.Model.Data.IData;
import org.example.Model.NodeCoordinate;

public interface IAI {
    public void train(IData[] trainingData);
    public int classify(IData input);
    public INode getNode(NodeCoordinate coordinate);
    public INode getNode(int i, int j);
    public int[] getLayers();
}
