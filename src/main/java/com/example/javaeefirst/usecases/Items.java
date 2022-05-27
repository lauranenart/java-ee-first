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
    private Item itemToAdd = new Item();

    @Getter
    @Setter
    private Client client;

    @Getter
    private List<Item> allItems;

    @Getter
    private List<Item> allClientItems;

    @Getter
    @Setter
    private Integer itemId;

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

        loadAllClientItems();
    }

    private void loadAllItems(){
        this.allItems = itemsDAO.loadAll();
    }
    private void loadAllClientItems(){
        //this.allClientItems = shoppingCartsDAO.loadAllClientItems(this.client.getShoppingCart().getId());
        this.allClientItems = shoppingCartsDAO.findOne(this.client.getShoppingCart().getId()).getItems();
    }

    @Transactional
    public void createItem() {
        itemsDAO.persist(itemToCreate);
    }

    @Transactional
    public void addItem(){
        itemToAdd = itemsDAO.findOne(itemId);
        client.getShoppingCart().getItems().add(itemToAdd);
        shoppingCartsDAO.update(client.getShoppingCart());
    }
}
