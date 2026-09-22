package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private static final Map<Long, Product> PRODUCTS = new ConcurrentHashMap<>();

    static {
        PRODUCTS.put(1L, new Product(1L, "Laptop", 55000.0, 10));
        PRODUCTS.put(2L, new Product(2L, "Mouse", 500.0, 100));
        PRODUCTS.put(3L, new Product(3L, "Keyboard", 1200.0, 50));
    }

    @GET
    public List<Product> list() {
        return List.copyOf(PRODUCTS.values());
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Product product = PRODUCTS.get(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(product).build();
    }
}
