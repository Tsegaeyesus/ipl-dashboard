package com.dreamtech.ipldb.service;

import com.dreamtech.ipldb.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    Optional<Team> getAllTeams(String teamName);

    Team findById(long id);

    List<Team> findAll();
}
