package com.dreamtech.ipldb.data;

import com.dreamtech.ipldb.model.Match;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class MatchDataProcess implements ItemProcessor<MatchInput,Match> {
    @Override
    public Match process(MatchInput matchInput) throws Exception {
        Match match=new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDateTime.parse(matchInput.getDate()));
        match.setPlayOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());


        return null;
    }

}
