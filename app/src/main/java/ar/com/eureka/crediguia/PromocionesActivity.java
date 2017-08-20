package ar.com.eureka.crediguia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class PromocionesActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.promociones_navigation_destacadas:
                    myWebView.loadUrl("file:///android_asset/crediguia/promociones.html");
                    return true;
                case R.id.promociones_navigation_cercanas:
                    Intent intent = new Intent(PromocionesActivity.this, PromocionesCercanasActivity.class);
                    startActivity(intent);

                    return true;

            }
            return false;
        }

    };
    private  WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);
        getSupportActionBar().hide(); //Si hereda de  AppCompatActivity
        //getActionBar().hide(); //Si hereda de  Activity

        myWebView = (WebView) findViewById(R.id.webView_promociones_tabs);
        WebSettings webSettings = myWebView.getSettings();
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        //myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //webView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("file:///android_asset/crediguia/promociones.html");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_promociones);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



}
