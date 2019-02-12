package com.mandisoft.services.member;

import com.mandisoft.services.member.security.IJWTBuilder;
import com.mandisoft.services.member.security.JWTBuilderImpl;
import com.mandisoft.services.member.service.BuyEntryService;
import com.mandisoft.services.member.service.UserService;
import com.mandisoft.services.member.serviceImpl.BuyEntryServiceImpl;
import com.mandisoft.services.member.serviceImpl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.mandisoft.services.member")
@PropertySource({ "classpath:jwt.properties" })
public class MemberApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public BuyEntryService buyEntryService() {
        return new BuyEntryServiceImpl();
    }

    @Bean
    public IJWTBuilder jwtBuilder() {
        return new JWTBuilderImpl();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST",
                        "PUT", "DELETE", "PATCH");
    }
}
