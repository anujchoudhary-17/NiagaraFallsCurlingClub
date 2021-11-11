package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Team;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.User;
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
public class TeamManagementController {

  private String teamId;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private UserRepository userRepository;

  String leagueId;
  boolean isUserListEmpty;

  @RequestMapping(value = "/team_management", method = RequestMethod.GET)
  public String team_management(Model model, @RequestParam("leagueId") String lid) {
    isUserListEmpty = false;
    leagueId = lid;
    model.addAttribute("userList", totalUsers());
    User user = findUser();

    model.addAttribute("teamList", totalTeams());
    model.addAttribute("teamSelect", "123");
    model.addAttribute("userObj", user);

    model.addAttribute("isUserListEmpty", isUserListEmpty);
    System.out.println(totalUsers().get(0).getFirstName());

    leagueId = lid;
    return "views/teamManagement";

  }

  @RequestMapping(value = "/create_team", method = RequestMethod.POST)
  public String createTeam(RedirectAttributes redirectAttrs, @RequestParam String teamName) {

    List<User> listUser = new ArrayList<User>();
    List<User> allUsers = totalUsers();

    Team team = new Team(teamName, leagueId);

    teamRepository.save(team);

    redirectAttrs.addAttribute("leagueId", leagueId);
    return "redirect:team_management";
  }

  @PostMapping(value = "/saveTeam")
  public String saveTeams(RedirectAttributes redirectAttrs, ModelMap model, @RequestParam String teamIdPlz,
      @ModelAttribute("user") User userWithUserId) {

    updateUser(userWithUserId.getTeamId(), teamIdPlz);

    redirectAttrs.addAttribute("leagueId", leagueId);
    return "redirect:team_management";
  }

  private List<User> totalUsers() {

    List<User> users = userRepository.usersWithParticularLeagueId(leagueId);

    List<User> finalUsers = new ArrayList<>();

    for (int i = 0; i < users.size(); i++) {

      List<String> teamIds = users.get(i).getTeamId();

      if (teamIds == null) {
        finalUsers.add(users.get(i));
      } else {
        boolean isAlreadyInLeaguesTeam = false;
        for (int j = 0; j < teamIds.size(); j++) {
          Team team = teamRepository.findById(teamIds.get(j)).orElse(null);
          if (team.getLeagueId().equals(leagueId)) {
            isAlreadyInLeaguesTeam = true;
          }
        }
        if (!isAlreadyInLeaguesTeam) {
          finalUsers.add(users.get(i));
        }
      }
    }

    if (finalUsers.size() == 0) {
      User user = new User();
      user.setFirstName("Sorry No users found in this league without any team");
      isUserListEmpty = true;
      finalUsers.add(user);
    }

    return finalUsers;
  }

  private List<Team> totalTeams() {
    List<Team> teams = teamRepository.teamsWithParticularLeagueId(leagueId);

    return teams;
  }

  private void updateUser(List<String> userIdList, String teamId) {
    for (int i = 0; i < userIdList.size(); i++) {
      List<String> tids;
      User user = userRepository.findById(userIdList.get(i)).orElse(null);
      if (user.getTeamId() == null) {
        tids = new ArrayList<>();
      } else {
        tids = user.getTeamId();
      }
      tids.add(teamId);
      user.setTeamId(tids);
      userRepository.save(user);

    }
  }

  private User findUser() {
    User user = userRepository.findById("6140d83e3be3d26a996723d3").orElse(null);
    return user;
  }

}
