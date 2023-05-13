import * as React from "react";
import MuiAppBar from "@mui/material/AppBar";

function AppBar(props) {
  return (
    <MuiAppBar
      elevation={0}
      position="fixed"
      sx={{ backgroundColor: "#69696a" }}
      {...props}
    />
  );
}

export default AppBar;
