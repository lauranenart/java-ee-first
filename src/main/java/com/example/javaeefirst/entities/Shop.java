package com.example.javaeefirst.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Shop.findAll", query = "select s from Shop as s")
})
@Table(name = "SHOPS")
@Getter @Setter
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String address;

    @OneToMany(mappedBy = "shop")
    private List<Client> clients = new ArrayList<>();

    public Shop() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id.equals(shop.id) && title.equals(shop.title) && address.equals(shop.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, address);
    }
}
