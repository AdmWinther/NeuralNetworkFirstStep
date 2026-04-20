package org.example.Model;

import org.example.Model.ActivationFunction.ActivationFunctionFactory;
import org.example.Model.ActivationFunction.EnumActivationFunction;
import org.example.Model.ActivationFunction.IActivationFunction;
import org.example.Model.Interfaces.IClassifierAIFactory;
import org.example.Model.Interfaces.IConnection;

import java.util.HashMap;
import java.util.Map;

public class AiFactory implements IClassifierAIFactory {
    private final int[] layers;
    private float initialNodesBias;

    public AiFactory(int[] numberOfNodesPerLayer){
        this.layers = numberOfNodesPerLayer;
    }

    @Override
    public IClassifierAIFactory setInitialWeight(float initialWeight) {
        return this;
    }

    @Override
    public IClassifierAIFactory setInitialBias(float initialNodeBias) {
        this.initialNodesBias = initialNodeBias;
        return this;
    }

    @Override
    public AI build() {
        Map<NodeCoordinate, Node> nodes = new HashMap<>();
        ActivationFunctionFactory activationFunctionFactory = new ActivationFunctionFactory();

        for(int i = 0; i < layers.length; i++){
            for(int j = 0; j < layers[i]; j++){
                // Use the factory here, and pass the interface to the node
                IActivationFunction activationFunction = activationFunctionFactory.build(EnumActivationFunction.TANH);
                
                Node node = new Node(new NodeCoordinate(i, j), initialNodesBias, activationFunction);

                if (i == 0) {
                    node.setConnections(new IConnection[0]);
                } else {
                    IConnection[] connections = new IConnection[layers[i-1]];
                    for(int k = 0; k < layers[i-1]; k++){
                        NodeCoordinate upstreamNodeCoordinate = new NodeCoordinate(i-1, k);
                        float randomWeight = (float) (Math.random() * 0.2f - 0.1f);
                        Connection connection = new Connection(upstreamNodeCoordinate, randomWeight);
                        connection.setUpstreamNode(nodes.get(new NodeCoordinate(i-1, k)));
                        connections[k] = connection;
                    }
                    node.setConnections(connections);
                }
                nodes.put(node.getCoordinate(), node);
            }
        }
        return new AI(layers, nodes);
    }
}
