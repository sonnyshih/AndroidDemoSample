package com.sonny.example.firebaserealtimedatabase;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    public TextView authorView;
    public TextView bodyView;

    public CommentViewHolder(View itemView) {
        super(itemView);

        authorView = (TextView) itemView.findViewById(R.id.comment_author);
        bodyView = (TextView) itemView.findViewById(R.id.comment_body);
    }
}
