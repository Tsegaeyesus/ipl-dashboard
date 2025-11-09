package com.dreamtech.ipldb.service;

import com.dreamtech.ipldb.model.Match;
import com.dreamtech.ipldb.model.Team;
import com.dreamtech.ipldb.repo.MatchRepo;
import com.dreamtech.ipldb.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private MatchRepo matchRepo;



    @Override
    public Optional<Team> getAllTeams(String teamName) {
        Pageable pageable= PageRequest.of(0,10);
        List<Match> matches=matchRepo.getAllMatchByTeamNameOrderByDateDesc(teamName,pageable);
        Optional<Team> team=teamRepo.findByTeamNameIgnoreCase(teamName);
        if(team.isPresent()){
            team.get().setMatchList(matches);
        }

        return team;
    }

    @Override
    public Team findById(long id) {
        return teamRepo.findById(id);
    }

    @Override
    public List<Team> findAll() {
        return teamRepo.findAll();
    }

}
