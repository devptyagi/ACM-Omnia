package com.acm.omnia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acm.omnia.Model.Question;
import com.acm.omnia.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {

    ArrayList<Question> questionList;

    public FaqAdapter(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_single, parent, false);
        return new FaqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqViewHolder holder, int position) {
        Question q = questionList.get(position);
        holder.txtQues.setText(q.getQuestion());
        holder.txtAns.setText(q.getAns());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class FaqViewHolder extends RecyclerView.ViewHolder {

        TextView txtQues;
        ExpandableTextView txtAns;

        public FaqViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQues = itemView.findViewById(R.id.txtQues);
            txtAns = itemView.findViewById(R.id.txtAns);
        }
    }
}
