package ar.com.eureka.crediguia.webInterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import ar.com.eureka.crediguia.LoginActivity;
import ar.com.eureka.crediguia.http.LoginRestClient;
import ar.com.eureka.crediguia.modelo.CUENTA_Info;
import ar.com.eureka.crediguia.modelo.CUENTA_UltimosResumenes;
import ar.com.eureka.crediguia.utiles.ModelBBDD;

/**
 * Created by malapi on 31/07/17.
 */

public class MainActivity {
    Context mContext;

    /** Instantiate the interface and set the context */
    public MainActivity(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void loadPage(String in){
        final String url = "file:///android_asset/crediguia/" + in;
        ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(url);
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
    public void login(String callback,String jsonData){
        //CUENTA_UltimosResumenes bbdd = new CUENTA_UltimosResumenes(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        //final String json = bbdd.darInformacion("resultado");
        //final String callbackFunction = "javascript:" + callback + "('" + json + "')";
        System.out.println("login");
        try {
            JSONObject data = new JSONObject(jsonData); //Convert from string to object, can also use JSONArray
            System.out.println("login Aca +"+data.toString());

            HashMap[] parametros =new HashMap[1];
            HashMap<String,String> parametro = new HashMap<String,String>();
            parametro.put("nroCuenta","113519");
            parametros[0]=parametro;
            new LoginRestClient(callback, mContext,this).execute(parametros);

        } catch (Exception ex) {

        }

       // ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(callback);
    }

    @JavascriptInterface
    public void loginVolver(String callback){
        CUENTA_Info bbdd = new CUENTA_Info(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        final String json = bbdd.darInformacion("resultado");
        final String callbackFunction = "javascript:" + callback + "('" + json + "')";
        ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(callbackFunction);
    }
    @JavascriptInterface
    public void getResumenes(String callback){
        CUENTA_UltimosResumenes bbdd = new CUENTA_UltimosResumenes(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        final String json = bbdd.darInformacion("resultado");
        final String callbackFunction = "javascript:" + callback + "('" + json + "')";
        ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(callbackFunction);
    }

}

