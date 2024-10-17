import axios from "axios";
import React, { useState } from "react";
import { TextField, Button, Grid, Container } from "@mui/material";
import { useNavigate } from "react-router-dom";

function ChangePassword() {
  const [passwordData, setPasswordData] = useState({
    newPassword: "",
    confirmPassword: "",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const eventHandler = (e) => {
    setPasswordData({ ...passwordData, [e.target.name]: e.target.value });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    if (passwordData.newPassword !== passwordData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    const storedUserId = sessionStorage.getItem("userId");

    try {
      const response = await axios.put(
        `http://localhost:8080/updatePassword/${storedUserId}`,
        {
          userPassword: passwordData.newPassword,
        }
      );
      console.log(response.data);
      await axios.post(`http://localhost:8080/updateUserStatus`, {
        userId: storedUserId,
      });
      navigate("/");
    } catch (err) {
      console.error(err);
      setError("Failed to change password. Please try again.");
    }
  };

  return (
    <Container style={{ marginTop: "10%" }} maxWidth="sm">
      <div className="card">
        <div className="card-header text-center">Change Password</div>
        <div className="card-body">
          {error && <div style={{ color: "red" }}>{error}</div>}{" "}
          <form onSubmit={submitHandler}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  label="New Password"
                  name="newPassword"
                  value={passwordData.newPassword}
                  required
                  onChange={eventHandler}
                  type="password"
                  fullWidth
                  size="small"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  label="Confirm Password"
                  name="confirmPassword"
                  value={passwordData.confirmPassword}
                  required
                  onChange={eventHandler}
                  type="password"
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
                  Change Password
                </Button>
              </Grid>
            </Grid>
          </form>
        </div>
      </div>
    </Container>
  );
}

export default ChangePassword;
