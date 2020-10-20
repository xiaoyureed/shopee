package io.github.xiaoyureed.shopeegateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author : xiaoyureed
 * 2020/10/20
 */
@Configuration
public class GlobalCorsConfig {

    /**
     * 全局跨域
     *
     * you can also onfigure it in file:
     * https://docs.spring.io/spring-cloud-gateway/docs/2.2.5.RELEASE/reference/html/#cors-configuration
     */
    @Bean
    public CorsWebFilter corsWebFilter() {// CorsWebFilter 位于响应式包下, 其他类也是类似
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);// 允许携带 cookie 跨域

        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}
