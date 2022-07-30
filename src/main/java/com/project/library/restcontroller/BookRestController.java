package com.project.library.restcontroller;

import com.project.library.model.Book;
import com.project.library.model.BookCategory;
import com.project.library.service.BookCategoryService;
import com.project.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/book")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryService bookCategoryService;

    @GetMapping(value = {"/", "/list"})
    public List<Book> all(){
        return bookService.getAllBook();
    }

    @GetMapping(value = "/{id}/list")
    public List<Book> get(@PathVariable(name = "id") Long id){
        BookCategory category = bookCategoryService.get(id);
        return bookService.getByBookCategory(category);
    }

    @GetMapping(value = "/{id}/available")
    public List<Book> getAvailableBooks(@PathVariable(name = "id") Long id){
        BookCategory category = bookCategoryService.get(id);
        return bookService.getAvailableByCategory(category);
    }
}
