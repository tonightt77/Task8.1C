package com.example.itubeapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaylistViewHolder extends RecyclerView.ViewHolder {
    public TextView youtubeUrlTV;
    public PlaylistViewHolder(@NonNull View itemView) {
        super(itemView);
        youtubeUrlTV = itemView.findViewById(R.id.youtube_url);
    }
}
