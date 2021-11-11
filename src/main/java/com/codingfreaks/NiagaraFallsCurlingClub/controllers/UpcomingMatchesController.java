package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeList;

import com.codingfreaks.NiagaraFallsCurlingClub.constants.TournamentRequestStatus;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Match;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Tournament;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.TournamentRequest;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.User;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.MatchRepository;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.UserRepository;
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
public class UpcomingMatchesController {

    String userId;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @RequestMapping(value = "/upcoming_matches", method = RequestMethod.GET)
    public String tournaments(Model model, RedirectAttributes redirectAttrs, @RequestParam("uid") String uid) {

        model.addAttribute("userid", uid);
        userId = uid;
        model.addAttribute("usersMatches", allMatchesList());
        return "views/upcomingMatches";
    }

    @PostMapping("/goToMatch")
    public String goToMatch(Model model, RedirectAttributes redirectAttrs, @RequestParam("matchId") String matchId) {
        redirectAttrs.addAttribute("matchId", matchId);
        redirectAttrs.addAttribute("userId", userId);
        return "redirect:match_details";
    }

    private List<String> totalTeams() {
        User user = userRepository.findById(userId).orElse(null);
        List<String> teamIdList = user.getTeamId();
        return teamIdList;
    }

    private List<Match> allMatchesList() {
        List<Match> allMatches = new ArrayList<Match>();
        allMatches = matchRepository.findAll();
        List<Match> usersMatch = new ArrayList<Match>();

        List<String> teamIds = totalTeams();

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
