/**
 * Clase: Palabra.java
 *
 * Version: 1.0
 *
 * Fecha Creacion: 26/08/2014
 *
 * Fecha Modificacion:
 *
 * Autor: Carlos Tuiran
 *
 * Copyrigth: CECAR 
 */

package edu.CECAR.Logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public final class Palabra {

	private static Properties pArchivo = new Properties();
	private static String archivo = "palabras.properties";
	private static FileInputStream fisArchivo;


	public static void agregarPalabra(String palabra, int valor)  {

		try {
			fisArchivo=new FileInputStream(archivo);
			pArchivo.load(fisArchivo);
		} catch (FileNotFoundException e1) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			
			FileOutputStream fileOutputStream = new FileOutputStream(archivo);

			pArchivo.setProperty(palabra, String.valueOf(valor));

			pArchivo.store(fileOutputStream, null);

			fileOutputStream.close();
		} catch (IOException e) {

//			e.printStackTrace();

		} 



	}

	public static int buscarPalabra(String palabra) {

		String resultado = null;

		try {

			if (fisArchivo == null) {

				fisArchivo = new FileInputStream(archivo);
				pArchivo.load(fisArchivo);

			}

			resultado = pArchivo.getProperty(palabra.toLowerCase());

		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} 
		if(resultado!=null){
			return Integer.parseInt(resultado);
		}else{
			return 0;
		}
	}

	public static Properties listaPalabras() {

		Properties resultado = new Properties();

		try {

			if (fisArchivo == null) {

				fisArchivo = new FileInputStream(archivo);
				pArchivo.load(fisArchivo);

			}

			resultado = pArchivo;

		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} 

		return resultado;
	}

}
