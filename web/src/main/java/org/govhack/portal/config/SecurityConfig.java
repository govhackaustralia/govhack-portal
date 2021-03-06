package org.govhack.portal.config;

import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.UserRepository;
import org.govhack.portal.security.GovhackUserService;
import org.govhack.portal.security.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    public static class UISecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        UserRepository userRepository;
        @Autowired
        AuthenticationManager manager;
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public HttpSessionStrategy httpSessionStrategy() {
            return new HeaderHttpSessionStrategy();
        }

        @Bean
        BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Bean
        UserDetailsService<User> userUserDetailsService(UserRepository userRepo) {
            return new UserDetailsService<>(new GovhackUserService(userRepo));
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/home", "/error", "/exit", "/api/user/create");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.headers().cacheControl().disable();
            http.headers().frameOptions().sameOrigin();
            http.csrf().disable();
            http.formLogin()
                    .passwordParameter("password").usernameParameter("username")
                    .loginPage("/login").loginProcessingUrl("/do-login")
                    .successForwardUrl("/login-success").failureForwardUrl("/login-error").permitAll()
                    .and().logout().permitAll();
            http.authorizeRequests()
                    .antMatchers("/resources/**", "/error", "/exit").permitAll()
                    .anyRequest().authenticated();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }
}