/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iamraphson.jusibe.core;

import com.google.common.base.Strings;
import com.iamraphson.jusibe.core.connection.HttpURLConnection;
import com.iamraphson.jusibe.core.exceptions.IsNullException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 *
 * @author Raphson
 */
public class Jusibe {
    
    /**
     * public key
     * @var String
     */
    private String publicKey;
    
    /**
     * Access Token
     * @var String
     */
    private String accessToken;
    
    /**
     * Instance of HttpURLConnection
     * @var object
     */
    private HttpURLConnection client = null;
    
    
    public Jusibe(String publicKey, String accessToken) throws IsNullException{
        if(Strings.isNullOrEmpty(publicKey))
            throw new IsNullException("The Public Key can not be null or empty. Please pass it to the constructor");
        
        if(Strings.isNullOrEmpty(accessToken))
            throw new IsNullException("The Access Token can not be null or empty. Please pass it to the constructor");
        
        
        this.publicKey = publicKey;
        this.accessToken = accessToken;
    }
    
    /**
     * Send SMS using the Jusibe API
     * @param payload
     * @return $this
     * @throws com.iamraphson.jusibe.core.exceptions.IsNullException
     * @throws java.net.MalformedURLException
     */
    public String sendSMS(Map<String, String> payload) 
            throws IsNullException, MalformedURLException, IOException{
        if (this.isNullOrEmpty(payload)) {
            throw new IsNullException("Message Payload can not be empty. Please fill the appropriate details");
        }
        
        client = new HttpURLConnection(this.publicKey, this.accessToken);
        return client.performPostRequest("/smsapi/send_sms", payload);
    }

    
    public boolean isNullOrEmpty( final Map< ?, ? > m ) {
        return m == null || m.isEmpty();
    }
    
}
