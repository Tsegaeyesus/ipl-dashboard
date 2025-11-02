import {React} from 'react'
import {Link} from 'react-router-dom'
import './MatchSmall.scss'

export const MatchSmall=({teamName,match})=>{
  const otherTeam=teamName===match.team1?match.team2:match.team1;
  const otherTeamRoute=`/teams/${otherTeam}`;
  const IsWon=teamName===match.matchWinner
    return (
         <div className={`Match-small ${IsWon ? 'match-detail-won':'match-detail-lose'}`}>
      <h4>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h4>
      <h4>{match.matchWinner} wins the match by {match.resultMargin} wickets</h4>
   
        </div>
    )
}