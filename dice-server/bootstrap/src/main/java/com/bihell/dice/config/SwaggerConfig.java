package com.bihell.dice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author haseochen
 */
@Configuration
@EnableSwagger2
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SwaggerConfig {

    private final DiceProperties diceProperties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.bihell.dice"))
                .paths(PathSelectors.any())
                .build()
                .enable(!diceProperties.isDocDisabled());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dice")
                .description("个人管理系统 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("https://github.com/bihell/Dice")
                .version("1.0")
                .contact(new Contact("胖哥", "http://www.bihell.com/", "tpxcer@outlook.com"))
                .license("MIT")
                .build();
    }
}
