package com.example.javaeefirst.mybatis.model;

import java.util.List;

public class ShoppingCart {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SHOPPING_CART.ID
     *
     * @mbg.generated Tue May 10 18:34:01 EEST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SHOPPING_CART.CLIENT_ID
     *
     * @mbg.generated Tue May 10 18:34:01 EEST 2022
     */
    private Integer clientId;
    private List<Items> items;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SHOPPING_CART.ID
     *
     * @return the value of PUBLIC.SHOPPING_CART.ID
     *
     * @mbg.generated Tue May 10 18:34:01 EEST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SHOPPING_CART.ID
     *
     * @param id the value for PUBLIC.SHOPPING_CART.ID
     *
     * @mbg.generated Tue May 10 18:34:01 EEST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SHOPPING_CART.CLIENT_ID
     *
     * @return the value of PUBLIC.SHOPPING_CART.CLIENT_ID
     *
     * @mbg.generated Tue May 10 18:34:01 EEST 2022
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SHOPPING_CART.CLIENT_ID
     *
     * @param clientId the value for PUBLIC.SHOPPING_CART.CLIENT_ID
     *
     * @mbg.generated Tue May 10 18:34:01 EEST 2022
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public List<Items> getItems() {
        return items;
    }
}