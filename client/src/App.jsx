import { Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";
import { Routes, Route } from "react-router-dom";
import GlobalStyle from "./components/GlobalStyle/GlobalStyle";

function App() {
  return (
    <>
      <Navbar />
      <Outlet />
      <GlobalStyle />
    </>
  );
}

export default App;
