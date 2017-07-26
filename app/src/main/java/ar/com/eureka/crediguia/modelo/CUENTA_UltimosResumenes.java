package ar.com.eureka.crediguia.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.eureka.crediguia.utiles.Ventanas;


public class CUENTA_UltimosResumenes extends BDSQLiteHelper   {

    private String nombreTabla = this.getClass().getSimpleName();

	public CUENTA_UltimosResumenes(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase db) {
		super.onCreate(db);
		
		
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onUpgrade(db, oldVersion, newVersion); 
		
		
	}

	public List<JSONObject> darCampoJSONObject(String campo){
        List<JSONObject> resultado = new ArrayList();
        String where = "1=1 ";
        String sql = " SELECT "+campo
                + " FROM "+nombreTabla
                + " WHERE  "+where;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                 String campoR = c.getString(0);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(campoR);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                resultado.add(jsonObject);
             } while(c.moveToNext());
        }

        return resultado;
    }

	public String darInformacion(String campo){
		String resultado ="";
		String where = "1=1 ";
		String sql = " SELECT "+campo
				+ " FROM "+nombreTabla
				+ " WHERE  "+where;
		Cursor c = db.rawQuery(sql, null);
		if (c.moveToFirst()) {
			do {
				String campoR = c.getString(0);
				JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject(campoR);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				resultado += campoR;
			} while(c.moveToNext());
		}

		return resultado;
	}
	
	/*public List<HashMap> darInformacion(HashMap filtros){
		String where = "1=1 ";
		String sql = " SELECT * "
				+ " FROM CUENTA_Info as c"
				+ " WHERE  "+where;

		Cursor c = db.rawQuery(sql, null);
		List<HashMap> resultado = this.darResultados(c);
        //HashMap un = Conversiones.jsonObjectToHashMap(object);
		//Ventanas.debug("ACA EN DarInformacion "+resultado.size());
        return resultado;
	}*/
	
	
	public boolean add(HashMap<String,String> un){
		boolean ok = false;
		if(db != null){
            String consulta = " INSERT INTO "+nombreTabla+"(nroCuenta,resultado) " +
                    "VALUES ('"+un.get("nroCuenta")+"','"+un.get("resultado").toString()+"'"+");";
            System.out.println(consulta);
		   db.execSQL(consulta);
            System.out.println("Listo Guarde");
		   ok = true;
		}
		return ok;
	}
	
	
	public boolean update(HashMap<String,String> un){
		boolean ok = false;
		if(db != null){
		   db.execSQL(" UPDATE "+nombreTabla+" SET resultado ='"+un.get("resultado")+"'"+";");
		   ok = true;
		}
		return ok;
	}
	
	public boolean remove(HashMap<String,String> un){
		boolean ok = false;
		if(db != null){
		   db.execSQL(" DELETE FROM "+nombreTabla+"   "+";");
		   ok = true;
		}
		return ok;
	}

    public boolean cargarInformacionCompleta(HashMap arg0){
        super.cargarInformacionCompleta(arg0);

        System.out.println("cargarInformacionCompleta "+nombreTabla);
        this.limpiarInformacionCompleta(nombreTabla);

        if(db != null){
           if(!arg0.isEmpty()){
                Ventanas.debug("ACA EN FOR BBDD"+arg0.toString());
               System.out.println("cargarInformacionCompleta "+arg0.toString());
                this.add(arg0);
            }
            return true;
        }else {
            return false;
        }
    }
	
	public boolean cargarInformacionCompleta(List<HashMap> arg0){
		super.cargarInformacionCompleta(arg0);

        //System.out.println("cargarInformacionCompleta "+this.getClass().getSimpleName());
		this.limpiarInformacionCompleta(this.getClass().getSimpleName());
		
		if(db != null){
			for(int i=0; i < arg0.size(); i++){
				HashMap<String,String> un=arg0.get(i);
				Ventanas.debug("ACA EN FOR BBDD"+un.toString());
                this.add(un);
            }
            return true;
        }else {
        	return false;
        }
	}


}
