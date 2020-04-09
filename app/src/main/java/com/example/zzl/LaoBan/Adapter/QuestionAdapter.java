package com.example.zzl.LaoBan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zzl.LaoBan.Bean.Question;
import com.example.zzl.LaoBan.R;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> implements View.OnClickListener {
    private int resourceId;
    private CallBack mCallBack;

    public QuestionAdapter(Context context, int textViewResourceId, List<Question> objects, CallBack callBack) {
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

        Question question = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.questionImg = view.findViewById(R.id.news_item_img);
            viewHolder.questionTitle = view.findViewById(R.id.news_item_title);
            viewHolder.questionDelete = view.findViewById(R.id.delete_item);
            viewHolder.questionId = view.findViewById(R.id.news_item_author);
            viewHolder.questionDate = view.findViewById(R.id.news_item_date);
            view.setTag(viewHolder);
            notifyDataSetChanged();
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
            notifyDataSetChanged();
        }

        if (question.getQuestionPicture() != null) {
            viewHolder.questionImg.setImageBitmap(question.getQuestionPicture());
            notifyDataSetChanged();
        }
        viewHolder.questionImg.setImageBitmap(question.getQuestionPicture());
        viewHolder.questionTitle.setText(question.getQuestionTitle());
        viewHolder.questionId.setText(question.getQuestionId().toString());
        viewHolder.questionDate.setText(question.getUpdateTime());

        viewHolder.questionDelete.setTag(position);
        viewHolder.questionDelete.setOnClickListener(this);
        notifyDataSetChanged();
        return view;
    }


    static class ViewHolder {
        ImageView questionImg;
        TextView questionTitle;
        TextView questionId;
        TextView questionDate;
        ImageView questionDelete;
    }

    @Override
    public void onClick(View view) {
        mCallBack.click(view);
    }
}
