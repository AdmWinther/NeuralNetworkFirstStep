package org.example.View.Menu;

import org.example.Model.AppState;
import org.example.View.IO.IUserInterface;

import java.util.ArrayList;
import java.util.List;

public non-sealed class SubMenu extends IMenuComponent {
    List<IMenuComponent> subMenuElements;

    public SubMenu(String name){
        this.subMenuElements = new ArrayList<IMenuComponent>();
        if(!name.endsWith(" >")) name = name + " >";
        super(name);
    }

    @Override
    public void actionOnSelection(IUserInterface userInterface, AppState appState) {
        this.getNextAction(userInterface, this, appState);
    }


    public SubMenu add(IMenuComponent element) {
        this.subMenuElements.add(element);
        return this;
    }

    private String getListOfItems() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append("\n");
        int counter = 0;
        for (IMenuComponent element : this.subMenuElements) {
            sb.append(String.valueOf(++counter)).append(" - ").append(element.getName());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void getNextAction(IUserInterface userInterface, SubMenu parentMenu, AppState appState){
        String options = this.getListOfItems();
        userInterface.output(options);
        int select = userInterface.getInt("Choose an option: ", 1, this.subMenuElements.size());
        select--;
        subMenuElements.get(select).actionOnSelection(userInterface, appState);
    }
}
