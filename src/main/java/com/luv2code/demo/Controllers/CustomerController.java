package com.luv2code.demo.Controllers;

import com.luv2code.demo.Entity.Customer;
import com.luv2code.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(@Qualifier("customerService") CustomerService customerService) {
        this.customerService=customerService;
    }

    @GetMapping("/list")
    public String getList(Model model){

        List<Customer> customerList = customerService.getAllSortedByFirstName();
        model.addAttribute("customers",customerList);
        return "list-customers";
    }

    @GetMapping("/showFormToAdd")
    public String showFormToAdd(Model model){
        model.addAttribute("customer",new Customer());
        return "customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute(name = "customer") Customer customer, Model model){
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public ModelAndView showFormForUpdate(@PathVariable String id, ModelAndView modelAndView){
        try{
            Integer customerId = Integer.valueOf(id);

            //get the customer from service implementation
            Customer customer = customerService.findById(customerId);

            //set customer as model attribute to pre-populate the form
            modelAndView.addObject("customer",customer);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        //setting view name
        modelAndView.setViewName("customer-form");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id ){
        customerService.deleteById(id);
//        new RedirectView("/customer/list");
        return "redirect:/customer/list";
    }





}
