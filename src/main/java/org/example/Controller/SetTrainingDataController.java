package org.example.Controller;

import org.example.Model.AppState;
import org.example.Model.Data.CSVDataFactory;
import org.example.Model.Data.IData;
import org.example.View.IO.IUserInterface;

public class SetTrainingDataController implements IMenuController {
    IUserInterface userInterface;
    public SetTrainingDataController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        this.userInterface.output("You are about to set the training data for the model.");
        this.userInterface.output("Default value is: ..\\MINST_dataset\\MNIST_CSV\\mnist_train\\mnist_train_.csv");
        boolean useDefault = this.userInterface.getYesNo("Do you want to use the default training data?");
        if(useDefault) {
            appState.setTrainingData(new CSVDataFactory().readCSVCollection("..\\MINST_dataset\\MNIST_CSV\\mnist_train\\mnist_train_.csv"));
        } else {
            String address = this.userInterface.getString("Enter the address for the training data:(press enter if default address is ok)");
            appState.setTrainingData(new CSVDataFactory().readCSVCollection(address));
        }

    }
}
