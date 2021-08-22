package com.subzero.subzero;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private  Sensor proximitySensor;
    private SensorEventListener proximitySensorListner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        if(proximitySensor == null){
            Toast.makeText(this,"Proximity Sensor is not Available",Toast.LENGTH_LONG).show();
            finish();
        }
        proximitySensorListner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[0]< proximitySensor.getMaximumRange()){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                }
                else if(event.values[0] == 5){
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);

                }
                else if(event.values[0] == 6){
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

                }
                else if(event.values[0] == 7){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);

                }
                else {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(proximitySensorListner,proximitySensor,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListner);
    }
}