import React, { useState } from "react";
import axios from "axios";
import { TextField, Button, Grid, Container } from "@mui/material";
import { FormControl, InputLabel, Select, MenuItem } from "@mui/material";
import { useNavigate } from "react-router-dom";
import UserNavbarComponent from "../User/UserNavbarComponent";

function DepositOrWithdraw() {
  const [data, setData] = useState({
    amount: "",
    type: "",
    transactionDate: "",
  });

  const handlerChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const navigate = useNavigate();

  const storedAccountId = sessionStorage.getItem("accountId");

  const submitHandler = (e) => {
    e.preventDefault();
    const url =
      data.type === "deposit"
        ? `http://localhost:8080/deposit/${storedAccountId}`
        : `http://localhost:8080/withdraw/${storedAccountId}`;

    axios
      .post(url, { amount: data.amount })
      .then((res) => {
        console.log(res.data);
        alert("Transaction successful");
        navigate("/userNav");
      })
      .catch((error) => {
        console.error(error);
        alert("Transaction failed");
      });
  };

  return (
    <>
      <div>
        <UserNavbarComponent />
      </div>
      <Container style={{ marginTop: "7%" }} maxWidth="sm">
        <div className="card">
          <div className="card-header text-center">
            Amount Deposit or Withdraw
          </div>
          <div className="card-body">
            <form onSubmit={submitHandler}>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <FormControl fullWidth required>
                    <InputLabel id="type-label">Transaction Type</InputLabel>
                    <Select
                      label="Transaction Type"
                      labelId="type-label"
                      name="type"
                      value={data.type}
                      onChange={handlerChange}
                    >
                      <MenuItem value="deposit">Deposit</MenuItem>
                      <MenuItem value="withdraw">Withdraw</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    label="Amount"
                    name="amount"
                    type="number"
                    value={data.amount}
                    required
                    onChange={handlerChange}
                    fullWidth
                  />
                </Grid>

                <Grid item xs={12}>
                  <Button
                    variant="contained"
                    color="primary"
                    type="submit"
                    fullWidth
                  >
                    Submit
                  </Button>
                </Grid>
              </Grid>
            </form>
          </div>
        </div>
      </Container>
    </>
  );
}

export default DepositOrWithdraw;
