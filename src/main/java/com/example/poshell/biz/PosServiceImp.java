package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public void checkout(Cart cart) {

    }

    @Override
    public void total(Cart cart) {

    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {

        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        this.getCart().addItem(new Item(product, amount));
        return true;
    }

    @Override
    public void clearCart() { posDB.clearCart(); }

    @Override
    public Boolean modifyCart(int index, int amount) {
        int nums = posDB.getCart().getItems().size();
        if(index > nums || index < 1) {
            return false;
        }
        Item newItem = posDB.getCart().getItems().get(index - 1);
        newItem.setAmount(amount);
        posDB.getCart().getItems().set(index - 1, newItem);
        return true;
    }

    @Override
    public Boolean deleteProduct(int index) {
        int nums = posDB.getCart().getItems().size();
        if(index > nums || index < 1) {
            return false;
        }
        posDB.getCart().getItems().remove(index - 1);
        return true;
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
