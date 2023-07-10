import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

function UpdateExpense(props) {
  const token = sessionStorage.getItem("jwtToken");
  const params = useParams();
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
      .put("http://localhost:8080/update/" + params.id, expense, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(() => {
        alert("Updated");
        window.location = "/";
      })
      .catch((error) => {
        console.log(error);
      });
    e.preventDefault();
  };

  useEffect(() => {
    console.log("inside update use effect");
    axios
      .get("http://localhost:8080/expense/" + params.id, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setExpense(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [token, params.id]);

  return (
    <div>
      <br />
      <h3>Update Expense</h3>
      <br />
      <form onSubmit={saver}>
        <div className="form-group">
          <label>Name </label>
          <input
            type="name"
            value={expense.name}
            id="name"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <label>Description </label>
          <input
            type="textarea"
            value={expense.description}
            id="description"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <label>Amount </label>
          <input
            type="number"
            value={expense.amount}
            id="amount"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <div className="form-group">
          <label>Category </label>
          <input
            type="name"
            value={expense.category}
            id="category"
            onChange={handler}
            className="form-control"
          />
        </div>
        <br />
        <input type="submit" value="Submit" className="btn btn-warning" />
      </form>
    </div>
  );
}

export default UpdateExpense;
