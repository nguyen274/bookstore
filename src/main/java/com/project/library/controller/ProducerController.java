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

import com.project.library.model.Producer;
import com.project.library.service.ProducerService;

@Controller
@RequestMapping(value = "/producer")
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String index(Model model) {
		List<Producer> produceres = producerService.getAllProducer();
		
		model.addAttribute("produceres", produceres);
		return "/producer/list";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProducer(Model model) {
	Producer producer = new Producer();
	model.addAttribute("producer", producer);
	
	return "/producer/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editProducer(@PathVariable("id") Long id, Model model) {
    	Optional<Producer> producerEdit = producerService.findProducerById(id);
    	
    	  
    	if(producerEdit != null) {
    		producerEdit.ifPresent(producer -> model.addAttribute("producer", producer));
    		return "/producer/form";
    	} else {
    		return "redirect:/producer/add";
    	}
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Producer producer, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            return  "/producer/form";
        }
		if (producerService.checkUniqueCode(producer.getProducerCode()) > 0){
			redirectAttributes.addFlashAttribute("successMsgs", "M?? NXB '" + producer.getProducerCode() + "' ???? ???????c ????ng k??.");
			return "redirect:/producer/add";
		}
		if (producerService.checkUniqueName(producer.getProducerName()) > 0){
			redirectAttributes.addFlashAttribute("successMsgs1", "T??n NXB '" + producer.getProducerCode() + "' ???? ???????c ????ng k??.");
			return "redirect:/producer/add";
		}

        if(producer.getId() == null){
            producerService.addNew(producer);
            redirectAttributes.addFlashAttribute("successMsg", "'" + producer.getProducerName() + "' ???? ???????c th??m nh?? xu???t b???n m???i.");
            return "redirect:/producer/add";
        } else {
            Producer updateProducer = producerService.saveProducer(producer);
            redirectAttributes.addFlashAttribute("successMsg", "Thay ?????i '" + producer.getProducerName() + "' th??nh c??ng. ");
            return "redirect:/producer/edit/"+updateProducer.getId();
        }

    }
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteProducer(@PathVariable("id") Long id, Model model){
    	producerService.deleteProducer(id);
        return "redirect:/producer";
    }
}
