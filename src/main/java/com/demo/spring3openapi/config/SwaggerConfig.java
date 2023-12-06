package com.demo.spring3openapi.config;

import com.demo.spring3openapi.constant.HeaderConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components()
                .addSecuritySchemes(HeaderConstant.HEADER_KEY, getScheme(HeaderConstant.HEADER_KEY));

        return new OpenAPI()
                .info(new Info()
                        .title("My Spring 3 OPENAPI DEMO APP")
                        .description("This is a demo app for using swagger 3 openapi")
                    .version("1.0.0"))
                .path("/test", getPathItem())
                .components(components)
                .security(List.of(new SecurityRequirement().addList(HeaderConstant.HEADER_KEY)));
    }

    private PathItem getPathItem() {
        PathItem pathItem = new PathItem();
        Operation operation = new Operation();
        operation.addParametersItem(new Parameter()
                .in("header")
                .name(HeaderConstant.HEADER_KEY)
                .schema(new Schema()._default(HeaderConstant.HEADER_VALUE).type("string")).name("para schema name").description("para schema desc"));
        pathItem.get(operation);
        return pathItem;
    }

    private SecurityScheme getScheme(String headerKey) {
        return new SecurityScheme()
                .name(headerKey)
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .scheme("header");
    }
}
