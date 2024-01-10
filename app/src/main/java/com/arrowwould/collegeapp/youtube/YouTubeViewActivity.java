package com.arrowwould.collegeapp.youtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arrowwould.collegeapp.R;

public class YouTubeViewActivity extends AppCompatActivity {

    private static final String URL_TO_LOAD = "https://www.youtube.com/@arrowwouldcoders";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_view);

        webView = findViewById(R.id.webview);
        initializeWebView();
        loadWebPage(URL_TO_LOAD);
    }

    private void initializeWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript
        webSettings.setDomStorageEnabled(true); // Enable DOM Storage
        webSettings.setUseWideViewPort(true); // Enable support for viewport meta tag
        webSettings.setLoadWithOverviewMode(true); // Zoom out if the content width exceeds the viewport width
        webSettings.setBuiltInZoomControls(true); // Enable pinch-to-zoom
        webSettings.setDisplayZoomControls(false); // Hide the zoom controls

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY); // Overlay the scrollbar
        webView.setScrollbarFadingEnabled(false); // Keep the scrollbar always visible
    }

    private void loadWebPage(@NonNull String url) {
        webView.loadUrl(url);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause(); // Pause the WebView to save resources when the activity is paused
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume(); // Resume the WebView when the activity is resumed
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.clearCache(true); // Clear the WebView cache when the activity is destroyed
        }
    }
}
