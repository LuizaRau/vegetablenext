package pl.pila.vegetable.Usteni.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomDaoAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Autowired
    public CustomDaoAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        Assert.notNull(name, "Trzeba podać nazwę użytkownika");
        Assert.notNull(credentials, "Reguły nie mogą być puste");
        if (credentials instanceof String) {
            return null;
        }
        String password = credentials.toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        boolean passwordMatch = userDetails.getPassword().equals(password);
        if (!passwordMatch) {
            throw new BadCredentialsException("Nie właściwe hasło");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name,password,userDetails.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
