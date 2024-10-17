import React, { useState } from "react";
import axios from "axios";
import { TextField, Button, Grid, Container } from "@mui/material";
import { FormControl, InputLabel, Select, MenuItem } from "@mui/material";
import { useNavigate } from "react-router-dom";
import AdminNavbarComponent from "./AdminNavbarComponent";

function CreateUserAccount() {
  const [data, setData] = useState({
    userFirstName: "",
    userLastName: "",
    userEmail: "",
    userDOB: "",
    userMobile: "",
    userGender: "",
    address: "",
    ifscCode: "",
    branchName: "",
  });

  const handlerChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const navigate = useNavigate();

  const submitHandler = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/createAccount", data)
      .then((res) => {
        console.log(res.data);
        setData(res.data);
        alert("Account successfully Created");
        navigate("/adminNav");
      })
      .catch((e) => console.log(e));
  };

  return (
    <>
      <div>
        <AdminNavbarComponent />
      </div>
      <Container style={{ marginTop: "3%" }} maxWidth="sm">
        <div className="card">
          <div className="card-header text-center">Create Account</div>
          <div className="card-body">
            <form onSubmit={submitHandler}>
              <Grid container spacing={2}>
                <Grid item xs={6}>
                  <TextField
                    label="First Name"
                    name="userFirstName"
                    value={data.userFirstName}
                    required
                    onChange={handlerChange}
                    fullWidth
                    size="small"
                  />
                </Grid>
                <Grid item xs={6}>
                  <TextField
                    label="Last Name"
                    name="userLastName"
                    value={data.userLastName}
                    required
                    onChange={handlerChange}
                    fullWidth
                    size="small"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    label="Email"
                    name="userEmail"
                    value={data.userEmail}
                    required
                    onChange={handlerChange}
                    fullWidth
                    size="small"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    label="Date of Birth"
                    name="userDOB"
                    type="date"
                    value={data.userDOB}
                    required
                    onChange={handlerChange}
                    fullWidth
                    size="small"
                    InputLabelProps={{
                      shrink: true,
                    }}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    label="Mobile Number"
                    name="userMobile"
                    required
                    value={data.userMobile}
                    onChange={handlerChange}
                    fullWidth
                    size="small"
                  />
                </Grid>

                {/* Branch Name Select */}
                <Grid item xs={6}>
                  <FormControl fullWidth required size="small">
                    <InputLabel id="branch-label">Branch Name</InputLabel>
                    <Select
                      label="Branch Name"
                      labelId="branch-label"
                      name="branchName"
                      value={data.branchName}
                      onChange={(e) => {
                        const branchName = e.target.value;
                        const ifscCode =
                          branches.find((branch) => branch.name === branchName)
                            ?.ifsc || "";
                        setData({ ...data, branchName, ifscCode });
                      }}
                    >
                      {branches.map((branch) => (
                        <MenuItem key={branch.ifsc} value={branch.name}>
                          {branch.name}
                        </MenuItem>
                      ))}
                    </Select>
                  </FormControl>
                </Grid>
                <Grid item xs={6}>
                  <TextField
                    label="IFSC"
                    name="ifscCode"
                    required
                    value={data.ifscCode}
                    onChange={handlerChange}
                    fullWidth
                    InputProps={{
                      readOnly: true,
                    }}
                    size="small"
                  />
                </Grid>

                <Grid item xs={12}>
                  <FormControl fullWidth required size="small">
                    <InputLabel id="gender-label">Gender</InputLabel>
                    <Select
                      label="Gender"
                      labelId="gender-label"
                      name="userGender"
                      value={data.userGender}
                      onChange={handlerChange}
                    >
                      <MenuItem value="male">Male</MenuItem>
                      <MenuItem value="female">Female</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>

                <Grid item xs={12}>
                  <TextField
                    label="Address"
                    name="address"
                    required
                    value={data.address}
                    onChange={handlerChange}
                    fullWidth
                    multiline
                    rows={4}
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
                    Create Account
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

const branches = [
  { name: "Chennai", ifsc: "ABC123456" },
  { name: "Coimbatore", ifsc: "DEF123456" },
  { name: "Madurai", ifsc: "GHI123456" },
  { name: "Trichy", ifsc: "JKL123456" },
  { name: "Tirunelveli", ifsc: "MNO123456" },
  { name: "Salem", ifsc: "PQR123456" },
  { name: "Erode", ifsc: "STU123456" },
  { name: "Tiruppur", ifsc: "VWX123456" },
  { name: "Vellore", ifsc: "YZA123456" },
  { name: "Kanyakumari", ifsc: "BCD123456" },
  { name: "Nagapattinam", ifsc: "EFG123456" },
  { name: "Pudukkottai", ifsc: "HIJ123456" },
  { name: "Karur", ifsc: "KLM123456" },
  { name: "Dindigul", ifsc: "NOP123456" },
  { name: "Sivagangai", ifsc: "QRS123456" },
  { name: "Thanjavur", ifsc: "TUV123456" },
  { name: "Nagercoil", ifsc: "WXY123456" },
  { name: "Thiruvarur", ifsc: "ZAB123456" },
  { name: "Villupuram", ifsc: "CDE123456" },
  { name: "Kanchipuram", ifsc: "FGH123456" },
  { name: "Tiruvannamalai", ifsc: "IJK123456" },
  { name: "Ranipet", ifsc: "LMN123456" },
  { name: "Ariyalur", ifsc: "OPQ123456" },
  { name: "Perambalur", ifsc: "RST123456" },
  { name: "Thiruvarur", ifsc: "UVW123456" },
  { name: "Krishnagiri", ifsc: "XYZ123456" },
  { name: "Cuddalore", ifsc: "QAZ123456" },
  { name: "Virudhunagar", ifsc: "WSX123456" },
  { name: "Dharmapuri", ifsc: "EDC123456" },
  { name: "Tenkasi", ifsc: "RFV123456" },
  { name: "Sivakasi", ifsc: "TGB123456" },
  { name: "Puducherry", ifsc: "YHN123456" },
  { name: "Ambur", ifsc: "UJM123456" },
  { name: "Kallakurichi", ifsc: "IKJ123456" },
  { name: "Kodaikanal", ifsc: "PLM123456" },
  { name: "Valparai", ifsc: "MNB123456" },
];

export default CreateUserAccount;
