package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeList;

import com.codingfreaks.NiagaraFallsCurlingClub.constants.TournamentRequestStatus;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.League;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Tournament;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.TournamentRequest;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.LeagueRepository;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.Tournament.TournamentRepository;
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
public class ViewParticularLeague {

    String leagueId;
    String adminId;
    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TournamentRequestRepository tournamentRequestRepository;

    @RequestMapping(value = "/view_particular_league", method = RequestMethod.GET)
    public String tournaments(Model model, RedirectAttributes redirectAttrs, @RequestParam("leagueId") String lid,
            @RequestParam("aid") String aid) {

        adminId = aid;
        leagueId = lid;
        model.addAttribute("adminId", adminId);

        return "views/viewLeague";
    }

    @PostMapping("/teamManagementNavigate")
    public String teamManagementNavigate(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("leagueId", leagueId);
        redirectAttrs.addAttribute("aid", adminId);
        return "redirect:team_management";
    }

    @PostMapping("/matchManagementNavigate")
    public String matchManagementNavigate(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("leagueId", leagueId);
        redirectAttrs.addAttribute("aid", adminId);

        return "redirect:create_match";
    }

}
