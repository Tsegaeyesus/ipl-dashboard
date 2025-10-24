package com.dreamtech.ipldb.data;

import com.dreamtech.ipldb.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class JobCompletionListener implements JobExecutionListener {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus().equals(BatchStatus.COMPLETED)){
            System.out.println("Batch Job completed successfully!!!!");


//            jdbcTemplate.execute(" insert into team (team_name,total_matches,total_wins) \tselect team_mem.team, team_mem.total_match,winner.total_win From \n" +
//                    "\t(select sum(total_match) total_match, team from (select count(team1) total_match ,team1 as team From match group by team1\n" +
//                    "\tunion\n" +
//                    "\t select count(team2) total_match, team2 as team from match group by team2\n" +
//                    "\t) group by team ) as team_mem\n" +
//                    "left outer join\n" +
//                    "\t(select count(match_winner) total_win,match_winner From match group by match_winner) as winner\n" +
//                    "\ton team_mem.team=winner.match_winner");

            Map<String,Team> teamData=new HashMap<>();
            entityManager.createQuery("select  team1 team,count(team1) teamCount from Match group by team1",Object[].class)
                    .getResultList()
                    .stream()
                    .map(rs->new Team((String)rs[0],(long)rs[1]))
                    .forEach(team -> teamData.put(team.getTeamName(),team));

            entityManager.createQuery("select  team2 team,count(team2) teamCount from Match group by team2",Object[].class)
                    .getResultList()
                    .stream()
                    .map(rs->new Team((String)rs[0],(long)rs[1]))
                    .forEach(team ->
                            {
                                team.setTotalMatches(teamData.get(team.getTeamName()).getTotalMatches() + team.getTotalMatches());
                                teamData.put(team.getTeamName(), team);
                            }
                    );
            entityManager.createQuery("select matchWinner winner,count(matchWinner) total from Match group by matchWinner",Object[].class)
                    .getResultList()
                    .stream()
                    .map(rs -> new Team((String)rs[0],(long)rs[1]))
                    .forEach(team-> {
                         Team teamInter=teamData.get(team.getTeamName());
                        if(teamInter!=null) {
                            teamInter.setTotalWins(team.getTotalMatches());
                            teamData.put(team.getTeamName(), teamInter);
                        }
                    });
            for(Map.Entry<String, Team> team: teamData.entrySet()){
   System.out.println(team.getKey() +"========"+team.getValue().getTeamName() +"===="+team.getValue().getTotalWins()+"===="+team.getValue().getTotalMatches());

            }

            teamData.values().stream().forEach(team -> entityManager.persist(team));

        }
    }
}