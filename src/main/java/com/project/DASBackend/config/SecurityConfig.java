package com.project.DASBackend.config;

//import com.project.DASBackend.filter.FirebaseTokenFilter;
//import com.project.DASBackend.filter.JwtRequestFilter;
import com.project.DASBackend.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private FirebaseTokenFilter firebaseTokenFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/login","/authenticate", "/register","/upload").permitAll() // Allow access to login endpoint
                        .anyRequest().permitAll() // Protect other endpoints
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        //.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create session if required
                );
//                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//                http.addFilterBefore(firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class);
                http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/login"); // Ignore security for login endpoint
    }


}


