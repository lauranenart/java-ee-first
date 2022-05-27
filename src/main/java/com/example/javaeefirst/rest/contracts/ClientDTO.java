package com.example.javaeefirst.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientDTO {
    private String FirstName;
    private String LastName;

    private Integer ShoppingCart;
}
