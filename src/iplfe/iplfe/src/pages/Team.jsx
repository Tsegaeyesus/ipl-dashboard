import {React,useState,useEffect} from 'react'
import { useParams,Link } from 'react-router-dom';
import {MatchSmall} from '../components/MatchSmall';
import {MatchDetail} from '../components/MatchDetail';
import { Chart } from '../components/Chart';
import './Team.scss'



export const Team=()=>{
    const {teamName}=useParams();
    const [team,setTeam]=useState({});
    const [match,setMatch]=useState([])
    const [err,setError]=useState()
    const encodedTeamName = encodeURIComponent(teamName);
    const urlMatches=`/teams/${encodedTeamName}/matches/2020`;

     useEffect(
()=>{
   const fetchTeam= async ()=>{
    try{
        const url=`/api/v1/teams/${encodedTeamName}`;

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
    

        <div className='teamPage'>
           
           <div className='teamName'>
             <h2>{err ? <i>{err}</i>: team.teamName}</h2> 
           </div>
           <div className='teamPagetSummary'>
            <Chart totalMatches={team.totalMatches} totalWins={team.totalWins}/>
              {err ? err: <h3>Total Matches: {team.totalMatches} TotalWins: {team.totalWins}</h3> }
           </div>
           
        
    {
        match && match.length <=0 ? err:match.slice(0,1).map((match)=>{
            return <div className='teamPageLatestMatch' key={match.id}>
                    <h4>Latest Match</h4>
                <MatchDetail  teamName={team.teamName} match={match}/>
                </div>
        })
    }
           {
 match && match.length <=0 ? err :match.slice(1,4).map((t)=>{
  return <div className='teamPageMatchDetail' key={t.id}>
      <MatchSmall  teamName={team.teamName} match={t}/>

    </div>
})
           }
                 <div className='teamPageMore'>
                    <Link to={urlMatches}>More</Link>
                {/* <a href='#'>More</a> */}
           </div>
           
        </div>
    )

}