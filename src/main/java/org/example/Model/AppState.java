package org.example.Model;

import org.example.Model.Data.IData;
import org.example.Model.Interfaces.IAI;

public class AppState {
    private IAI ai;
    private boolean isTrained;
    private IData[] trainingData;
    private boolean continueRunning = true;


    public AppState() {
        this.ai = null;
        this.isTrained = false;
    }

    public IAI getAI() {
        return this.ai;
    }

    public void setAi(IAI ai) {
        System.out.println("Setting AI");
        this.ai = ai;
        this.isTrained =  false;
    }

    public boolean isTrained() {
        return isTrained;
    }

    public void setAI(IAI ai) {
        this.ai = ai;
        this.isTrained = false;
    }

    public void train(IData[] trainingData) {
        ai.train(trainingData);
        isTrained = true;
    }

    public boolean continueRunning() {
        return this.continueRunning;
    }

    public void stopRunning() {
        this.continueRunning = false;
    }

    public boolean isTrainingDataSet() {
        return !(trainingData == null);
    }

    public void setTrainingData(IData[] iData) {
        this.trainingData = iData;
    }

    public IData[] getTrainingData() {
        return this.trainingData;
    }
}
