package com.dreamtech.ipldb.repo;

import com.dreamtech.ipldb.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends JpaRepository<Match,Long> {

    @Query(value="select * from Match where (team1=?2 or team2= ?2) and EXTRACT(YEAR from date)=?1",nativeQuery = true)
    List<Match> getMatchesPerYear(int year, String teamName);


}
