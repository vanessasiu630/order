package com.van.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.spingframework.http.ResponseEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.entity.StringEntity;
import java.util.Random;

@SpringBootApplication

public class GatewayController {

    BodyBuilder accepted();
    
    @Autowired
    
    @PostMapping(value = "/order", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody OrderCreateRequestDto request) throws Exception {
        /* 
         * URI uri = "http://localhost:8443/order";

        Gson json = new Gson();
    
        HttpURLConnection con = (HttpURLConnection) uri.openConnection();
        StringEntity se = new StringEntity(json.toJson(request));
        
    
        modelMapper.map(order, OrderCreateRequestDto.class);

        */
        try {
            Random r = new Random();
            Thread.sleep(r.nextInt(5000));
            return ResponseEntity.accepted().build();
        }
        
        
    }
} 
