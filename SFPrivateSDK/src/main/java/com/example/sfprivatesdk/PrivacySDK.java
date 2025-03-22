package com.example.sfprivatesdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class PrivacySDK extends AppCompatActivity {
    private static Context context;
    private WebView webView;
    private static String comurl;

    public PrivacySDK(){}
    // 初始化隐私政策
    public static void initPrivacy(Context context_, PrivacyConfig config){
        String search = "?code=" + config.code + "&userId=" + config.userId + "&version=" + config.version;
        comurl = "http://beta-h5.shufeng.cn/pages/privacy/index" + search;
        context = context_;
        PrivacySDK.init(context);
    }
    // 初始化提交表单
    public static void initPrivacyForm(Context context_, PrivacyFormConfig config) {
        String search = "?code=" + config.code + "&userId=" + config.userId;
        comurl = "http://beta-h5.shufeng.cn/pages/privacyForm/index" + search;
        context = context_;
        PrivacySDK.init(context);
    }
    // 初始化activity
    private static void init(Context context) {
        Intent intent = new Intent(context, PrivacySDK.class);
        if (!(context instanceof Activity)) {
            // 如果不是 Activity 上下文，需要添加 FLAG_ACTIVITY_NEW_TASK 标志
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
    // 生命周期
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sf_sdk_activity_main);

        webView = findViewById(R.id.sf_sdk_webview);

        // 设置 WebView 的布局参数
        ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
        // 设置屏幕高度
        layoutParams.height = (int) (PrivacySDK.getScreenHeight(context) * 0.8);
        webView.setLayoutParams(layoutParams);

        // 启用 JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 设置js交互
        webView.addJavascriptInterface(new JsBridge(this), "jsBridge");

        // 设置 WebViewClient，使页面在 WebView 内打开
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);

        webView.loadUrl(comurl);
    }
    // 物理返回按键控制
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 不执行任何操作，直接消耗事件
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 获取屏幕高度
    private static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
    private void clearWebview(){
        if (webView != null) {
            // 在主线程安全销毁
            runOnUiThread(() -> {
                webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                webView.clearHistory();
                ((ViewGroup) webView.getParent()).removeView(webView);
                webView.destroy();
                webView = null;
            });
        }
        finish();
    }
    // JavaScript接口类
    private class JsBridge {
        private Context context;
        public JsBridge(Context context) {
            this.context = context;
        }
        @JavascriptInterface
        public void postMessage(String message) {
            // 根据消息类型处理不同逻辑
            switch (message){
                case "close": // 关闭弹框
                    clearWebview();
                    break;
                case "reject": // 拒绝隐私政策
                    break;
                case "success": // 同意隐私政策
                    clearWebview();
                    break;
            }
        }
        @JavascriptInterface
        public void postMessage(String message, String json) throws JSONException {
            JSONObject jsonObject = new JSONObject(json);
            // String name = jsonObject.getString("name");
            // int age = jsonObject.getInt("age");
            // 根据消息类型处理不同逻辑
        }
    }
}