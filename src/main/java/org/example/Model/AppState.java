package org.example.Model;

import org.example.Model.Data.IData;

public class AppState implements IAppState {
    private IAI ai;
    private IData[] trainingData;
    private IData[] testData;
    private boolean continueRunning = true;


    public AppState() {
        this.ai = null;
    }

    @Override
    public IAI getAI() {
        return this.ai;
    }

    @Override
    public void setAi(IAI ai) {
        System.out.println("Setting AI");
        this.ai = ai;
    }

    @Override
    public void setAI(IAI ai) {
        this.ai = ai;
    }

    @Override
    public void train(IData[] trainingData) {
        ai.train(trainingData);
    }

    @Override
    public boolean continueRunning() {
        return this.continueRunning;
    }

    @Override
    public void stopRunning() {
        this.continueRunning = false;
    }

    @Override
    public boolean isTrainingDataSet() {
        return !(trainingData == null);
    }

    @Override
    public void setTrainingData(IData[] iData) {
        this.trainingData = iData;
    }

    @Override
    public IData[] getTrainingData() {
        return this.trainingData;
    }

    @Override
    public void setTestData(IData[] data) {
        this.testData = data;
    }

    @Override
    public IData[] getTestData() {
        return this.testData;
    }
}
