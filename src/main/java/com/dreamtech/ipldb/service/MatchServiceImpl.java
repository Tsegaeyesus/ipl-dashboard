package com.dreamtech.ipldb.service;

import com.dreamtech.ipldb.model.Match;
import com.dreamtech.ipldb.repo.MatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;


@Service
public class MatchServiceImpl implements MatchService{


    @Autowired
    private MatchRepo matchRepo;

    @Override
    public List<Match> getMatchesPerYear(int year,String teamName) {

        return matchRepo.getMatchesPerYear(year,teamName);
    }
}
