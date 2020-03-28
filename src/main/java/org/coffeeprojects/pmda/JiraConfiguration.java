package org.coffeeprojects.pmda;

import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JiraConfiguration {

    @Bean
    Logger.Level feign() {
        return Logger.Level.FULL;
    }

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("julien@herubel.com", "KxmWqia8pY0XXUMws60M0217");
    }
}