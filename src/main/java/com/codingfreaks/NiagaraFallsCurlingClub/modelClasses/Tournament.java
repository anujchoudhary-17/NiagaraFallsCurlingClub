package com.codingfreaks.NiagaraFallsCurlingClub.modelClasses;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.codingfreaks.NiagaraFallsCurlingClub.constants.TournamentRequestStatus;
import com.codingfreaks.NiagaraFallsCurlingClub.constants.TournamentRequestStatus.StatusENUM;
import com.codingfreaks.NiagaraFallsCurlingClub.constants.WeeklyTimings.WeeklyTimingsENUM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "tblTournaments")
public class Tournament
{

    @MongoId(FieldType.OBJECT_ID)
	private String tournamentId;

    private String name,description;
    private int startTime,endTime,maximumParticipants;



    public Tournament(String name, String description, int startTime, int endTime,
            int maximumParticipants) {
  
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maximumParticipants = maximumParticipants;
    }





    public String getTournamentId() {
        return tournamentId;
    }
    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }





    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getStartTime() {
        return startTime;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public int getEndTime() {
        return endTime;
    }
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    public int getMaximumParticipants() {
        return maximumParticipants;
    }
    public void setMaximumParticipants(int maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
    }

    
}