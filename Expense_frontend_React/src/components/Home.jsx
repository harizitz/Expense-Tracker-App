import React from "react";
import { Link } from "react-router-dom";
export default function Home(props) {
  return (
    <div>
      <br />
      <h3>Please Login or Register </h3>
      <br />
      <Link to="/login" className="btn btn-primary">
        Login
      </Link>
      <br />
      <br />
      <Link to="/register" className="btn btn-warning">
        Register
      </Link>
    </div>
  );
}
