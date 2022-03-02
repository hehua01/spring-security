package com.example.simple.config;

import com.example.simple.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @Date 2022/3/2 14:43
 * @Description
 */
@Configuration
// 这个注解配合@PreAuthorize("hasAuthority('ADMIN')")使用
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomerUserDetailService customerUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 信息在内存中
        auth.inMemoryAuthentication()
                .withUser("hehua")
                .password(bcryptPasswordEncoder().encode("456"))
                .roles("admin");

        // 信息在spring-security自带的数据库中
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123");

        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer =
                auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);
        if(!configurer.getUserDetailsService().userExists("hehua")){
            configurer.withUser("hehua").password(pwd).roles("ADMIN");
        }
        if(!configurer.getUserDetailsService().userExists("hehua02")){
            configurer.withUser("hehua02").password(pwd).roles("USER");
        }

        // 信息在自定义的账号数据库中
        auth.userDetailsService(customerUserDetailService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .antMatchers("/admin/**").hasRole("ADMIN")
                // .antMatchers("/user/**").hasRole("USER")
                // .antMatchers("/app/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
