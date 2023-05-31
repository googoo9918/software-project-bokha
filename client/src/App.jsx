import { Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";
import { Routes, Route } from "react-router-dom";
import GlobalStyle from "./components/GlobalStyle/GlobalStyle";
import { RecoilRoot } from "recoil";

function App() {
  return (
    <RecoilRoot>
      <Navbar />
      <Outlet />
      <GlobalStyle />
    </RecoilRoot>
  );
}

export default App;
