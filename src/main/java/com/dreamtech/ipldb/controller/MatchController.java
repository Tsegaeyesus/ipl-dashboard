package com.dreamtech.ipldb.controller;


import com.dreamtech.ipldb.model.Match;
import com.dreamtech.ipldb.model.Team;
import com.dreamtech.ipldb.service.MatchService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/matches/{teamName}")
    public ResponseEntity<List<Match>> matches(@PathVariable("teamName") String teamName, @RequestParam("year") int year){
        List<Match> matches=matchService.getMatchesPerYear(year,teamName);
        return ResponseEntity.ok().body(matches);
    }
}
