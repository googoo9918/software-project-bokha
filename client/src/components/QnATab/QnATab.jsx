import React, { useState, useEffect } from "react";
import {
  QnAContainer,
  QnADescription,
  QnATabView,
  QnATitle,
  QnATitleBox,
} from "./QnATab.styled";
import axios from "axios";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";

export default function QnATab() {
  const [QnAList, setQnAList] = useState([]);

  useEffect(() => {
    axios.get(`/data/QnA.json`).then((res) => setQnAList(res.data.items));
  }, []);

  return (
    <>
      <QnATitleBox>
        <h1>질의응답</h1>
      </QnATitleBox>
      <QnAContainer>
        {QnAList.map((QnA, i) => (
          <QnABox QnA={QnA} key={i} />
        ))}
      </QnAContainer>
    </>
  );
}

function QnABox(props) {
  const { title, description } = props.QnA.snippet;
  const [open, setOpen] = useState(false);
  return (
    <>
      <QnATabView
        onClick={() => {
          setOpen(true);
          console.log("클릭클릭");
        }}
        style={{ cursor: "pointer" }}
      >
        <QnATitle>
          <span style={{ color: "pink" }}>Q. </span>
          {title}
        </QnATitle>
      </QnATabView>
      <Dialog open={open}>
        <DialogTitle>
          <span style={{ color: "pink" }}>Q. </span>
          {title}
        </DialogTitle>
        <DialogContent>
          <DialogContentText>
            <span style={{ color: "pink" }}>A. </span>
            {description}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            variant="outlined"
            onClick={() => {
              setOpen(false);
              console.log("클릭됨");
            }}
          >
            닫기
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
