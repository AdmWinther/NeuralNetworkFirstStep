package org.example.Model.Tools;

import org.example.Model.IJsonableConnection;

public class JsonableConnection implements IJsonableConnection {
    private int upstreamNodeLayer;
    private int upstreamNodeIndex;
    private float connectionWeight;

    public JsonableConnection(int upstreamNodeLayer, int upstreamNodeIndex, float connectionWeight) {
        this.upstreamNodeLayer = upstreamNodeLayer;
        this.upstreamNodeIndex = upstreamNodeIndex;
        this.connectionWeight = connectionWeight;
    }

    @Override
    public int getUpstreamNodeLayer() {
        return upstreamNodeLayer;
    }
    @Override
    public void setUpstreamNodeLayer(int upstreamNodeLayer) {
        this.upstreamNodeLayer = upstreamNodeLayer;
    }

    @Override
    public int getUpstreamNodeIndex() {
        return upstreamNodeIndex;
    }
    @Override
    public void setUpstreamNodeIndex(int upstreamNodeIndex) {
        this.upstreamNodeIndex = upstreamNodeIndex;
    }

    @Override
    public float getConnectionWeight() {
        return connectionWeight;
    }
    @Override
    public void setConnectionWeight(float connectionWeight) {
        this.connectionWeight = connectionWeight;
    }
}
