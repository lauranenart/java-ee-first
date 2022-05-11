package com.example.javaeefirst.persistence;
import com.example.javaeefirst.entities.Item;
import com.example.javaeefirst.entities.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ItemsDAO {
    @Inject
    private EntityManager em;

    public List<Item> loadAll() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }

    public void persist(Item item){
        this.em.persist(item);
    }

    public Item findOne(Integer id){
        return em.find(Item.class, id);
    }

    public Item update(Item item){
        return em.merge(item);
    }
}
