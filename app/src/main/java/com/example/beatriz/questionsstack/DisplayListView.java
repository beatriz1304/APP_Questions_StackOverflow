package com.example.beatriz.questionsstack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    QuestionAdapter questionAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = (ListView)findViewById(R.id.listview);

        questionAdapter = new QuestionAdapter(this, R.layout.row_layout);
        listView.setAdapter(questionAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        // Log.d("teste",json_string);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            jsonObject = new JSONObject(json_string);

            JSONArray jarray = jsonObject.getJSONArray("items");

            int count = 0;
            String title,imageUser,nameUser,score;

            while (count<20){
                JSONObject object = jarray.getJSONObject(count);
                JSONObject oOwner = object.getJSONObject("owner");

                title = object.getString("title");
                imageUser = oOwner.getString("profile_image");
                nameUser = oOwner.getString("display_name");
                score = object.getString("view_count");



                Questions questions = new Questions(title, imageUser, nameUser,score);
                questionAdapter.add(questions);
                count ++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
