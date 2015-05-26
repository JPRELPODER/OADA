/**
 * Clase: Buscar.java
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

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

public final class Buscar {

	public static ArrayList<String> buscar(String directorio){
		ArrayList<String> resultado=new ArrayList<String>();

		String extensiones[]={"txt","dat"};

		FileNameExtensionFilter ficheros = new FileNameExtensionFilter("Mis Extensiones",extensiones);
		File dir=new File(directorio);
		File[] listFiles = dir.listFiles();

		if(listFiles!=null){
			for (int i=0;i<listFiles.length;i++){
				if(listFiles[i].isDirectory()){
					ArrayList<String> r=Buscar.buscar(listFiles[i].toPath().toString());
					if(r!=null){
						resultado.addAll(r);
					}
				}else{
					if(ficheros.accept(listFiles[i])){
						resultado.add(listFiles[i].toPath().toString());						
					}
				}
			}
		}
		return resultado;
	}
}
