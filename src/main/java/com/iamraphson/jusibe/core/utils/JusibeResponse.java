/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iamraphson.jusibe.core.utils;

/**
 *
 * @author Raphson
 */
public class JusibeResponse {
    
    /**
     * Response Code
     * @var int
     */
    private int responseCode;
    
    
    /**
     * Response Message
     * @var String
     */
    private String responseMessage;

    public JusibeResponse() {
    }

    
    public JusibeResponse(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
    
    /**
     * Return Response Code 
     * @return 
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Set Response Code 
     * @param responseCode 
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Return Response Message 
     * @return 
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Set Response Message 
     * @param responseMessage
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
    /**
     * Convert JusibeResponse to String format 
     * @return 
     */
    public String toString() {
        return "Code: " + responseCode + " . message: " + responseMessage;
    }
    
}
