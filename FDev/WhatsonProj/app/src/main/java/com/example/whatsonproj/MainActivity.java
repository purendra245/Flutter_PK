package com.example.whatsonproj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webview;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView)findViewById(R.id.webview);
        init();
    }


    private void init(){

//        webview.invalidate();
//        webview.getSettings().setBuiltInZoomControls(true);
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webview.getSettings().setSupportMultipleWindows(true);
//        webview.getSettings().setDomStorageEnabled(true);
//        webview.getSettings().setUseWideViewPort(true);
//        webview.getSettings().setLoadWithOverviewMode(true);
//        webview.setScrollbarFadingEnabled(false);
//        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);


//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());

        webview.loadUrl("file:///android_asset/chat.html");
        webview.requestFocus();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
//        progressDialog.show();

//        webview.setWebViewClient(new WebViewClient() {
//
//            public void onPageFinished(WebView view, String url) {
//                try {
//                    progressDialog.dismiss();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        webview.setWebChromeClient(new WebChromeClient());
    }

}