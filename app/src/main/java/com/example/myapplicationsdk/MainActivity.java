package com.example.myapplicationsdk;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sfprivatesdk.PrivacySDK;
import com.example.sfprivatesdk.PrivacyConfig;
import com.example.sfprivatesdk.PrivacyFormConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrivacyConfig config = new PrivacyConfig("7213440552216101011","20250312181856A008","v1");
        PrivacySDK.initPrivacy(this,config);
        // PrivacyFormConfig formConfig = new PrivacyFormConfig("7213440552216101011","20250312180413A007");
        // PrivacySDK.initPrivacyForm(this, formConfig);
    }
}