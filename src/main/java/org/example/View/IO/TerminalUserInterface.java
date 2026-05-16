package org.example.View.IO;

import java.util.Scanner;

public class TerminalUserInterface implements IUserInterface {
    @Override
    public void output(String message) {
        System.out.println(message);
    }

    @Override
    public String getString(String promt) {
        Scanner sc = new  Scanner(System.in);
        System.out.print(promt);
        return sc.next();
    }

    @Override
    public String getStringOrEmpty(String promt) {
        Scanner sc = new  Scanner(System.in);
        System.out.print(promt);
        return sc.nextLine();
    }

    @Override
    public int getInt(String s) {
        Scanner sc = new  Scanner(System.in);
        System.out.print(s);
        return sc.nextInt();
    }

    @Override
    public int getInt(String prompt, int min, int max) {
        int number = this.getInt(prompt);
        while ((number < min || number > max)){
            this.output("The number is not in the acceptable range.");
            this.output("Please enter a number from " + min + " to " + max + ".");
            number = this.getInt(prompt);
        };
        return number;
    }

    @Override
    public boolean getYesNo(String prompt) {
        String answer = this.getString(prompt);
        while(true) {
            if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                return true;
            }
            if(answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                return false;
            }
            answer = this.getString(prompt);
        }
    }

    @Override
    public String getStringWithDefaultAcceptEmpty(String s, String defaultValue) {
        String prompt = s + " (" + defaultValue + ")";
        String newString = this.getStringOrEmpty(prompt);
        if("".equalsIgnoreCase(newString)){
            return defaultValue;
        } else {
            return  newString;
        }
    }

    @Override
    public boolean getYesNoAcceptsEmpty(String prompt) {
        String promptExtended = prompt + "(Yes)";
        String answer = this.getStringOrEmpty(promptExtended);
        while(true) {
            if(answer.equalsIgnoreCase("")) {
                return true;
            }
            this.getYesNo(prompt);
        }
    }
}
