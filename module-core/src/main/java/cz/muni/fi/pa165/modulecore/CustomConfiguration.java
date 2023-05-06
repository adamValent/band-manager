package cz.muni.fi.pa165.modulecore;

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
                        .requestMatchers("/albums/**").authenticated()
                        .requestMatchers("/bands/**").authenticated()
                        .requestMatchers("/invitations/**").authenticated()
                        .requestMatchers("/songs/**").authenticated()
                        .requestMatchers("/tours/**").authenticated()
                        .requestMatchers("/users/**").authenticated()
                        // user endpoints
                        .requestMatchers(HttpMethod.GET, "/users/withoutBand").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.GET, "/users/bySong/**").hasAnyAuthority("SCOPE_test_2", "SCOPE_test_1")
                        .requestMatchers(HttpMethod.POST, "/users").hasAnyAuthority("SCOPE_test_2", "SCOPE_test_1")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("SCOPE_test_2", "SCOPE_test_1")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("SCOPE_test_2", "SCOPE_test_1")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("SCOPE_test_2", "SCOPE_test_1")
                        // tour endpoints
                        .requestMatchers(HttpMethod.GET, "/tours/**").hasAnyAuthority("SCOPE_test_2", "SCOPE_test_1")
                        .requestMatchers(HttpMethod.PUT, "/tours/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.DELETE, "/tours/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.POST, "/tours").hasAuthority("SCOPE_test_1")
                        // song endpoints
                        .requestMatchers(HttpMethod.PUT, "/songs/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.DELETE, "/songs/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.POST, "/songs").hasAuthority("SCOPE_test_1")
                        // invitation endpoints
                        .requestMatchers(HttpMethod.GET, "/invitations/**").hasAnyAuthority("SCOPE_test_2", "SCOPE_test_1")
                        .requestMatchers(HttpMethod.PUT, "/invitations/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.DELETE, "/invitations/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.POST, "/invitations").hasAuthority("SCOPE_test_1")
                        // band endpoints
                        .requestMatchers(HttpMethod.PUT, "/band/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.DELETE, "/band/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.POST, "/bands").hasAuthority("SCOPE_test_1")
                        // album endpoints
                        .requestMatchers(HttpMethod.PUT, "/albums/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.DELETE, "/albums/**").hasAuthority("SCOPE_test_1")
                        .requestMatchers(HttpMethod.POST, "/albums/**").hasAuthority("SCOPE_test_1")
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
