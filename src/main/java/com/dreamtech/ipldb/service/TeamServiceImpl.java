package com.dreamtech.ipldb.service;

import com.dreamtech.ipldb.model.Team;
import com.dreamtech.ipldb.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo teamRepo;



    @Override
    public Optional<Team> getAllTeams(String teamName) {
        return teamRepo.findByTeamName(teamName);
    }

    @Override
    public Team findById(long id) {
        return teamRepo.findById(id);
    }

}
