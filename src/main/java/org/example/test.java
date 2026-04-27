package org.example;

import org.example.Model.Data.CSVDataFactory;
import org.example.Model.Data.Data;
import org.example.Model.Data.IData;
import org.example.Model.Node;
import org.example.Model.NodeCoordinate;
import org.example.View.IO.IUserInterface;
import org.example.View.IO.TerminalUserInterface;
import org.example.View.Menu.MainMenu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main() {
        MainMenu mainMenu = new MainMenu(new TerminalUserInterface());
        mainMenu.build();
        mainMenu.start();
    }
}
