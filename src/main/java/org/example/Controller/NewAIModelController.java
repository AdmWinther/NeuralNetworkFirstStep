package org.example.Controller;

import org.example.Model.AiFactory;
import org.example.Model.Interfaces.IAI;
import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

import java.io.InputStream;
import java.util.Properties;

public class NewAIModelController implements IMenuController{
    IUserInterface userInterface;

    public NewAIModelController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                userInterface.output("Sorry, unable to find config.properties");
                // Default value for MNIST if config is missing
            }
            prop.load(input);
            int width = Integer.parseInt(prop.getProperty("image.width"));
            int height = Integer.parseInt(prop.getProperty("image.height"));


            userInterface.output("You are about to make a new AI model.");

            int numberOfLayers = userInterface.getInt("How many layers do you want the model to have? ");
            int[] layers = new int[numberOfLayers];
            userInterface.output("The first layer must have the same number of nodes as the input data.");
            userInterface.output("Therefore, since input are images of " + width + "x" + height + " pixels, then the first layer must have " + width * height + " nodes.");
            layers[0] = width * height;

            userInterface.output("The last layer has the same size as the number of classes. So the last has 10 nodes.");
            layers[numberOfLayers-1] = 10;

            userInterface.output("now choose the number of nodes in the inner layers.");

            for(int i = 1; i < numberOfLayers-1; i++) {
                layers[i] = userInterface.getInt("How many nodes in layer " + i + "?");
            }
            AiFactory factory = new AiFactory(layers);
            IAI ai = factory.build();
            appState.setAI(ai);
        } catch (Exception e) {
            System.err.println("Error loading properties: " + e.getMessage());
        }
    }
}
