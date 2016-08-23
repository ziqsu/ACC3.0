package com.ziqisu.acc30;

/**
 * Created by ziqisu on 7/20/16.
 */
public class Mean {
    static float Meansum;
    public static float computeMean(float[] S){
        Meansum=0f;
        for(int i=0; i<S.length;i++){
            Meansum=Meansum+S[i];
        }
        return Meansum/S.length;
    }
}

