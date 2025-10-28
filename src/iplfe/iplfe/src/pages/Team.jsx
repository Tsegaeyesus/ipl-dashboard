import {React,useState,useEffect} from 'react'
import {MatchSmall} from '../components/MatchSmall';
import {MatchDetail} from '../components/MatchDetail';



export const Team=()=>{
    const [team,setTeam]=useState({});

     useEffect(
()=>{
   const fetchTeam=()=>{
        const response=fetch('http://localhost:9091/api/v1/teams/Delhi Capitals')
        .then(response=>response.json()
        .then((data) =>{
            console.log(data);
            setTeam(data);
        }))
    }
    fetchTeam();
}
    ,[]);
    return (
        <div>
            <h2>Team Name {team.teamName}</h2>
            <MatchDetail/>
            <MatchSmall/>
            <MatchSmall/>
        </div>
    )

}