package org.example;

import org.example.Model.Data.CSVDataFactory;
import org.example.Model.Data.Data;
import org.example.Model.Data.IData;
import org.example.Model.Node;
import org.example.Model.NodeCoordinate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main() {
        CSVDataFactory csvDataFactory = new CSVDataFactory();
//        IData data = csvDataFactory.readSingleCSV(".\\src\\main\\java\\org\\example\\this.csv");
//        System.out.println("what we read: ");
//        System.out.println(Arrays.toString(data.getValues()));


        IData[] manyData = csvDataFactory.readCSVCollection("..\\MINST_ataset\\MNIST_CSV\\mnist_test\\mnist_test.csv");
        System.out.println("all we read: ");
        for (IData iData : manyData)
            System.out.println(Arrays.toString(iData.getValues()));

        int id = 1;
        System.out.println("number "+ id + ":");
        System.out.println(Arrays.toString(manyData[id].getValues()));
        System.out.println("label:");
        System.out.println(manyData[id].getLabel());
    }
}
