package org.example.Model.Tools;

import java.util.ArrayList;

public class ArrayListArray {
    public static float[] createArray(ArrayList<Float> array) {
        float[] result = new float[array.size()];
        for(int i = 0; i < result.length; i++)
            result[i] = array.get(i);
        return result;
    }

    public static ArrayList<Float> createArrayList(float[] array) {
        ArrayList<Float> result = new ArrayList<>(array.length);
        for(int i = 0; i < array.length; i++)
            result.add(array[i]);
        return result;
    }
}
