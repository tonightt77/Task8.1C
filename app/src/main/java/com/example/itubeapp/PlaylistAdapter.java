package com.example.itubeapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistViewHolder> {
    private LayoutInflater inflater;
    private List<Playlist> playlist;
    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new PlaylistViewHolder(inflater.inflate(R.layout.item_playlist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        holder.youtubeUrlTV.setText(playlist.get(position).getVideoUrl());

    }

    @Override
    public int getItemCount() {
        return playlist == null?0: playlist.size();
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }
}
