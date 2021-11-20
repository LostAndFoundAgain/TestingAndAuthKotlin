package com.winter.testkotlin.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("admin")
            .roles("ADMIN")
            .and()
            .withUser("user")
            .password("user")
            .roles("USER");
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/admin").hasRole("ADMIN") // this means "/admin" is accessible by admin
            .antMatchers("/user").hasAnyRole("USER", "ADMIN") // /user by both
            .antMatchers("/").permitAll()
            .and().formLogin()
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder = NoOpPasswordEncoder.getInstance()
}