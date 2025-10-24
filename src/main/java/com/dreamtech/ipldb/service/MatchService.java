package com.dreamtech.ipldb.service;

import com.dreamtech.ipldb.model.Match;

import java.time.Year;
import java.util.List;

public interface MatchService {

    List<Match> getMatchesPerYear(int year,String teamName);
}
