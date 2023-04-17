package com.mygdx.spacechoppers.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.spacechoppers.data.networking.Message;


import java.net.URISyntaxException;

public class Main {

    public static  void main(String[] args) throws URISyntaxException, InterruptedException, JsonProcessingException {

        String json = "{\"action\":\"CREATE_LOBBY\",\"lobbyID\":\"3345\",\"success\":true}";
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(json, Message.class);
        System.out.println(message);



    }
}
