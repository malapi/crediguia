package ar.com.eureka.crediguia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

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
    private  TextView mTextFechaCierre;
    private  TextView mTextDisponible;
    private  TextView mTextLimite;
    private  TextView mTextLimite_2;
    private TextView mTextDisponible_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logueado);

        mTextFechaCierre = (TextView) findViewById(R.id.contenido_fecha_pago);
        mTextDisponible = (TextView) findViewById(R.id.contenido_disponible);
        mTextDisponible_2 = (TextView) findViewById(R.id.label_disponible);
        mTextLimite = (TextView) findViewById(R.id.contenido_limites);
        mTextLimite_2 = (TextView) findViewById(R.id.label_limites);
        CUENTA_Info bbdd = new CUENTA_Info(this, ModelBBDD.nombreBD, null, ModelBBDD.version);
        List<JSONObject> info = bbdd.darCampoJSONObject("resultado");
        //System.out.println("Informacion "+info);
        if (info.size()>0){
          JSONObject obj =   info.get(0);
            String nombre = Conversiones.get(obj,"F_NombresYApellidos");
            this.setTitle(nombre);
            mTextFechaCierre.setText(Conversiones.get(obj,"UltimoCobro->FechaCancelacion"));
            mTextDisponible.setText("Disponible Total: "+Conversiones.get(obj,"DisponibleTotal"));
            mTextDisponible_2.setText("Disponible Dif: "+Conversiones.get(obj,"DisponibleDiferencial"));
            mTextLimite.setText("Limite Total: "+Conversiones.get(obj,"LimiteTotal"));
            mTextLimite_2.setText("Limite Dif: "+Conversiones.get(obj,"LimiteDiferencial"));

        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.usuario_logueado_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
