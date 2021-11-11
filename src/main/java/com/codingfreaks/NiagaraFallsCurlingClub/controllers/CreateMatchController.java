package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Team;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.User;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.MatchRepository;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.TeamRepository;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.tags.form.SelectTag;

@Controller
public class CreateMatchController {

  private String teamId;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private MatchRepository matchRepository;

  String leagueId;
  boolean isUserListEmpty;

  @RequestMapping(value = "/create_match", method = RequestMethod.GET)
  public String create_match(Model model, @RequestParam("leagueId") String lid) {
    leagueId = lid;

    model.addAttribute("teamList", totalTeams());

    leagueId = lid;
    return "views/create_match";

  }

  @PostMapping(value = "/createMatchPost")
  public String createMatchPost(RedirectAttributes redirectAttrs, ModelMap model, @RequestParam String teamIdPlz,
      @ModelAttribute("user") User userWithUserId) {

    redirectAttrs.addAttribute("leagueId", leagueId);
    return "redirect:create_match";
  }

  private List<Team> totalTeams() {
    List<Team> teams = teamRepository.teamsWithParticularLeagueId(leagueId);

    return teams;
  }

}
