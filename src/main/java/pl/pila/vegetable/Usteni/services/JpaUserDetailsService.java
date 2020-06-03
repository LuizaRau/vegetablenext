package pl.pila.vegetable.Usteni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pila.vegetable.Usteni.model.Users;
import pl.pila.vegetable.Usteni.repository.UserRepository;

import java.util.Optional;
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public JpaUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usernameOptional = userRepository.findByUsername(username);
        if(!usernameOptional.isPresent()){
            throw new UsernameNotFoundException("Nie ma takiego użytkownika" + username);
        }
        Users user = usernameOptional.get();
        return user;
    }
}
