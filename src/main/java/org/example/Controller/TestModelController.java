package org.example.Controller;

import org.example.Model.AppState;
import org.example.Model.Data.CSVDataFactory;
import org.example.Model.Data.IData;
import org.example.View.IO.IUserInterface;

public class TestModelController implements IMenuController {
    IUserInterface userInterface;
    public TestModelController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        if(appState.getAI() == null){
            userInterface.output("No model exists. First make a model and train it, or load a currently trained model.");
            return;
        }
        if(!appState.getAI().isTrained()){
            userInterface.output("The model is not trained. First train the model.");
            return;
        }

        userInterface.output("Run testing the model using the test data set.");

        //Set the address of the test data
        this.userInterface.output("Default address for the test set data is: ..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_test_.csv");
        boolean useDefault = this.userInterface.getYesNoAcceptsEmpty("Do you want to use the default test data?");
        if(useDefault) {
            appState.setTestData(new CSVDataFactory().readCSVCollection("..\\MINST_dataset\\MNIST_CSV\\mnist_train\\mnist_train_.csv"));
        } else {
            String address = this.userInterface.getString("Enter the address for the training data:(press enter if default address is ok)");
            appState.setTestData(new CSVDataFactory().readCSVCollection(address));
        }
        int wrongAnswers = 0;
        for(IData data: appState.getTestData()){
            int answer = appState.getAI().classify(data);
            if(answer != data.getLabel()) wrongAnswers++;
        }

        float accuracyPercent = 100.0f * (appState.getTestData().length - wrongAnswers) / appState.getTestData().length;
        userInterface.output("Model test result:");
        userInterface.output("Size of test set: " + appState.getTestData().length);
        userInterface.output("Wrong answers: " + wrongAnswers);
        userInterface.output("Accuracy: " + String.format("%.2f", accuracyPercent) + "percent.");
        return;
    }
}
