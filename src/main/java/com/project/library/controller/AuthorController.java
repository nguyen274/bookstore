package com.project.library.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.library.model.Author;
import com.project.library.service.AuthorService;



@Controller
@RequestMapping(value = "/author")
public class AuthorController {
	
	@Autowired
    private AuthorService authorService;


    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    public String index(Model model){
        List<Author> authors = authorService.getAllAuthor();

        model.addAttribute("authors", authors);

        return "author/list";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAuthor(Model model){
        model.addAttribute("author", new Author());

        return "author/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAuthor(@PathVariable("id") Long authorId, Model model){
        Optional<Author> authorEdit = authorService.findAuthorById(authorId);
        if(authorEdit.isPresent()) {
            authorEdit.ifPresent(author -> model.addAttribute("author", author));
            return "/author/form";
        } else {
            return "redirect:/author/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Author author, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            System.out.println("here");

            return  "/author/form";
        }

        /*if (authorService.checkUniqueCode(author.getAuthorCode()) > 0){
            redirectAttributes.addFlashAttribute("successMsgs", "M?? T??c Gi??? '" + author.getAuthorCode() + "' ???? ???????c ????ng k??.");
            return "redirect:/author/add";
        }*/

        System.out.println("hello:" + author.toString());
        if(author.getId() == null){
            authorService.addNew(author);
            redirectAttributes.addFlashAttribute("successMsg", "'" + author.getAuthorName() + "' ???? ???????c th??m t??c gi??? m???i.");
            return "redirect:/author/add";
        } else {
            Author updateAuthor = authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("successMsg", "Thay ?????i '" + author.getAuthorName() + "' th??nh c??ng. ");
            return "redirect:/author/edit/"+updateAuthor.getId();
        }

    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteAuthor(@PathVariable("id") Long authorId){
        authorService.deleteAuthor(authorId);
        return "redirect:/author/list";
    }
    @ExceptionHandler(Exception.class)
    public String handleUnwantedException(SQLException e, final RedirectAttributes redirectAttributes) {
        e.printStackTrace();
        redirectAttributes.addFlashAttribute("successMsgs", "M?? T??c Gi??? ???? ???????c ????ng k??.");
        return "redirect:/author/add";
    }

}
