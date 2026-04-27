package org.example.Controller;

import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

import java.util.Arrays;

public class GetModelPropController implements IMenuController {
    IUserInterface userInterface;

    public GetModelPropController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        userInterface.output("Current Model Properties:");

        userInterface.output("Layers:");
        this.userInterface.output(Arrays.toString(appState.getAI().getLayers()));

        //Todo: Add properties like is trained, name, etc.
    }
}
