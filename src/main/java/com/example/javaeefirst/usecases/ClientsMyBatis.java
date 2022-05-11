package com.example.javaeefirst.usecases;

import com.example.javaeefirst.entities.Shop;
import com.example.javaeefirst.mybatis.dao.ClientsMapper;
import com.example.javaeefirst.mybatis.dao.ShopsMapper;
import com.example.javaeefirst.mybatis.model.Clients;
import com.example.javaeefirst.mybatis.model.Shops;
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
public class ClientsMyBatis {
    @Inject
    private ClientsMapper clientMapper;

    @Inject
    private ShopsMapper shopMapper;

    @Getter
    private List<com.example.javaeefirst.mybatis.model.Clients> allClients;

    @Getter @Setter
    private com.example.javaeefirst.mybatis.model.Clients clientToCreate = new Clients();

    @Getter @Setter
    private Shops shop;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer shopId = Integer.parseInt(requestParameters.get("shopId"));
        this.shop = shopMapper.selectByPrimaryKey(shopId);
        this.loadAllClients();
    }

    private void loadAllClients() {
        this.allClients = clientMapper.selectAll();
    }

    @Transactional
    public String createClient() {

        clientMapper.insert(clientToCreate);
        return "/myBatis/clients?faces-redirect";
    }
}
