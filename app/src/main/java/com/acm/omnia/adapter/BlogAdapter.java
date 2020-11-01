package com.acm.omnia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acm.omnia.Model.Blog;
import com.acm.omnia.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {

    ArrayList<Blog> blogList;
    OnBlogClickedListener onBlogClickedListener;

    public BlogAdapter(ArrayList<Blog> blogList, OnBlogClickedListener onBlogClickedListener) {
        this.blogList = blogList;
        this.onBlogClickedListener = onBlogClickedListener;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_single, parent, false);
        return new BlogViewHolder(view, onBlogClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog blog = blogList.get(position);
        holder.txtBlogTitle.setText(blog.getTitle());
        Picasso.get().load(blog.getImgUrl()).into(holder.imgBlog);
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    class BlogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgBlog;
        TextView txtBlogTitle;
        OnBlogClickedListener onBlogClickedListener;

        public BlogViewHolder(@NonNull View itemView, OnBlogClickedListener onBlogClickedListener) {
            super(itemView);
            imgBlog = itemView.findViewById(R.id.imgBlog);
            txtBlogTitle = itemView.findViewById(R.id.txtBlogTitle);
            this.onBlogClickedListener = onBlogClickedListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBlogClickedListener.onBlogClick(getAdapterPosition());
        }
    }

    public interface OnBlogClickedListener {
        void onBlogClick(int position);
    }

}
