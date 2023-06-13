package com.example.cosmosservicemaven.controller;

import com.azure.spring.data.cosmos.core.query.CosmosPageRequest;
import com.example.cosmosservicemaven.kafka.KafkaConfig;
import com.example.cosmosservicemaven.model.City;
import com.example.cosmosservicemaven.serializer.Message;
import com.example.cosmosservicemaven.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private KafkaConfig kafkaConfig;

    @GetMapping("/cities")
    public Map<String, Object> getCities(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize, @RequestParam String message) {
        pageSize = (pageSize != null) ? pageSize : 100;
        pageNumber = (pageNumber != null) ? pageNumber : 0;
        message = (message != null) ? message : "";

        Map<String, Object> result = new HashMap<>();

        final CosmosPageRequest pageRequest = new CosmosPageRequest(pageNumber, pageSize, null);

        Page<City> page = cityService.findAll(pageRequest);
        List<City> pageContent = page.getContent();

        result.put("cities", pageContent);
        result.put("messages", kafkaConfig.getMessages());

        Message messageToSend = new Message();
        messageToSend.setText(message);
        kafkaConfig.send(messageToSend);

        return result;
    }
}
