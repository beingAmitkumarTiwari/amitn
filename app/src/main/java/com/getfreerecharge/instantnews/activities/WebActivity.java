package com.getfreerecharge.instantnews.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.getfreerecharge.instantnews.R;
import com.victor.loading.rotate.RotateLoading;

import java.util.concurrent.TimeUnit;


/**
 * Created by amit on 2/23/2017.
 */

public class WebActivity extends AppCompatActivity {

    RotateLoading rotateLoading;

    LinearLayout ll1, ll2;
    WebView mainWebView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);

        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);

        mainWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String urll = getIntent().getStringExtra("EXTRA_MESSAGE");
        mainWebView.loadUrl(urll);
        new MyTask().execute();
    }

    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ll1.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.GONE);
            rotateLoading.start();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ll1.setVisibility(View.GONE);
            ll2.setVisibility(View.VISIBLE);
            rotateLoading.stop();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
