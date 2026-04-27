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
        SubMenu newModelMenu = new SubMenu("New Model");
        MenuOption makeNewModel = new MenuOption("Make New Model", new NewAIModelController(userInterface));
        MenuOption setTrainingData = new MenuOption("set Training Data", new SetTrainingDataController(userInterface));
        MenuOption trainNewModel = new MenuOption("Train Model", new TrainModelController(userInterface));
        MenuOption exitNewModelMenu = new MenuOption("Exit", new ExitMenuController(userInterface, this));
        newModelMenu.add(makeNewModel);
        newModelMenu.add(setTrainingData);
        newModelMenu.add(trainNewModel);
        newModelMenu.add(exitNewModelMenu);
        this.add(newModelMenu);

        MenuOption currentModelMenu = new MenuOption("Current Model Properties", new GetModelPropController(userInterface));
        this.add(currentModelMenu);

        MenuOption loadModel = new MenuOption("Load Model", null);
        MenuOption exitApplication = new MenuOption("Exit", new ExitApplicationController());

        SubMenu classify = new SubMenu("Classify");
        classify.add(new MenuOption("From Image", null));
        classify.add(new MenuOption("From CSV", null));
        MenuOption exitClassify = new MenuOption("Exit", new ExitMenuController(userInterface, this));

        SubMenu alaki = new SubMenu("Alaki");
        alaki.add(new MenuOption("pooch", null));
        alaki.add(new MenuOption("Hogo", null));
        MenuOption exitAlaki = new MenuOption("Exit", new ExitMenuController(userInterface, classify));
        classify.add(alaki);
        alaki.add(exitAlaki);

        classify.add(exitClassify);

        this.add(loadModel);
        this.add(classify);
        this.add(exitApplication);
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
