package com.dasiolmapserver.dasiolmap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.dasiolmapserver.dasiolmap")
@EnableElasticsearchRepositories(basePackages = "com.dasiolmapserver.dasiolmap.dasiolstore.repository")
public class DataConfig {
    
}
