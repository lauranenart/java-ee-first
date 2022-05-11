package com.example.javaeefirst.usecases;

import com.example.javaeefirst.entities.Shop;
import com.example.javaeefirst.persistence.ShopsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Shops {

    @Inject
    private ShopsDAO shopsDAO;

    @Getter
    @Setter
    private Shop shopToCreate = new Shop();

    @Getter
    private List<Shop> allShops;

    @PostConstruct
    public void init(){
        loadAllShops();
    }

    @Transactional
    public void createShop(){
        this.shopsDAO.persist(shopToCreate);
    }

    private void loadAllShops(){
        this.allShops = shopsDAO.loadAll();
    }
}
