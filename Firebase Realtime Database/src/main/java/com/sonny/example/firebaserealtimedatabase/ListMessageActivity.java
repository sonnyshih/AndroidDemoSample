package com.sonny.example.firebaserealtimedatabase;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.sonny.example.firebaserealtimedatabase.entity.Post;

public class ListMessageActivity extends BaseActivity{

    private RecyclerView recycler;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> adapter;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_message);

        database = FirebaseDatabase.getInstance().getReference();

        recycler = (RecyclerView) findViewById(R.id.messages_list);
        recycler.setHasFixedSize(true);
        // Set up Layout Manager, reverse layout
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recycler.setLayoutManager(linearLayoutManager);


        Query postsQuery = getQuery(database);

        adapter = new MessageAdapter(this, Post.class, R.layout.item_post,
                PostViewHolder.class, postsQuery);

        recycler.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public String getUid() {

        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("user-posts")
                .child(getUid());
    }
}
