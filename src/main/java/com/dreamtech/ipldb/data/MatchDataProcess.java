package com.dreamtech.ipldb.data;

import com.dreamtech.ipldb.model.Match;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MatchDataProcess implements ItemProcessor<MatchInput,Match> {
    @Override
    public Match process(MatchInput matchInput) throws Exception {
        Match match=new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDateTime.parse(matchInput.getDate()));
        match.setPlayOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());
        String firstInningTeam,secondInningTeam="";
        if(matchInput.getToss_decision().equalsIgnoreCase("bat")){
          firstInningTeam=matchInput.getToss_winner();
            secondInningTeam= matchInput.getWinner().equalsIgnoreCase(matchInput.getTeam1()) ?
                    matchInput.getTeam2() :
                    matchInput.getTeam1();
        }
        else{
           secondInningTeam= matchInput.getToss_winner();
            firstInningTeam=matchInput.getWinner().equalsIgnoreCase(matchInput.getTeam1()) ?
                    matchInput.getTeam1() :
                    matchInput.getTeam2();
        }
        match.setTeam1(firstInningTeam);
        match.setTeam2(secondInningTeam);
        match.setTossDecision(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());


        return null;
    }

}
