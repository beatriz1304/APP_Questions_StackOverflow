package com.example.beatriz.questionsstack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Beatriz on 10/04/2016.
 */
public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

    private List<ButtonInfo> buttonList;

    public ButtonAdapter(List<ButtonInfo> buttonList) {
        this.buttonList = buttonList;
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }

    @Override
    public void onBindViewHolder(ButtonViewHolder buttonViewHolder, int i) {
        ButtonInfo buttonInfo = buttonList.get(i);
        buttonViewHolder.vName.setText(buttonInfo.name);
        buttonViewHolder.tx_image.setImageResource(buttonInfo.image);
        buttonViewHolder.button.setId(buttonInfo.id);

    }

    @Override
    public ButtonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.tag_button_layout, viewGroup, false);

        return new ButtonViewHolder(itemView);
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected ImageView tx_image;
        protected Button button;
        public ButtonViewHolder(View v) {
            super(v);
            //v.setId(R.string.button1);
          //  int teste = v.getId();

            vName =  (TextView) v.findViewById(R.id.txtName);
            tx_image = (ImageView)v.findViewById(R.id.imageView);
            button = (Button)v.findViewById(R.id.imageButton);

        }
    }
}

