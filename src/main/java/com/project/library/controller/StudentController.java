package com.project.library.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.project.library.common.Constants;
import com.project.library.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.library.model.Grade;
import com.project.library.model.Student;
import com.project.library.service.StudentService;



@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Autowired
    private StudentService studentService;

    /*@Autowired
    private GradeService gradeService;

    @ModelAttribute(name = "grade")
    private List<Grade> gradeList(){
        return gradeService.getAllBySort();
    }*/


    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String index(Model model){
        List<Student> student = studentService.getAllStudent();

        model.addAttribute("student", student);

        return "student/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStudent(Model model){
    	Student student = new Student();
        model.addAttribute("student", new Student());

        return "student/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editStudent(@PathVariable("id") Long studentId, Model model){
        Optional<Student> studentEdit = studentService.findStudentById(studentId);
        if(studentEdit.isPresent()) {
            studentEdit.ifPresent(student -> model.addAttribute("student", student));
            return "/student/form";
        } else {
            return "redirect:/student/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Student student, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            System.out.println("here");

            return  "/student/form";
        }
       /* if (studentService.checkUniquePhone(student.getStudentPhone()) > 0){
            redirectAttributes.addFlashAttribute("successMsgs", "Số Điện Thoại '" + student.getStudentPhone() + "' đã được đăng kí.");
            return "redirect:/student/add";
//            bindingResult.rejectValue("studentPhone","error.phone");
        }*/
        System.out.println("hello:" + student.toString());
        if(student.getId() == null){
            student.setStatus(Constants.STUDENT_NOT_STATUS);
        	studentService.addNew(student);
            redirectAttributes.addFlashAttribute("successMsg", "'" + student.getStudentName() + "' đã được thêm sv mới.");
            return "redirect:/student/add";
        } else {
            student.setStatus(Constants.STUDENT_NOT_STATUS);
        	Student updateStudent = studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + student.getStudentName() + "' thành công. ");
            return "redirect:/student/edit/"+updateStudent.getId();
        }

    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model){
    	studentService.deleteStudent(studentId);
        return "redirect:/student/list";
    }
    @ExceptionHandler(Exception.class)
    public String handleUnwantedException(SQLException e, final RedirectAttributes redirectAttributes) {
        e.printStackTrace();
        redirectAttributes.addFlashAttribute("successMsgs", "Số Điện Thoại đã được đăng kí.");
        return "redirect:/student/add";
    }
}
