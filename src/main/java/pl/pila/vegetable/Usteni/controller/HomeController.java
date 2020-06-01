package pl.pila.vegetable.Usteni.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pila.vegetable.Usteni.model.Order;
import pl.pila.vegetable.Usteni.model.Users;
import pl.pila.vegetable.Usteni.repository.OrderProductRepository;
import pl.pila.vegetable.Usteni.repository.OrderRepository;
import pl.pila.vegetable.Usteni.repository.ProductRepository;
import pl.pila.vegetable.Usteni.repository.UserRepository;


import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@SessionAttributes({"userses", "orderid"})
public class HomeController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;


    public HomeController(OrderRepository orderRepository,
                           UserRepository userRepository, ProductRepository productRepository,
                           OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @GetMapping({"","/"})

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






        return "index";
    }

    @GetMapping("/duda")
    public String duda(){
        return "products/list";
    }


}
