package ar.com.eureka.crediguia.utiles;

import java.util.HashMap;

public class ModelBBDD {
	
	public static String nombreBD="DBCrediguia";
	public static int version=8;
	
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
			"CREATE TABLE CUENTA_UltimosResumenes ("
			+ "  id INTEGER PRIMARY KEY,"
			+ "  nroCuenta TEXT , "
			+ "  resultado TEXT "
			+ "); "+
			"CREATE TABLE INFO_ProximosCierres ("
			+ "  id INTEGER PRIMARY KEY,"
			+ "  nroCuenta TEXT , "
			+ "  resultado TEXT "
			+ "); "+
			"CREATE TABLE INFO_Login ("
			+ "  id INTEGER PRIMARY KEY,"
			+ "  nroCuenta TEXT , "
			+ "  nroDocumento TEXT, "
			+ "  password TEXT,"
            + "  resultado TEXT "
			+ "); "+
            "CREATE TABLE CUENTA_Cobros ("
            + "  id INTEGER PRIMARY KEY,"
            + "  nroCuenta TEXT , "
            + "  resultado TEXT "
            + "); ";


	
	public static String sqlDrop = "DROP TABLE IF EXISTS CUENTA_Info;"+
            "DROP TABLE IF EXISTS CUENTA_Cobros;"+
			"DROP TABLE IF EXISTS CUENTA_UltimosResumenes;"+
			"DROP TABLE IF EXISTS INFO_ProximosCierres;"+
            "DROP TABLE IF EXISTS INFO_Login;"+
			"DROP TABLE IF EXISTS CUENTA_Autorizaciones;";

	
	public static String sqlDefault = "INSERT INTO CUENTA_Info(nroCuenta,resultado) "
			+ "	VALUES(113519,'');";
	

}
