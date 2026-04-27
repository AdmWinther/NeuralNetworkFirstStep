package org.example.Controller;

import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;
import org.example.View.Menu.SubMenu;

public class ExitMenuController implements IMenuController {
    IUserInterface userInterface;
    SubMenu parentMenu;

    public ExitMenuController(IUserInterface userInterface, SubMenu parentMenu) {
        this.parentMenu = parentMenu;
        this.userInterface = userInterface;
    }

    @Override
    public void execute(AppState appState) {
        this.parentMenu.actionOnSelection(userInterface, appState);
    }
}
