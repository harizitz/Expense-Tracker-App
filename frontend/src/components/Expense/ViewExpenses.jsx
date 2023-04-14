import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function ViewExpense() {
  const token = sessionStorage.getItem("jwtToken");
  const [expenses, setExpenses] = useState([]);

  const deleteExpense = (id) => {
    if (window.confirm("Are you sure? You want to delete ?")) {
      axios
        .delete("http://localhost:8080/expense/" + id, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then(() => {
          console.log("Deleted");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/expenses", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        setExpenses(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [token, expenses]);

  return (
    <div>
      <br />
      <h3>View Expenses</h3>
      <br />
      {expenses.length === 0 && <div>No results</div>}
      {expenses.map((item, key) => {
        return (
          <div key={key} className="form-control">
            <br />
            <h6>Id: {item.id}</h6>
            <h6>Name : {item.name}</h6>
            <h6>Description : {item.description}</h6>
            <h6>Amount : {item.amount}</h6>
            <h6>Category : {item.category}</h6>
            <h6>Created Date : {item.createdDate}</h6>
            <h6>Updated Date : {item.updatedDate}</h6>
            <Link to={`/updateexpense/${item.id}`} className="btn btn-warning">
              Edit
            </Link>
            &nbsp;
            <button
              onClick={() => deleteExpense(item.id)}
              className="btn btn-danger"
            >
              Delete
            </button>
            <br />
          </div>
        );
      })}
    </div>
  );
}

export default ViewExpense;
