import React, { useEffect, useState } from "react";
import { FavoritesTitleBox, FavoritesView } from "./Favorites.styled";
import ProgramCard from "../ProgramCard/ProgramCard";
import axios from "axios";

export default function Favorites() {
  const [favorites, setFavorites] = useState([]);
  const accessToken = localStorage.getItem("accessToken");

  useEffect(() => {
    axios
      .get(
        `${import.meta.env.VITE_APP_HOST}/api/programs/savelist?page=1&size=9`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then((res) => {
        setFavorites(res.data.data);
        console.log(res.data.data);
      });
  }, []);
  return (
    <>
      <FavoritesTitleBox>
        <h2>즐겨찾기 목록</h2>
        <p>즐겨찾기 한 복지 프로그램 목록을 조회해 보세요!</p>
      </FavoritesTitleBox>
      <FavoritesView>
        {favorites.map((el, idx) => (
          <ProgramCard program={el} key={idx} />
        ))}
      </FavoritesView>
    </>
  );
}
