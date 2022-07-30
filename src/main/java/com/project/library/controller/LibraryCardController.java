package com.project.library.controller;

import com.project.library.common.Constants;
import com.project.library.model.Course;
import com.project.library.model.Customer;
import com.project.library.model.LibraryCard;
import com.project.library.model.Student;
import com.project.library.service.CustomerService;
import com.project.library.service.LibraryCardService;
import com.project.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/library-card")
public class LibraryCardController {

    @Autowired
    private LibraryCardService libraryCardService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CustomerService customerService;

    @ModelAttribute(name = "students")
    public List<Student> getAllBySort(){
        return studentService.getAllBySort();
    }

    @ModelAttribute(name = "customers")
    public List<Customer> getSort(){return customerService.getAllBySort(); }

    @ModelAttribute(name = "cardTypes")
    public List<String> cardTypes(){
        return Constants.MEMBER_TYPES;
    }

    @RequestMapping(value = {"/", "/list"},  method = RequestMethod.GET)
    public String showCardPage(Model model){
        List<LibraryCard> libraryCardList = libraryCardService.getAllLibraryCard();
        model.addAttribute("cards", libraryCardList);
        return "/library-card/list";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCardPage(Model model){
        model.addAttribute("libraryCard", new LibraryCard());
        return "/library-card/form";
    }

    @RequestMapping(value = "/add-customer", method = RequestMethod.GET)
    public String addCardCustomerPage(Model model){
        model.addAttribute("libraryCard1", new LibraryCard());
        return "/library-card/form-customer";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCardPage(@PathVariable(name = "id") Long id, Model model){
        Optional<LibraryCard> courseEdit = libraryCardService.findLibraryCardById(id);
        if(courseEdit.isPresent()) {
            courseEdit.ifPresent(card -> model.addAttribute("card", card));
            return "/library-card/form";
        } else {
            return "redirect:/library-card/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCard(@Valid LibraryCard libraryCard, BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors() ) {
            return "/library-card/form";
        }

        if( libraryCard.getId() == null ) {
            Student student = libraryCard.getStudent();
            student.setStatus(Constants.STUDENT_HAVE_STATUS);
            studentService.saveStudent(student);

            libraryCard.setName(student.getStudentName());
            libraryCardService.addNew(libraryCard);
            redirectAttributes.addFlashAttribute("successMsg", "'" + libraryCard.getStudent().getStudentName()+"' is added as a new member.");
            return "redirect:/library-card/add";
        } else {
            LibraryCard updatedMember = libraryCardService.saveLibraryCard( libraryCard );
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + libraryCard.getStudent().getStudentName()+"' are saved successfully. ");
            return "redirect:/library-card/edit/" + updatedMember.getId();
        }
    }

    @RequestMapping(value = "/save-customer", method = RequestMethod.POST)
    public String saveCardCustomer(@Valid LibraryCard libraryCard, BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors() ) {
            return "/library-card/form-customer";
        }

        if( libraryCard.getId() == null ) {
            Customer customer = libraryCard.getCustomer();
            customer.setStatus(Constants.CUSTOMER_STATUS);
            customerService.saveCustomer(customer);

            libraryCardService.addNew(libraryCard);
            redirectAttributes.addFlashAttribute("successMsg", "'" + libraryCard.getCustomer().getCustomerName()+"' is added as a new member.");
            return "redirect:/library-card/add-customer";
        } else {
            LibraryCard updatedMember = libraryCardService.saveLibraryCard( libraryCard );
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + libraryCard.getCustomer().getCustomerName()+"' are saved successfully. ");
            return "redirect:/library-card/edit/" + updatedMember.getId();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeCard(@PathVariable(name = "id") Long id, Model model){
        libraryCardService.deleteLibraryCard(id);
        return "redirect:/library-card/list";
    }

}
