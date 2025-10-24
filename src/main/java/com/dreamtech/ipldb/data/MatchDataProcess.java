package com.dreamtech.ipldb.data;

import com.dreamtech.ipldb.model.Match;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MatchDataProcess implements ItemProcessor<MatchInput,Match> {
    @Override
    public Match process(MatchInput matchInput) throws Exception {
     //   System.out.println("Match data:"+matchInput.getWinner());
        Match match=new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());
        match.setMatchWinner(matchInput.getWinner());
        match.setTossWinner(matchInput.getToss_winner());
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
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());


        return match;
    }

}
