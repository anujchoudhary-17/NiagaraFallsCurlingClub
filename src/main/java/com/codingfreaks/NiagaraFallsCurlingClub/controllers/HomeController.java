package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {


 
    @RequestMapping(value="/home", method = RequestMethod.GET)
	public String signIn(Model model,RedirectAttributes redirectAttrs,@RequestParam("uid") String attr) {
   
        System.out.println("USER ID RECEIVED : "+attr);
        model.addAttribute("userid", attr);
        model.addAttribute("name", "ANUJ");
		return "/views/home";
	}
    
}
