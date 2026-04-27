package org.example.Controller;

import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

public class TrainModelController implements IMenuController {
    IUserInterface userInterface;

    public TrainModelController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        if(!appState.isTrainingDataSet()){
            this.userInterface.output("First set the training data.");
            return;
        }
        appState.getAI().train(appState.getTrainingData());
    }
}
