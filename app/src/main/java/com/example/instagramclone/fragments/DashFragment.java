package com.example.instagramclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramclone.R;
import com.example.instagramclone.adapters.PostsAdapter;
import com.example.instagramclone.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashFragment extends Fragment {

    public final String TAG = "DashFragment";

    RecyclerView rvFeed;
    PostsAdapter adapter;

    private List<Post> feed;

    public DashFragment() {
        // required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_dash, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        rvFeed = view.findViewById(R.id.rvFeed);
        
        feed = new ArrayList<>(); // initalize as empty list
        adapter = new PostsAdapter(getContext(), feed); // create adapter
        rvFeed.setAdapter(adapter); // set adapter to rv
        rvFeed.setLayoutManager(new LinearLayoutManager(getContext()));// set layout manager on rv
        queryPosts();
    }

    private void queryPosts() {
        // specify class to query -- Post
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER); // include reference to user key
        // get all posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) { // error
                    Log.e(TAG, "Parse Exception while getting posts: " + e);
                    return;
                } // else success
                for (Post post : posts) {
                    Log.i(TAG, "Post by "+ post.getUser().getUsername()
                            + ": " + post.getDescription());
                }
                // add all posts to feed and notify the adapter
                feed.addAll(posts);
                Collections.reverse(feed);
                adapter.notifyDataSetChanged();
            }
        });
    }

}