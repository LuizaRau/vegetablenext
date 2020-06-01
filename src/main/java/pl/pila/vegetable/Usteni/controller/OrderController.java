package pl.pila.vegetable.Usteni.controller;

import org.springframework.data.annotation.Transient;
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
        long id =2;
        Optional<Users> user = userRepository.findById(id);
        Users u = user.get();
        Order order = new Order();
        order.setUser(u);
        orderRepository.save(order);

        return " " + order.toString();


    }

    @GetMapping("/addtoorder/{id}")
    @ResponseBody
    public String addToOrder(@PathVariable long id){
        Product product = productRepository.findById(id).get();
        Order order = orderRepository.findById(Long.valueOf(1)).get();
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
    @GetMapping("/formorder")
    public String formAddProductToOrder(Model model){
        Product product = productRepository.findById((long) 1).get();

        OrderProduct op = new OrderProduct();

       model.addAttribute("product",product);
        model.addAttribute("op",op);

        return "order/add";

    }

    @PostMapping("/order/add")
    @ResponseBody
    public String saveProductToOrder(@ModelAttribute OrderProduct op){
        Order order = orderRepository.findById(Long.valueOf(1)).get();
        Product product = productRepository.findById(Long.valueOf(1)).get();
       List<OrderProduct> oplist = orderProductRepository.findOrderProductsByOrder_Id(Long.valueOf(1));
        for (OrderProduct val:oplist
             ) {
            if (val.getProduct().getId()==1){
                return "produkt jest już w zamówieniu";
            }

        }

        op.setProduct(product);
        op.setOrder(order);
        orderProductRepository.save(op);

        return "added";
    }







}
