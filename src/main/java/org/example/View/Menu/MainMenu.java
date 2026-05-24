package org.example.View.Menu;

import org.example.Controller.*;
import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

public class MainMenu extends SubMenu {
    IUserInterface userInterface;
    AppState appState;

    public MainMenu(IUserInterface userInterface) {
        super("Main Menu");
        this.userInterface = userInterface;
        System.out.println("new MainMentu");
        this.appState = new AppState();
    }

    public void build() {
        SubMenu modelMenu = getModelMenu();
        this.add(modelMenu);

        MenuOption exitApplication = new MenuOption("Exit", new ExitApplicationController());

        SubMenu classify = new SubMenu("Classify");
        classify.add(new MenuOption("From PNG Image", new PNGImageClassifierController(userInterface)));
        classify.add(new MenuOption("From CSV", new ClassifyCsvFileController(userInterface)));
        MenuOption exitClassify = new MenuOption("Exit", new ExitMenuController(userInterface, this));

        classify.add(exitClassify);

        this.add(classify);
        this.add(exitApplication);
    }

    private SubMenu getModelMenu() {
        SubMenu newModelMenu = new SubMenu("Model");
        MenuOption makeNewModel = new MenuOption("Make a new model", new NewAIModelController(userInterface));
        MenuOption setTrainingData = new MenuOption("Set training data", new SetTrainingDataController(userInterface));
        MenuOption trainNewModel = new MenuOption("Train the model", new TrainModelController(userInterface));
        MenuOption testModel = new MenuOption("Test the model", new TestModelController(userInterface));
        MenuOption LoadModel = new MenuOption("Load A model", new LoadModelController(userInterface));
        MenuOption SaveModel = new MenuOption("Save the model", new SaveModelController(userInterface));
        MenuOption currentModelProperties = new MenuOption("Current Model Properties", new GetModelPropController(userInterface));

        MenuOption WeighBiosVisualizer = new MenuOption("Weights & Biases Visualizer", new WeightBiosVisualizeController(userInterface));

        MenuOption exitNewModelMenu = new MenuOption("Exit", new ExitMenuController(userInterface, this));
        newModelMenu.add(makeNewModel);
        newModelMenu.add(setTrainingData);
        newModelMenu.add(trainNewModel);
        newModelMenu.add(testModel);
        newModelMenu.add(LoadModel);
        newModelMenu.add(SaveModel);
        newModelMenu.add(currentModelProperties);
        newModelMenu.add(WeighBiosVisualizer);
        newModelMenu.add(exitNewModelMenu);
        return newModelMenu;
    }

    public void getNextAction(){
        this.getNextAction(userInterface, this, appState);
    }

    public void start(){
        while(appState.continueRunning()){
            this.getNextAction();
        }
    }
}
