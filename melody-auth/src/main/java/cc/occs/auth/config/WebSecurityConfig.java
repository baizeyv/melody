package cc.occs.auth.config;

import cc.occs.auth.filter.JwtFilter;
import cc.occs.auth.handle.AuthorizedFailedHandler;
import cc.occs.auth.handle.JwtAccessDeniedHandler;
import cc.occs.auth.service.impl.MelodyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    /**
     * UserDetails Service
     */
    private final MelodyUserDetailsServiceImpl melodyUserDetailsService;

    /**
     * Json Web Token Access Denied Handler
     */
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final AuthorizedFailedHandler authorizedFailedHandler;

    /**
     * Json Web Token Filter
     */
    private final JwtFilter jwtFilter;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // Set UserDetailsService
                .userDetailsService(this.melodyUserDetailsService)
                // Use BCrypt 进行密码的 hash
                .passwordEncoder(passwordEncoder());
    }

    /**
     * API White List
     */
    private static final String[] WHITE_LIST_URLS = {
            "/auth/cert/signup",
            "/auth/cert/login",
            "/test/test",
            "/test/test1",
            "/auth/cert/tmp"
    };

    @Autowired
    public WebSecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler, AuthorizedFailedHandler authorizedFailedHandler, JwtFilter jwtFilter, MelodyUserDetailsServiceImpl melodyUserDetailsService) {
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.authorizedFailedHandler = authorizedFailedHandler;
        this.jwtFilter = jwtFilter;
        this.melodyUserDetailsService = melodyUserDetailsService;
    }

    /**
     * Load BCrypt Password Encoder
     * @return PasswordEncoder
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler).and()
                // don't need csrf
                .csrf().disable()
                // enable cross-domain
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(authorizedFailedHandler).and()
                // don't need session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // disable cache
                .headers().cacheControl().and()
                .and().build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
