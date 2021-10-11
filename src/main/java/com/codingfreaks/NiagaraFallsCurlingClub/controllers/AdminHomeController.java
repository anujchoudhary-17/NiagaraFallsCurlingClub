package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminHomeController {
	
	String adminId;
	

    @RequestMapping(value="/adminHome", method = RequestMethod.GET)
	public String signIn(Model model,RedirectAttributes redirectAttrs,@RequestParam("aid") String adminId) {
   
        System.out.println("ADMIN ID RECEIVED : "+adminId);
        model.addAttribute("adminid", adminId);
        this.adminId = adminId;
        model.addAttribute("name", "Kashish");
		return "views/adminHome";
	}

}
