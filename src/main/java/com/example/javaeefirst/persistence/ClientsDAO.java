package com.example.javaeefirst.persistence;

import com.example.javaeefirst.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ClientsDAO {
    @Inject
    private EntityManager em;

    public void persist(Client client){
        this.em.persist(client);
    }

    public Client findOne(Integer id){
        return em.find(Client.class, id);
    }

    public Client update(Client client){
        return em.merge(client);
    }
}
