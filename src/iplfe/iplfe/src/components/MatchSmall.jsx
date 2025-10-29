import {React} from 'react'
export const MatchSmall=({match})=>{
    return (
        <div>
      <p>{match.team1} vs {match.team2}</p>
        </div>
    )
}