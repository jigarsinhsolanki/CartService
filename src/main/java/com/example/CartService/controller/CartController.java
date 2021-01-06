package com.example.CartService.controller;

import com.example.CartService.entity.Cart;
import com.example.CartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/addCartItem")
    public Cart addCartItem(@RequestBody Cart cartItem){

        return cartService.saveCartItem(cartItem);
    }

    @PostMapping("/addCartItems")
    public List<Cart> addCartItems(@RequestBody List<Cart> cartItems){

        return cartService.saveCartItems(cartItems);
    }

    @GetMapping("/cartItems")
    public List<Cart> findAllCartItems(){

        return cartService.getCartItems();
    }
    @GetMapping("/cartIetem/{id}")
    public Cart findCartIetemById(@PathVariable int id){

        return cartService.getCartItemById(id);
    }

    @GetMapping("/cartIetem/{name}")
    public Cart findCartIetemByName(@PathVariable String name){

        return cartService.getCartItemByName(name);
    }

    @PutMapping("/updateCartIetem")
    public Cart updateCartIetem(@RequestBody Cart cart){

        return cartService.updateCartIetem(cart);
    }

    @DeleteMapping("delete/{id}")
    public String deleteCartItem(@PathVariable int id){

        return cartService.deleteCartItem(id);
    }

}

