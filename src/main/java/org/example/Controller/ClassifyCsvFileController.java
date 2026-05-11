package org.example.Controller;

import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

public class ClassifyCsvFileController implements IMenuController {
    IUserInterface userInterface;
    public ClassifyCsvFileController(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        ;
    }
}
