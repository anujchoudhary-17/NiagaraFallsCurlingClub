package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.time.Instant;

import javax.management.AttributeList;

import com.codingfreaks.NiagaraFallsCurlingClub.constants.TournamentRequestStatus;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Tournament;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.TournamentRequest;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.Tournament.TournamentRepository;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.Tournament.TournamentRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CreateTournamentController{

    String userId;
    @Autowired
    private TournamentRepository tournamentRepository;
 

    @RequestMapping(value="/create_tournament", method = RequestMethod.GET)
	public String signIn(Model model,RedirectAttributes redirectAttrs,@RequestParam("uid") String uid) {
        Tournament tournament=new Tournament();
        model.addAttribute("tournamentData", tournament);
        model.addAttribute("userid", uid);
        userId=uid;
		return "views/createTournament";
	}

    @PostMapping("/createTournamentFunction")
    public String createTournamentFunction(
        Model model,@ModelAttribute Tournament tournamentData, RedirectAttributes redirectAttrs
    ) {
        tournamentRepository.save(tournamentData);
        redirectAttrs.addAttribute("uid", userId);
        System.out.println("Successfully Saved Tournament Data !");
        return "redirect:createTournament";
    }

    
}
