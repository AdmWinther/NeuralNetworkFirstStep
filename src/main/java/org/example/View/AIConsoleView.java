package org.example.View;

import org.example.Model.Interfaces.IAI;
import org.example.Model.Interfaces.IConnection;
import org.example.Model.Interfaces.INode;

import java.util.Locale;

public class AIConsoleView {

    public void printAIState(IAI ai) {
        int[] layers = ai.getLayers();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < layers.length; i++) {
            // Print weights for each node in the layer
            for (int j = 0; j < layers[i]; j++) {
                INode node = ai.getNode(i, j);
                sb.append(formatInputWeights(node));
            }
            sb.append("\n");

            // Print node coordinates, value, and bias
            for (int j = 0; j < layers[i]; j++) {
                INode node = ai.getNode(i, j);
                sb.append(String.format(Locale.US, "Cord.(%d,%d)", node.getCoordinate().layer(), node.getCoordinate().index()));
                sb.append(String.format(Locale.US, ",Val.: %.2f", node.getValue()));
                sb.append(String.format(Locale.US, ", Bias: %.2f", node.getBias()));
                sb.append("          ");
            }
            sb.append("\n\n");
        }
        System.out.println(sb.toString());
    }

    private String formatInputWeights(INode node) {
        StringBuilder stringBuilder = new StringBuilder();
        IConnection[] connections = node.getConnections();
        if (connections != null) {
            for (IConnection connection : connections) {
                String formattedWeight = String.format(Locale.US, "%.2f", connection.getWeight());
                stringBuilder.append(formattedWeight).append(", ");
            }
        }
        int howManySpaceToAdd = 43 - stringBuilder.length();
        stringBuilder.append(" ".repeat(Math.max(0, howManySpaceToAdd)));
        return stringBuilder.toString();
    }
}
