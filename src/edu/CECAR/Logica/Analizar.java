/**
 * Clase: Analizar.java
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

import java.util.StringTokenizer;

public final class Analizar {

	public static int anaizar(String oracion) {

		int resultado = 0;


		//Se separan todas palabras de la oraci�n
		StringTokenizer stringTokenizer = new StringTokenizer(oracion.replaceAll("[.|,|;|:|?|�|�|!|(|)]", ""));

		while(stringTokenizer.hasMoreTokens()) {

			//Se obtiene cada palabra y se busca su traducci�n
			String palabraEspanol = stringTokenizer.nextToken(); 
			int palabraIngles = Palabra.buscarPalabra(palabraEspanol.toLowerCase());

			//Se remplaza cada palabra por su traducci�n si existe
			resultado = resultado+palabraIngles;

		}


		return resultado;


	}
}
