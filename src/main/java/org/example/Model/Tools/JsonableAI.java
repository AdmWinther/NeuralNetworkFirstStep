package org.example.Model.Tools;

import org.example.Model.AI;
import org.example.Model.ActivationFunction.IActivationFunction;
import org.example.Model.Connection;
import org.example.Model.Interfaces.IAI;
import org.example.Model.Interfaces.IConnection;
import org.example.Model.Interfaces.INode;
import org.example.Model.Node;
import org.example.Model.NodeCoordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonableAI {
    private String name;
    private int[] layers;
    private boolean isTrained;
    private List<JsonableNode> jsonableNodes;

    public JsonableAI(IAI ai) {
        this.name = ai.getName();
        this.layers = ai.getLayers();
        this.isTrained = ai.isTrained();
        this.jsonableNodes  = new ArrayList<JsonableNode>();
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i]; j++) {
                INode node = ai.getNode(i,j);
                JsonableNode jsonableNode = new JsonableNode();
                jsonableNode.setLayer(i).setIndex(j).setBias(node.getBias()).setActivationFunction(node.getActivationFunction());

                //Make a Jsonable connection list for all upstream nodes. we need to save the coordinate of the upstream node and the weight of the connection.
                IConnection[] connections = node.getConnections();
                for(IConnection connection : connections){
                    int upstreamNodeLayre = connection.getUpstreamNode().getCoordinate().layer();
                    int upstreamNodeIndex = connection.getUpstreamNode().getCoordinate().index();
                    float upstreamNodeWeight = connection.getWeight();
                    JsonableConnection jsonableConnection = new JsonableConnection(upstreamNodeLayre, upstreamNodeIndex, upstreamNodeWeight);
                    jsonableNode.addConnection(jsonableConnection);
                }
                this.jsonableNodes.add(jsonableNode);
            }
        }
    }

    public IAI getAI() {
        if(jsonableNodes.isEmpty()){
            throw new RuntimeException("JsonableAI has not been initialized");
        }

        Map<NodeCoordinate, Node> nodes = new HashMap<>();

        for(JsonableNode jsonableNodes: this.jsonableNodes){

            int layer = jsonableNodes.getLayer();
            int index = jsonableNodes.getIndex();
            float bias = jsonableNodes.getBias();
            IActivationFunction activationFunction = jsonableNodes.getActivationFunction();
            Node node = new Node(new NodeCoordinate(layer, index), bias, activationFunction);

            if (layer == 0) {
                node.setConnections(new IConnection[0]);
            } else {

                List<JsonableConnection> jsonableConnections = jsonableNodes.getConnections();

                IConnection[] connections = new IConnection[jsonableConnections.size()];
                int connectionCounter = 0;
                for(JsonableConnection jsonableConnection: jsonableConnections){
                    int upstreamNodeLayer = jsonableConnection.getUpstreamNodeLayer();
                    int upstreamNodeIndex = jsonableConnection.getUpstreamNodeIndex();

                    NodeCoordinate upstreamNodeCoordinate = new NodeCoordinate(upstreamNodeLayer, upstreamNodeIndex);
                    float connectionWeight = jsonableConnection.getConnectionWeight();
                    Connection connection = new Connection(upstreamNodeCoordinate, connectionWeight);
                    connection.setUpstreamNode(nodes.get(new NodeCoordinate(upstreamNodeLayer, upstreamNodeIndex)));
                    connections[connectionCounter] = connection;
                    connectionCounter++;
                }
                node.setConnections(connections);
            }

            nodes.put(node.getCoordinate(), node);
        }

        return new AI(layers, nodes, name, isTrained);

    }
}
