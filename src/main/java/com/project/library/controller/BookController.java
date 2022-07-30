package com.project.library.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.library.model.Author;
import com.project.library.model.Book;
import com.project.library.model.BookCategory;
import com.project.library.model.BookStore;
import com.project.library.model.Producer;
import com.project.library.service.AuthorService;
import com.project.library.service.BookCategoryService;
import com.project.library.service.BookService;
import com.project.library.service.BookStoreService;
import com.project.library.service.ProducerService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	private AuthorService authorService;
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	@Autowired
	private BookStoreService bookStoreService;
	
	@Autowired
	private ProducerService producerService;
	
	@ModelAttribute(name = "author")
	public List<Author> authors(){
		return authorService.getAllBySort();
	}
	
	@ModelAttribute(name = "bookCategory")
	public List<BookCategory> bookCategories(){
		return bookCategoryService.getAllBySort();
	}
	
	@ModelAttribute(name = "bookStore")
	public List<BookStore> bookStores(){
		return bookStoreService.getAllBySort();
	}
	
	@ModelAttribute(name = "producer")
	public List<Producer> producers(){
		return producerService.getAllBySort();
	}
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String showBookPage(Model model) {
		List<Book> books = bookService.getAllBook();
		model.addAttribute("books", books);
		
		return "book/list";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		
		return "/book/form";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		Optional<Book> bookEdit = bookService.findBookById(id);
		
		if(bookEdit != null) {
			bookEdit.ifPresent(book -> model.addAttribute("book", book));
			return "/book/form";
		} else {
			return "redirect:/book/add";
		}
	}
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Book book, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
    	System.out.println(book);
    	System.out.println(bindingResult);
    	if( bindingResult.hasErrors()){
            return  "/book/form";
            
        }
        if(book.getId() == null){
            bookService.addNew(book);
            redirectAttributes.addFlashAttribute("successMsg", "'" + book.getBookName() + "' đã được thêm tác giả mới.");
            return "redirect:/book/add";
        } else {
            Book updateBook = bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + book.getBookName() + "' thành công. ");
            return "redirect:/book/edit/"+updateBook.getId();
        }
    }
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.deleteBook(id);
		return "redirect:/book";
	}
    
}
