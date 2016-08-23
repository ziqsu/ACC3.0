package com.ziqisu.acc30;

/**
 * Created by ziqisu on 7/20/16.
 */

import org.jtransforms.fft.FloatFFT_1D;
public class FFT {
    public static float[] computeFFT (float[] DATA){
        FloatFFT_1D fft = new FloatFFT_1D(DATA.length);
        float[] FFT = new float[DATA.length];
        for(int i =0; i<DATA.length;i++){
            FFT[i]=DATA[i];
        }
        fft.realForward(FFT);
        //Log.i("fft is:",fft.toString());
        return FFT;

    }

    public static float[] getAbsolute(float[] S){
        float[] Re = new float[S.length/2+1];
        float[] Im = new float[S.length/2+1];
        float[] Abs = new float[S.length/2+1];
        for(int k=1;k<S.length/2;k++){
            Re[k]=S[2*k];
            Im[k]=S[2*k+1];
        }
        Im[0]=0;
        Im[S.length/2]=0;
        Re[0]=S[0];
        Re[S.length/2]=S[1];
        for(int j=0;j<Abs.length;j++){
            Abs[j]= (float) Math.sqrt( Math.pow(Re[j],2)+Math.pow(Im[j],2));
        }
        return Abs;
    }


}
