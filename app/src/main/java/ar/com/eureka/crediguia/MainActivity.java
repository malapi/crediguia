package ar.com.eureka.crediguia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private WebView myWebView;
    private Handler handler;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    loadURL("crediguia.html");
                    //Intent intent = new Intent(MainActivity.this, TabsActivity.class);
                    //Bundle info = new Bundle();
                    //intent.putExtras(info);
                    //startActivity(intent);
                    //
                    return true;
                case R.id.navigation_dashboard:
                    //myWebView.loadUrl("file:///android_asset/crediguia/home.html");
                    loadURL("home.html");
                    //intent = new Intent(MainActivity.this, LoginActivity.class);
                    //startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    //myWebView.loadUrl("file:///android_asset/crediguia/promociones.html");
                    loadURL("promociones.html");
                    //Intent intent = new Intent(MainActivity.this, PromocionesActivity.class);
                    //startActivity(intent);

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //Si hereda de  AppCompatActivity
        //getActionBar().hide(); //Si hereda de  Activity

        myWebView = (WebView) findViewById(R.id.webView_principal_tabs);
        handler = new Handler();
        myWebView.setInitialScale(80);
        //myWebView.setScrollContainer(true);
        //myWebView.bringToFront();
        //myWebView.setScrollbarFadingEnabled(true);
        //myWebView.setVerticalScrollBarEnabled(true);
        myWebView.setHorizontalScrollBarEnabled(true);
        //myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setUseWideViewPort(true);
        //myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.getSettings().setJavaScriptEnabled(true);





        //myWebView.setHorizontalScrollBarEnabled(true);

        myWebView.addJavascriptInterface(new ar.com.eureka.crediguia.webInterface.MainActivity(this), "MainActivity");
        //this.loadURL("splash.html");
        this.loadURL("home.html");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void loadURL(final String in){
        handler.post(new Runnable() {
            public void run() {
                if(in.endsWith("html")){
                    myWebView.loadUrl("file:///android_asset/crediguia/"+in);
                } else {
                    myWebView.loadUrl(in);
                }

            }
        });
    }

}
