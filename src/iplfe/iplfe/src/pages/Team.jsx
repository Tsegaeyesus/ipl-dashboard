import {React,useState,useEffect} from 'react'
import { useParams } from 'react-router-dom';
import {MatchSmall} from '../components/MatchSmall';
import {MatchDetail} from '../components/MatchDetail';



export const Team=()=>{
    const {teamName}=useParams();
    const [team,setTeam]=useState({});
    const [match,setMatch]=useState([])
    const [err,setError]=useState()

     useEffect(
()=>{
   const fetchTeam= async ()=>{
    try{
        const encodedTeamName = encodeURIComponent(teamName);
        const url=`http://localhost:9091/api/v1/teams/${encodedTeamName}`;
// console.log(url)
      const response=await fetch(url);
      if(!response.ok){
    throw new Error("Not found")
      }

    const data= await response.json();
    console.log(data);
    setTeam(data);
    console.log(data.matchList)
    setMatch(data.matchList)
        }
    catch(error){
            console.log("error message",error.message);
            setError(error.message);
        }
    }
    fetchTeam();
}
    ,[teamName]);
        if(Object.keys(team).length === 0 && !err)
            {
            return (<h2>Loading...</h2>)
            }

    return (
    
 
        <div className='team'>
      
            <h2>Team: {err ? <i>{err}</i>: team.teamName}</h2> 
           
            {err ? err: <h3>Total Matches: {team.totalMatches} TotalWins: {team.totalWins}</h3> }
           
            <h4>Latest Match</h4>
    {
        match && match.length <=0 ? err:match.slice(0,1).map((match)=>{
            return <MatchDetail key={match.id} teamName={team.teamName} match={match}/>
        })
    }
           <h5>More Matches</h5>
           {
 match && match.length <=0 ? err :match.slice(1,4).map((t)=>{
  return <MatchSmall key={t.id} teamName={team.teamName} match={t}/>
})
           }
        </div>
    )

}