import { Routes, Route } from "react-router-dom"
import GlobalStyle from "./components/GlobalStyle/GlobalStyle"
import SearchPage from "./pages/SearchPage"

function App() {
  return (
    <div className="App">
      <GlobalStyle />
      <Routes>
        <Route path="/search" element={<SearchPage />}></Route>
      </Routes>
    </div>
  )
}

export default App