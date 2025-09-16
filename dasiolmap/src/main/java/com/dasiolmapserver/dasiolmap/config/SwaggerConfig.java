package com.dasiolmapserver.dasiolmap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

        // @Bean
        // public Docket api() {

        // return new Docket(DocumentationType.SWAGGER_2)
        // .useDefaultResponseMessages(false)
        // .select()
        // .apis(RequestHandlerSelectors.basePackage("com.toy.project"))
        // .paths(PathSelectors.ant("/api/**"))
        // .build()
        // .securityContexts(Arrays.asList(securityContext()))
        // .securitySchemes(Arrays.asList(apiKey()));
        // }

        // private SecurityContext securityContext() {
        // return springfox.documentation.spi.service.contexts.SecurityContext
        // .builder()
        // .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
        // }

        // List<SecurityReference> defaultAuth() {
        // AuthorizationScope authorizationScope = new AuthorizationScope("global",
        // "accessEverything");
        // AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        // authorizationScopes[0] = authorizationScope;
        // return Arrays.asList(new SecurityReference("Bearer +Access Token",
        // authorizationScopes));
        // }

        // private ApiKey apiKey() {
        // return new ApiKey("Bearer +Access Token", "Authorization", "header");
        // }

}
