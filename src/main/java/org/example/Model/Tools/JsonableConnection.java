package org.example.Model.Tools;

import org.example.Model.Interfaces.IJsonableConnection;

public class JsonableConnection implements IJsonableConnection {
    private int upstreamNodeLayer;
    private int upstreamNodeIndex;
    private float connectionWeight;

    public JsonableConnection(int upstreamNodeLayer, int upstreamNodeIndex, float connectionWeight) {
        this.upstreamNodeLayer = upstreamNodeLayer;
        this.upstreamNodeIndex = upstreamNodeIndex;
        this.connectionWeight = connectionWeight;
    }

    public int getUpstreamNodeLayer() {
        return upstreamNodeLayer;
    }
    public void setUpstreamNodeLayer(int upstreamNodeLayer) {
        this.upstreamNodeLayer = upstreamNodeLayer;
    }

    public int getUpstreamNodeIndex() {
        return upstreamNodeIndex;
    }
    public void setUpstreamNodeIndex(int upstreamNodeIndex) {
        this.upstreamNodeIndex = upstreamNodeIndex;
    }

    public float getConnectionWeight() {
        return connectionWeight;
    }
    public void setConnectionWeight(float connectionWeight) {
        this.connectionWeight = connectionWeight;
    }
}
