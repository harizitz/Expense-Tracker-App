import { useState } from "react";
import axios from "axios";

function Login() {
  const [user, setUser] = useState({ email: "", password: "" });

  const emailHandler = (e) => {
    setUser({ ...user, email: e.target.value });
  };
  const passwordHandler = (e) => {
    setUser({ ...user, password: e.target.value });
  };

  const login = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/login", user)
      .then((response) => {
        sessionStorage.setItem("jwtToken", response.data.jwtToken);
        alert("Login Successful !");
        window.location = "/";
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <br /> <h3>Login</h3>
      <form onSubmit={login}>
        <div className="form-group">
          <label>Email </label>
          <input
            type="email"
            onChange={emailHandler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <label>Password </label>
          <input
            type="password"
            onChange={passwordHandler}
            className="form-control"
          />
        </div>
        <br />
        <input type="submit" value="Submit" className="btn btn-success" />
      </form>
      <br />
    </div>
  );
}

export default Login;
