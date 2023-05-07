package cz.muni.fi.pa165.moduleemail;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomConfiguration {
    public static final String SECURITY_SCHEME_NAME = "Bearer";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/email/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/email").hasAnyAuthority("SCOPE_test_1", "SCOPE_test_2")
                        .requestMatchers(HttpMethod.POST, "/email/band/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.POST, "/email/**/manager").hasAuthority("SCOPE_test_2")
                        .requestMatchers(HttpMethod.POST, "/email/tour/**").hasAnyAuthority("SCOPE_test_1")
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::opaqueToken)
        ;
        return http.build();
    }

    @Bean
    public OpenApiCustomizer openAPICustomizer() {
        return openApi -> openApi.getComponents()
                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description("provide a valid access token")
                );
    }
}
