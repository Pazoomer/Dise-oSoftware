/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.exceptions;

/**
 *
 * @author luiis
 */
public class ValidacionException extends Exception {

    public ValidacionException() {
    }

    public ValidacionException(String message) {
        super(message);
    }

    public ValidacionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidacionException(Throwable cause) {
        super(cause);
    }

    public ValidacionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
