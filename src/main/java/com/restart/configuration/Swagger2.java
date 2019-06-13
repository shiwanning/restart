package com.restart.configuration;


import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.restart.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name("Authorization")
                                .parameterType("header")
                                .type(new TypeResolver().resolve(String.class))
                                .modelRef(new ModelRef("string"))
                                .defaultValue("push-token-here")
                                .build()))
                .apiInfo(apiInfo())
                //                .globalOperationParameters(getHeaders())
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_VALUE));
    }

    private ApiInfo apiInfo() {


        return new ApiInfoBuilder()
                .title("REST API Document")
                .description("Backend Use")
                .termsOfServiceUrl("Terms of service")
                .version("1.0")
                .build();
    }
}
