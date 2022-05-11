package com.example.javaeefirst.usecases;

import com.example.javaeefirst.entities.Client;
import com.example.javaeefirst.entities.Item;
import com.example.javaeefirst.entities.Shop;
import com.example.javaeefirst.entities.ShoppingCart;
import com.example.javaeefirst.persistence.ClientsDAO;
import com.example.javaeefirst.persistence.ItemsDAO;
import com.example.javaeefirst.persistence.ShoppingCartsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Items {

    @Inject
    private ClientsDAO clientsDAO;

    @Inject
    private ShoppingCartsDAO shoppingCartsDAO;

    @Inject
    private ItemsDAO itemsDAO;

    @Getter
    @Setter
    private Client client;

    @Getter
    private List<Item> allItems;

    @Getter
    @Setter
    private Item itemToCreate = new Item();

    @PostConstruct
    public void init() {
        loadAllItems();

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer clientId = Integer.parseInt(requestParameters.get("clientId"));
        this.client = clientsDAO.findOne(clientId);
    }

    private void loadAllItems(){
        this.allItems = itemsDAO.loadAll();
    }

    @Transactional
    public void createItem() {
        itemToCreate.getShoppingCarts().add(this.client.getShoppingCart());
        itemsDAO.persist(itemToCreate);
        this.client.getShoppingCart().getItems().add(itemToCreate);
    }
}