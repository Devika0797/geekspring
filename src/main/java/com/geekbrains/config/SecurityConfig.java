package com.geekbrains.config;

import com.geekbrains.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource myDataSource;
    private UserService userService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    @Autowired
    public void setMyDataSource(DataSource myDataSource){
        this.myDataSource = myDataSource;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCustomAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(myDataSource);
//        User.UserBuilder users = User.builder();
//        auth.inMemoryAuthentication()
//                .withUser(users.username("alex").password("{noop}1234").roles("USER", "ADMIN"))
//                .withUser(users.username("alex").password("{noop}1234").roles("USER"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("ADMIN", "USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
