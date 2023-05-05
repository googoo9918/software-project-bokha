import React from "react";
import Container from "@mui/material/Container";
import Box from "@mui/material/Box";

export default function Sidebar() {
  return (
    <Box
      sx={{
        mt: 12,
        position: "fixed",
        left: 30,
      }}
    >
      <Box sx={{ fontSize: 24 }}>복지 검색 🔍</Box>
      <Box sx={{ borderBottom: 1, width: 200, mt: 2, mb: 2 }}></Box>
      <Box sx={{ fontSize: 24 }}>복지 검색</Box>
      <Box
        sx={{ borderBottom: 1, width: 200, mt: 2, mb: 2, color: "#bdbdbd" }}
      ></Box>
      <Box sx={{ fontSize: 24 }}>음성 검색</Box>
      <Box
        sx={{ borderBottom: 1, width: 200, mt: 2, mb: 2, color: "#bdbdbd" }}
      ></Box>
    </Box>
  );
}
