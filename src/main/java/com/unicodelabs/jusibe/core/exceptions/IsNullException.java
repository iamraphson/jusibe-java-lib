/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicodelabs.jusibe.core.exceptions;

/**
 *
 * @author Raphson
 */
public class IsNullException extends Exception {
    
    public IsNullException(){}
    
    public IsNullException(String message){
        super(message);
    }
    
    public IsNullException(Throwable cause){
        super(cause);
    }
        
    public IsNullException(String message, Throwable cause){
        super(message, cause);
    }
        
    public IsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
