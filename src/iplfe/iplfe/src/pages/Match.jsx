import { useState, useEffect } from "react"
import { useParams } from 'react-router-dom'
import { MatchDetail } from "../components/MatchDetail"

export const Match = () => {

    const [error, setError] = useState(null)
    const [match, setMatch] = useState([])
    // const teamName=encodeURIComponent()
    const { teamName, year } = useParams()

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
    }, [])

    if (Object.keys(match) === 0 && !error) {
        <h2>Loading...</h2>
    }
    return (
        <div>
            <h1>Match Page</h1>
            {
                match.map((match) => {
                    return <MatchDetail key={match.id} match={match} teamName={teamName}/>
                })
            }
        </div>
    )
}