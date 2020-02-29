package tk.nenua4e.mc.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web
                .ignoring()
                .antMatchers("/index.html", "/assets/**", "/*.js", "/*.css", "/favicon.ico", "/*.woff*");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/project", "/contact").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .logout();
    }
}
