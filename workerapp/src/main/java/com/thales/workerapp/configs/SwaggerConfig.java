package com.thales.workerapp.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build()
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
        .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
        .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT())
        .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("WORKER APP")
                .description("WORKER that consumes data from the RABBITMQ queue and creates events for each created client.")
                .version("1.0")
                .build();
    }
    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("INTERNAL SERVER ERROR")
                .build());
        }};
    }
    private List<ResponseMessage> responseMessageForPOST()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("INTERNAL SERVER ERROR")
                .build());
        }};
    }
    private List<ResponseMessage> responseMessageForPUT()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("INTERNAL SERVER ERROR")
                .build());
        }};
    }
    private List<ResponseMessage> responseMessageForDELETE()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("INTERNAL SERVER ERROR")
                .build());
        }};
    }
}
