import {React,useEffect,useState} from 'react'
import {Link} from 'react-router-dom'
import './Home.scss'





export const Home=()=>{
    const[teams,setTeams]=useState([]);
    const[error,setError]=useState()
useEffect(()=>{
const fetchTeams=async()=>{
    try{
    const response=await fetch('http://localhost:9090/api/v1/');
    if(!response){
        throw new Error('Not able to fetch')
    }
    const data=await response.json();
    console.log(data)
    setTeams(data)

}
catch(error){
console.log("Error occured ",error.message)
setError(error.message);
}
};

fetchTeams();

},[])

    return (
        <>
        <div className='title'> IPL Dashboard</div>
        <div className='homepage'>
            
            {
              teams.length >=0 ? (

                  teams.map((team)=>{
                       return  <div className='team' key={team.id}><Link className='teamlink' to={`/teams/${team.teamName}`}>{team.teamName}</Link></div>
                    })
              ) :error

            }

        </div>
        
        </>
    )
}