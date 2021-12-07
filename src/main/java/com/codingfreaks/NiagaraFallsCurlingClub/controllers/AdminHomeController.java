package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Admin;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.AdminRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminHomeController {

    String adminId;

    @Autowired
    AdminRespository adminRespository;

    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String signIn(Model model, RedirectAttributes redirectAttrs, @RequestParam("aid") String adminId) {

        System.out.println("ADMIN ID RECEIVED : " + adminId);
        model.addAttribute("adminId", adminId);
        this.adminId = adminId;
        model.addAttribute("adminName", getAdminDetails().firstName);
        return "views/adminHome";
    }

    @RequestMapping("/createTournamentNavigate")
    public String createTournamentNavigate(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("aid", adminId);
        return "redirect:create_tournament";
    }

    @PostMapping("/createEventNavigate")
    public String createEventNavigate(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("aid", adminId);
        return "redirect:create_event";
    }

    @PostMapping("/createLeagueNavigate")
    public String createLeagueNavigate(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("aid", adminId);
        return "redirect:create_league";
    }

    @PostMapping("/viewLeaguesAdmin")
    public String viewLeaguesAdmin(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("aid", adminId);
        return "redirect:view_leagues";
    }

    private Admin getAdminDetails(){
        return adminRespository.findById(adminId).orElse(null);
    }

}
