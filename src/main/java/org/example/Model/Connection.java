package org.example.Model;

import org.example.Model.Interfaces.IConnection;
import org.example.Model.Interfaces.INode;

public class Connection implements IConnection{
    private INode upstreamNode;
    private NodeCoordinate upstreamNodeCoordinate;
    private float weight;

    public Connection(NodeCoordinate upstreamNodeCoordinate, float initialWeight){
        this.upstreamNodeCoordinate = upstreamNodeCoordinate;
        this.weight = initialWeight;
    }

    @Override
    public NodeCoordinate getUpstreamNodeCoordinate() {
        return this.upstreamNodeCoordinate;
    }

    @Override
    public void setUpstreamNodeCoordinate(NodeCoordinate upstreamNodeCoordinate) {
        this.upstreamNodeCoordinate = upstreamNodeCoordinate;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public void setUpstreamNode(INode upstreamNode) {
        this.upstreamNode = upstreamNode;
    }

    @Override
    public INode getUpstreamNode() {
        return this.upstreamNode;
    }
}
