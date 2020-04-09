package com.example.zzl.LaoBan.Adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zzl.LaoBan.Bean.Answer;
import com.example.zzl.LaoBan.R;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    private List<Answer> mAnswerList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView answerImage;
        TextView answerContent;

        public ViewHolder(View view) {
            super(view);
            answerImage = view.findViewById(R.id.answer_image);
            answerContent = view.findViewById(R.id.answer_text);
        }
    }

    public AnswerAdapter(List<Answer> answerList) {
        mAnswerList = answerList;
    }

    @NonNull
    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Answer answer = mAnswerList.get(position);
        holder.answerImage.setImageResource(0);
        holder.answerContent.setText(answer.getAnswerContent());
    }

    @Override
    public int getItemCount() {
        return mAnswerList.size();
    }
}