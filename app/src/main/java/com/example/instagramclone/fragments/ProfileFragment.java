package com.example.instagramclone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.instagramclone.LoginActivity;
import com.example.instagramclone.MainActivity;
import com.example.instagramclone.R;
import com.parse.ParseUser;

public class ProfileFragment extends Fragment {

    Button btnLogout;

    public ProfileFragment() {
        // required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_profile, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        btnLogout = view.findViewById(R.id.btnlogOut);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // log the user out
                ParseUser.logOut();
                // go to login screen
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}