import { Team } from './pages/Team'
import { Match } from './pages/Match'
import {Home} from './pages/Home'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <div className='App' >
      <Router >

        <Routes>
          <Route path='/' element={<Home/>}/>
            <Route path="/teams/:teamName/matches/:year" element={<Match/>} />
            <Route path="/teams/:teamName" element={<Team />} />
        </Routes>
      </Router>

    </div>
  )

}

export default App
