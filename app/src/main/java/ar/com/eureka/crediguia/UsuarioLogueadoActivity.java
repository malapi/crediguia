package ar.com.eureka.crediguia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.eureka.crediguia.http.AutorizacionesRestClient;
import ar.com.eureka.crediguia.http.CobrosRestClient;
import ar.com.eureka.crediguia.http.LoginRestClient;
import ar.com.eureka.crediguia.http.ResumenesRestClient;
import ar.com.eureka.crediguia.modelo.CUENTA_Info;
import ar.com.eureka.crediguia.modelo.CUENTA_UltimosResumenes;
import ar.com.eureka.crediguia.utiles.Conversiones;
import ar.com.eureka.crediguia.utiles.ModelBBDD;

public class UsuarioLogueadoActivity extends AppCompatActivity {

    private TextView mTextMessage;

    HashMap<String,String> parametro = new HashMap<String,String>();{
        parametro.put("nroCuenta","113519");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_ultimo_resumen:
                    /*File file = new File(Environment.getExternalStorageDirectory() +"/mypd‌​f.pdf");
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);*/




                    //WebView webviewer = new WebView(UsuarioLogueadoActivity.this);
                    //webviewer.getSettings().setJavaScriptEnabled(true);
                    //webviewer.getSettings().setPluginsEnabled(true);
                    //setContentView(webviewer);
                    //webviewer.loadUrl("https://drive.google.com/viewer?url=http://www.tra.org.bh/media/document/sample1.pdf");
                    //setContentView(webviewer);

                    //mTextMessage.setText(R.string.title_home);


                    HashMap[]  parametros =new HashMap[1];
                    parametros[0]=parametro;
                    new ResumenesRestClient(null, UsuarioLogueadoActivity.this).execute(parametros);

                    return true;
                case R.id.navigation_ultimos_pagos:
                    /*List<HashMap> lista = new ArrayList<>();
                    for(int i=0; i < 5; i++){
                        HashMap un = new HashMap();
                        un.put("id",i);
                        un.put("descripcion","Pago  "+i);
                        un.put("detalle","Detalle del Pago "+i);
                        lista.add(un);
                    }
                    intent = new Intent(UsuarioLogueadoActivity.this, ItemListActivity.class);
                    info = new Bundle();
                    info.putSerializable("lista", (Serializable) lista);
                    info.putString("titulo","Ultimos Pagos");
                    intent.putExtras(info);
                    startActivity(intent);
                    */
                    parametros =new HashMap[1];
                    parametros[0]=parametro;
                    new CobrosRestClient(null, UsuarioLogueadoActivity.this).execute(parametros);

                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_ultimas_compras:
                    /*lista = new ArrayList<>();
                    for(int i=0; i < 5; i++){
                        HashMap un = new HashMap();
                        un.put("id",i);
                        un.put("descripcion","Compra  "+i);
                        un.put("detalle","Detalle de Compra "+i);
                        lista.add(un);
                    }
                    intent = new Intent(UsuarioLogueadoActivity.this, ItemListActivity.class);
                    info = new Bundle();
                    info.putSerializable("lista", (Serializable) lista);
                    info.putString("titulo","Ultimas Compras");
                    intent.putExtras(info);
                    startActivity(intent);*/
                    parametros =new HashMap[1];
                    parametros[0]=parametro;
                    new AutorizacionesRestClient(null, UsuarioLogueadoActivity.this).execute(parametros);

                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_cupon_pagos:
                    Intent intent = new Intent(UsuarioLogueadoActivity.this, PdfViewerActivity.class);
                    Bundle info = new Bundle();
                    info.putString("titulo","Cupon de Pagos");
                    info.putString("url","http://josepjurado.com/wp-content/uploads/2015/02/C%C3%B3digo-de-barras-EAN128.png");
                    intent.putExtras(info);
                    //startActivity(intent);
                    parametros =new HashMap[1];
                    parametros[0]=parametro;
                    new ResumenesRestClient(null, UsuarioLogueadoActivity.this,WebActivity.class).execute(parametros);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    private WebView myWebView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logueado);
        getSupportActionBar().hide(); //Si hereda de  AppCompatActivity
        //getActionBar().hide(); //Si hereda de  Activity
        CUENTA_Info bbdd = new CUENTA_Info(this, ModelBBDD.nombreBD, null, ModelBBDD.version);
        List<JSONObject> info = bbdd.darCampoJSONObject("resultado");
        //System.out.println("Informacion "+info);
        myWebView = (WebView) findViewById(R.id.webviewlogin);
        handler = new Handler();
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new UsuarioLogueadoActivity.WebAppInterface(this), "Android");
        myWebView.loadUrl("file:///android_asset/crediguia/home.html");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.usuario_logueado_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
