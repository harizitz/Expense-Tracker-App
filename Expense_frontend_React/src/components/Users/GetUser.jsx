import { useEffect, useState } from "react";
import axios from "axios";

function GetUser() {
  const token = sessionStorage.getItem("jwtToken");
  const [user, setUser] = useState({
    id: 0,
    name: "",
    email: "",
    age: 0,
    createdAt: "",
  });

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
  }, [token, user]);

  return (
    <div>
      <br />
      <br />
      <h3>Profile Details</h3>
      <br />
      <br />
      <br />
      <h5>Name : {user.name}</h5>
      <br />
      <h5>Email : {user.email}</h5>
      <br />
      <h5>Age : {user.age}</h5>
      <br />
      <h5>Created At : {user.createdAt}</h5>
      <br />
    </div>
  );
}

export default GetUser;
