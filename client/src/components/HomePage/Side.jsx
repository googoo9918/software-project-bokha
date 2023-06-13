import React from "react";
import { Link } from "react-scroll";
import styled from "styled-components";
import Icon01 from "../../assets/HomePageIcon01.png";
import Icon02 from "../../assets/HomePageIcon02.png";
import Icon03 from "../../assets/HomePageIcon03.png";
import Icon04 from "../../assets/HomePageIcon04.png";

const SideDiv = styled.div`
  width: 10%;
  position: fixed;
  right: 3rem;
  margin-top: 200px;
  div {
    display: flex;
    flex-direction: column;
  }
`;

const LinkDiv = styled.div`
  margin: 10px 0px;
  cursor: pointer;
  font-size: 25px;
`;

export default function Side() {
  return (
    <SideDiv>
      <div>
        <LinkDiv>
          <Link to="1" spy={true} smooth={true}>
            <img style={{ width: "33px" }} src={Icon01} />
          </Link>
        </LinkDiv>
        <LinkDiv>
          <Link to="2" spy={true} smooth={true}>
            <img style={{ width: "33px" }} src={Icon02} />
          </Link>
        </LinkDiv>
        <LinkDiv>
          <Link to="3" spy={true} smooth={true}>
            <img style={{ width: "33px" }} src={Icon03} />
          </Link>
        </LinkDiv>
        <LinkDiv>
          <Link to="4" spy={true} smooth={true}>
            <img style={{ width: "33px" }} src={Icon04} />
          </Link>
        </LinkDiv>
      </div>
    </SideDiv>
  );
}
