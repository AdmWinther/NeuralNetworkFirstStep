package org.example.Model;

import org.example.Model.Interfaces.IAI;
import org.example.Model.Interfaces.IConnection;
import org.example.Model.Data.IData;
import org.example.Model.Interfaces.INode;

import java.util.Map;

public class AI implements IAI {
    private final int[] layers;
    private final Map<NodeCoordinate, Node> nodes;

    public AI(int[] layers, Map<NodeCoordinate, Node> nodes){
        this.layers = layers;
        this.nodes = nodes;
    }

    @Override
    public void train(IData[] trainingData) {
        float learningRate = 0.05f; // This could be parameterized
        int epochs = 10; // This could be parameterized
        
        for (int epoch = 0; epoch < epochs; epoch++) {
            float totalLoss = 0;
            for (IData sample : trainingData) {
                forwardPass(sample);
                
                // Calculate loss for monitoring (Mean Squared Error style)
                totalLoss += calculateLoss(sample.getLabel());
                
                backwardPass(sample.getLabel());
                updateParameters(learningRate);
            }
            System.out.println("Epoch " + epoch + " completed. Avg Loss: " + (totalLoss / trainingData.length));
        }
    }

    private float calculateLoss(int targetLabel) {
        float loss = 0;
        int lastLayerIndex = layers.length - 1;
        for (int j = 0; j < layers[lastLayerIndex]; j++) {
            float target = (j == targetLabel) ? 1.0f : 0.0f;
            float output = getNode(lastLayerIndex, j).getValue();
            loss += 0.5f * (target - output) * (target - output);
        }
        return loss;
    }

    private void backwardPass(int targetLabel) {
        // 1. Reset all deltas
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i]; j++) {
                getNode(i, j).setDelta(0);
            }
        }

        // 2. Output layer deltas
        int lastLayerIndex = layers.length - 1;
        for (int j = 0; j < layers[lastLayerIndex]; j++) {
            INode outputNode = getNode(lastLayerIndex, j);
            float target = (j == targetLabel) ? 1.0f : 0.0f;
            // derivative of Mean Squared Error: (output - target)
            float error = outputNode.getValue() - target;
            outputNode.setDelta(error * outputNode.getActivationFunction().derivative(outputNode.getNetInput()));
        }

        // 3. Hidden layer deltas (pushing error backward)
        for (int i = lastLayerIndex; i > 1; i--) {
            for (int j = 0; j < layers[i]; j++) {
                INode downstreamNode = getNode(i, j);
                float downstreamDelta = downstreamNode.getDelta();
                
                for (IConnection connection : downstreamNode.getConnections()) {
                    INode upstreamNode = connection.getUpstreamNode();
                    upstreamNode.addToDelta(downstreamDelta * connection.getWeight());
                }
            }
            
            // Apply derivatives to the layer we just pushed error into (i-1)
            for (int k = 0; k < layers[i - 1]; k++) {
                INode node = getNode(i - 1, k);
                node.setDelta(node.getDelta() * node.getActivationFunction().derivative(node.getNetInput()));
            }
        }
    }

    private void updateParameters(float learningRate) {
        // Skip input layer (index 0) as it has no weights/biases to update
        for (int i = 1; i < layers.length; i++) {
            for (int j = 0; j < layers[i]; j++) {
                INode node = getNode(i, j);
                float delta = node.getDelta();

                // Update bias
                node.setBias(node.getBias() - learningRate * delta);

                // Update weights
                for (IConnection connection : node.getConnections()) {
                    INode upstreamNode = connection.getUpstreamNode();
                    float newWeight = connection.getWeight() - (learningRate * delta * upstreamNode.getValue());
                    connection.setWeight(newWeight);
                }
            }
        }
    }

    @Override
    public int classify(IData input) {
        forwardPass(input);
        
        // Find the node with the maximum value in the output layer
        int lastLayerIndex = layers.length - 1;
        int result = 0;
        float maxVal = Float.NEGATIVE_INFINITY;
        
        for (int j = 0; j < layers[lastLayerIndex]; j++) {
            float nodeValue = this.getNode(lastLayerIndex, j).getValue();
            if (nodeValue > maxVal) {
                maxVal = nodeValue;
                result = j;
            }
        }
        return result;
    }

    private void forwardPass(IData input) {
        // 1. Reset node values and net inputs for a fresh pass
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i]; j++) {
                INode node = this.getNode(i, j);
                node.setValue(0);
                node.setNetInput(0);
            }
        }

        // 2. Initialize input layer with data
        for (int i = 0; i < this.layers[0]; i++) {
            INode firstLayerNode = this.getNode(0, i);
            firstLayerNode.setValue(input.getValues()[i]);
        }

        // 3. Forward propagation through hidden and output layers
        for (int i = 1; i < layers.length; i++) {
            for (int j = 0; j < layers[i]; j++) {
                INode nodeToCalculate = this.getNode(i, j);
                IConnection[] connections = nodeToCalculate.getConnections();
                
                float weightedSum = 0;
                for (IConnection connection : connections) {
                    INode upstreamNode = connection.getUpstreamNode();
                    weightedSum += (float) (connection.getWeight() * upstreamNode.getValue());
                }
                
                // Store net input (weighted sum + bias)
                nodeToCalculate.setNetInput(weightedSum + nodeToCalculate.getBias());

                // Store activated value
                nodeToCalculate.setValue(nodeToCalculate.getActivationFunction().getValue(nodeToCalculate.getNetInput()));
            }
        }
    }

    @Override
    public INode getNode(NodeCoordinate coordinate) {
        return this.nodes.get(coordinate);
    }

    @Override
    public INode getNode(int i, int j) {
        return this.nodes.get(new NodeCoordinate(i, j));
    }

    @Override
    public int[] getLayers() {
        return this.layers;
    }
}
