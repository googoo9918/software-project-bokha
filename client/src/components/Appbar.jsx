import * as React from "react";
import MuiAppBar from "@mui/material/AppBar";

function AppBar(props) {
  return (
    <MuiAppBar
      elevation={0}
      position="fixed"
      z-index="5"
      sx={{ backgroundColor: "#CEE5D0" }}
      {...props}
    />
  );
}

export default AppBar;
