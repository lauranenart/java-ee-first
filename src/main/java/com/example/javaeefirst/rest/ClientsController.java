package com.example.javaeefirst.rest;

import com.example.javaeefirst.entities.Client;
import com.example.javaeefirst.entities.Shop;
import com.example.javaeefirst.persistence.ClientsDAO;
import com.example.javaeefirst.persistence.ShopsDAO;
import com.example.javaeefirst.rest.contracts.ClientDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/clients")
public class ClientsController {
    @Inject
    @Setter
    @Getter
    private ClientsDAO clientsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Client client = clientsDAO.findOne(id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ClientDTO clientDto = new ClientDTO();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setShoppingCart(client.getShoppingCart().getId());
        return Response.ok(clientDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer shopId,
            ClientDTO clientData) {
        try {
            Client existingClient = clientsDAO.findOne(shopId);
            if (existingClient == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingClient.setFirstName(clientData.getFirstName());
            existingClient.setLastName(clientData.getLastName());
            clientsDAO.update(existingClient);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
