import {react} from 'react'
import { PieChart, Pie, Cell, Tooltip, Legend } from "recharts";
  const COLORS = ["#4CAF50", "#F44336"]; // green for wins, red for losses

export const Chart=({totalMatches,totalWins})=>{
const data=[
    {"name":"totalMatches","value":totalMatches===null ? 0:totalMatches},
    {"name":"totalWins","value":totalWins===null ?0:totalWins},
]
return <div>
   <PieChart width={300} height={300}>
        <Pie
          data={data}
          cx="50%"
          cy="50%"
          outerRadius={100}
          fill="#8884d8"
          dataKey="value"
          label
        >
          {data.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index]} />
          ))}
        </Pie>
        <Tooltip />
        <Legend />
      </PieChart>
</div>

}