import { useState } from "react";
import axios from "axios";

function AddExpense() {
  const token = sessionStorage.getItem("jwtToken");
  const [expense, setExpense] = useState({
    name: "",
    description: "",
    amount: 0,
    category: "",
  });

  const handler = (e) => {
    setExpense({ ...expense, [e.currentTarget.id]: e.target.value });
  };

  const saver = (e) => {
    axios
      .post("http://localhost:8080/addExpense/", expense, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => {
        alert("Added Successfully");
        window.location = "/";
      })
      .catch((error) => {
        console.log(error);
      });
    e.preventDefault();
  };

  return (
    <div>
      <br />
      <br />
      <h3>Add Expense</h3>
      <br />
      <form onSubmit={saver}>
        <div className="form-group">
          <label>Name </label>
          <input
            type="name"
            id="name"
            className="form-control"
            onChange={handler}
          />
        </div>
        <div className="form-group">
          <label>Description </label>
          <input
            type="textarea"
            id="description"
            className="form-control"
            onChange={handler}
          />
          <br />
        </div>
        <div className="form-group">
          <label>Amount </label>
          <input
            type="number"
            id="amount"
            className="form-control"
            onChange={handler}
          />
          <br />
        </div>
        <div className="form-group">
          <label>Category </label>
          <input
            type="name"
            id="category"
            className="form-control"
            onChange={handler}
          />
          <br />
        </div>
        <div className="form-group">
          <input type="submit" value="Submit" className="btn btn-warning" />
        </div>
      </form>
    </div>
  );
}

export default AddExpense;
