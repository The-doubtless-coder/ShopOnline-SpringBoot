package org.shoponline.org.apigatewayservice.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity//gateway uses webflux
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
//        //noinspection removal
//        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)//don't disable when posting, uniquely identify the form using a code
//                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.
//                        pathMatchers("/eureka/web")//allow this url without authenticating
//                        .permitAll().anyExchange().authenticated())
//                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
//              return serverHttpSecurity.build(); //enable resource server, enable jwts
//    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf().disable()
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/eureka/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return serverHttpSecurity.build();
    }

//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        return ReactiveJwtDecoders.fromIssuerLocation(issuerUri);
//    }
}
