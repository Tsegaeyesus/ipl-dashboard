import { useState, useEffect } from "react"
import { useParams } from 'react-router-dom'
import { MatchDetail } from "../components/MatchDetail"
import './Match.scss'
import { YearSelector } from "../components/YearSelector"

export const Match = () => {

    const [error, setError] = useState(null)
    const [match, setMatch] = useState([])
    // const teamName=encodeURIComponent()
    const { teamName, year } = useParams()

   const fetchmatchesOnChange=async(year)=>{
        try{
        const response=await fetch(`/api/v1/matches/${teamName}?year=${year}`)
        const data=await response.json();
        setMatch(data)
        if(!response){
            throw new Error("no mache found")
        }
    }
    catch(err){
            console.log(err)
            setError(err)
    }

    }
    useEffect(() => {
    
        const fetchMatches = async () => {
            try {
                const response = await fetch(`http://localhost:9090/api/v1/matches/${teamName}?year=${year}`)
                const data = await response.json();
                setMatch(data)
                console.log(data)
                if (!response) {
                    throw new Error("No response")
                }

            } catch (err) {
                console.log(err)
                setError(err)
            }
        }
        fetchMatches();
    }, [year])

    if (Object.keys(match) === 0 && !error) {
        <h2>Loading...</h2>
    }
    return (
        <div className="match-page">
            <div className="years">

                <YearSelector/>

            
            </div>
            <div className="matches">
            <h1>Match Page</h1>
            {
             Object.keys(match).length>0 ?  
             (

                 match.map((match) => {
                        return <MatchDetail key={match.id} match={match} teamName={teamName}/>
                    })
                
             ) :<h2>No match found for this year</h2>
            }
            </div>
        </div>
    )
}