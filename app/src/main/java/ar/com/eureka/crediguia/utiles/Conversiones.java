package ar.com.eureka.crediguia.utiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Conversiones {

	
	
	public static String getNotNull(Object arg0){
		String str="";
        System.out.println("Conversiones " + arg0.getClass().getName());
		if(arg0!=null && !arg0.toString().equalsIgnoreCase("null")
				){
			str = arg0.toString();
			
		}
		
		return str;
	}


	public static String get(JSONObject obj,String campo){
        String valor = "";
        try {
        if(campo.contains("->")){
            String[] scampo =campo.split("->");
            JSONObject objR = obj;
            for(int i=0; i < scampo.length; i++){
                String un = scampo[i];
                if(objR.has(un) && !objR.isNull(un) && objR.get(un).getClass().getName().toString().startsWith("org.json.JSONObject")){
                        objR = objR.getJSONObject(un);
                }else{
                    valor = objR.getString(un);
                }
            }
        } else {
            if(obj.has(campo) && !obj.isNull(campo)){
                    valor = obj.getString(campo);
            }
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return valor;

    }

	public static JSONArray getArray(JSONObject obj,String campo){
		JSONArray valor = new JSONArray();
		try {
			//System.out.println(" class "+obj.get(campo).getClass().getName().toString());
			if(campo.contains("->")){
				String[] scampo =campo.split("->");
				JSONObject objR = obj;
				for(int i=0; i < scampo.length; i++){
					String un = scampo[i];
					if(objR.has(un) && !objR.isNull(un) && objR.get(un).getClass().getName().toString().startsWith("org.json.JSONArray")){
						objR = objR.getJSONObject(un);
					}else{
						valor = objR.getJSONArray(un);
					}
				}
			} else {
				if(obj.has(campo) && !obj.isNull(campo) && obj.get(campo).getClass().getName().toString().startsWith("org.json.JSONArray")){
					valor = obj.getJSONArray(campo);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return valor;

	}
	private static String darParametrosPostDesdeHashMap(HashMap un){
		String parametros = "";
		Iterator it = un.keySet().iterator();
		while(it.hasNext()){
			String clave = it.next().toString();
			parametros +=clave+"/"+un.get(clave).toString();
		}
		return parametros;
	}
	
	public static String darParametrosPost(HashMap[] arg0){
		String parametros = "";
		
		if(arg0.length>0){
			for(int i=0; i < arg0.length; i++){
				HashMap un = arg0[i];
				if(un.get("esunalista")!=null){
					List<HashMap> lista = (List)un.get("esunalista");
					for(int j=0; j< lista.size(); j++){
						HashMap elUnAdentro = lista.get(j);
						parametros += darParametrosPostDesdeHashMap(elUnAdentro);

					}
					
				} else {
					parametros = darParametrosPostDesdeHashMap(un);
				}
				
				
				
				
			}
		}
		
		
		return parametros;
	}
	

	public static StringBuilder inputStreamToString(InputStream is)
	 {  
	  String line = "";
	  StringBuilder stringBuilder = new StringBuilder();
	  BufferedReader rd = new BufferedReader( new InputStreamReader(is) );  
	  try
	  {
	   while( (line = rd.readLine()) != null )
	   {
	    stringBuilder.append(line);
	   }
	  }
	  catch( IOException e)
	  {
	   e.printStackTrace(); 
	  }

	  return stringBuilder;
	 }

    public static HashMap jsonObjectToHashMap(JSONObject obj){

        HashMap un = new HashMap();
        try {


                Iterator it = obj.keys();

                while(it.hasNext()){

                    String key =it.next().toString();
					System.out.println("Conversiones " + key);
					System.out.println("Conversiones " + obj.get(key));

					System.out.println("Conversiones " + obj.get(key).getClass().getName().toString());
					if(obj.get(key) != null && !obj.get(key).toString().equalsIgnoreCase("null") && obj.get(key).getClass().getName().toString().startsWith("org.json.JSONObject")){
						un.put(key, jsonObjectToHashMap(obj.getJSONObject(key)));
					} else {
						un.put(key, Conversiones.getNotNull(obj.get(key)));
					}

                }

                //Ventanas.debug("ACA 1.1"+un.toString()+"\n");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return un;
    }

	public static List<HashMap> jsonArrayToList(JSONArray array){
		
		List<HashMap> resultado=new ArrayList();
		try {
			
		for (int i = 0; i < array.length(); i++) 
	    {
	        //idCliente, idClienteFicha,mes,anio,valorConsumo,fAlta,usAlta
	        JSONObject row;
				row = array.getJSONObject(i);
			
	        Iterator it = row.keys();
	        HashMap<String,String> un = new HashMap();
	        while(it.hasNext()){
	        	String key =it.next().toString();
	        	un.put(key, Conversiones.getNotNull(row.getString(key)));
	        }
	        
	        //Ventanas.debug("ACA 1.1"+un.toString()+"\n");
	        resultado.add(un);
	    }
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return resultado;	
	}
}
