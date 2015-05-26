/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.Logica;

import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JJ Arroyo
 */
public class PalabraTest {
    
    public PalabraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of agregarPalabra method, of class Palabra.
     */
    @Test
    public void testAgregarPalabra() {
        System.out.println("agregarPalabra");
        String palabra = "";
        int valor = 0;
        Palabra.agregarPalabra(palabra, valor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPalabra method, of class Palabra.
     */
    @Test
    public void testBuscarPalabra() {
        System.out.println("buscarPalabra");
        String palabra = "";
        int expResult = 0;
        int result = Palabra.buscarPalabra(palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaPalabras method, of class Palabra.
     */
    @Test
    public void testListaPalabras() {
        System.out.println("listaPalabras");
        Properties expResult = null;
        Properties result = Palabra.listaPalabras();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
