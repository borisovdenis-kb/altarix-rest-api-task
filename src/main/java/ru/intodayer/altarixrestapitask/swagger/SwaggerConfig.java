package ru.intodayer.altarixrestapitask.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("ru.intodayer.altarixrestapitask.controllers"))
            .build()
            .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo(
            "Altarix REST API Task",
            "Management of organizational and staff structure of the company.",
            "1.0",
            "",
            new Contact("Borisov Denis","https://github.com/borisovdenis-kb/altarix-rest-api-task","borisovdenis-kb@yandex.ru"),
            "",
            "",
            Collections.emptyList()
        );
    }
}
