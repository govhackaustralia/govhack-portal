package org.govhack.portal.config;

import org.govhack.portal.data.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Order(3)
    public static class UISecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        UserRepository userRepository;
        @Autowired
        AuthenticationManager manager;

        @Bean
        BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/", "/error", "/exit");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.headers().cacheControl().disable();
            http.headers().frameOptions().sameOrigin();
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/resources/**", "/error", "/exit").permitAll();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

}