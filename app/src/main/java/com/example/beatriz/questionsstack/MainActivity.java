package com.example.beatriz.questionsstack;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String json_string;
    String json_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);


        // Using a RecyclerView because of its performance benefits vs. ListView
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recList.setLayoutManager(llm);
        recList.setLayoutManager((new GridLayoutManager(this, 2)));

        ButtonAdapter buttonAdapter = new ButtonAdapter(createButtonInfoList());
        recList.setAdapter(buttonAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //TODO create a better way to get the url and the instances.
    //TODO find out why the stringArray in string.xml file isn't working, to make the code more clear
    private List<ButtonInfo> createButtonInfoList() {

        List<ButtonInfo> list = new ArrayList<ButtonInfo>();

        ButtonInfo buttonInfo1 = new ButtonInfo();
        buttonInfo1.name="Android";
        buttonInfo1.setId(R.string.tag1);

        buttonInfo1.image=R.drawable.android; list.add(buttonInfo1);

        ButtonInfo buttonInfo2 = new ButtonInfo();buttonInfo2.name="Android Studio"; buttonInfo2.setId(R.string.tag2);buttonInfo2.image=R.drawable.androidstudio;  list.add(buttonInfo2);
        ButtonInfo buttonInfo3 = new ButtonInfo();buttonInfo3.name="iOS";buttonInfo3.setId(R.string.tag3);buttonInfo3.image=R.drawable.ios; list.add(buttonInfo3);
        ButtonInfo buttonInfo4 = new ButtonInfo();buttonInfo4.name="Swift";buttonInfo4.setId(R.string.tag4);buttonInfo4.image=R.drawable.swift; list.add(buttonInfo4);
        ButtonInfo buttonInfo5 = new ButtonInfo();buttonInfo5.name="Xcode";buttonInfo5.setId(R.string.tag5);buttonInfo5.image=R.drawable.xcode; list.add(buttonInfo5);

        return list;
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
        Log.d("android", view.getId()+" ");
        switch(view.getId()) {
            case R.string.tag1:
               tag = "android";
               break;
            case R.string.tag2:
                tag = "android-studio";
                break;
            case R.string.tag3:
                tag="ios";
                break;
            case R.string.tag4:
                tag="swift";
                break;
            case R.string.tag5:
                tag="xcode";
                break;
        }

        json_url = "https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=creation&tagged="+tag+"&site=stackoverflow";
        new BackgroundTask().execute();

    }

}
