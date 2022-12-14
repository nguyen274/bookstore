package com.project.library.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.project.library.common.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
	public String save(@Valid Book book, final BindingResult bindingResult, final RedirectAttributes redirectAttributes,
	@RequestParam("image") MultipartFile multipartFile ) throws IOException {
		if (bookService.checkUniqueISBN(book.getBookCode()) > 0){
			redirectAttributes.addFlashAttribute("successMsgs", "M?? ISBN '" + book.getBookCode() + "' ???? ???????c ????ng k??.");
			return "redirect:/book/add";
		}
		if(!multipartFile.isEmpty()){
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			book.setImage(fileName);
			book.setStatus(1);
			Book saveBook = bookService.saveBook(book);

			String uploadDir = "book-photos/" +saveBook.getId();
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
		} else {
			if(book.getImage().isEmpty()) book.setImage(null);
			bookService.saveBook(book);
		}

		///
    	System.out.println(book);
    	System.out.println(bindingResult);
    	if( bindingResult.hasErrors()){
            return  "/book/form";
            
        }



        if(book.getId() == null){
            bookService.addNew(book);
            redirectAttributes.addFlashAttribute("successMsg", "'" + book.getBookName() + "' ???? ???????c th??m s??ch m???i.");
            return "redirect:/book/list";
        } else {
            Book updateBook = bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMsg", "Thay ?????i '" + book.getBookName() + "' th??nh c??ng. ");
            return "redirect:/book/edit/"+updateBook.getId();
        }
    }
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.deleteBook(id);
		return "redirect:/book";
	}
    
}
