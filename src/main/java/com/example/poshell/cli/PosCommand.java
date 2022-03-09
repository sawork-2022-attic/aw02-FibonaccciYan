package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Clear Cart", key = "c")
    public String clearCart() {
        posService.clearCart();
        return posService.getCart().toString();
    }

    @ShellMethod(value = "Show Cart", key = "s")
    public String showCart() {
        if(posService.getCart() == null) {
            return "There's no cart found. Press 'n' to create a new one.";
        }
        return posService.getCart().toString();
    }

    @ShellMethod(value = "Modify Product Amount", key = "m")
    public String modifyCart(int index, int amount) {
        if(posService.getCart() == null) {
            return "There's no cart found. Press 'n' to create a new one.";
        }
        if(posService.modifyCart(index, amount)) {
            return "Success!";
        } else {
            return "Failed! Please check again.";
        }
    }

    @ShellMethod(value = "Delete Certain Product", key = "d")
    public String deleteProduct(int index) {
        if(posService.getCart() == null) {
            return "There's no cart found. Press 'n' to create a new one.";
        }
        if(posService.deleteProduct(index)) {
            return "Success!";
        } else {
            return "Failed! Please check again.";
        }
    }
}
