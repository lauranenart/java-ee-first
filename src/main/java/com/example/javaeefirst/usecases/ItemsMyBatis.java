package com.example.javaeefirst.usecases;

import com.example.javaeefirst.entities.Client;
import com.example.javaeefirst.entities.Item;
import com.example.javaeefirst.mybatis.dao.ClientsMapper;
import com.example.javaeefirst.mybatis.dao.ItemsMapper;
import com.example.javaeefirst.mybatis.model.Clients;
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
public class ItemsMyBatis {
    @Inject
    private ClientsMapper clientsMapper;

    @Inject
    private ItemsMapper itemsMapper;

    @Getter
    @Setter
    private Clients client;

    @Getter
    private List<com.example.javaeefirst.mybatis.model.Items> allItems;

    @Getter
    @Setter
    private com.example.javaeefirst.mybatis.model.Items itemToCreate = new com.example.javaeefirst.mybatis.model.Items();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer clientId = Integer.parseInt(requestParameters.get("clientId"));
        this.client = clientsMapper.selectByPrimaryKey(clientId);
        this.loadAllItems();
    }

    private void loadAllItems(){
        this.allItems = itemsMapper.selectAll();
    }

    @Transactional
    public String createItem() {
        itemsMapper.insert(itemToCreate);
        return "/myBatis/items?faces-redirect";
    }
}
