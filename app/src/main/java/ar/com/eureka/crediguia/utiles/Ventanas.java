package ar.com.eureka.crediguia.utiles;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Ventanas {

	
	public static void debug(String contenido){
		String path = Environment.getExternalStorageDirectory().getPath();
		File file = new File(path, "debug.txt");
		
        //OutputStreamWriter osw=null;
		try {
			
			FileWriter fileWriter= new FileWriter(file,true);			
			//osw = new OutputStreamWriter(new FileOutputStream(file) );
			//osw = new OutputStreamWriter(fileWriter);
			
		
			fileWriter.write(contenido);
			fileWriter.flush();
			fileWriter.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
