import React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import Home from "./pages/Home";
import NotFound from "./pages/NotFound.jsx";
import Search from "./pages/Search.jsx";
import Map from "./pages/Map.jsx";
import Notice from "./pages/Notice.jsx";
import Mypage from "./pages/Mypage.jsx";
import DetailPage from "./pages/DetailPage.jsx";
import SignUpPage from "./pages/SignUpPage.jsx";
import SignInPage from "./pages/SignInPage.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <NotFound />,
    children: [
      { index: true, path: "/", element: <Home /> },
      { path: "/search", element: <Search /> },
      { path: "/map", element: <Map /> },
      { path: "/notice", element: <Notice /> },
      { path: "/mypage", element: <Mypage /> },
      { path: "/search/:programIdx", element: <DetailPage /> },
      { path: "/signin", element: <SignInPage /> },
      { path: "/signup", element: <SignUpPage /> },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  // <React.StrictMode>
  <RouterProvider router={router} />
  // </React.StrictMode>
);
