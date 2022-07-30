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

import com.project.library.model.BookCategory;
import com.project.library.service.BookCategoryService;

@Controller
@RequestMapping(value = "/book-category")
public class BookCategoryController {
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String index(Model model) {
		List<BookCategory> bookCategories = bookCategoryService.getAllBookCategory();
		
		model.addAttribute("bookCategories", bookCategories);
		
		return "/book-category/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBookCategory(Model model) {
		BookCategory category = new BookCategory();
		model.addAttribute("bookCategory", category);
		
		return "/book-category/form";
	}
	
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBookCategory(@PathVariable("id") Long categoryId, Model model) {
    	Optional<BookCategory> bookCategoryEdit = bookCategoryService.findBookCategoryById(categoryId);
    	
  
    	if(bookCategoryEdit != null) {
    		bookCategoryEdit.ifPresent(bookCategory -> model.addAttribute("bookCategory", bookCategory));
    		return "/book-category/form";
    	} else {
    		return "redirect:/book-category/add";
    	}
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid BookCategory bookCategory, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            return  "/book-category/form";
        }
        
        if(bookCategory.getId() == null){
            bookCategoryService.addNew(bookCategory);
            redirectAttributes.addFlashAttribute("successMsg", "'" + bookCategory.getBookCategoryName() + "' đã được thêm tác giả mới.");
            return "redirect:/book-category/add";
        } else {
            BookCategory updateAuthor = bookCategoryService.saveBookCategory(bookCategory);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + bookCategory.getBookCategoryName() + "' thành công. ");
            return "redirect:/book-category/edit/"+updateAuthor.getId();
        }

    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteBookCategory(@PathVariable("id") Long categoryId, Model model){
    	bookCategoryService.deleteBookCategory(categoryId);
        return "redirect:/book-category";
    }
}
