package tk.nenua4e.mc.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import tk.nenua4e.mc.server.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    UserService userService;

    public SecurityConfig(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void configure(WebSecurity web)
    {
        web
                .ignoring()
                .antMatchers("/index.html", "/assets/**", "/*.js", "/*.css", "/favicon.ico", "/*.woff*", "/*.ttf");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/news/feed").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(this.userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
