package com.example.itubeapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        String videoUrl = getIntent().getStringExtra("videoUrl");
        // Extract the video ID from the videoUrl. Assuming the videoUrl is of the format "https://www.youtube.com/watch?v=VIDEO_ID"
        String videoId = videoUrl.split("v=")[1];
        String html = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe>";
        webView.loadData(html, "text/html", "UTF-8");
    }


}
