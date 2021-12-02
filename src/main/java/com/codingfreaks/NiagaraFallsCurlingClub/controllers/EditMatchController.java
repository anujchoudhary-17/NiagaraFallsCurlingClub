package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Match;
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
public class EditMatchController {

  String userId;
  String matchId;
  String adminId;

  @Autowired
  private MatchRepository matchRepository;

  boolean isUserListEmpty;

  @RequestMapping(value = "/editMatch", method = RequestMethod.GET)
  public String editMatch(Model model, @RequestParam("mid") String mid, @RequestParam("aid") String aid) {

    adminId = aid;
    matchId = mid;
    model.addAttribute("userId", userId);
    model.addAttribute("matchId", matchId);
    model.addAttribute("adminId", adminId);
    model.addAttribute("matchDetails", getMatchDetails());
    return "views/editMatch";

  }

  @PostMapping("/update_match")
  public String update_match(Model model, RedirectAttributes redirectAttrs, @RequestParam Integer team1Score,
      @RequestParam Integer team2Score) {
    Match match = getMatchDetails();

    if (team1Score > team2Score)
      match.setWinnerId(match.getTeam1Id());
    else
      match.setWinnerId(match.getTeam2Id());

    match.setTeam1Score(team1Score);
    match.setTeam2Score(team2Score);

    matchRepository.save(match);
    redirectAttrs.addAttribute("mid", matchId);
    redirectAttrs.addAttribute("aid", adminId);
    return "redirect:editMatch";
  }

  private Match getMatchDetails() {
    return matchRepository.findById(matchId).orElse(null);
  }
}
