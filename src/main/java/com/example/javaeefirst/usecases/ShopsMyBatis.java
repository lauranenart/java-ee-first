package com.example.javaeefirst.usecases;

import com.example.javaeefirst.entities.Shop;
import com.example.javaeefirst.mybatis.dao.ShopsMapper;
import com.example.javaeefirst.mybatis.model.Shops;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ShopsMyBatis {
    @Inject
    private ShopsMapper shopMapper;

    @Getter
    private List<Shops> allShops;

    @Getter @Setter
    private Shops shopToCreate = new Shops();

    @PostConstruct
    public void init() {
        this.loadAllShops();
    }

    private void loadAllShops() {
        this.allShops = shopMapper.selectAll();
    }

    @Transactional
    public String createShop() {
        shopMapper.insert(shopToCreate);
        return "/myBatis/shops?faces-redirect";
    }
}
