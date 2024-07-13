package com.hire.dennisward;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GithubViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_view);

        // Get URLs from Intent
        Intent intent = getIntent();
        String githubUrl = intent.getStringExtra("github_url");
        final String videoUrl = intent.getStringExtra("video_url");

        // Initialize WebView
        WebView webViewGitHub = findViewById(R.id.webview_github);
        WebSettings webSettingsGitHub = webViewGitHub.getSettings();
        webSettingsGitHub.setJavaScriptEnabled(true);
        webViewGitHub.setWebViewClient(new WebViewClient());
        webViewGitHub.loadUrl(githubUrl);

        // Set up button to watch video
        Button buttonWatchVideo = findViewById(R.id.button_watch_video);
        buttonWatchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open video URL in WebViewActivity
                Intent intent = new Intent(GithubViewActivity.this, WebViewActivity.class);
                intent.putExtra("url", videoUrl);
                startActivity(intent);
            }
        });
    }
}