package com.baiheng.webviewbasicdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * WebView基本使用示例
 */
public class MainActivity extends AppCompatActivity {

    private Button mOpenWebViewBtn;
    private RelativeLayout mRelativeLayout;
    private WebView mWebView;
    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpenWebViewBtn = (Button) findViewById(R.id.open_webview);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.webview_layout);
        mWebView = (WebView) findViewById(R.id.webview);

        // 通过布局添加webView
//        mWebView = new WebView(this);
//        mWebView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        mRelativeLayout.addView(mWebView);

        initWebViewSet(mWebView);
        setWebView();   //设置WebViewClient和WebChromeClient
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
        mOpenWebViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUrl();
            }
        });
    }

    // 初始化设置WebView，可以设置webView的相关属性
    @SuppressLint({"SetJavaScriptEnabled","JavascriptInterface"})
    private void initWebViewSet(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.addJavascriptInterface(new JSBridge(),"android");

    }
    //设置webViewClient和WebChromeClient
    private void setWebView() {
        //根据需要重写相关方法
        mWebViewClient = new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        };
        // 根据需要重写相关方法
        mWebChromeClient = new WebChromeClient() {

        };
    }

    // 加载网页
    private void loadUrl() {
        // 加载url地址
        mWebView.loadUrl("https://blog.csdn.net/");

        //加载本地htm文件
        mWebView.loadUrl("file:////android_asset/web.html");

        //本地调用网页方法后返回
        testEvaluateJavascript(mWebView);
    }
    // 网页调用本地代码
    public class JSBridge{
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(),"通过Native传递的Toast" + message,Toast.LENGTH_LONG).show();
        }
    }
    // 本地调用网页方法后返回
    private void testEvaluateJavascript(WebView webView) {
        webView.evaluateJavascript("ok()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.i("nihao",s);
            }
        });
    }
}
