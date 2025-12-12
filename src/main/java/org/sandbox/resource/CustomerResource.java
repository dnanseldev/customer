package org.sandbox.resource;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sandbox.dto.CustomerDTO;
import org.sandbox.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

@Path("/api/customers")
public class CustomerResource {
    private static final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> findAllCustomer() {
        return this.customerService.findAllCustomers();
    }

    @POST
    @Transactional
    public Response saveCustomer(CustomerDTO customerDTO) {
        try {
            this.customerService.createNewCustomer(customerDTO);
            return Response.ok().build();
        } catch (Exception e) {
            log.error("Failed to save customer", e);
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, CustomerDTO customerDTO) {
        try {
            this.customerService.changeCustomer(id, customerDTO);
            return Response.accepted().build();
        } catch (Exception e) {
            log.error("Failed to save customer", e);
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        try {
            this.customerService.deleteCustomer(id);
            return Response.noContent().build();
        } catch (Exception e) {
            log.error("Failed to delete customer", e);
            return Response.serverError().build();
        }
    }
}
