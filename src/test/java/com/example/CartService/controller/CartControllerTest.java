package com.example.CartService.controller;

import com.example.CartService.entity.Cart;
import com.example.CartService.repository.CartRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartRepository cartRepository;

    @Test
    public void testCartItems() throws Exception {

        List<Cart> listOfCartItems=new ArrayList<>();
        listOfCartItems.add(new Cart(10,"S7",1,500,500.50));
        Mockito.when(cartRepository.findAll()).thenReturn(listOfCartItems);

        String url = "/cartItems";

        MvcResult mvcResult= mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

       String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

               String expectedJsonResponse= objectMapper.writeValueAsString(listOfCartItems);

               assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);



    }


    @Test
    void addCartItem() throws Exception {

        Cart newItem=new Cart(10,"S7",2,500,500.5);
        Cart saveItem=new Cart(10,"S7",2,500,500.5);

        Mockito.when(cartRepository.save(newItem)).thenReturn(saveItem);

        String url="/addCartItem";

        mockMvc.perform(
                post(url).contentType("application/json")
                .content(objectMapper.writeValueAsString(newItem))).andExpect(status().isOk())
                .andExpect(content().string("S7"));
    }
}
