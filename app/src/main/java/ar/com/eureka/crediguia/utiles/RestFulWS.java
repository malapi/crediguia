package ar.com.eureka.crediguia.utiles;

import java.util.HashMap;

public class RestFulWS {
	/**
	 * La url del servidor.
	 */
	//public static  String HTTP_RESTFUL="http://192.168.0.34/www/euh2ows/ws/";
	//public static  String HTTP_RESTFUL="http://192.168.1.107/www/euh2ows/ws/";
	//public static  String HTTP_RESTFUL="http://192.168.1.112/www/euh2ows/ws/";
	

	public static  String HTTP_RESTFUL="http://usuarios.crediguia.com.ar:31561/AutoC.svc/";
	public static HashMap<String,String> HTTP_RESTFULL = createMap();

	private static HashMap<String, String> createMap()
	{
		HashMap<String,String> HTTP_RESTFULL = new HashMap<String,String>();
		HTTP_RESTFULL.put("AutoC", "http://usuarios.crediguia.com.ar:31561/AutoC.svc/");
		HTTP_RESTFULL.put("APP", "http://usuarios.crediguia.com.ar:31561/APP.svc/");
		return HTTP_RESTFULL;
	}
	
	
}
