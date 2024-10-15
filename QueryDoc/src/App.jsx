import Home from "./pages/home";
import Crud from "./pages/crud";
import Overall from "./pages/overall";
import { BrowserRouter as Router, Route,Routes } from "react-router-dom"


export default function App() {
  return (
    <>
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        
        <Route path="/crud" element={<Crud />} />
        <Route path="/overall" element={<Overall />} />
    
      </Routes>
    </Router>
    </>
  )
}

