package com.example.CartService.service;
import com.example.CartService.entity.Cart;
import com.example.CartService.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
//
//    public Cart saveCartItems(Cart cart)
//    {
//        return cartRepository.save(cart);
//    }
//
//
//    public List<Cart> saveCartItem(List<Cart> cacartItemsrt)
//    {
//        return cartRepository.saveAll(cartItems);
//    }

    public List<Cart> getCartItems(){

        return cartRepository.findAll();
    }
    public Cart getCartItemById(int id){

        return cartRepository.findById(id).orElse(null);
    }

    public Cart getCartItemByName(String name){

        return cartRepository.findByName(name);
    }

    public String deleteCartItem(int id){

        cartRepository.deleteById(id);
        return "cartItem removed !! "+id;
    }

    public Cart saveCartItem(Cart cartItem) {

        return cartRepository.save(cartItem);
    }

    public List<Cart> saveCartItems(List<Cart> cartItems) {

        return cartRepository.saveAll(cartItems);
    }

    public Cart updateCartIetem(Cart cart) {
        Cart existingCartItem=cartRepository.findById(cart.getId()).orElse(null);
        existingCartItem.setName(cart.getName());
        existingCartItem.setQuantity(cart.getQuantity());
        existingCartItem.setPrice(cart.getPrice());
        existingCartItem.setSubtotal(cart.getSubtotal());
        return cartRepository.save(existingCartItem);


    }
}
