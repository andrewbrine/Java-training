package com.matcher.matcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class MatcherControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void verifyPathsExist() throws Exception {
        mvc.perform(get("/order-book")).andExpect(status().is2xxSuccessful());
        mvc.perform(get("/trades-list")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void ordersCanBeAdded() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer();

        Order order = new Order(1234, 1.0, 1, "buy");
        String orderString = ow.writeValueAsString(order);
        mvc.perform(post("/add-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderString))
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/order-book"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void tradesOccur() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer();

        Order order1 = new Order(1234, 1.0, 1, "buy");
        String order1String = ow.writeValueAsString(order1);
        mvc.perform(post("/add-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(order1String))
                .andExpect(status().is2xxSuccessful());

        Order order2 = new Order(1234, 1.0, 1, "sell");
        String order2String = ow.writeValueAsString(order2);
        mvc.perform(post("/add-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(order2String))
                .andExpect(status().is2xxSuccessful());


        mvc.perform(get("/trades-list"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}