/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicodelabs.jusibe.core;

import com.unicodelabs.jusibe.core.Jusibe;
import com.unicodelabs.jusibe.core.exceptions.IsNullException;
import com.unicodelabs.jusibe.core.utils.JusibeResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Raphson
 */
public class JusibeTest {
    
    @Test(expected = Exception.class)
    public void testNothingWasNotPassedToJusibeConstructor() throws Exception {
        new Jusibe(null, null);
    }
    
    @Test
    public void testGet() throws MalformedURLException , IsNullException, IOException {
        Jusibe client = new Jusibe(
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXX");
        
        JusibeResponse response = client.checkAvailableCredits();
        assertEquals(200, response.getResponseCode());
    }
    
    @Test
    public void testClassInstance() throws MalformedURLException , IsNullException {
        Jusibe client = new Jusibe(
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXX");
        
        assert(client instanceof Jusibe);
    }
    
    @Test
    public void testGetWith401Error() throws MalformedURLException , IsNullException, IOException {
        Jusibe client = new Jusibe(
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXXX");
        
        JusibeResponse response = client.checkAvailableCredits();
        assertEquals(401, response.getResponseCode());
    }
    
}
