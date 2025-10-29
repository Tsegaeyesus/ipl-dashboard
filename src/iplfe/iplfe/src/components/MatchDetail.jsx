import {React} from 'react'
export const MatchDetail=({match})=>{
    return (
        <div>
            <p>{match.team1} Vs {match.team2}</p>
        </div>
    )
}