package ar.com.eureka.crediguia.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ar.com.eureka.crediguia.utiles.ModelBBDD;


public class BDSQLiteHelper extends SQLiteOpenHelper {
	
	public SQLiteDatabase db;
	
	public BDSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		//this.close();
		this.db = this.getWritableDatabase();
		
	}

	public void onCreate(SQLiteDatabase db) {
		String[] sqlCreateArr = ModelBBDD.sqlCreate.split(";");
		if(sqlCreateArr.length>0){
			for(int i=0; i < sqlCreateArr.length; i++){
				String str = sqlCreateArr[i];
				if(!str.trim().equalsIgnoreCase("")){
					Log.v("ACA", str);
					db.execSQL(str);
				}
				
			}
		}
		//db.execSQL(ModelBBDD.sqlCreate);
		
	}

	public List<HashMap> darResultados(Cursor c){
		List<HashMap> resultado = new ArrayList();
		if(db != null)
        {
			if (c.moveToFirst()) {
        		String[] columnas = c.getColumnNames();
        		do {
        			HashMap<String,String> cc= new HashMap();
        			for(int i=0; i < columnas.length; i++){
        				cc.put(columnas[i], c.getString(i));
        			}
        			resultado.add(cc);
        		} while(c.moveToNext());
        	}
        	
        }
        return resultado;
		
	}
	
	
	public boolean cargarInformacionCompleta(List<HashMap> arg0){
		
		return true;
	}
	public boolean cargarInformacionCompleta(HashMap arg0){

		return true;
	}
	public boolean limpiarInformacionCompleta(String queTabla){
		String consulta ="DELETE FROM "+queTabla+";";
		if(db!=null){
			db.execSQL(consulta);
		}
		
		
		return true;
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Elimino las BBDD
		String[] sqlDropArr = ModelBBDD.sqlDrop.split(";");
		if(sqlDropArr.length>0){
			for(int i=0; i < sqlDropArr.length; i++){
				String str = sqlDropArr[i];
				if(!str.trim().equalsIgnoreCase("")){
					Log.v("ACA", str);
					db.execSQL(str);
				}
				
			}
		}

		
		
		//Creo las tablas de la BBDD.
		String[] sqlCreateArr = ModelBBDD.sqlCreate.split(";");
		if(sqlCreateArr.length>0){
			for(int i=0; i < sqlCreateArr.length; i++){
				String str = sqlCreateArr[i];
				if(!str.trim().equalsIgnoreCase("")){
					Log.v("ACA", str);
					db.execSQL(str);
				}
				
			}
		}
		
		//Cargo los valores por Defecto
		String[] sqlDefaultArr = ModelBBDD.sqlDefault.split(";");
		if(sqlDefaultArr.length>0){
			for(int i=0; i < sqlDefaultArr.length; i++){
				String str = sqlDefaultArr[i];
				if(!str.trim().equalsIgnoreCase("")){
					Log.v("ACA", str);
					db.execSQL(str);
				}
				
			}
		}
	}
	
	public void close(){
		if(db.isOpen())
			this.db.close();
	}
	

}
