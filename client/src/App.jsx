import { Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";
import { Routes, Route } from "react-router-dom";
import GlobalStyle from "./components/GlobalStyle/GlobalStyle";
import { RecoilRoot } from "recoil";
import { useLocation } from "react-router-dom";
import { useEffect } from "react";

function App() {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return (
    <RecoilRoot>
      <Navbar />
      <Outlet />
      <GlobalStyle />
    </RecoilRoot>
  );
}

export default App;
