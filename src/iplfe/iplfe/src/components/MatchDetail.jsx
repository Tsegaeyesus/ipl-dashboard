import {React} from 'react'
import {Link} from 'react-router-dom'
export const MatchDetail=({teamName,match})=>{
       
        const otherTeam= teamName===match.team1 ? match.team2 :match.team1;
        const otherTeamRoute=`/teams/${otherTeam}`
    return (
        <div>

            <h3>Vs <Link to={otherTeamRoute}>{otherTeam} </Link> </h3>
                <p>The match took place on <b>{match.date }</b> in the city of <b>{match.city }</b> 
                and Stadium <b>{match.venue }</b>
                and the winner was <b>{match.matchWinner } by {match.resultMargin}</b></p>
            
        </div>
    )
}