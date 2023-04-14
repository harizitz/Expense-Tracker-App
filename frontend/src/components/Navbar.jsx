import React from "react";
import { Link } from "react-router-dom";
function Navbar() {
  return (
    <nav className="navbar navbar-dark navbar-expand-md bg-dark">
      <Link to="/" className="navbar-brand">
        Expense App
      </Link>
      <div className="collpase navbar-collapse ">
        <ul className="navbar-nav mr-auto">
          <li className="navbar-item">
            <Link to="/" className="nav-link">
              Home
            </Link>
          </li>
          <li className="navbar-item">
            <Link to="/addexpense" className="nav-link">
              Add Expense
            </Link>
          </li>
          <li className="navbar-item">
            <Link to="/updateuser" className="nav-link">
              Update User
            </Link>
          </li>
          <li className="navbar-item">
            <Link to="/getuser" className="nav-link">
              Get User
            </Link>
          </li>
          <li className="navbar-item">
            <Link
              onClick={() => {
                if (window.confirm("Are you sure to logout?")) {
                  sessionStorage.clear();
                  window.location = "/";
                }
              }}
              className="btn btn-danger"
            >
              Logout
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
