package ar.com.eureka.crediguia.webInterface;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import ar.com.eureka.crediguia.LoginActivity;
import ar.com.eureka.crediguia.PromocionesActivity;
import ar.com.eureka.crediguia.PromocionesCercanasActivity;
import ar.com.eureka.crediguia.http.AutorizacionesRestClient;
import ar.com.eureka.crediguia.http.CobrosRestClient;
import ar.com.eureka.crediguia.http.INFOProximosCierresRestClient;
import ar.com.eureka.crediguia.http.LoginRestClient;
import ar.com.eureka.crediguia.http.ResumenesRestClient;
import ar.com.eureka.crediguia.modelo.CUENTA_Autorizaciones;
import ar.com.eureka.crediguia.modelo.CUENTA_Cobros;
import ar.com.eureka.crediguia.modelo.CUENTA_Info;
import ar.com.eureka.crediguia.modelo.CUENTA_UltimosResumenes;
import ar.com.eureka.crediguia.modelo.INFO_Login;
import ar.com.eureka.crediguia.modelo.INFO_ProximosCierres;
import ar.com.eureka.crediguia.utiles.ModelBBDD;

/**
 * Created by malapi on 31/07/17.
 */

public class MainActivity {
    Context mContext;

    public static String nrocuenta="";
    private static String usuario="";
    private static String pass="";

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "28272137:malapi:113519", "22664811:113519:113519","24784660:110469:110469"
    };
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
        boolean verificarLogin = false;
        System.out.println("login");
        if(jsonData == null){
            //Hay que verificar si tenemos los datos de login cargados en la base de datos
            INFO_Login bbdd = new INFO_Login(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
            List<HashMap> resultado = bbdd.darInformacion(new HashMap());
            System.out.println("login "+resultado+" size " +resultado.size());
            if(resultado.size()<=0){
                //showToast("No existen datos de Session Guardados");
                final String callbackFunction = "javascript:siguiente('#login')";
                ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(callbackFunction);
            } else {
                HashMap info = resultado.get(0);
                nrocuenta =info.get("nroCuenta").toString();
                usuario = info.get("nroDocumento").toString();
                pass = info.get("password").toString();
                verificarLogin = true;
            }
        } else {

            try {
                JSONObject data = new JSONObject(jsonData); //Convert from string to object, can also use JSONArray
                System.out.println("login Aca +"+data.toString());
                usuario = data.getString("txtcorreo");
                pass = data.getString("txtpass");
                verificarLogin = true;
            } catch (JSONException e) {
                e.printStackTrace();
            }


                //String nroCuenta ="";
                /*for (String credential : DUMMY_CREDENTIALS) {
                    String[] pieces = credential.split(":");
                    if (pieces[0].equals(data.getString("txtcorreo"))) {
                        // Account exists, return true if the password matches.
                        if(pieces[1].equals(data.getString("txtpass"))){
                            nrocuenta =pieces[2];
                            INFO_Login bbdd = new INFO_Login(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
                            HashMap info = new HashMap();
                            info.put("nroCuenta",pieces[2]);
                            info.put("usuario",pieces[0]);
                            info.put("pass",pieces[1]);
                            info.put("resultado","{\"RequestsStatusOK\":true}");
                            bbdd.cargarInformacionCompleta(info);
                            break;
                        }
                    }

                } */
        }
        /*if(nrocuenta.equalsIgnoreCase("")){
            this.showToast("Error de Uusuario o Contraseña. Vuelva a Intentarlo");
        } else {
            HashMap[] parametros =new HashMap[1];
            //nroDocumento/24784660/password/110469
            HashMap<String,String> parametro = new HashMap<String,String>();
            parametro.put("nroCuenta",nrocuenta);
            parametros[0]=parametro;
            new LoginRestClient(callback, mContext,this).execute(parametros);
        }*/
            if(verificarLogin){
                HashMap[] parametros =new HashMap[1];
                //nroDocumento/24784660/password/110469
                HashMap<String,String> parametro = new HashMap<String,String>();
                parametro.put("nroDocumento",usuario);
                parametro.put("password",pass);
                parametros[0]=parametro;
                new LoginRestClient(callback, mContext,this).execute(parametros);
            }

    }

    @JavascriptInterface
    public void cerrarSession(String callback){
        INFO_Login bbdd = new INFO_Login(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        bbdd.limpiarInformacionCompleta();
        nrocuenta = "";
        final String callbackFunction = "javascript:siguiente('#login')";
        ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(callbackFunction);

    }

    @JavascriptInterface
    public void buscarConsumo(String callback){
        HashMap[] parametros =new HashMap[1];
        HashMap<String,String> parametro = new HashMap<String,String>();
        parametro.put("nroCuenta",nrocuenta);
        parametros[0]=parametro;
        new AutorizacionesRestClient(callback, mContext,this).execute(parametros);

        }

    @JavascriptInterface
    public void buscarCierres(String callback){
        HashMap[] parametros =new HashMap[1];
        HashMap<String,String> parametro = new HashMap<String,String>();
        //parametro.put("nroCuenta",nrocuenta);
        parametros[0]=parametro;
        new INFOProximosCierresRestClient(callback, mContext,this).execute(parametros);

    }

    @JavascriptInterface
    public void buscarResumen(String callback){
        HashMap[] parametros =new HashMap[1];
        HashMap<String,String> parametro = new HashMap<String,String>();
        parametro.put("nroCuenta",nrocuenta);
        parametros[0]=parametro;
        new ResumenesRestClient(callback, mContext,this).execute(parametros);

    }

    @JavascriptInterface
    public void buscarPagos(String callback){
        HashMap[] parametros =new HashMap[1];
        HashMap<String,String> parametro = new HashMap<String,String>();
        parametro.put("nroCuenta",nrocuenta);
        parametros[0]=parametro;
        new CobrosRestClient(callback, mContext,this).execute(parametros);

    }

    @JavascriptInterface
    public void mostrarConsumo(String callback){
        INFO_Login bbdd = new INFO_Login(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        final String infoCuenta = bbdd.darInformacion("resultado");
        CUENTA_Autorizaciones bbdd2 = new CUENTA_Autorizaciones(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        final String infoConsumo = bbdd2.darInformacion("resultado");
        INFO_ProximosCierres bbdd3 = new INFO_ProximosCierres(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        final String infoCierres = bbdd3.darInformacion("resultado");
        CUENTA_UltimosResumenes bbdd4 = new CUENTA_UltimosResumenes(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        final String infoResumen = bbdd4.darInformacion("resultado");

        final String callbackFunction = "javascript:" + callback + "('" + infoCuenta + "','"+infoConsumo+"','"+infoCierres+"','"+infoResumen+"')";
        ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(callbackFunction);
    }
    @JavascriptInterface
    public void mostrarPagos(String callback){
        CUENTA_Cobros bbdd = new CUENTA_Cobros(mContext, ModelBBDD.nombreBD, null, ModelBBDD.version);
        final String infoPagos = bbdd.darInformacion("resultado");

        final String callbackFunction = "javascript:" + callback + "('" + infoPagos + "')";
        ((ar.com.eureka.crediguia.MainActivity)mContext).loadURL(callbackFunction);
    }

    @JavascriptInterface
    public void mostrarPromociones(){
        Intent intent = new Intent(mContext, PromocionesCercanasActivity.class);
        mContext.startActivity(intent);
    }



}

