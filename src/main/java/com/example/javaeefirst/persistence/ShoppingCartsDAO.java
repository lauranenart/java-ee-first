package com.example.javaeefirst.persistence;
import com.example.javaeefirst.entities.Item;
import com.example.javaeefirst.entities.ShoppingCart;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ShoppingCartsDAO {
    @Inject
    private EntityManager em;

    public void persist(ShoppingCart shoppingCart){
        this.em.persist(shoppingCart);
    }

    public List<Item> loadAllClientItems(Integer id) {
        return em.createQuery("SELECT i FROM Item i inner join ShoppingCart s where s.id = '" + id + "'", Item.class).getResultList();
    }

    public ShoppingCart findOne(Integer id){
        return em.find(ShoppingCart.class, id);
    }

    public ShoppingCart update(ShoppingCart shoppingCart){
        return em.merge(shoppingCart);
    }
}
