package org.example.View.Menu;

import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

public sealed abstract class AbsMenuComponent permits MenuOption, SubMenu {
    String name = "";
    public AbsMenuComponent(String name){
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public abstract void actionOnSelection(IUserInterface userInterface, AppState appState);
}
