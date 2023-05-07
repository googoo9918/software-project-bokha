import React from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@mui/styles";
import { Box, Button, Typography } from "@mui/material";

const useStyles = makeStyles({
  root: {
    position: "fixed",
    left: 30,
    marginTop: 80,
  },
  header: {
    fontSize: 28,
    marginBottom: 2,
    marginLeft: 15,
    color: "black",
  },
  divider: {
    borderBottom: "1px solid black",
    width: 250,
    marginTop: 16,
    marginBottom: 16,
  },
  navbarMenu: {
    fontSize: 35,
    marginTop: 20,
    color: "black",
  },
});

export default function Sidebar(props) {
  const { items } = props;
  const classes = useStyles();
  const sidebarMenu = items.slice(1);

  return (
    <Box className={classes.root}>
      <Box>
        <Button>
          <Box variant="h4" className={classes.navbarMenu}>
            {items[0].title}
          </Box>
        </Button>
        <Box className={classes.divider} />
      </Box>
      {sidebarMenu.map((item, index) => (
        <Box key={index}>
          <Button>
            <Box variant="h6" className={classes.header}>
              {item.title}
            </Box>
          </Button>
          <Box className={classes.divider} />
        </Box>
      ))}
    </Box>
  );
}

Sidebar.propTypes = {
  items: PropTypes.arrayOf(
    PropTypes.shape({
      title: PropTypes.string.isRequired,
    })
  ),
};

Sidebar.defaultProps = {
  items: [],
};
