package com.project.library.restcontroller;

import com.project.library.model.LibraryCard;
import com.project.library.service.CustomerService;
import com.project.library.service.LibraryCardService;
import com.project.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/member")
public class MemberRestController {
    @Autowired
    private LibraryCardService libraryCardService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = {"/", "/list"})
    public List<LibraryCard> all() {
        return libraryCardService.getAllLibraryCard();
    }
}
