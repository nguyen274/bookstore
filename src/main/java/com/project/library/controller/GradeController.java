package com.project.library.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.project.library.model.Major;
import com.project.library.service.CourseService;
import com.project.library.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.library.model.Course;
import com.project.library.model.Grade;
import com.project.library.service.GradeService;



@Controller
@RequestMapping(value = "/grade")
public class GradeController {
	
	@Autowired
    private GradeService gradeService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private MajorService majorService;

    @ModelAttribute(name = "major")
    private List<Major> majorList(){
        return majorService.getAllBySort();
    }

    @ModelAttribute(name = "course")
    private List<Course> courseList(){
        return courseService.getAllBySort();
    }


    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String index(Model model){
        List<Grade> grade = gradeService.getAllGrade();

        model.addAttribute("grade", grade);

        return "grade/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGrade(Model model){

        model.addAttribute("grade", new Grade());

        return "grade/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGrade(@PathVariable("id") Long gradeId, Model model){
        Optional<Grade> gradeEdit = gradeService.findGradeById(gradeId);
        if(gradeEdit.isPresent()) {
            gradeEdit.ifPresent(grade -> model.addAttribute("grade", grade));
            return "/grade/form";
        } else {
            return "redirect:/grade/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Grade grade, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            System.out.println("here");

            return  "/grade/form";
        }
        System.out.println("hello:" + grade.toString());
        if(grade.getId() == null){
        	gradeService.addNew(grade);
            redirectAttributes.addFlashAttribute("successMsg", "'" + grade.getGradeName() + "' đã được thêm lớp mới.");
            return "redirect:/grade/add";
        } else {
        	Grade updateGrade = gradeService.saveGrade(grade);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + grade.getGradeName() + "' thành công. ");
            return "redirect:/grade/edit/"+updateGrade.getId();
        }

    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteGrade(@PathVariable("id") Long gradeId, Model model){
    	gradeService.deleteGrade(gradeId);
        return "redirect:/grade/list";
    }

}
