import React from "react";
import { Link } from "react-scroll";
import styled from "styled-components";

const SideDiv = styled.div`
  width: 10%;
  position: fixed;
  right: 5rem;
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
            <span>🔳</span>
          </Link>
        </LinkDiv>
        <LinkDiv>
          <Link to="2" spy={true} smooth={true}>
            <span>🔳</span>
          </Link>
        </LinkDiv>
        <LinkDiv>
          <Link to="3" spy={true} smooth={true}>
            <span>🔳</span>
          </Link>
        </LinkDiv>
        <LinkDiv>
          <Link to="4" spy={true} smooth={true}>
            <span>🔳</span>
          </Link>
        </LinkDiv>
      </div>
    </SideDiv>
  );
}
