/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author stefan
 */
public class HakatonException extends WebApplicationException{

    public HakatonException() {
        super(Response.status(404).entity("Stranica nije nadjena").build());
    }
    
    public HakatonException(String message) {
        super(Response.status(500).entity(new ErrorMessage(message, 500)).type(MediaType.APPLICATION_JSON).build());
    }
    
    public HakatonException(String message, int code) {
        super(Response.status(code).entity(new ErrorMessage(message, code)).type(MediaType.APPLICATION_JSON).build());
    }
    
}
