package com.project.library.controller;

import com.project.library.common.Constants;
import com.project.library.model.BookCategory;
import com.project.library.model.CallCard;
import com.project.library.model.Course;
import com.project.library.service.BookCategoryService;
import com.project.library.service.CallCardService;
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
@RequestMapping(value = "/issue")
public class CallCardController {
    @Autowired
    private CallCardService callCardService;

    @Autowired
    private BookCategoryService bookCategoryService;

    @ModelAttribute(name = "cardTypes")
    public List<String> cardTypes(){
        return Constants.MEMBER_TYPES;
    }

    @ModelAttribute("categories")
    public List<BookCategory> getCategories(){
        return bookCategoryService.getAllBySort();
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listIssuePage(Model model) {
        model.addAttribute("issues", callCardService.getAllUnreturned());
        return "/issue/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newIssuePage(Model model) {
        return "/issue/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editIssuePage(@PathVariable Long id, Model model){
        Optional<CallCard> courseEdit = callCardService.findById(id);
        if(courseEdit.isPresent()) {
            courseEdit.ifPresent(course -> model.addAttribute("issue", course));
            return "/issue/edit";
        } else {
            return "redirect:/issue/list";
        }
    }

    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String printIssuePage(@PathVariable Long id, Model model){
        Optional<CallCard> courseEdit = callCardService.findById(id);
        if(courseEdit.isPresent()) {
            courseEdit.ifPresent(course -> model.addAttribute("issue", course));
            return "/issue/print";
        } else {
            return "redirect:/issue/list";
        }
    }



}
