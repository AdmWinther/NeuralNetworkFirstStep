package org.example.Model.Tools;

import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.example.Model.ActivationFunction.*;
import org.example.Model.Interfaces.IAiJsonAdapter;
import org.example.Model.Interfaces.IConnection;
import org.example.Model.Interfaces.INode;
import org.example.Model.Connection;
import org.example.Model.Node;
import org.example.Model.NodeCoordinate;

public class AiJsonAdapter implements IAiJsonAdapter {

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(getActivationFunctionRuntimeFactory())
                .registerTypeAdapterFactory(getNodeRuntimeFactory())
                .registerTypeAdapterFactory(getConnectionRuntimeFactory())
                .registerTypeAdapterFactory(getNodeCoordinateRuntimeFactory())
                .setPrettyPrinting()
                .create();
    }

    private static RuntimeTypeAdapterFactory<IActivationFunction> getActivationFunctionRuntimeFactory() {
        return RuntimeTypeAdapterFactory.of(IActivationFunction.class)
                .registerSubtype(ActivationFunctionReLU.class)
                .registerSubtype(ActivationFunctionSigmoid.class)
                .registerSubtype(ActivationFunctionSoftSign.class)
                .registerSubtype(ActivationFunctionStep.class)
                .registerSubtype(ActivationFunctionTanh.class);
    }

    private static RuntimeTypeAdapterFactory<INode> getNodeRuntimeFactory() {
        return RuntimeTypeAdapterFactory.of(INode.class)
                .registerSubtype(Node.class);
    }

    private static RuntimeTypeAdapterFactory<IConnection> getConnectionRuntimeFactory(){
        return RuntimeTypeAdapterFactory.of(IConnection.class)
                .registerSubtype(Connection.class);
    }

    private static RuntimeTypeAdapterFactory<NodeCoordinate> getNodeCoordinateRuntimeFactory(){
        return RuntimeTypeAdapterFactory.of(NodeCoordinate.class)
                .registerSubtype(NodeCoordinate.class);
    }
}
