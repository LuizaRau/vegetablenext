package pl.pila.vegetable.Usteni.services.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.pila.vegetable.Usteni.model.Role;
import pl.pila.vegetable.Usteni.model.Users;
import pl.pila.vegetable.Usteni.repository.RoleRepository;
import pl.pila.vegetable.Usteni.repository.UserRepository;
import pl.pila.vegetable.Usteni.services.SignUpService;

import java.util.*;

@Service
public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository,RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Users signUpUser(Users user) {
        Role userRole = roleRepository.findByName("USER").get();
        Role adminRole = roleRepository.findByName("ADMIN").get();


        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
       Users savedUser = userRepository.save(user);

        return savedUser;
    }
}
