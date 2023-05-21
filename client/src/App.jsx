import { Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";
import { Routes, Route } from "react-router-dom";
import { useLocation } from "react-router-dom";
import GlobalStyle from "./components/GlobalStyle/GlobalStyle";

function App() {
  const isHome = location.pathname === "/";
  return (
    <>
      {!isHome && <Navbar />}
      <Outlet />
      <GlobalStyle />
    </>
  );
}

export default App;
