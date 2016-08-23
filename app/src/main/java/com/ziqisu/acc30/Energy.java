package com.ziqisu.acc30;

/**
 * Created by ziqisu on 7/20/16.
 */
public class Energy {
    static float EnergySum;
    public static float computeEnergy(float[] X){
        EnergySum=0f;
        for(int i=1; i<X.length/2;i++){
            EnergySum=EnergySum+X[i]*X[i];

            //EnergySum=EnergySum+X[i]*X[i];
        }
        return EnergySum/512;

    }

}

