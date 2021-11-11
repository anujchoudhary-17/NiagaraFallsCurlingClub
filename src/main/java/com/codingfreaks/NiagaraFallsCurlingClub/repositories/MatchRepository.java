package com.codingfreaks.NiagaraFallsCurlingClub.repositories;

import java.util.List;

import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Match;
import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MatchRepository extends MongoRepository<Match, String> {

}
