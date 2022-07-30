package com.project.library.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.library.model.BookStore;
import com.project.library.service.BookStoreService;

@Controller
@RequestMapping(value = "/book-store")
public class BookStoreController {
	@Autowired
	private BookStoreService bookStoreService;
	
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String index(Model model) {
		List<BookStore> bookStores = bookStoreService.getAllBookStore();
		
		model.addAttribute("bookStores", bookStores);
		
		return "/book-store/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBookStore(Model model) {
		BookStore bookStore = new BookStore();
		model.addAttribute("bookStore", bookStore);
		
		return "/book-store/form";
	}
	
	 @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	 public String editBookStore(@PathVariable("id") Long id, Model model) {
		 
		 Optional<BookStore> bookStoreEdit = bookStoreService.findBookStoreById(id);
		 
		 if(bookStoreEdit != null) {
			 bookStoreEdit.ifPresent(bookStore -> model.addAttribute("bookStore",bookStore));
			 return "/book-store/form";
		 } else {
			 return "redirect:/book-store/add";
		 }
	 }
	
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	 public String save(@Valid BookStore bookStore, final BindingResult bindingResult, final RedirectAttributes redirectAttributes ) {
		 if(bindingResult.hasErrors()) {
			 return "/book-store/form";
		 }
		 
		 if(bookStore.getId() == null) {
			 bookStoreService.addNew(bookStore);
			 redirectAttributes.addFlashAttribute("successMsg", "'" + bookStore.getBookStoreName() + "' đã được thêm tác giả mới.");
			 return "redirect:/book-store/add";
		 } else {
			
			 BookStore updateBookStore = bookStoreService.saveBookStore(bookStore);
			 redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + bookStore.getBookStoreName() + "' thành công. ");
	         return "redirect:/book-store/edit/"+updateBookStore.getId();
		 }
	 }
	 
	 @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	 public String deleteBookStore(@PathVariable("id") Long id, Model model) {
		 bookStoreService.deleteBookStore(id);
		 return "redirect:/book-store";
	 }
}
