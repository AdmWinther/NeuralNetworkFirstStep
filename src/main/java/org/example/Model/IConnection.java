package org.example.Model;

public interface IConnection {
    public NodeCoordinate getUpstreamNodeCoordinate();
    public void setUpstreamNodeCoordinate(NodeCoordinate nodeCoordinate);
    public float getWeight();
    public void setWeight(float weight);
    public void setUpstreamNode(INode node);
    public INode getUpstreamNode();
}
