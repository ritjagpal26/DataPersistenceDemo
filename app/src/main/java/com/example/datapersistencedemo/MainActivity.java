package com.example.datapersistencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
public  static  final String TAG = "MAinActivity";
    public  static  final String SHARED_PREF = "name";
    public  static  final String KEY_NAME = "key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //there is class named and sharedprefrences

        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        //write  into shared PRefrences
        sharedPreferences.edit().putString(KEY_NAME," Evneet").apply();

        //read from sharedPreferences
        String name = sharedPreferences.getString(KEY_NAME,"Rizul");
        Log.i(TAG,"onCreate : " +name);

//        //save an array
        ArrayList<String> names = new ArrayList<>(Arrays.asList(" Evneet","Rizul","RItik"));
//        sharedPreferences.edit().putStringSet("names", new HashSet<String>(names)).apply();
//
//        //retrive data
//        Set<String> recNames =sharedPreferences.getStringSet("names", new HashSet<String>(
//        ));
//        Log.i(TAG,"onCreate: "+recNames.toString() );

        try {
            sharedPreferences.edit().putString("names",ObjectSerializer.serialize(names)).apply();
            Log.i(TAG,"onCreate : " +ObjectSerializer.serialize(names));

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> recNames = new ArrayList<>();
        try {
            recNames = (ArrayList) ObjectSerializer.deserialize(sharedPreferences.getString("names", ObjectSerializer.serialize( new ArrayList<>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"onCreate : " + recNames.toString());



    }
}
