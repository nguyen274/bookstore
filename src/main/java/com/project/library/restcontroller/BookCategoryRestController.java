package com.project.library.restcontroller;

import com.project.library.model.BookCategory;
import com.project.library.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/category")
public class BookCategoryRestController {
    @Autowired
    private BookCategoryService bookCategoryService;

    @GetMapping(value = {"/","/list"})
    public List<BookCategory> all(){
        return bookCategoryService.getAllBookCategory();
    }
}
