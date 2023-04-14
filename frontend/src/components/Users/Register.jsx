import { useState } from "react";
import axios from "axios";

function Register() {
  const [user, setUser] = useState({
    name: "",
    email: "",
    age: 0,
  });

  const handler = (e) => {
    setUser({ ...user, [e.currentTarget.id]: e.target.value });
  };

  const register = (e) => {
    axios
      .post("http://localhost:8080/register", user)
      .then(() => {
        console.log("registered");
        alert("Registered ! Please login");
        window.location = "/login";
      })
      .catch((error) => {
        console.log(error);
      });
    e.preventDefault();
  };

  return (
    <div>
      <br />
      <h3>Register</h3>
      <form onSubmit={register}>
        <div className="form-group">
          <label>Name </label>
          <input
            type="name"
            id="name"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <label>Email </label>
          <input
            type="email"
            id="email"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <label>Password </label>
          <input
            type="password"
            id="password"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <label>Age </label>
          <input
            type="number"
            id="age"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <input type="submit" value="Submit" className="btn btn-success" />
        </div>
      </form>
    </div>
  );
}

export default Register;
