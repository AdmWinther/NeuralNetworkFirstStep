package org.example.Controller;

import org.example.Model.AppState;

public class ExitApplicationController implements IMenuController{

    public  ExitApplicationController(){}

    @Override
    public void execute(AppState appState) {
        //TODO: Ask the user if they are sure they want to exit.

        appState.stopRunning();
    }
}
