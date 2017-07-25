package com.sonny.example.firebaserealtimedatabase;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sonny.example.firebaserealtimedatabase.entity.Post;
import com.sonny.example.firebaserealtimedatabase.entity.User;

import java.util.HashMap;
import java.util.Map;

public class PostNewMessageActivity extends BaseActivity implements OnClickListener {

    private DatabaseReference database;
    private EditText titleEditText;
    private EditText messageEditText;
    private Button postNewMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_new_message);

        database = FirebaseDatabase.getInstance().getReference();

        titleEditText = (EditText) findViewById(R.id.postNewMessage_titleEditText);
        messageEditText = (EditText) findViewById(R.id.postNewMessage_messageEditText);

        postNewMessageButton = (Button) findViewById(R.id.postNewMessage_postNewMessageButton);
        postNewMessageButton.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void onPostNewMessageButtonClick(){
        final String title = titleEditText.getText().toString();
        final String body = messageEditText.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            titleEditText.setError("Required");
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            messageEditText.setError("Required");
            return;
        }

        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        final String userId = getUid();

        database.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        if (user == null) {
                            // User is null, error out
                            Log.e("Mylog", "User " + userId + " is unexpectedly null");
                            Toast.makeText(PostNewMessageActivity.this, "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user.username, title, body);
                            Toast.makeText(PostNewMessageActivity.this, "Post Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // Finish this Activity, back to the stream
//                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Mylog", "getUser:onCancelled", databaseError.toException());

                    }
                });

    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously

        // Get the unique key. The key is given from the realtime database.
        String key = database.child("posts").push().getKey();


        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        database.updateChildren(childUpdates);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.postNewMessage_postNewMessageButton:
                onPostNewMessageButtonClick();
                break;

            default:
                break;
        }
    }
}
