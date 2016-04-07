package com.example.beatriz.questionsstack;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String json_string;
    String json_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String JSON_STRING;

        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(Void... voids){
            try{
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values){ super.onProgressUpdate(values);}

        @Override
        protected void onPostExecute(String result){

            json_string = result;
            if(json_string==null){
                Toast.makeText(getApplicationContext(),"First Get JSON",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(getApplicationContext(),DisplayListView.class);
                intent.putExtra("json_data",json_string);
                startActivity(intent);
            }

        }

    }

    public void parseJSON(View view){
        String tag="";
        switch(view.getId()) {
            case R.id.b1:
               tag = "android";
              // Log.d("android", "getandroid");
                break;
            case R.id.b2:
                tag = "ios";
                break;
            case R.id.b3:
                tag="android-studio";
                break;
            case R.id.b4:
                tag="xcode";
                break;
            case R.id.b5:
                tag="swift";
                break;
        }

        json_url = "https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=creation&tagged="+tag+"&site=stackoverflow";
        new BackgroundTask().execute();

    }

}
