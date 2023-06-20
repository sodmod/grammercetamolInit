package com.grammercetamol.securities.configurations;

import com.grammercetamol.implementation.UserDetailsServicesImpl;
import com.grammercetamol.securities.jwt.AuthFilter;
import com.grammercetamol.securities.jwt.JwtEntryPoint;
import com.grammercetamol.securities.passwordEncoder.PasswordEncrypt;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.NEVER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true,
        jsr250Enabled = true
)
@AllArgsConstructor
public class WebConfig {
    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
    @Autowired
    private UserDetailsServicesImpl userDetailsServices;
    @Autowired
    private PasswordEncrypt passwordEncrypt;
    @Autowired
    private JwtEntryPoint entryPoint;

    @Autowired
    @Bean
    public AuthFilter filter() {
        return new AuthFilter();
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServices);
        authenticationProvider.setPasswordEncoder(passwordEncrypt.bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable();
        http
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint);
        http
                .sessionManagement()
                .sessionCreationPolicy(NEVER);
        http
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/secured/**").permitAll()
                .antMatchers("/api/cloudinary/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest()
                .authenticated();
        http
                .authenticationProvider(daoAuthenticationProvider());

        http
                .addFilterBefore(
                        filter(),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
