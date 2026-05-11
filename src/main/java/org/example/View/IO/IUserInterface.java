package org.example.View.IO;

public interface IUserInterface {
    public void output(String message);
    public String getString(String promt);
    String getStringOrEmpty(String promt);
    int getInt(String s);

    int getInt(String prompt, int min, int max);

    boolean getYesNo(String prompt);

    String getStringWithDefault(String s, String name);
}
