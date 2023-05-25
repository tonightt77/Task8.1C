package com.example.itubeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare UI components
    private EditText videoUrlInput;
    private Button playButton;
    private Button addToPlaylistButton;
    private Button myPlaylistButton;

    // Declare database tables
    private UserTB userTB;
    private PlaylistTB playlistTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        videoUrlInput = findViewById(R.id.youtube_url);
        playButton = findViewById(R.id.play);
        addToPlaylistButton = findViewById(R.id.add_to_playlist);
        myPlaylistButton = findViewById(R.id.my_playlist);

        // Initialize database tables
        userTB = new UserTB(this);
        playlistTB = new PlaylistTB(this);
        // Open the playlist database
        playlistTB.open();

        // Set a click listener for the play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = videoUrlInput.getText().toString();
                // If the videoUrl is not empty, send it to the PlayerActivity to play the video
                if (!videoUrl.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                    intent.putExtra("videoUrl", videoUrl);
                    startActivity(intent);
                } else {
                    // If the videoUrl is empty, display a toast message
                    Toast.makeText(MainActivity.this, "Please input a valid URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set a click listener for the addToPlaylist button
        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = videoUrlInput.getText().toString();
                // If the videoUrl is not empty, add it to the playlist
                if (!videoUrl.isEmpty()) {
                    Playlist playlist = new Playlist();
                    // Get the username from SharedPreferences
                    SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    String username = prefs.getString("username", "");
                    playlist.setUsername(MyApplication.user.getUsername());
                    playlist.setVideoUrl(videoUrl);
                    // Insert the playlist into the database
                    playlistTB.insertIntoPlaylist(playlist);
                    Toast.makeText(MainActivity.this, "Video added to playlist", Toast.LENGTH_SHORT).show();
                } else {
                    // If the videoUrl is empty, display a toast message
                    Toast.makeText(MainActivity.this, "Please input a valid URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set a click listener for the myPlaylist button
        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the PlaylistActivity when the button is clicked
                Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);
                startActivity(intent);
            }
        });
    }
}
