package com.example.cosmosservicemaven.config;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class AppConfiguration extends AbstractCosmosConfiguration {

    @Value("${spring.cloud.azure.cosmos.endpoint}")
    private String uri;

    @Value("${spring.cloud.azure.cosmos.key}")
    private String key;

    @Value("${spring.cloud.azure.cosmos.database}")
    private String cosmosDbDatabase;

    @Value("${spring.cloud.azure.cosmos.region}")
    private String region;

    @Bean
    public CosmosClientBuilder appCosmosClientBuilder() {
        List<String> regions = new ArrayList<>();
        regions.add(region);

        return new CosmosClientBuilder()
                .key(key)
                .endpoint(uri)
                .preferredRegions(regions);
    }


    @Bean
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
                .enableQueryMetrics(true)
                .build();
    }

    @Override
    protected String getDatabaseName() {
        return cosmosDbDatabase;
    }
}
