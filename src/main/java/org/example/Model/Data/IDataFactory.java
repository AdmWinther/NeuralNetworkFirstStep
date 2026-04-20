package org.example.Model.Data;

public interface IDataFactory {
    public IData readSingleCSV(String path);
    public IData[] readCSVCollection(String path);
}
