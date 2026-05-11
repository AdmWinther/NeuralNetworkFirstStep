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
        classify.add(new MenuOption("From Image", null));
        classify.add(new MenuOption("From CSV", new ClassifyCsvFileController(userInterface)));
        MenuOption exitClassify = new MenuOption("Exit", new ExitMenuController(userInterface, this));

        SubMenu alaki = new SubMenu("Alaki");
        alaki.add(new MenuOption("pooch", null));
        alaki.add(new MenuOption("Hogo", null));
        MenuOption exitAlaki = new MenuOption("Exit", new ExitMenuController(userInterface, classify));
        classify.add(alaki);
        alaki.add(exitAlaki);

        classify.add(exitClassify);

        this.add(classify);
        this.add(exitApplication);
    }

    private SubMenu getModelMenu() {
        SubMenu newModelMenu = new SubMenu("Model");
        MenuOption makeNewModel = new MenuOption("Make New Model", new NewAIModelController(userInterface));
        MenuOption setTrainingData = new MenuOption("set Training Data", new SetTrainingDataController(userInterface));
        MenuOption trainNewModel = new MenuOption("Train Model", new TrainModelController(userInterface));
        MenuOption LoadModel = new MenuOption("Load A Model", new LoadModelController(userInterface));
        MenuOption SaveModel = new MenuOption("Save Model", new SaveModelController(userInterface));
        MenuOption currentModelProperties = new MenuOption("Current Model Properties", new GetModelPropController(userInterface));
        MenuOption exitNewModelMenu = new MenuOption("Exit", new ExitMenuController(userInterface, this));
        newModelMenu.add(makeNewModel);
        newModelMenu.add(setTrainingData);
        newModelMenu.add(trainNewModel);
        newModelMenu.add(LoadModel);
        newModelMenu.add(SaveModel);
        newModelMenu.add(currentModelProperties);
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
