/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.Logica;

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
public class AtributoTest {
    
    public AtributoTest() {
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
     * Test of anio method, of class Atributo.
     */
    @Test
    public void testAnio() {
        System.out.println("anio");
        String ubicacion = "";
        String expResult = "";
        String result = Atributo.anio(ubicacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mes method, of class Atributo.
     */
    @Test
    public void testMes() {
        System.out.println("mes");
        String ubicacion = "";
        String expResult = "";
        String result = Atributo.mes(ubicacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of autor method, of class Atributo.
     */
    @Test
    public void testAutor() {
        System.out.println("autor");
        String ubicacion = "";
        String expResult = "";
        String result = Atributo.autor(ubicacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
