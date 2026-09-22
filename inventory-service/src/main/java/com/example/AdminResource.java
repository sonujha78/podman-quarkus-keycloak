package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

    @GET
    public String adminOnly() {
        return "{\"message\": \"Hello Admin! This is a protected admin-only endpoint.\"}";
    }
}
