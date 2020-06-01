package pl.pila.vegetable.Usteni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class HomeController {
    @GetMapping({"","/"})
    public String home(){
        return "index";
    }

    @GetMapping("/duda")
    public String duda(){
        return "products/list";
    }


}
