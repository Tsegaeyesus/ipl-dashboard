import {React,useEffect,useState} from 'react'
import './YearSelector.scss'
import { useParams,Link } from 'react-router-dom';
export const YearSelector=({fetch})=>{
        let years=[];
        let startYear=2008;
        let endYear=2020;
        const {teamName}=useParams();
        for(let i=startYear;i<=endYear;i++){
            years.push(i);
        }
        

    return (
        <div className='yearSelector'>
            {
                years.map((year)=>{
                   return <ul>
                    <li>
                        <Link to={`/teams/${teamName}/matches/${year}`}>{year}</Link>
                    </li>
                   </ul>
                   
                //    <h2 onClick={()=>fetch(year)}>{year}</h2>
                })
            }
        </div>
    )
}