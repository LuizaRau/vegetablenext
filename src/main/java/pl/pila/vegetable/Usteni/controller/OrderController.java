package pl.pila.vegetable.Usteni.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pila.vegetable.Usteni.model.Order;
import pl.pila.vegetable.Usteni.model.OrderProduct;
import pl.pila.vegetable.Usteni.model.Product;
import pl.pila.vegetable.Usteni.model.Users;
import pl.pila.vegetable.Usteni.repository.OrderProductRepository;
import pl.pila.vegetable.Usteni.repository.OrderRepository;
import pl.pila.vegetable.Usteni.repository.ProductRepository;
import pl.pila.vegetable.Usteni.repository.UserRepository;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;


    public OrderController(OrderRepository orderRepository,
                           UserRepository userRepository, ProductRepository productRepository,
                           OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addOrder(){
        Integer id =2;
        Optional<Users> user = userRepository.findById(id);
        Users u = user.get();
        Order order = new Order();
        order.setUser(u);
        orderRepository.save(order);

        return " " + order.toString();


    }

    @GetMapping("/addtoorder/{id}")
    @ResponseBody
    public String addToOrder(@PathVariable Integer id){
        Product product = productRepository.findById(id).get();
        Order order = orderRepository.findById(1).get();
        OrderProduct op = new OrderProduct();
        op.setProduct(product);
        op.setOrder(order);
        op.setQuantity(0.5);
        orderProductRepository.save(op);
        return "" + productRepository.findById(id).toString();
    }

    /*@GetMapping("/listorder/{id}")
    public String listOrderById(@PathVariable Integer id){

    }*/
    @GetMapping("/orderadd/{id}")
    public String formAddProductToOrder(Model model, @PathVariable Integer id){
        Product product;
        product = productRepository.findById(id).get();
        OrderProduct op = new OrderProduct();
        model.addAttribute("product",product);
        model.addAttribute("op",op);

        return "/order/add";

    }

    @PostMapping("/orderadd")
        public String saveProductToOrder(@ModelAttribute OrderProduct op,@ModelAttribute Product product, Model model,
                                        HttpSession ses){
        Iterable<Product> products= productRepository.findAll();

        model.addAttribute("products",products);

        Order order = new Order();
        Integer orderId = (Integer) ses.getAttribute("orderid");
        List<OrderProduct> opList = orderProductRepository.findOrderProductsByOrder_Id(orderId);

        Product p = productRepository.findById(product.getId()).get();


        for (OrderProduct val:opList
             ) {
            if (val.getProduct().getId()==product.getId()){
                Integer idOrderProduct = val.getId();
                orderProductRepository.deleteById(idOrderProduct);
                Integer idProduct = product.getId();
                System.out.println(idProduct);
                model.addAttribute("op",op);





                return "redirect:/orderadd1/"+idProduct;
            }

        }

        double sum = op.getQuantity()*p.getPrice();
        op.setSum(sum);
        order.setId(orderId);
        op.setProduct(product);
        op.setOrder(order);
        orderProductRepository.save(op);

        return "user_panel";
    }


    @GetMapping("/orderadd1/{id}")
    public String formAddProductToOrder1(Model model, @PathVariable Integer id){
        Product product;
        product = productRepository.findById(id).get();
        OrderProduct op = new OrderProduct();
        model.addAttribute("product",product);
        model.addAttribute("op",op);

        return "/order/add1";

    }
    @GetMapping("/cart/{id}")
    public String showCart(Model model,@PathVariable Integer id){
        double total = 0.0;
        List<OrderProduct> opList = orderProductRepository.findOrderProductsByOrder_Id(id);
        model.addAttribute("cartA",opList);
        for (OrderProduct val:opList
        ) {
             total = total + val.getSum();

            }
        model.addAttribute("total",total);



        return "cart";
    }
    @GetMapping("/saveorder/{id}")
    public String saveOrder(@PathVariable Integer id,HttpSession ses){
        System.out.println(id);
        Optional<Users> user = userRepository.findById(id);
        Users u = user.get();
        Order order = new Order();
        order.setUser(u);
        orderRepository.save(order);



        return "redirect:/start_panel";
    }







}
