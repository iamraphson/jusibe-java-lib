/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicodelabs.jusibe.core;

import com.google.common.base.Strings;
import com.unicodelabs.jusibe.core.connection.JusibeClient;
import com.unicodelabs.jusibe.core.exceptions.IsNullException;
import com.unicodelabs.jusibe.core.utils.JusibeResponse;
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
     * Instance of JusibeClient
     * @var object
     */
    private JusibeClient client = null;
    
    
    public Jusibe(String publicKey, String accessToken) throws IsNullException, MalformedURLException{
        if(Strings.isNullOrEmpty(publicKey))
            throw new IsNullException("The Public Key can not be null or empty. Please pass it to the constructor");
        
        if(Strings.isNullOrEmpty(accessToken))
            throw new IsNullException("The Access Token can not be null or empty. Please pass it to the constructor");
        
        
        this.publicKey = publicKey;
        this.accessToken = accessToken;
        
        client = new JusibeClient(this.publicKey, this.accessToken);
    }
    
    
    /**
     * Send SMS using the Jusibe API
     * @param payload
     * @return 
     * @throws com.unicodelabs.jusibe.core.exceptions.IsNullException
     * @throws java.net.MalformedURLException
     */
    public JusibeResponse sendSMS(Map<String, String> payload) 
            throws IsNullException, MalformedURLException, IOException{
        if (this.isNullOrEmpty(payload)) {
            throw new IsNullException("Message Payload can not be empty. Please fill the appropriate details");
        }
        
        return client.performPostRequest("/smsapi/send_sms", payload);
    }
    
    
    /**
     * Check the available SMS credits left in your JUSIBE account
     * @return 
     * @throws java.io.IOException 
     */
    public JusibeResponse checkAvailableCredits() throws IOException{
        return client.performGetRequest("/smsapi/get_credits");
    }

    
    /**
     * Check the delivery status of a sent SMS
     * @param messageID
     * @return 
     * @throws com.unicodelabs.jusibe.core.exceptions.IsNullException
     * @throws java.io.IOException
     */
    public JusibeResponse checkDeliveryStatus(String messageID) throws IsNullException, IOException{
        if(Strings.isNullOrEmpty(messageID))
            throw new IsNullException("Message ID can not be empty.");
        
        return client.performGetRequest("/smsapi/delivery_status?message_id=" + messageID);
    }
    
    
    /**
     * check if map collection is empty...
     * @param m
     * @return 
     */
    public boolean isNullOrEmpty( final Map< ?, ? > m ) {
        return m == null || m.isEmpty();
    }
    
}
