package com.luv2code.demo.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public RedirectView main(){
        return new RedirectView("/customer/list");
//        return "redirect:/customer/list";
    }
}
