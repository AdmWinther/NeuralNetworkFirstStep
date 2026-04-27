package org.example.View.Menu;

import org.example.Controller.IMenuController;
import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

public final class MenuOption extends IMenuComponent {
    private final IMenuController controller;

    public MenuOption(String name, IMenuController controller){
        this.controller = controller;
        super(name);
    }

    public IMenuController getController() {
        return this.controller;
    }

    @Override
    public void actionOnSelection(IUserInterface userInterface, AppState appState) {
        this.controller.execute(appState);
    }
}
