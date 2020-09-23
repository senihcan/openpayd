package com.openpayd.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public Docket apiDocket() {

    return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
        .apis(RequestHandlerSelectors.basePackage("com.openpayd.task"))
        .paths(PathSelectors.any()).build();
  }

  private ApiInfo getApiInfo() {

    return new ApiInfoBuilder().title("OpenPayd Service API")
        .description("OpenPayd Service")
        .version("0.0.1")
        .contact(new Contact("Senih Can Kardeş", "", "senihcan@gmail.com")).build();
  }
}
