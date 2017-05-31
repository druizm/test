package com.example.davidruiz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webView);

        final String url = "https://consultorasqa.somosbelcorp.com/Login/IngresoExterno?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJWZXJzaW9uIjoiTm9Tb3BvcnRhIiwiUGFpcyI6IlBFIiwiQ29kaWdvVXN1YXJpbyI6IjAxMTYxMTQ4NiIsIlBhZ2luYSI6IlBFRElETyJ9.wLH8NuUP605Sew0SXI9ZDoDLN_3vawa4XaSpFvPeRHM";

        startWebView(url);

    }

    private void startWebView(final String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webView.setWebChromeClient(new WebChromeClient());

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setFocusable(true);

        webView.setWebViewClient(new WebViewClient() {

            @Deprecated
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.w("WebActivity", "Error loading page " + description);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getBaseContext(), "TERMINO", Toast.LENGTH_SHORT).show();
            }
        });


        // Other webview options

        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null);
         */

        //Load url in webview
        webView.loadUrl(url);


    }

    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

}
