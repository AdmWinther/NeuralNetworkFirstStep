package org.example.Model;

import org.example.Model.Data.IData;

public interface IAppState {
    public IAI getAI();
    public void setAi(IAI ai);
    public void setAI(IAI ai);
    public void train(IData[] trainingData);
    public boolean continueRunning();
    public void stopRunning();
    public boolean isTrainingDataSet();
    public void setTrainingData(IData[] iData);
    public IData[] getTrainingData();

    void setTestData(IData[] iData);

    IData[] getTestData();
}
