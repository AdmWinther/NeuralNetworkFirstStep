package org.example.Controller;

import org.example.Model.AppState;
import org.example.Model.Data.CSVDataFactory;
import org.example.Model.Data.IData;
import org.example.View.IO.IUserInterface;

public class ClassifyCsvFileController implements IMenuController {
    IUserInterface userInterface;
    public ClassifyCsvFileController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        userInterface.output("Reading csv file");
        String fileAddress = userInterface.getString("Enter the address of the file you want to classify: ");
        if(!fileAddress.endsWith(".csv")) fileAddress = fileAddress + ".csv";
        IData data = new CSVDataFactory().readSingleCSV(fileAddress);
        int result = appState.getAI().classify(data);
        userInterface.output("Classified data: " + result);
    }
}
