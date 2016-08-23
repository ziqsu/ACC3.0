package com.ziqisu.acc30;

/**
 * Created by ziqisu on 7/20/16.
 */

import android.util.Log;
import android.os.Handler;

import java.util.ArrayList;
import java.lang.Runnable;


import weka.core.Instance;
import weka.core.DenseInstance;




public class Feature extends Thread {



    String[] activity = {"Run","Drive","Sit","Climb Stairs","Walk"};

    static int message;
    Handler handler = new Handler();
    Runnable runnable = new Runnable(){
        public void run(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.activitytext.setText(activity[message]);
                    Log.i("text send:","great");

                }
            });
        }
    };










    public void run()  {
       /* Classifier tree = new J48();
        String state;
        state = Environment.getExternalStorageState();*/

        //J48 tree = new J48();
        /*try {
            File model = new File("/sdcard/j48.model");
            tree = (J48) SerializationHelper.read(new FileInputStream("/sdcard/j48.model"));
            //if(!model.exist())
            if(!model.exists()){
                //File model = new File("/sdcard/j48.model");
                BufferedReader reader = new BufferedReader(new FileReader("/sdcard/1.arff"));
                Instances data = new Instances(reader);
                Log.i("the first instance is:",data.firstInstance().toString());
                reader.close();
                data.setClassIndex(data.numAttributes()-1);
                tree.buildClassifier(data);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/sdcard/j48.model"));
                oos.writeObject(tree);
                oos.flush();
                oos.close();
            }else{

            }
            /*BufferedReader reader = new BufferedReader(new FileReader("/sdcard/2.arff"));
            Instances data = new Instances(reader);
            Log.i("the first instance is:",data.firstInstance().toString());
            reader.close();*/

            /*data.setClassIndex(data.numAttributes()-1);
            tree.buildClassifier(data);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/sdcard/j48.model"));
            oos.writeObject(tree);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        SampleData sampledata = new SampleData();








        while (MainActivity.start) {
            //isTrainingSet.delete();

            try{
                sampledata = collectData.blockingQueue.take();
            }catch(InterruptedException e){
                e.printStackTrace();
            }


            Instance inst = new DenseInstance(15);
            ArrayList<Double> feature = new ArrayList<Double>();



            sampledata.FFTX = FFT.computeFFT(sampledata.dataX);
            sampledata.FFTY = FFT.computeFFT(sampledata.dataY);
            sampledata.FFTZ = FFT.computeFFT(sampledata.dataZ);
            sampledata.FFTXreal = FFT.getAbsolute(sampledata.FFTX);
            sampledata.FFTYreal = FFT.getAbsolute(sampledata.FFTY);
            sampledata.FFTZreal = FFT.getAbsolute(sampledata.FFTZ);




            feature.add((double) Mean.computeMean(sampledata.dataX));
            feature.add((double) Energy.computeEnergy(sampledata.FFTXreal));
            feature.add((double)Entropy.computeEntropy(sampledata.FFTXreal));
            feature.add((double) Correlation.computeCorrelation(sampledata.dataX, sampledata.dataY));
            feature.add((double) Correlation.computeCorrelation(sampledata.FFTXreal,sampledata.FFTYreal));
            feature.add((double) Mean.computeMean(sampledata.dataY));
            feature.add((double) Energy.computeEnergy(sampledata.FFTYreal));
            feature.add(Entropy.computeEntropy(sampledata.FFTYreal));
            feature.add((double) Correlation.computeCorrelation(sampledata.dataY, sampledata.dataZ));
            feature.add((double) Correlation.computeCorrelation(sampledata.FFTYreal,sampledata.FFTZreal));
            feature.add((double) Mean.computeMean(sampledata.dataZ));
            feature.add((double) Energy.computeEnergy(sampledata.FFTZreal));
            feature.add(Entropy.computeEntropy(sampledata.FFTZreal));
            feature.add((double)Correlation.computeCorrelation(sampledata.dataX, sampledata.dataZ));
            feature.add((double) Correlation.computeCorrelation(sampledata.FFTXreal,sampledata.FFTZreal));






            try {

                Double fDistribution;
                fDistribution = WekaClassifier.classify(feature.toArray());
                Log.i("fDistribution is:",Double.toString(fDistribution));
                message = fDistribution.intValue();
                Log.i("message is:",Integer.toString(message));
                new Thread(runnable).start();
                if(activity[message]==MainActivity.trueActivity){
                    MainActivity.previousTime=MainActivity.accTime;
                    MainActivity.accTime= Double.valueOf(System.nanoTime());
                    MainActivity.sumTime=MainActivity.sumTime+MainActivity.accTime-MainActivity.previousTime;
                    Log.i("run sum time:",Double.toString(MainActivity.sumTime));
                }else{
                    MainActivity.previousTime=MainActivity.accTime;
                    MainActivity.accTime= Double.valueOf(System.nanoTime());
                }
                //double d = WekaWrapper.classifyInstance(isTrainingSet.firstInstance());


            } catch (Exception e) {
                e.printStackTrace();
            }








        }
    }

}

