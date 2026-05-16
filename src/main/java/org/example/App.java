package org.example;

import org.example.View.IO.TerminalUserInterface;
import org.example.View.Menu.MainMenu;

public class App 
{
    public static void main()
    {
        MainMenu mainMenu = new MainMenu(new TerminalUserInterface());
        mainMenu.build();
        mainMenu.start();
    }
}
