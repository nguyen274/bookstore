package com.project.library.restcontroller;

import com.project.library.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// check unique
@RestController

public class StudentRestController {
//    @Autowired
//    private StudentServiceImpl ssi;
//    @PostMapping("/student/check_unique")
//    public String checkUnique(@Param("id") Long id, @Param("studentPhone") String studentPhone){
//        return ssi.checkUnique(id,studentPhone);
//    }
}
