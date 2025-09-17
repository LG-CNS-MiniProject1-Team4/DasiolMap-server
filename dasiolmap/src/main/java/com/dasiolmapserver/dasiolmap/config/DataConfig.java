package com.dasiolmapserver.dasiolmap.config;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.dasiolmapserver.dasiolmap",
    excludeFilters = @Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = ElasticsearchRepository.class
    )
)
@EnableElasticsearchRepositories(basePackages = "com.dasiolmapserver.dasiolmap.dasiolstore.repository")
public class DataConfig {
}