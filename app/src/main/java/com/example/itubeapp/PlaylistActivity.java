package com.example.itubeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playlist);
        RecyclerView recyclerView = findViewById(R.id.playlistView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlaylistAdapter playlistAdapter = new PlaylistAdapter();
        recyclerView.setAdapter(playlistAdapter);

        PlaylistTB playlistTB = new PlaylistTB(this);
        playlistTB.open();
        List<Playlist> playlists = playlistTB.selectByUser(MyApplication.user.getUsername());

        if (playlists.isEmpty()) {
            TextView emptyTextView = findViewById(R.id.emptyTextView);
            emptyTextView.setText("Empty list");
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            playlistAdapter.setPlaylist(playlists);
            playlistAdapter.notifyDataSetChanged();
        }
    }
}

