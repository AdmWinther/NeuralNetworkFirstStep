package org.example.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.example.Model.AI;
import org.example.Model.AppState;
import org.example.Model.Interfaces.IAI;
import org.example.Model.Tools.AiJsonAdapter;
import org.example.Model.Tools.JsonableAI;
import org.example.View.IO.IUserInterface;

import java.io.IOException;

public class LoadModelController implements IMenuController {
    IUserInterface userInterface;

    public LoadModelController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        //Get the address for the model
        String address = userInterface.getString("What is the address of the model?");

        //Read the file
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(java.nio.file.Files.readString(java.nio.file.Paths.get(address)));

        } catch (IOException e){
            throw new RuntimeException("Error reading file" + e.getMessage());
        }

        //Make sure it is a correct format file.
        Gson gson = AiJsonAdapter.getGson();
        try {
            JsonableAI jsonableAI = gson.fromJson(stringBuilder.toString(), JsonableAI.class);
            //Build a model based on the information in the file
            IAI ai = jsonableAI.getAI();
            appState.setAI(ai);
        } catch (JsonSyntaxException e) {
            throw  new RuntimeException("Error parsing json" + e.getMessage());
        }

    }
}
