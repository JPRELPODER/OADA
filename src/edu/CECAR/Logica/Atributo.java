/**
 * Clase: Organizar.java
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public final class Atributo {


	public static String anio(String ubicacion){

		Path path = Paths.get(ubicacion);
		String anio="";
		BasicFileAttributes attributes = null;
		try {
			attributes = Files.readAttributes(path, BasicFileAttributes.class);
			anio=attributes.creationTime().toString().split("-")[0];
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return anio;

	}

	public static String mes(String ubicacion){

		Path path = Paths.get(ubicacion);
		String mes="";
		BasicFileAttributes attributes = null;
		try {
			attributes = Files.readAttributes(path, BasicFileAttributes.class);
			mes=attributes.creationTime().toString().split("-")[1];
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return mes;

	}


	public static String autor(String ubicacion){

		Path path = Paths.get(ubicacion);
		String autor="";
		try {
			autor=Files.getOwner(path).toString();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autor;

	}
}
