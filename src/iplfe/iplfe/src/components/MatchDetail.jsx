import {React} from 'react'
import {Link} from 'react-router-dom'
import './MatchDetail.scss'

export const MatchDetail=({teamName,match})=>{
       
        const otherTeam= teamName===match.team1 ? match.team2 :match.team1;
        const otherTeamRoute=`/teams/${otherTeam}`
        const IsWon=teamName===match.matchWinner
    return (
        <div className={`MatchDetail ${IsWon ? 'match-detail-won':'match-detail-lose'}`}>
            {/* <div className={`MatchDetail ${IsWon ? 'match-detail-won' : 'match-detail-lose'}`}> */}

                
                <div className='match-dtail-main'>
                <span>Vs</span> 
                    <h2 className='match-team'><Link to={otherTeamRoute}>{otherTeam} </Link> </h2>
                    <h4 className='match-date'>{match.date }</h4>
                    <h2 className='match-venue'>{match.venue }</h2>
                    <h4 className='match-winner'><b>{match.matchWinner } Won by {match.resultMargin} wickets</b></h4>

                </div>
                <div className='match-detail-extra'>
                <h2 className='match-firstInning'>First Inning</h2>
                <h4 className='match-firstInning'>{match.team1}</h4>
                <h2 className='match-secondInning'>Second Inning</h2>
                <h4 className='match-secondInning'>{match.team2}</h4>
                <h2 className='match-manofzmatch'>Man of the match</h2>
                <h4 className='match-manofzmatch'>{match.playerOfMatch}</h4>
                <h2 className='match-umpires'>Umpires</h2>
                <h4 className='match-umpires'>{match.umpire1} and {match.umpire2}</h4>

                </div>
            
        </div>
    )
}