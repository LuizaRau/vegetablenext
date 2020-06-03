package pl.pila.vegetable.Usteni.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pila.vegetable.Usteni.model.Order;
import pl.pila.vegetable.Usteni.model.Product;
import pl.pila.vegetable.Usteni.model.Users;
import pl.pila.vegetable.Usteni.repository.OrderProductRepository;
import pl.pila.vegetable.Usteni.repository.OrderRepository;
import pl.pila.vegetable.Usteni.repository.ProductRepository;
import pl.pila.vegetable.Usteni.repository.UserRepository;
import pl.pila.vegetable.Usteni.services.SignUpService;


import java.util.List;


@Controller
//@SessionAttributes({"userses", "orderid"})
public class HomeController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final SignUpService signUpService;


    public HomeController(OrderRepository orderRepository,
                           UserRepository userRepository, ProductRepository productRepository,
                           OrderProductRepository orderProductRepository,SignUpService signUpService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.signUpService = signUpService;
    }

    @GetMapping({"/nic","/ddd"})

    public String home(Model model){
        long orderid = 0;
        Users user = userRepository.findById(Long.valueOf(1)).get();
        model.addAttribute("userses",user);
        List<Order> orders = (List<Order>) orderRepository.findAll();

        for (Order val: orders
                ) {
            orderid = val.getId();
        }
        orderid = orderid+1;
        model.addAttribute("orderid",orderid);






        return "user_panel";
    }

    @RequestMapping("/user_panel")
    public String listProducts(Model model){
        Iterable<Product> products= productRepository.findAll();

        model.addAttribute("products",products);

        return "user_panel";

    }


    @RequestMapping({"/admin_panel"})
    public String adminPanel(){
        return "admin_panel";
    }



    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/sign_up")
    @ResponseBody
    public String singUp(){
        Users userToAdd = new Users();
        userToAdd.setUsername("Zbigniew");
        userToAdd.setPassword("zbigniew");
        userToAdd.setFullName("Zbigniew Kowalski");
        userToAdd.setEmail("zbyszek@kowalski");
        signUpService.signUpUser(userToAdd);
        return "Użytkownik dodany";
    }
    @GetMapping("/register")
    public String registerUser(Model model){
        Users user = new Users();
        model.addAttribute("user",user);

        return "register";

    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute Users user){
        user.setActive(true);
        signUpService.signUpUser(user);

        return "/login";
    }
    @GetMapping({"/",""})
    public String indexPage(Model model){
        Iterable<Product> products= productRepository.findAll();
        model.addAttribute("products",products);


        return "index";

    }
    @GetMapping("/logo")
    public String logoutPage(Model model){



        return "index";

    }

}