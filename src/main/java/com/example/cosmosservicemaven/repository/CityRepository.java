package com.example.cosmosservicemaven.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.cosmosservicemaven.model.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CosmosRepository<City, String> {
}
