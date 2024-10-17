import axios from "axios";
import React, { useState } from "react";
import { TextField, Button, Grid, Container } from "@mui/material";
import { useNavigate } from "react-router-dom";

function LoginUser() {
  const [login, setLogin] = useState({ userEmail: "", userPassword: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const eventHandler = (e) => {
    setLogin({ ...login, [e.target.name]: e.target.value });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    if (
      login.userEmail === "admin@gmail.com" &&
      login.userPassword === "12345"
    ) {
      sessionStorage.setItem("adminId", 101);
      const adminId = sessionStorage.getItem("adminId");
      console.log(adminId);

      navigate("/adminNav");
      return;
    }

    try {
      const res = await axios.post("http://localhost:8080/login", login);
      console.log(res.data);

      const userId = res.data.userId;
      const freshUser = res.data.freshUser;
      console.log(freshUser);
      sessionStorage.setItem("userId", userId);
      console.log("Stored User ID:", userId);

      setLogin({ userEmail: "", userPassword: "" });
      if (freshUser) {
        navigate("/changePassword");
      } else {
        navigate("/userNav");
      }
    } catch (err) {
      console.error(err);
      setError("Login failed. Please check your credentials.");
    }
  };

  return (
    <Container style={{ marginTop: "10%" }} maxWidth="sm">
      <div className="card">
        <div className="card-header text-center">Login</div>
        <div className="card-body">
          {error && <div style={{ color: "red" }}>{error}</div>}
          <form onSubmit={submitHandler}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  label="Email"
                  name="userEmail"
                  value={login.userEmail}
                  required
                  onChange={eventHandler}
                  fullWidth
                  size="small"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  label="Password"
                  name="userPassword"
                  type="password"
                  required
                  value={login.userPassword}
                  onChange={eventHandler}
                  fullWidth
                  size="small"
                />
              </Grid>
              <Grid item xs={12}>
                <Button
                  variant="contained"
                  color="primary"
                  type="submit"
                  fullWidth
                >
                  Login
                </Button>
              </Grid>
            </Grid>
          </form>
        </div>
      </div>
    </Container>
  );
}

export default LoginUser;
