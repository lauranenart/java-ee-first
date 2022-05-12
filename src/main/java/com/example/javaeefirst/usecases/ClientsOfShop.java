package com.example.javaeefirst.usecases;

import com.example.javaeefirst.entities.Client;
import com.example.javaeefirst.entities.Item;
import com.example.javaeefirst.entities.Shop;
import com.example.javaeefirst.entities.ShoppingCart;
import com.example.javaeefirst.persistence.ClientsDAO;
import com.example.javaeefirst.persistence.ItemsDAO;
import com.example.javaeefirst.persistence.ShoppingCartsDAO;
import com.example.javaeefirst.persistence.ShopsDAO;
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
public class ClientsOfShop {

    @Inject
    private ClientsDAO clientsDAO;

    @Inject
    private ShoppingCartsDAO shoppingCartsDAO;

    @Inject
    private ShopsDAO shopsDAO;

    @Inject
    private ItemsDAO itemsDAO;

    @Getter @Setter
    private Shop shop;

    @Getter
    @Setter
    private Client clientToCreate = new Client();

    @Getter
    @Setter
    private ShoppingCart shoppingCartToCreate = new ShoppingCart();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer shopId = Integer.parseInt(requestParameters.get("shopId"));
        this.shop = shopsDAO.findOne(shopId);
    }

    @Transactional
    public void createClient() {
        clientToCreate.setShop(this.shop);
        clientsDAO.persist(clientToCreate);

        shoppingCartToCreate.setClient(clientToCreate);
        shoppingCartsDAO.persist(shoppingCartToCreate);
    }
}
