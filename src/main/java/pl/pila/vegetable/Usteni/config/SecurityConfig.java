package pl.pila.vegetable.Usteni.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import pl.pila.vegetable.Usteni.component.CustomDaoAuthenticationProvider;
import pl.pila.vegetable.Usteni.services.JpaUserDetailsService;




@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

JpaUserDetailsService userDetailsService;
CustomDaoAuthenticationProvider authenticationProvider;

@Autowired
public SecurityConfig(JpaUserDetailsService userDetailsService,CustomDaoAuthenticationProvider authenticationProvider){
    this.authenticationProvider = authenticationProvider;
    this.userDetailsService =userDetailsService;
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(userDetailsService);
    auth.authenticationProvider(authenticationProvider);
}








    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/product/**").hasAuthority("USER")
                .antMatchers("/user_panel").hasAuthority("USER")
                .antMatchers("/admin_panel").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user_panel", true)
                .and().csrf().disable();









    }


    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
