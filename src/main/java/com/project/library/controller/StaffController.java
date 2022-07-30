package com.project.library.controller;

import com.project.library.model.Staff;
import com.project.library.model.User;
import com.project.library.service.StaffService;
import com.project.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private UserService userService;



    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    public String pageStaff(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "/staff/list";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
        public String addStaff(Model model){
        Staff staff = new Staff();
        model.addAttribute("staff",staff);
        return "/staff/form";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editStaff(@PathVariable("id") Long id, Model model) {
        Optional<Staff> staffEdit = staffService.findStaffById(id);


        if(staffEdit != null) {
            staffEdit.ifPresent(staff -> model.addAttribute("staff", staff));
            return "/staff/form";
        } else {
            return "redirect:/staff/add";
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Staff staff, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            return  "/staff/form";
        }

        if(staff.getId() == null){
            staffService.addNew(staff);
            redirectAttributes.addFlashAttribute("successMsg", "'" + staff.getStaffName() + "' đã được thêm nhà xuất bản mới.");
            return "redirect:/staff/register/"+staff.getId();
        } else {
            Staff updateStaff = staffService.saveStaff(staff);
            redirectAttributes.addFlashAttribute("successMsg", "Thay đổi '" + staff.getStaffName() + "' thành công. ");
            return "redirect:/staff/edit/"+updateStaff.getId();
        }

    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model){
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }
}
