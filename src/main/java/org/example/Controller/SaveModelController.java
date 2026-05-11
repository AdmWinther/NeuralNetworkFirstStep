package org.example.Controller;

import com.google.gson.Gson;
import org.example.Model.AppState;
import org.example.Model.Tools.AiJsonAdapter;
import org.example.Model.Tools.JsonableAI;
import org.example.View.IO.IUserInterface;

import java.io.IOException;

public class SaveModelController implements IMenuController {
    IUserInterface userInterface;

    public SaveModelController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        //Inform the user that the model must be saved.
        userInterface.output("The model must be saved.");

        // ask if they need to change the model name?
        appState.getAI().setName(userInterface.getStringWithDefault("What should be the model name?", appState.getAI().getName()));

        // Ask the file name they want use for saving the model
        String whereToSave = userInterface.getStringWithDefault("What should be the model where?", "./");

        // Make a JSON of the model
        StringBuilder stringBuilder = new StringBuilder();
        Gson gson = AiJsonAdapter.getGson();
        JsonableAI jsonableAI = new JsonableAI(appState.getAI());
        stringBuilder.append(gson.toJson(jsonableAI));

        // Write the stringBuilder to a .json file
        try {
            java.nio.file.Files.writeString(java.nio.file.Paths.get(whereToSave + appState.getAI().getName() + ".json"), stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file" + e.getMessage());
        }
    }
}
