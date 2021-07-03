package com.example.demo;

import com.example.demo.model.Bid;
import com.example.demo.model.Customer;
import com.example.demo.repository.BidRepository;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BidRestControllerTest extends AbstractTest {

    @Autowired
    BidRepository bidRepository;

    Bid bid1;
    Bid bid2;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        bid1= new Bid();
        bid1.setId((long) 1);
        bid1.setAmout(5.0);
        bid1.setPlacedAtDateTime(new Date(2021, 2,4));
        bidRepository.save(bid1);
        bid2 = new Bid();
        bid2.setId((long) 2);
        bid2.setAmout(3.2);
        bid2.setPlacedAtDateTime(new Date(2021, 3,4));
        bidRepository.save(bid2);
    }

    @Test
    public void getCustomersList() throws Exception {
        String uri = "/bids";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE, "application/hal+json")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String response = mvcResult.getResponse().getContentAsString();

        String content = extractEmbeddedFromHalJson(response,"bids");
        Bid[] bidList = super.mapFromJson(content, Bid[].class);
        assertTrue(bidList.length > 0);
        assertEquals(bidList[0].getAmout(), bid1.getAmout());
        assertEquals(bidList[1].getAmout(), bid2.getAmout());

    }

    @Test
    public void getOneCustomer() throws Exception {
        String uri = "/bids/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE, "application/hal+json")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String response = mvcResult.getResponse().getContentAsString();
        Bid bid = super.mapFromJson(response, Bid.class);
        assertEquals(bid.getAmout(), bid1.getAmout());
    }

    @Test
    public void postOneCustomer() throws Exception {
        String uri = "/bids";

        Bid bid = new Bid();
        bid.setAmout(4.1);

        String json = super.mapToJson(bid);

        MvcResult postMvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE, "application/hal+json")
                .content(json))
                .andReturn();

        int status = postMvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String response = postMvcResult.getResponse().getContentAsString();
        Bid bid3 = super.mapFromJson(response, Bid.class);
        assertEquals(bid3.getAmout(), bid.getAmout());
    }

}
