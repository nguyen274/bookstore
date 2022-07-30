package com.project.library.controller;

import com.project.library.model.Course;
import com.project.library.model.Major;
import com.project.library.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String index(Model model){
        List<Major> majors = majorService.getAllMajor();

        model.addAttribute("majors", majors);

        return "major/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addMajor(Model model){
        model.addAttribute("major", new Major());

        return "major/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editMajor(@PathVariable("id") Long id, Model model){
        Optional<Major> majorEdit = majorService.findMajorById(id);
        if(majorEdit.isPresent()) {
            majorEdit.ifPresent(major -> model.addAttribute("major", major));
            return "/major/form";
        } else {
            return "redirect:/major/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Major major, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){

            return  "/major/form";
        }

        if(major.getId() == null){
            majorService.addNew(major);
            redirectAttributes.addFlashAttribute("successMsg", "'" + major.getMajorName() + "' đã được thêm ngành học mới.");
            return "redirect:/major/add";
        } else {
            Major updateMajor = majorService.saveMajor(major);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + major.getMajorName() + "' thành công. ");
            return "redirect:/major/edit/"+updateMajor.getId();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteMajor(@PathVariable("id") Long id, Model model){
        majorService.deleteMajor(id);
        return "redirect:/major/list";
    }

    }
