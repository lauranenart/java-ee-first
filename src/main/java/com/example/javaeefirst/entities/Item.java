package com.example.javaeefirst.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ITEMS")
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "select i from Item as i")
})
@Getter @Setter
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Float price;

    private Integer barcode;

    @ManyToMany(mappedBy="items")
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public Item() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) && title.equals(item.title) && barcode.equals(item.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, barcode);
    }
}
