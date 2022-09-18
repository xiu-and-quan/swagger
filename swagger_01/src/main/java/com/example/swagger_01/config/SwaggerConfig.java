package com.example.swagger_01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
//参考文档https://www.cnblogs.com/iqiuq/p/14883839.html
@Configuration
@EnableSwagger2 //开启swagger2,若启动类上添加了该注解，则配置类可以不添加
public class SwaggerConfig {
    // 创建swagger bean
    @Bean
    public Docket docket(Environment environment) {
        // 配置swagger的docket的bean实例
        Profiles profiles = Profiles.of("dev","test");
        // 通过environment.acceptsProfiles()判断是否指定的环境中，是，则为true
        boolean flag = environment.acceptsProfiles(profiles);
        // Docket是swagger全局配置对象
        // DocumentationType：指定文档类型为swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                // swagger信息
                .apiInfo(apiInfo())
                .enable(flag)
                // swagger 扫描包配置
                // select()获取Docket中的选择器，返回ApiSelectorBuilder构造选择器，如扫描扫描包的注解
                .select()
                /**
                 * requestHandlerSelectors：请求处理选择器
                 * basePackage()：扫描指定包下的所有接口
                 * any()：扫描所有的包
                 * none()：不扫描
                 * withClassAnnotation()：扫描指定类上的注解，参数是一个注解的放射对象
                 * withMethodAnnotation()：扫描方法上的注解
                 */
                // 指定扫描器扫描的规则（断言）
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger_01.controller"))
                .build();
    }

    // swagger文档信息
    public ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact(
                // 文档发布者的名称
                "xiu",
                // 文档发布者的网站地址
                "https://localhost:8080/swagger-ui.html",
                // 文档发布者的电子邮箱
                "1613598422@qq.com"
        );
        return new ApiInfo(
                // 标题
                "xiu swagger api",
                // 文档描述
                "swaggerLearning",
                // 版本号
                "1.0",
                // 服务组url地址
                "https://localhost:8080/swagger-ui.html",
                // 作者信息
                contact,
                // 开源组织
                "Apache 2.0",
                // 开源地址
                "http://www.apache.org/licenses/LICENSE-2.0",
                // 集合
                new ArrayList()
        );
    }
}
