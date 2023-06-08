package com.example.cosmosservicemaven.controller;

import com.azure.spring.data.cosmos.core.query.CosmosPageRequest;
import com.example.cosmosservicemaven.model.City;
import com.example.cosmosservicemaven.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> getCities() {
        final CosmosPageRequest pageRequest = new CosmosPageRequest(0, 100, null);

        Page<City> page = cityService.findAll(pageRequest);
        List<City> pageContent = page.getContent();

        while (page.hasNext()) {
            Pageable nextPageable = page.nextPageable();
            page = cityService.findAll(nextPageable);
            pageContent = page.getContent();
        }

        return pageContent;
    }
}
