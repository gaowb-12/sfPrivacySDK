package com.example.myapplicationsdk;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sfprivatesdk.PrivacySDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrivacySDK.initPrivacyForm(
        MainActivity.this,
        "http://192.168.0.104:10086/pages/privacyForm/index?code=20250312180413A007&userId=7213440552216101011"
        );
    }
}