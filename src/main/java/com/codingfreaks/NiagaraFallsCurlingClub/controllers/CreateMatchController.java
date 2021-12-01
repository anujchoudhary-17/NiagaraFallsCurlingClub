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
public class CreateMatchController {

  private String teamId;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private MatchRepository matchRepository;

  String leagueId, adminId;
  boolean isUserListEmpty;

  @RequestMapping(value = "/create_match", method = RequestMethod.GET)
  public String create_match(Model model, @RequestParam("leagueId") String lid, @RequestParam("aid") String aid) {
    leagueId = lid;

    model.addAttribute("teamList", totalTeams());
    model.addAttribute("usersMatches", allMatchesList());
    leagueId = lid;
    adminId = aid;
    model.addAttribute("adminId", adminId);

    return "views/createMatch";

  }

  @PostMapping(value = "/create_match")
  public String createMatchPost(RedirectAttributes redirectAttrs, ModelMap model, @RequestParam String matchName,
      @RequestParam String matchDate, @RequestParam String teamId1, @RequestParam String teamId2,
      @RequestParam Integer sheetNumber, @ModelAttribute("user") User userWithUserId) {

    Optional<Team> team1 = teamRepository.findById(teamId1);
    Optional<Team> team2 = teamRepository.findById(teamId2);

    Match match = new Match(matchName, teamId1, teamId2, sheetNumber, team1.get().getTeamName(),
        team2.get().getTeamName(), matchDate);

    matchRepository.save(match);

    redirectAttrs.addAttribute("leagueId", leagueId);
    redirectAttrs.addAttribute("aid", adminId);
    return "redirect:view_particular_league";
  }

  @PostMapping(value = "/goToEditMatch")
  public String goToMatch(Model model, RedirectAttributes redirectAttrs, @RequestParam("mid") String matchId) {
    redirectAttrs.addAttribute("mid", matchId);
    redirectAttrs.addAttribute("aid", adminId);
    return "redirect:editMatch";
  }

  private List<Team> totalTeams() {
    List<Team> teams = teamRepository.teamsWithParticularLeagueId(leagueId);

    return teams;
  }

  private List<Match> allMatchesList() {
    List<Match> allMatches = new ArrayList<Match>();
    allMatches = matchRepository.findAll();
    List<Match> usersMatch = new ArrayList<Match>();

    List<Team> teams = totalTeams();
    List<String> teamIds = new ArrayList<String>() {

    };

    for (Team team : teams) {
      teamIds.add(team.getTeamId());
    }

    for (Match match : allMatches) {
      String team1Id = match.getTeam1Id();
      String team2Id = match.getTeam2Id();

      if (teamIds.contains(team1Id) || teamIds.contains(team2Id)) {
        usersMatch.add(match);
      }
    }

    return usersMatch;
  }

}
