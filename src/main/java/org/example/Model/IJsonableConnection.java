package org.example.Model;

public interface IJsonableConnection {
    public int getUpstreamNodeLayer();
    public void setUpstreamNodeLayer(int upstreamNodeLayer);

    public int getUpstreamNodeIndex();
    public void setUpstreamNodeIndex(int upstreamNodeIndex);

    public float getConnectionWeight();
    public void setConnectionWeight(float connectionWeight);
}
