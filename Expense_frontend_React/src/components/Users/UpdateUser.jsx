import { useEffect, useState } from "react";
import axios from "axios";

function UpdateUser() {
  const token = sessionStorage.getItem("jwtToken");
  const [user, setUser] = useState({
    id: 0,
    name: "",
    email: "",
    age: 0,
    createdAt: "",
  });

  const update = (e) => {
    axios
      .put("http://localhost:8080/profile", user, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => {
        alert("Updated");
        window.location = "/getuser";
      })
      .catch((error) => {
        console.log(error);
      });
    e.preventDefault();
  };

  const handler = (e) => {
    setUser({ ...user, [e.currentTarget.id]: e.target.value });
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/profile", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setUser(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [token]);

  return (
    <div>
      <br />
      <h3>Update User</h3>
      <br />
      <form onSubmit={update}>
        <div className="form-group">
          <label>Name </label>
          <input
            type="name"
            id="name"
            value={user.name}
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
            value={user.email}
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
            value={user.age}
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

export default UpdateUser;
