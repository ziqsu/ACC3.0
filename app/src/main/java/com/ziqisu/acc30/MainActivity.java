package com.ziqisu.acc30;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Sensor mySensor;
    private SensorManager SM;


    // start and stop button will change the boolean start to be true and false
    static boolean start = false;

    protected collectData cData = null;
    protected Feature f = null;
    protected Handler handler;

    //set up activitytext, it will take show on the screen which activity is been recording now
    public static TextView activitytext;
    public static TextView accuracytext;


    private PowerManager.WakeLock mWakeLock;
    static String[] activity = {"Run","Drive","Sit","Climb Stairs","Walk","Cannot recognize activity"};

    //request permission at run time
    final private int RequestCodeAskPermission = 124;
    private static final int ReadPermission = 0x13;
    private static final int WritePermission = 0X11;
    private static final int AllPermissions = WritePermission+ReadPermission;
    private static String[] Permissions ={Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,};

    //google activity recoginition Api
    private static final String Tag = "MainActivity";
    //private GoogleApiClient mGoogleApiClient;
    //
    //public static TextView Google;
    //private ActivityDetectionBroadcastReceiver mBroadcastReceiver;
    public static String trueActivity=null;

    //initialize buttons
    Button startbutton;
    Button stopbutton;
    Button runbutton;
    Button sitbutton;
    Button walkbutton;
    Button drivebutton;
    Button climbbutton;


    //set up start time
    public static Double startTime;
    public static Double accTime;
    public static Double previousTime;
    public static Double sumTime;
    public static Double rate;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startbutton = (Button)findViewById(R.id.startbutton);
        Button stopbutton = (Button)findViewById(R.id.stopbutton);
        Button walkbutton = (Button)findViewById(R.id.walkbutton);
        Button runtbutton = (Button)findViewById(R.id.runbutton);
        Button sitbutton = (Button)findViewById(R.id.sitbutton);
        Button drivebutton = (Button)findViewById(R.id.drivebutton);
        Button climbbutton = (Button)findViewById(R.id.climbbutton);


        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
        //mWakeLock.acquire();

        activitytext = (TextView) findViewById(R.id.activitytext);
        activitytext.setVisibility(View.INVISIBLE);
        activitytext.setCursorVisible(false);

        accuracytext = (TextView) findViewById(R.id.accuracy);
        accuracytext.setVisibility(View.INVISIBLE);
        accuracytext.setCursorVisible(false);

        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        //handler = new Handler();

        //Google activity recognition Api
        /*Google =(TextView) findViewById(R.id.Google);
        Google.setVisibility(View.INVISIBLE);
        Google.setCursorVisible(false);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(ActivityRecognition.API)
                .build();
        mBroadcastReceiver = new ActivityDetectionBroadcastReceiver();*/
        //mGoogleApiClient.connect();

        //request permission at runtime
        boolean granted = true;
        for(String permission : Permissions){
            granted = granted && ContextCompat.checkSelfPermission(this,permission )==
                    PackageManager.PERMISSION_GRANTED;
        }
        if(!granted){
            Log.i("123","start");

            ActivityCompat.requestPermissions(this,Permissions,AllPermissions);
        }


    }

    protected void onStart(){
        super.onStart();
        //mGoogleApiClient.connect();
        //
        // Log.i(Tag,"OnStart, already connected mGoogleApiClient");
    }

    protected void onStop(){
        super.onStop();
        /*if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }*/
    }

    protected void onResume(){
        super.onResume();
        /*LocalBroadcastManager.getInstance(this).registerReceiver(
                mBroadcastReceiver,new IntentFilter(Constants.StringAction)
        );*/
    }

    protected void onPause(){
        super.onPause();
        /*LocalBroadcastManager.getInstance(this).unregisterReceiver(
                mBroadcastReceiver);*/
    }

    public void onRequestPermissionsResult(int requestCode, String[] Permissions, int[] grantedResults){
        switch (requestCode){
            case AllPermissions:{
                if(grantedResults.length>0
                        && grantedResults[0] ==PackageManager.PERMISSION_GRANTED
                        && grantedResults[1] ==PackageManager.PERMISSION_GRANTED
                        ){
                    Log.i("activity: ","All is granted");
                }else{
                    Log.e("activity:","access denied");
                }
            }
        }

    }


    public void onDestroy() {
        super.onDestroy();
        if(mWakeLock != null){
            mWakeLock.release();
        }

    }

    /*public void requestActivityUpdates(View view){
        if(!mGoogleApiClient.isConnected()){
            Toast.makeText(this,"Google Api Client is not yet connected!",
                    Toast.LENGTH_SHORT).show();
            Log.i(Tag,"Google Api Client is not yet connected");
        }else{
            ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                    mGoogleApiClient,0,getActivityDetectionPendingIntent()
            ).setResultCallback(this);
        }
    }*/

    /*public void removeActivityUpdates(View view){
        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(
                mGoogleApiClient,getActivityDetectionPendingIntent()
        ).setResultCallback(this);
    }*/

    /*private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(this, ActivitiesIntentService.class);
        return PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }*/

    public void setupStartButton(View view) {
        start = true;
        cData = new collectData();
        cData.start();
        SM.registerListener(cData,mySensor, SensorManager.SENSOR_DELAY_FASTEST);
        f = new Feature();
        f.start();
        activitytext.setText("Start");
        activitytext.setVisibility(View.VISIBLE);

        startTime = Double.valueOf(0);
        accTime = Double.valueOf(0);
        previousTime=Double.valueOf(0);
        sumTime=Double.valueOf(0);
        rate=Double.valueOf(0);

        //request activity update
        /*if(!mGoogleApiClient.isConnected()){
            Toast.makeText(this,"Google Api Client is not yet connected!",
                    Toast.LENGTH_SHORT).show();
            Log.i(Tag,"Google Api Client is not yet connected");
        }else{
            ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                    mGoogleApiClient,0,getActivityDetectionPendingIntent()
            ).setResultCallback(this);
        }*/


    }

    public void setupStopButton(View view) {
        try{
            start = false;
            SM.unregisterListener(cData);
            activitytext.setText("Stop");
            activitytext.setVisibility(View.VISIBLE);
            if(accTime-startTime >0){
                rate = sumTime/(accTime-startTime);
                if(rate>1){
                    rate=1.00;
                }
                accuracytext.setText(Double.toString(rate));
                accuracytext.setVisibility(View.VISIBLE);

                Log.i("sum time is:",Double.toString(sumTime));
                Log.i("start time is:",Double.toString(startTime));
                Log.i("acc time is:",Double.toString(accTime));
                Log.i("rate:",Double.toString(rate));
            }



        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void setupWalkButton(View view){
        if(trueActivity==null){
            startTime = Double.valueOf(System.nanoTime());
            previousTime = startTime;
            Log.i("walk start time:",Double.toString(startTime));

        }
        trueActivity="Walk";

    }

    public void setupSitButton(View view){
        if(trueActivity==null){
            startTime = Double.valueOf(System.nanoTime());
            previousTime = startTime;
            Log.i("sit start time:",Double.toString(startTime));
        }
        trueActivity="Sit";

    }

    public void setupRunButton(View view){
        if(trueActivity==null){
            startTime = Double.valueOf(System.nanoTime());
            previousTime = startTime;
            Log.i("run start time:",Double.toString(startTime));
        }
        trueActivity="Run";

    }

    public void setupClimbButton(View view){
        if(trueActivity==null){
            startTime = Double.valueOf(System.nanoTime());
            previousTime = startTime;
            Log.i("climb start time:",Double.toString(startTime));
        }
        trueActivity="Climb Stairs";

    }

    public void setupDriveButton(View view){
        if(trueActivity==null){
            startTime = Double.valueOf(System.nanoTime());
            previousTime = startTime;
            Log.i("drive start time:",Double.toString(startTime));
        }
        trueActivity="Drive";

    }





    //@Override
    /*public void onConnected(Bundle bundle) {
        Intent intent = new Intent(this,ActivitiesIntentService.class);
        PendingIntent pendingIntent = PendingIntent.getService
                (this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates
                (mGoogleApiClient,1000,pendingIntent);
        Log.i(Tag,"Connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(Tag,"Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(Tag,"Connection failed. Error:"+connectionResult.getErrorCode());
    }

    public String getDetectedActivity(int detectedActivityType){
        Resources resources = this.getResources();
        switch (detectedActivityType){
            case DetectedActivity.IN_VEHICLE:
                return resources.getString(R.string.in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return resources.getString(R.string.on_bicycle);
            case DetectedActivity.ON_FOOT:
                return resources.getString(R.string.on_foot);
            case DetectedActivity.RUNNING:
                return resources.getString(R.string.running);
            case DetectedActivity.WALKING:
                return resources.getString(R.string.walking);
            case DetectedActivity.STILL:
                return resources.getString(R.string.still);
            case DetectedActivity.TILTING:
                return resources.getString(R.string.tilting);
            case DetectedActivity.UNKNOWN:
                return resources.getString(R.string.unknown);
            default:
                return resources.getString(R.string.unidentifiable_activity);
        }
    }

    @Override
    public void onResult(Status status) {
        if(status.isSuccess()){
            Log.e(Tag,"Successfully add activity detection.");
        }else{
            Log.e(Tag,"Error: "+status.getStatusMessage());
        }
    }

    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent){
            ArrayList<DetectedActivity> detectedActivities =
                    intent.getParcelableArrayListExtra(Constants.StringExtra);
            String activityString = "";
            for(DetectedActivity activity: detectedActivities){
                activityString += "Activity:"+getDetectedActivity(activity.getType())
                        +", Confidence: "+activity.getConfidence()+"%\n";

            }
            Google.setText(activityString);
            Google.setVisibility(View.VISIBLE);
            Log.i(Tag,"google already set text!!!!!!!!!!!!");
        }
    }*/


}
