package com.example.beatriz.questionsstack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Beatriz on 07/04/2016.
 */
public class QuestionAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public QuestionAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Questions object) {
        super.add(object);
        list.add(object);
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        QuestionHolder questionHolder;
        row = convertView;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            questionHolder = new QuestionHolder();
            //questionHolder.tx_image = (ImageView) row.findViewById(R.id.row_image);

            questionHolder.tx_title = (TextView) row.findViewById(R.id.row_title);
            questionHolder.tx_nameUser = (TextView) row.findViewById(R.id.row_nameUser);
            questionHolder.tx_score = (TextView) row.findViewById(R.id.row_score);
            row.setTag(questionHolder);
        } else {
            questionHolder = (QuestionHolder) row.getTag();
        }

        Questions questions = (Questions) this.getItem(position);

//*******************************LOAD IMAGES *********************************************
      //  ImageView image = (ImageView) row.findViewById(R.id.row_image);
        CircleImageView image = (CircleImageView) row.findViewById(R.id.row_image);
        Picasso.with(getContext()).load(questions.getImageUser()).into(image);
//*****************************************************************************************

        questionHolder.tx_title.setText(questions.getTitle());
        questionHolder.tx_nameUser.setText(questions.getNameUser());
        questionHolder.tx_score.setText("Score: " + questions.getScore());
        return row;
    }

    static class QuestionHolder {
        public TextView tx_title, tx_nameUser, tx_score, tx_image;

    }
}
