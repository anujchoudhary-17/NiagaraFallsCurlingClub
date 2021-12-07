package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.time.Instant;

import javax.management.AttributeList;

import com.codingfreaks.NiagaraFallsCurlingClub.constants.TournamentRequestStatus;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.TournamentRequest;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.User;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.UserRepository;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.Tournament.TournamentRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserHomeController {

    String userId;
    @Autowired
    private TournamentRequestRepository tournamentRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String signIn(Model model, RedirectAttributes redirectAttrs, @RequestParam("uid") String uid) {
        userId = uid;
        model.addAttribute("userId", userId);
        model.addAttribute("userName", getUserDetails().getFirstName());
        return "views/home";
    }

    @RequestMapping("/tournamentNavigate")
    public String tournamentNavigate(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("uid", userId);
        return "redirect:tournaments";
    }

    @PostMapping("/editProfileNavigate")
    public String editProfileNavigate(Model model, RedirectAttributes redirectAttrs) {
        System.out.println("Edit Profile : " + userId);
        redirectAttrs.addAttribute("uid", userId);
        return "redirect:editprofile";
    }

    @PostMapping("/navigateLeagueSelection")
    public String navigateLeagueSelection(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("uid", userId);
        return "redirect:league_selection";
    }

    @PostMapping("/navigateAllMatches")
    public String navigateAllMatches(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("uid", userId);
        return "redirect:upcoming_matches";
    }

    @PostMapping("/navigateToEvents")
    public String navigateToEvents(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("uid", userId);
        return "redirect:view_events";
    }

    @PostMapping("/navigateToAllEvents")
    public String navigateToAllEvents(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("uid", userId);
        return "redirect:view_events";
    }


    private User getUserDetails(){
        return userRepository.findById(userId).orElse(null);
    }

}
