package pl.pila.vegetable.Usteni.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pila.vegetable.Usteni.model.Product;
import pl.pila.vegetable.Usteni.model.Users;
import pl.pila.vegetable.Usteni.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String testUser(){
        Users u1 = new Users();
        u1.setPassword("tata");
        u1.setUsername("Jan");
        u1.setLastName("Kowalski");
        u1.setLogin("jankowalski");
        u1.setEmail("jan@kowalski.pl");
        u1.setPassword("janek1");
        Users u2 = new Users();
        u2.setUsername("Zbyszek");
        u2.setLastName("Nowak");
        u2.setLogin("zbigniewnowak");
        u2.setEmail("zbigniew@nowak.pl");
        u2.setPassword("zbyszek1");
        userRepository.save(u1);
        userRepository.save(u2);

        return "User" +u1.toString() + u2.toString();
    }
    @GetMapping("/listu")
    public String listUsers(Model model){
        ArrayList<Users> users = new ArrayList<Users>(0);
        users = (ArrayList<Users>) userRepository.findAll();
        model.addAttribute("users",users);

        return "user/list";

    }
}
