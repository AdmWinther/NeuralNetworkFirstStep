package org.example.Model.Data;

public class Data implements IData {
    private float[] values;
    private int label;

    public Data(float[] values){
        this.values = values;
    }

    public Data(float[] values, int label){
        this.label = label;
        this.values = values;
    }

    public float[] getValues() {
        return this.values;
    }

    public void setValues(float[] values) {this.values = values;}

    public int getLabel() {return this.label;}

    public void setLabel(int label) {this.label = label;}
}
