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

import com.project.library.model.Course;
import com.project.library.service.CourseService;



@Controller
@RequestMapping(value = "/course")
public class CourseController {
	
	@Autowired
    private CourseService courseService;


    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String index(Model model){
        List<Course> course = courseService.getAllCourse();

        model.addAttribute("course", course);

        return "course/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCourse(Model model){
    	Course course = new Course();
        model.addAttribute("course", new Course());

        return "course/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCourse(@PathVariable("id") Long courseId, Model model){
        Optional<Course> courseEdit = courseService.findCourseById(courseId);
        if(courseEdit.isPresent()) {
            courseEdit.ifPresent(course -> model.addAttribute("course", course));
            return "/course/form";
        } else {
            return "redirect:/course/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Course course, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            System.out.println("here");

            return  "/course/form";
        }
        System.out.println("hello:" + course.toString());
        if(course.getId() == null){
        	courseService.addNew(course);
            redirectAttributes.addFlashAttribute("successMsg", "'" + course.getCourseName() + "' đã được thêm khóa mới.");
            return "redirect:/course/add";
        } else {
        	Course updateCourse = courseService.saveCourse(course);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + course.getCourseName() + "' thành công. ");
            return "redirect:/course/edit/"+updateCourse.getId();
        }

    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") Long courseId, Model model){
    	courseService.deleteCourse(courseId);
        return "redirect:/course/list";
    }

}
