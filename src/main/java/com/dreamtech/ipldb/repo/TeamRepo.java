package com.dreamtech.ipldb.repo;

import com.dreamtech.ipldb.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {
   Optional<Team> findByTeamNameIgnoreCase(String teamName);
   Team findById(long id);
   List<Team> findAll();
}
