package com.project.library.controller;

import com.project.library.common.Constants;
import com.project.library.model.Customer;
import com.project.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = {"/list","/"}, method = RequestMethod.GET)
    public String showCustomerPage(Model model){
        List<Customer> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);

        return "customer/list";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStudent(Model model){
        model.addAttribute("customer", new Customer());

        return "customer/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCustomer(@PathVariable("id") Long id, Model model){
        Optional<Customer> customerEdit = customerService.findCustomerById(id);
        if(customerEdit.isPresent()) {
            customerEdit.ifPresent(customer -> model.addAttribute("customer", customer));
            return "/customer/form";
        } else {
            return "redirect:/customer/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Customer customer, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){

            return  "/customer/form";
        }

        if(customer.getId() == null){
            customer.setStatus(Constants.CUSTOMER_NOT_STATUS);
            customerService.addNew(customer);
            redirectAttributes.addFlashAttribute("successMsg", "'" + customer.getCustomerName() + "' đã được thêm sv mới.");
            return "redirect:/customer/add";
        } else {
            customer.setStatus(Constants.CUSTOMER_NOT_STATUS);
            Customer updateStudent = customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + customer.getCustomerName() + "' thành công. ");
            return "redirect:/customer/edit/"+updateStudent.getId();
        }

    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable("id") Long id, Model model){
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }



}
