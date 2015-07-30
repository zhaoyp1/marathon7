package com.digiwes.product.resource.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by zhaoyp on 2015/7/28.
 */
@Provider
public class Exception404Mapper implements ExceptionMapper<IllegalArgumentException> {
    
	public Response toResponse(IllegalArgumentException e) {
    	
        return Response.status(404).entity(e.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
    }
}
