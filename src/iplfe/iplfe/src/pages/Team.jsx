import {React,useState,useEffect} from 'react'
import {MatchSmall} from '../components/MatchSmall';
import {MatchDetail} from '../components/MatchDetail';



export const Team=()=>{
    const [team,setTeam]=useState({});
    const [match,setMatch]=useState([])
    const [error,setError]=userState([])

     useEffect(
()=>{
   const fetchTeam= async ()=>{
        const response=await fetch('http://localhost:9091/api/v1/teams/Delhi Capitals')
        .then(response=>response.json()
        .then((data) =>{
            console.log(data);
            setTeam(data);
            console.log(data.matchList)
            setMatch(data.matchList)
        })).catch((err)=>setError(err))
    }
    fetchTeam();
}
    ,[]);
    return (
    
        <div className='team'>
            <h2>Team {team.teamName}</h2>
            <h3>Total Matches: {team.totalMatches} TotalWins: {team.totalWins}</h3>
            <h4>Latest Match</h4>
    {
        match==null?err:match.slice(0,1).map((match)=>{
            return <MatchDetail key={match.id} match={match}/>
        })
    }
           <h5>Previous Matches</h5>
           {
match==null? err :match.slice(0,4).map((t)=>{
  return <MatchSmall key={t.id} match={t}/>
})
           }
        </div>
    )

}