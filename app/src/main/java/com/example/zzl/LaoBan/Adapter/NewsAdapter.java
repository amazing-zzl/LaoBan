package com.example.zzl.LaoBan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zzl.LaoBan.Bean.News;
import com.example.zzl.LaoBan.R;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> implements View.OnClickListener {
    private int resourceId;
    private CallBack mCallBack;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects, CallBack callBack) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mCallBack = callBack;
        notifyDataSetChanged();
    }


    public interface CallBack {
        public void click(View view);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        News news = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.newsImg = view.findViewById(R.id.news_item_img);
            viewHolder.newsTitle = view.findViewById(R.id.news_item_title);
            viewHolder.newsDelete = view.findViewById(R.id.delete_item);
            viewHolder.newsAuthor = view.findViewById(R.id.news_item_author);
            viewHolder.newsDate = view.findViewById(R.id.news_item_date);
            view.setTag(viewHolder);
            notifyDataSetChanged();
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
            notifyDataSetChanged();
        }

        if (news.getNews_img() != null) {
            viewHolder.newsImg.setImageBitmap(news.getNews_img());
            notifyDataSetChanged();
        }
        viewHolder.newsImg.setImageBitmap(news.getNews_img());
        viewHolder.newsTitle.setText(news.getNews_title());
        viewHolder.newsAuthor.setText(news.getAuthor_name());
        viewHolder.newsDate.setText(news.getDate());

        viewHolder.newsDelete.setTag(position);
        viewHolder.newsDelete.setOnClickListener(this);
        notifyDataSetChanged();
        return view;
    }


    static class ViewHolder {
        ImageView newsImg;
        TextView newsTitle;
        TextView newsAuthor;
        TextView newsDate;
        ImageView newsDelete;
    }

    @Override
    public void onClick(View view) {
        mCallBack.click(view);
    }
}
