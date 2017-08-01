package ar.com.eureka.crediguia;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import ar.com.eureka.crediguia.modelo.CUENTA_UltimosResumenes;
import ar.com.eureka.crediguia.utiles.ModelBBDD;

public class WebActivity extends AppCompatActivity {

    private WebView myWebView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getSupportActionBar().hide(); //Si hereda de  AppCompatActivity
        //getActionBar().hide(); //Si hereda de  Activity
        myWebView = (WebView) findViewById(R.id.webview);
        handler = new Handler();
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        myWebView.loadUrl("file:///android_asset/crediguia/home.html");
    }

    private void loadURL(final String in){
        handler.post(new Runnable() {
            public void run() {
                myWebView.loadUrl(in);
            }
        });
    }


    class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void loadPage(String in){
            final String url = "file:///android_asset/crediguia/" + in;
            loadURL(url);
        }
        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void getJSONTData(String jsonData) {
            try {
                JSONObject data = new JSONObject(jsonData); //Convert from string to object, can also use JSONArray
                System.out.println("Aca +"+data.toString());
            } catch (Exception ex) {}
        }

        @JavascriptInterface
        public void getResumenes(String callback){
            CUENTA_UltimosResumenes bbdd = new CUENTA_UltimosResumenes(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
            final String json = bbdd.darInformacion("resultado");
            final String callbackFunction = "javascript:" + callback + "('" + json + "')";
            loadURL(callbackFunction);
        }

    }
}
