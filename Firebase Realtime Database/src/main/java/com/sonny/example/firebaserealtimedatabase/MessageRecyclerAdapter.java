package com.sonny.example.firebaserealtimedatabase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.gson.Gson;
import com.sonny.example.firebaserealtimedatabase.entity.Post;

public class MessageRecyclerAdapter
        extends FirebaseRecyclerAdapter<Post, PostViewHolder> {

    private DatabaseReference database;
    private Context context;

    public MessageRecyclerAdapter(Context context, Class<Post> modelClass, int modelLayout,
                                  Class<PostViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;

        database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void populateViewHolder(PostViewHolder postViewHolder, final Post post, int position) {

        final DatabaseReference postRef = getRef(position);

        final String postKey = postRef.getKey();

        Gson gson = new Gson();
        String string = gson.toJson(post);
        Log.d("Mylog", "JSON String="+string);


        // Determine if the current user has liked this post and set UI accordingly
        if (post.stars.containsKey(getUid())) {
            postViewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
        } else {
            postViewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
        }


        // Bind Post to ViewHolder, setting OnClickListener for the star button
        postViewHolder.setViewHolder(post, new View.OnClickListener() {
            @Override
            public void onClick(View starView) {
                // Need to write to both places the post is stored
                DatabaseReference globalPostRef = database.child("posts").child(postRef.getKey());
                DatabaseReference userPostRef = database.child("user-posts").child(post.uid).child(postRef.getKey());

                // Run two transactions
                onStarClicked(globalPostRef);
                onStarClicked(userPostRef);
            }
        });


        postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch PostDetailActivity
                Intent intent = new Intent(context, MessageDetailActivity.class);
                intent.putExtra(MessageDetailActivity.EXTRA_POST_KEY, postKey);
                context.startActivity(intent);
            }
        });
    }

    public String getUid() {

        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    private void onStarClicked(DatabaseReference postRef) {

        postRef.runTransaction(new Transaction.Handler() {

            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Post post = mutableData.getValue(Post.class);
                if (post == null) {
                    return Transaction.success(mutableData);
                }

                if (post.stars.containsKey(getUid())) {
                    // Unstar the post and remove self from stars
                    post.starCount = post.starCount - 1;
                    post.stars.remove(getUid());
                } else {
                    // Star the post and add self to stars
                    post.starCount = post.starCount + 1;
                    post.stars.put(getUid(), true);
                }

                // Set value and report transaction success
                mutableData.setValue(post);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d("Mylog", "postTransaction:onComplete:" + databaseError);
            }
        });
    }
}
