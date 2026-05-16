package org.example.Controller;

import org.example.Model.AppState;
import org.example.Model.Data.CSVDataFactory;
import org.example.Model.Data.IData;
import org.example.View.IO.IUserInterface;

public class PNGImageClassifierController implements IMenuController {
    IUserInterface userInterface;

    public PNGImageClassifierController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        userInterface.output("Reading PNG file");
        String fileAddress = userInterface.getString("Enter the address of the PNG file: ");
        if(!fileAddress.endsWith(".png")) fileAddress = fileAddress + ".png";
        //Make a csv out or the png file
        IData data = new CSVDataFactory().convertBMP2Data(fileAddress);

        int result = appState.getAI().classify(data);
        userInterface.output("Classified data: " + result);
    }
}
