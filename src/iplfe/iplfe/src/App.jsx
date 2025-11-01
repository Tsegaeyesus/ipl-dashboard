import {Team} from './pages/Team'
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom'

function App() {
  return (
    <div className='App' >
      <Router >
      <Routes>
        <Route path="/teams/:teamName" element={<Team/>} />
      </Routes>
      </Router>

    </div>
  )

}

export default App
