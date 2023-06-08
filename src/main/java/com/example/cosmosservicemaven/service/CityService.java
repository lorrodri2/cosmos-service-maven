package com.example.cosmosservicemaven.service;

import com.example.cosmosservicemaven.model.City;
import com.example.cosmosservicemaven.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }
}
