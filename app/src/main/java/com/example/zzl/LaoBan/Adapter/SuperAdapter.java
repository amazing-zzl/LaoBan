package com.example.zzl.LaoBan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zzl.LaoBan.Bean.Supermarket;
import com.example.zzl.LaoBan.R;

import java.util.List;


public class SuperAdapter extends ArrayAdapter<Supermarket> implements View.OnClickListener {
    private int resourceId;
    private CallBack mCallBack;

    public SuperAdapter(Context context, int textViewResourceId, List<Supermarket> objects, CallBack callBack) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mCallBack = callBack;
    }


    public interface CallBack {
        public void click(View view);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Supermarket supermarket = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.superImg = view.findViewById(R.id.news_item_img);
            viewHolder.superTitle = view.findViewById(R.id.news_item_title);
            viewHolder.superDelete = view.findViewById(R.id.delete_item);
            viewHolder.superAuthor = view.findViewById(R.id.news_item_author);
            viewHolder.superDate = view.findViewById(R.id.news_item_date);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (supermarket.getImage() != null) {
            viewHolder.superImg.setImageBitmap(supermarket.getImage());
        }
        viewHolder.superImg.setImageBitmap(supermarket.getImage());
        viewHolder.superTitle.setText(supermarket.getTitle());
        viewHolder.superAuthor.setText(supermarket.getSource());
        viewHolder.superDate.setText(supermarket.getDate());

        viewHolder.superDelete.setTag(position);
        viewHolder.superDelete.setOnClickListener(this);

        return view;
    }


    static class ViewHolder {
        ImageView superImg;
        TextView superTitle;
        TextView superAuthor;
        TextView superDate;
        ImageView superDelete;
    }

    @Override
    public void onClick(View view) {
        mCallBack.click(view);
    }
}
