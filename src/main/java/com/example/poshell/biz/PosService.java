package com.example.poshell.biz;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public void checkout(Cart cart);

    public void total(Cart cart);

    public boolean add(Product product, int amount);

    public boolean add(String productId, int amount);

    public void clearCart();

    public Boolean modifyCart(int index, int amount);

    public Boolean deleteProduct(int index);

    public List<Product> products();
}
