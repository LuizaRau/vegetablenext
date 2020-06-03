package pl.pila.vegetable.Usteni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pila.vegetable.Usteni.model.Product;
import pl.pila.vegetable.Usteni.repository.ProductRepository;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/product/")
public class ProductController {
    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



    @RequestMapping("/list")
    @ResponseBody
    public String listProducts(){

        return "Dupa";
    }

    @GetMapping("/addp")
    @ResponseBody
    public String addProducts(){
        Product p1 = new Product();
        p1.setName("buraki");
        p1.setJm("kg");
        p1.setPrice(3.00);
        p1.setDescription("czerwone podłużne lub okrągłe");
        productRepository.save(p1);
        Product p2 = new Product();
        p2.setName("pietruszka");
        p2.setJm("kg");
        p2.setPrice(5.00);
        p2.setDescription("z zeszłego roku");
        productRepository.save(p2);


        return " " + p1.toString() + p2.toString();
    }

    @GetMapping("/listp")
    public String listProducts(Model model){
        Iterable<Product> products= productRepository.findAll();

        model.addAttribute("products",products);

        return "products/list";

    }
    @GetMapping("/add")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product" ,product);



        return "products/add";

    }
    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product){


        productRepository.save(product);



        return "redirect:listp";

    }




}
