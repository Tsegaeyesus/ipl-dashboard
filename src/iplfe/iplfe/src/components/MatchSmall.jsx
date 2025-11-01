import {React} from 'react'
import {Link} from 'react-router-dom'
export const MatchSmall=({teamName,match})=>{
  const otherTeam=teamName===match.team1?match.team2:match.team1;
  const otherTeamRoute=`/teams/${otherTeam}`;
    return (
        <div>
      <p>vs <Link to={otherTeamRoute}>{otherTeam}</Link></p>
      <p><b>{match.matchWinner} wins the match by {match.resultMargin} wickets</b></p>
        </div>
    )
}