package org.example.Controller;

import org.example.Model.AppState;
import org.example.Model.IConnection;
import org.example.View.IO.IUserInterface;

public class WeightBiosVisualizeController implements IMenuController {
    IUserInterface userInterface;
    public WeightBiosVisualizeController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        userInterface.output("Weights & Biases Visualizer started.");
        int[] layers = appState.getAI().getLayers();

        //Make weights list
        // Weights of layer i, Layer 0 does not have upstream nodes and therefore no connection or weight.
        for(int layerNr = 1; layerNr < layers.length; layerNr++) {
            StringBuilder weightStringBuilder =  new StringBuilder();
            for (int nodeOfCurrentLayerIndex = 0; nodeOfCurrentLayerIndex < layers[layerNr]; nodeOfCurrentLayerIndex++) {

                //Connections of the current node
                IConnection[] connections = appState.getAI().getNode(layerNr, nodeOfCurrentLayerIndex).getConnections();
                for(IConnection connection : connections) {
//                    weightStringBuilder.append(nodeOfCurrentLayerIndex+1);
//                    weightStringBuilder.append(",");
//                    weightStringBuilder.append(connection.getUpstreamNode().getCoordinate().index()+1);
//                    weightStringBuilder.append(",");
                    weightStringBuilder.append(String.format("%.4f",connection.getWeight()));
//                    weightStringBuilder.append("\n");
                    weightStringBuilder.append(";");

                }
                weightStringBuilder.append("\n");

            }
            // Try to write to a file
            try {
                java.nio.file.Files.writeString(java.nio.file.Paths.get("./weights"+layerNr+".txt"), weightStringBuilder.toString());
            } catch (java.io.IOException e) {
                throw new RuntimeException("Error writing weights to file, " + e.getMessage());
            }
        }

    }
}
