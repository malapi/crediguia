package ar.com.eureka.crediguia.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import com.loopj.android.http.HttpGet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.eureka.crediguia.ItemListActivity;
import ar.com.eureka.crediguia.modelo.CUENTA_Autorizaciones;
import ar.com.eureka.crediguia.modelo.CUENTA_Cobros;
import ar.com.eureka.crediguia.utiles.Conversiones;
import ar.com.eureka.crediguia.utiles.ModelBBDD;
import ar.com.eureka.crediguia.utiles.RestFulWS;
import ar.com.eureka.crediguia.utiles.Ventanas;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by Malapi on 03/07/2017.
 */

public class CobrosRestClient extends AsyncTask<HashMap,Void,List<JSONObject>> {


    private ProgressDialog progressDialog;
    private Context context;
    private EditText editText;

    private Activity dondeir = null;
    private HashMap[] parametros;

    public String error="";
    public CobrosRestClient(EditText editText, Context context) {
        this.context = context;
        this.editText = editText;
    }

    public CobrosRestClient(EditText editText, Context context,Activity donde) {
        this.context = context;
        this.editText = editText;
        this.dondeir = donde;
    }

    /**
     * Metodo que se conecta al RESTFUL para obtener un resultado
     * */
    public List<JSONObject> getRestFul(HashMap[] arg0)
    {
        parametros = arg0;
        String operacion = RestFulWS.HTTP_RESTFUL+"CUENTA_Cobros/";
        HttpClient httpclient = new DefaultHttpClient();
        String parametros="";
        if(arg0.length>0){
            parametros = Conversiones.darParametrosPost(arg0);

        }
        HttpGet http = new HttpGet(operacion+parametros);
        Ventanas.debug("ACA 1");
        List<JSONObject> resultado=new ArrayList();
        try {
            HttpResponse response = httpclient.execute(http);

            String jsonResult = Conversiones.inputStreamToString(response.getEntity().getContent()).toString();
            //System.out.println("jsonResult "+jsonResult);
            JSONObject object = new JSONObject(jsonResult);
            //System.out.println("jsonResult object "+object.getString("RequestsStatusOK"));
            String status = object.getString("RequestsStatusOK");
            if( status.equals("true") )
            {
                //HashMap un = Conversiones.jsonObjectToHashMap(object);
                resultado.add(object);
                //Ventanas.debug("ACA 2"+resultado.size());
            }else{
                Ventanas.debug("Error "+object.getString("RequestsStatusObs"));
                System.out.println("jsonResult error "+object.getString("RequestsStatusObs"));
            }
        } catch (ClientProtocolException e) {
            Ventanas.debug("Error 2"+e.getMessage());
            this.error = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            Ventanas.debug("Error 3"+e.getMessage());
            this.error = e.getMessage();
            e.printStackTrace();
        } catch (JSONException e) {
            Ventanas.debug("Error 4"+e.getMessage());
            this.error = e.getMessage();
            e.printStackTrace();
        }
        return resultado;
    }


    /*public boolean guardarInformacion(List<HashMap> arg0){
        boolean termino =  false;
        System.out.println(" Guardo Info "+arg0);
        ConfiguracionSQLiteHelper bbddcc = new ConfiguracionSQLiteHelper(this.context, ModelBBDD.nombreBD, null, ModelBBDD.version);
        if(bbddcc.limpiarInformacionCompleta("configuracion")){
            bbddcc.insertValoresDefecto();
            termino = true;
        }

        ConsumoSQLiteHelper bbdd = new ConsumoSQLiteHelper(this.context, ModelBBDD.nombreBD, null, ModelBBDD.version);
        if(bbdd.cargarInformacionCompleta(arg0)){
            Ventanas.debug("ACA 4 Luego de la en la BBDD");
            termino = true;
        }

        ClienteSQLiteHelper bbddc = new ClienteSQLiteHelper(this.context, ModelBBDD.nombreBD, null, ModelBBDD.version);
        if(bbddc.cargarInformacionCompleta(arg0)){
            Ventanas.debug("ACA 4 Luego de la en la BBDD");
            termino = true;
        }
        return termino;
    }*/

    public boolean guardarInformacion(HashMap arg0){
        boolean termino =  false;
        System.out.println(" Guardo Info "+arg0);
        CUENTA_Cobros bbdd = new CUENTA_Cobros(this.context, ModelBBDD.nombreBD, null, ModelBBDD.version);
        if(bbdd.cargarInformacionCompleta(arg0)){
            Ventanas.debug("ACA 4 Luego de la en la BBDD");
            termino = true;
        }
        return termino;
    }

    @Override
    protected List<JSONObject> doInBackground(HashMap... arg0) {
        List<JSONObject> resultado = getRestFul(arg0);
        HashMap arg = new HashMap();
        arg.putAll(parametros[0]);
        arg.put("resultado",resultado.get(0).toString());
        boolean termino = guardarInformacion(arg);

        progressDialog.dismiss();
        return resultado;
    }




    /**
     * Antes de comenzar la tarea muestra el progressDialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Por favor espere", "Procesando...");
    }

    /**
     * Cuando se termina de ejecutar doInBackground se actualiza la interfaz
     * **/
    @Override
    protected void onPostExecute(List<JSONObject> resul) {

        if(this.editText != null){
            this.editText.setText(resul.toString());
        } else {
            Intent ir;
            if(this.dondeir != null){
                ir = new Intent(this.context,ItemListActivity.class);
            } else {
                ir = new Intent(this.context,ItemListActivity.class);
            }

            Bundle info = new Bundle();
            //CUENTA_Autorizaciones bbdd = new CUENTA_Autorizaciones(this.context, ModelBBDD.nombreBD, null, ModelBBDD.version);
            //List lista = bbdd.darCampoJSONObject("resultado");
            info.putString("bbdd","CUENTA_Cobros");
            info.putString("titulo","Ultimos Pagos");
            //ir.putExtra("lista", new Gson().toJson(resul.get(0)));
            //ir.putExtra("bbdd", bbdd); // sending our object
            ir.putExtras(info);
            this.context.startActivity(ir);

        }



    }


}//end:class