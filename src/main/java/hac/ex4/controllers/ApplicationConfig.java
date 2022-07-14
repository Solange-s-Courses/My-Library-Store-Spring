package hac.ex4.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Function in charge of the configuration of the program and the main things
 */
@Configuration
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    /**
     * function in charge of the configuration
     *
     * @param auth Authentication manager
     * @throws Exception exception if it does not work
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("password")).roles("ADMIN");
    }

    /**
     * Function in charge of the configuration of the users name and password
     *
     * @param http in charge of getting the securitty and to get the information of user
     * @throws Exception in case it does not work
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.formLogin().defaultSuccessUrl("/admin", true)
                .and().logout().logoutSuccessUrl("/admin")
                .and().authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN").and().exceptionHandling().accessDeniedPage("/error");


    }
}