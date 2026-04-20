package org.example;

import org.example.Model.AiFactory;
import org.example.Model.Data.CSVDataFactory;
import org.example.Model.Data.Data;
import org.example.Model.Interfaces.IAI;
import org.example.Model.Data.IData;
import org.example.View.AIConsoleView;

public class App 
{
    public static void main()
    {
        int[] layers = new int[]{784,128,64,32,10};
        AiFactory factory = new AiFactory(layers);
        IAI myAIModel = factory.setInitialBias(-0.5f).build();

        IData[] trainingData = new CSVDataFactory().readCSVCollection("..\\MINST_dataset\\MNIST_CSV\\mnist_train\\mnist_train_.csv");
        myAIModel.train(trainingData);

        IData test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_7.csv");
        int result = myAIModel.classify(test);
        System.out.println("7 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_1.csv");
        result = myAIModel.classify(test);
        System.out.println("1 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_2.csv");
        result = myAIModel.classify(test);
        System.out.println("2 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_3.csv");
        result = myAIModel.classify(test);
        System.out.println("3 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_4.csv");
        result = myAIModel.classify(test);
        System.out.println("4 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_5.csv");
        result = myAIModel.classify(test);
        System.out.println("5 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_6.csv");
        result = myAIModel.classify(test);
        System.out.println("6 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_7_1.csv");
        result = myAIModel.classify(test);
        System.out.println("7_1 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_8.csv");
        result = myAIModel.classify(test);
        System.out.println("8 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_4_1.csv");
        result = myAIModel.classify(test);
        System.out.println("4_1 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_0.csv");
        result = myAIModel.classify(test);
        System.out.println("0 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_0_1.csv");
        result = myAIModel.classify(test);
        System.out.println("0_1 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_3_1.csv");
        result = myAIModel.classify(test);
        System.out.println("3_1 classified as: " + result);

        test = new CSVDataFactory().readSingleCSV("..\\MINST_dataset\\MNIST_CSV\\mnist_test\\mnist_9.csv");
        result = myAIModel.classify(test);
        System.out.println("9 classified as: " + result);

//        new AIConsoleView().printAIState(myAIModel);
    }
}
