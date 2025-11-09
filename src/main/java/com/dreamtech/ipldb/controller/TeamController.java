package com.dreamtech.ipldb.controller;

import com.dreamtech.ipldb.exception.TeamNotFoundException;
import com.dreamtech.ipldb.model.Match;
import com.dreamtech.ipldb.model.Team;
import com.dreamtech.ipldb.service.TeamService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;
    @RequestMapping("/team")
    public String getMatches(Model model){
        Team team =teamService.findById(14);
        model.addAttribute("team",team);
        return "teamDetail";
    }


    @GetMapping("/")
    public ResponseEntity<List<Team>> getAllTeams(){

        List<Team> teams=teamService.findAll();
        return ResponseEntity.ok(teams);

    }
    @GetMapping("/teams/{teamName}")
    public ResponseEntity<Team> teams(@PathVariable String teamName) throws TeamNotFoundException {
        Optional<Team> teams=teamService.getAllTeams(teamName);
        if(teams.isEmpty()){
            throw new TeamNotFoundException("Not found error response");
        }
        else{
            return ResponseEntity.ok().body(teams.get());
        }
    }

}
