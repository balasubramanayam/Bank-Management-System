import React from "react";
import {
  Navbar,
  Nav,
  Container,
  Form,
  FormControl,
  Button,
} from "react-bootstrap";
import {
  FaUser,
  FaSignOutAlt,
  FaKey,
  FaSearch,
  FaRegListAlt,
  FaMoneyBillWave,
  FaDollarSign,
  FaMoneyCheckAlt,
} from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom";
import "../Styles/Navbar.css";

const UserNavbarComponent = () => {
  const navigate = useNavigate();
  const handleLogout = () => {
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("accountId");
    navigate("/");
  };
  return (
    <div>
      <Navbar bg="light" expand="lg" fixed="top">
        <Container fluid>
          <Navbar.Brand as={Link} to="/">
            Bank Management System
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link as={Link} to="/profile" className="nav-link-spacing">
                <FaUser />
                <strong> Account</strong>
              </Nav.Link>
              <Nav.Link
                as={Link}
                to="/changePassword"
                className="nav-link-spacing"
              >
                <FaKey />
                <strong> Change Password</strong>
              </Nav.Link>
              <Nav.Link
                as={Link}
                to="/transactionsList"
                className="nav-link-spacing"
              >
                <FaRegListAlt /> <strong>Transactions Details</strong>
              </Nav.Link>
              <Nav.Link
                as={Link}
                to="/depositOrWithdraw"
                className="nav-link-spacing"
              >
                <FaDollarSign /> <strong>Deposit/Withdraw</strong>
              </Nav.Link>
              <Nav.Link
                as={Link}
                to="/"
                className="nav-link-spacing"
                onClick={handleLogout}
              >
                <FaSignOutAlt />
                <strong> LogOut</strong>
              </Nav.Link>
            </Nav>
            <Form className="d-flex">
              <FormControl
                type="search"
                placeholder="Search"
                className="me-2 search-bar"
                aria-label="Search"
              />
              <Button variant="outline-success">
                <FaSearch />
              </Button>
            </Form>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
};

export default UserNavbarComponent;
