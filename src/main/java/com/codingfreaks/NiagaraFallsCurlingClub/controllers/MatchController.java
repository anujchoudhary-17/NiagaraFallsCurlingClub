package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

import ch.qos.logback.core.joran.conditional.ElseAction;

@Controller
public class MatchController {

    String userId;
    String matchId;

    @Autowired
    private MatchRepository matchRepository;

    @RequestMapping(value = "/match_details", method = RequestMethod.GET)
    public String match_details(Model model, RedirectAttributes redirectAttrs, @RequestParam("matchId") String mid,
            @RequestParam("userId") String uid) {
        userId = uid;
        matchId = mid;
        model.addAttribute("userId", userId);
        model.addAttribute("matchId", matchId);

        LocalDate matchDate = getConvertedDate(getMatchDetails().getDateAndTime());
        LocalDate currentDate = LocalDate.now();
        boolean isUpcomingMatch = matchDate.isAfter(currentDate);

        model.addAttribute("isUpcomingMatch", isUpcomingMatch);
        String WinnerTeamName = "";
        boolean noWinner = true;
        if (!isUpcomingMatch) {
            noWinner = false;

            if (getMatchDetails().getTeam1Score() > getMatchDetails().getTeam2Score()) {
                WinnerTeamName = getMatchDetails().getTeam1Name();
            } else if (getMatchDetails().getTeam1Score() < getMatchDetails().getTeam2Score()) {
                WinnerTeamName = getMatchDetails().getTeam2Name();

            } else {
                WinnerTeamName = "Draw";
            }
        }
        model.addAttribute("winnerName", WinnerTeamName);
        model.addAttribute("hasWinner", noWinner);

        model.addAttribute("matchDetails", getMatchDetails());
        return "views/match";
    }

    private Match getMatchDetails() {

        return matchRepository.findById(matchId).orElse(null);
    }

    private LocalDate getConvertedDate(String dateString) {
        String[] splittedString = dateString.split("T");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(splittedString[0], formatter);

        return date;
    }

}
