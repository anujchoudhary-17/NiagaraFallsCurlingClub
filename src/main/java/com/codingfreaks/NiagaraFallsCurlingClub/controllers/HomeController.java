package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.time.Instant;

import javax.management.AttributeList;

import com.codingfreaks.NiagaraFallsCurlingClub.constants.TournamentRequestStatus;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.TournamentRequest;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.Tournament.TournamentRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    String userId;
    @Autowired
    private TournamentRequestRepository tournamentRequestRepository;
 

    @RequestMapping(value="/home", method = RequestMethod.GET)
	public String signIn(Model model,RedirectAttributes redirectAttrs,@RequestParam("uid") String uid) {
   
        System.out.println("USER ID RECEIVED : "+uid);
        model.addAttribute("userid", uid);
        userId=uid;
        model.addAttribute("name", "ANUJ");
		return "views/home";
	}


    

    @RequestMapping("/regiterTournamentTest")
    public String regiterTournamentTest(
        Model model
    ) {
        long currentTimestamp = Instant.now().toEpochMilli();
        TournamentRequest tournamentRequest = new TournamentRequest("tournamentId", userId , currentTimestamp, TournamentRequestStatus.StatusENUM.pending);
        tournamentRequestRepository.save(tournamentRequest);
        System.out.println("Successfully Saved Tournament Data !");
        return "redirect:home";
    }

    @RequestMapping("/acceptTournamentInvitationTest")
    public String acceptTournamentInvitationTest(
        Model model
    ) {
        long currentTimestamp = Instant.now().toEpochMilli();
        TournamentRequest tournamentRequest = new TournamentRequest("tournamentId", userId , currentTimestamp, TournamentRequestStatus.StatusENUM.pending);
        tournamentRequestRepository.save(tournamentRequest);
        System.out.println("Successfully Accepted Tournament Request !");
        return "redirect:home";
    }


    @PostMapping("/editProfileNavigate")
    public String editProfileNavigate(
        Model model,  RedirectAttributes redirectAttrs
    ) {
        System.out.println("Edit Profile : "+userId);
        redirectAttrs.addAttribute("uid", userId);
        return "redirect:editprofile";
    }
    
    
}
