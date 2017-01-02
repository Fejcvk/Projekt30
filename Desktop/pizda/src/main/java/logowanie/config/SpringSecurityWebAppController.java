package logowanie.config;

import com.stormpath.spring.config.StormpathWebSecurityConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityWebAppController extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .apply(StormpathWebSecurityConfigurer.stormpath()).and()
                .authorizeRequests()
                .antMatchers("/").permitAll();
    }
}
