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
  FaUserPlus,
  FaSignOutAlt,
  FaSearch,
  FaRegListAlt,
} from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom";
import "../Styles/Navbar.css";

const AdminNavbarComponent = () => {
  const navigate = useNavigate();
  const handleLogout = () => {
    sessionStorage.removeItem("adminId");
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
              <Nav.Link
                as={Link}
                to="/createAccount"
                className="nav-link-spacing"
              >
                <FaUserPlus /> <strong>Create Account</strong>
              </Nav.Link>
              <Nav.Link as={Link} to="/userList" className="nav-link-spacing">
                <FaRegListAlt /> <strong> User List</strong>
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

export default AdminNavbarComponent;
