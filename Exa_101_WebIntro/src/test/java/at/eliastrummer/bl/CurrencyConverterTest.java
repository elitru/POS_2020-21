/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.bl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author root
 */
public class CurrencyConverterTest {
    
    public CurrencyConverterTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of convert method, of class CurrencyConverter.
     */
    @org.junit.jupiter.api.Test
    public void testConvert() {
        System.out.println("convert");
        float from = 1F;
        String toCurrency = "Rupien";
        float expResult = 0.011F * from;
        float result = CurrencyConverter.convert(from, toCurrency);
        assertEquals(expResult, result, 0.0);
    }
    
    @org.junit.jupiter.api.Test
    public void testInvalidCurrency() {
        System.out.println("wrong currency");
        float from = 1F;
        String toCurrency = "Test";
        float expResult = 0;
        try{
            float result = CurrencyConverter.convert(from, toCurrency);
            fail("no excpetion thrown");
        }catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid currency");
        }
    }
    
}
