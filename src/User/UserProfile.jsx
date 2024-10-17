import axios from "axios";
import React, { useEffect, useState } from "react";
import {
  Box,
  Typography,
  TextField,
  Button,
  Grid,
  Snackbar,
} from "@mui/material";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import MoneyOffIcon from "@mui/icons-material/MoneyOff";
import CalendarTodayIcon from "@mui/icons-material/CalendarToday";
import DescriptionIcon from "@mui/icons-material/Description";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import PhoneIcon from "@mui/icons-material/Phone";
import EmailIcon from "@mui/icons-material/Email";
import UserNavbarComponent from "../User/UserNavbarComponent";
import { Link } from "react-router-dom";

function UserProfile() {
  const [userData, setUserData] = useState({
    accountNumber: "",
    balance: "",
    accountCreationDate: "",
    ifscCode: "",
    branchName: "",
    user: {
      userFirstName: "",
      userLastName: "",
      userEmail: "",
      userDOB: "",
      userMobile: "",
      userGender: "",
      address: "",
    },
  });

  const storedUserId = sessionStorage.getItem("userId");

  const [isEditing, setIsEditing] = useState(false);
  const [loading, setLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    axios
      .get(`http://localhost:8080/accountId/${storedUserId}`)
      .then((response) => {
        setUserData(response.data);
        const accountId = response.data.id;
        sessionStorage.setItem("accountId", accountId);
        const storedAccountId = sessionStorage.getItem("accountId");
        console.log("Stored Account ID:", storedAccountId);
        setLoading(false);
      })
      .catch((err) => {
        setErrorMessage("Failed to load user data.");
        setLoading(false);
      });
  }, []);

  const handleEditClick = () => {
    setIsEditing(true);
  };

  const handleSaveClick = () => {
    axios
      .put(`http://localhost:8080/updateAccount/${storedUserId}`, userData)
      .then((response) => {
        setSuccessMessage("User data updated successfully.");
        setIsEditing(false);
      })
      .catch(() => {
        setErrorMessage("Error updating user data.");
      });
  };

  const handleCloseSnackbar = () => {
    setErrorMessage("");
    setSuccessMessage("");
  };

  const renderField = (label, value, onChange, icon, isEditable = true) => (
    <Grid item xs={12} sm={6} md={3}>
      <Typography
        variant="body1"
        fontWeight="bold"
        display="flex"
        alignItems="center"
      >
        {icon} {label}:
      </Typography>
      <div style={{ display: "flex", alignItems: "center" }}>
        {isEditable && isEditing ? (
          <TextField
            value={value}
            onChange={onChange}
            fullWidth
            margin="normal"
            size="small"
          />
        ) : (
          <Typography variant="body1" style={{ marginLeft: 8 }}>
            {value}
          </Typography>
        )}
      </div>
    </Grid>
  );

  return (
    <>
      <UserNavbarComponent />
      <Box sx={{ padding: 4, marginTop: 5 }}>
        <div className="card">
          <div className="card-header text-center text-bold">
            <strong> User Account Information</strong>
          </div>
          <div className="card-body">
            <Box sx={{ marginTop: 4 }}>
              <Grid container spacing={4}>
                {renderField(
                  "Account Number",
                  userData.accountNumber,
                  (e) =>
                    setUserData({ ...userData, accountNumber: e.target.value }),
                  <AccountCircleIcon />,
                  false
                )}
                {renderField(
                  "Balance",
                  userData.balance,
                  (e) => setUserData({ ...userData, balance: e.target.value }),
                  <MoneyOffIcon />,
                  false
                )}
                {renderField(
                  "Creation Date",
                  userData.accountCreationDate,
                  (e) =>
                    setUserData({
                      ...userData,
                      accountCreationDate: e.target.value,
                    }),
                  <CalendarTodayIcon />,
                  false
                )}
                {renderField(
                  "IFSC Code",
                  userData.ifscCode,
                  (e) => setUserData({ ...userData, ifscCode: e.target.value }),
                  <DescriptionIcon />,
                  false
                )}
                {renderField(
                  "Branch Name",
                  userData.branchName,
                  (e) =>
                    setUserData({ ...userData, branchName: e.target.value }),
                  <DescriptionIcon />,
                  false
                )}
                {renderField(
                  "First Name",
                  userData.user.userFirstName,
                  (e) =>
                    setUserData({
                      ...userData,
                      user: { ...userData.user, userFirstName: e.target.value },
                    }),
                  <AccountCircleIcon />
                )}
                {renderField(
                  "Last Name",
                  userData.user.userLastName,
                  (e) =>
                    setUserData({
                      ...userData,
                      user: { ...userData.user, userLastName: e.target.value },
                    }),
                  <AccountCircleIcon />
                )}
                {renderField(
                  "Email",
                  userData.user.userEmail,
                  (e) =>
                    setUserData({
                      ...userData,
                      user: { ...userData.user, userEmail: e.target.value },
                    }),
                  <EmailIcon />
                )}
                {renderField(
                  "Date of Birth",
                  userData.user.userDOB,
                  (e) =>
                    setUserData({
                      ...userData,
                      user: { ...userData.user, userDOB: e.target.value },
                    }),
                  <CalendarTodayIcon />
                )}
                {renderField(
                  "Mobile",
                  userData.user.userMobile,
                  (e) =>
                    setUserData({
                      ...userData,
                      user: { ...userData.user, userMobile: e.target.value },
                    }),
                  <PhoneIcon />
                )}
                {renderField(
                  "Gender",
                  userData.user.userGender,
                  (e) =>
                    setUserData({
                      ...userData,
                      user: { ...userData.user, userGender: e.target.value },
                    }),
                  <DescriptionIcon />
                )}
                {renderField(
                  "Address",
                  userData.user.address,
                  (e) =>
                    setUserData({
                      ...userData,
                      user: { ...userData.user, address: e.target.value },
                    }),
                  <LocationOnIcon />
                )}
              </Grid>
            </Box>

            <Button
              variant="contained"
              onClick={isEditing ? handleSaveClick : handleEditClick}
              sx={{ marginTop: 2 }}
            >
              {isEditing ? "Save" : "Edit"}
            </Button>
            <Link className=""></Link>
            <Snackbar
              open={!!errorMessage || !!successMessage}
              autoHideDuration={6000}
              onClose={handleCloseSnackbar}
              message={errorMessage || successMessage}
            />
          </div>
        </div>
      </Box>
    </>
  );
}

export default UserProfile;
