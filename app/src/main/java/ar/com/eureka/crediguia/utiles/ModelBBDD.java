package ar.com.eureka.crediguia.utiles;

import java.util.HashMap;

public class ModelBBDD {
	
	public static String nombreBD="DBCrediguia";
	public static int version=3;
	
	public static HashMap<String,String>user;
	
	public static String sqlCreate ="CREATE TABLE CUENTA_Info ("
    		+ "  id INTEGER PRIMARY KEY,"
    		+ "  nroCuenta TEXT , "
    		+ "  resultado TEXT "
    		+ "); " +
			"CREATE TABLE CUENTA_Autorizaciones ("
			+ "  id INTEGER PRIMARY KEY,"
			+ "  nroCuenta TEXT , "
			+ "  resultado TEXT "
			+ "); "+
            "CREATE TABLE CUENTA_Cobros ("
            + "  id INTEGER PRIMARY KEY,"
            + "  nroCuenta TEXT , "
            + "  resultado TEXT "
            + "); ";

	
	public static String sqlDrop = "DROP TABLE IF EXISTS CUENTA_Info;"+
            "DROP TABLE IF EXISTS CUENTA_Cobros;"+
			"DROP TABLE IF EXISTS CUENTA_Autorizaciones;";

	
	public static String sqlDefault = "INSERT INTO CUENTA_Info(nroCuenta,resultado) "
			+ "	VALUES(113519,'');";
	

}
