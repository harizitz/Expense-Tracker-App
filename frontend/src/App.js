
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import Home from "./components/Home"
import Navbar from "./components/Navbar"
import AddExpense from "./components/Expense/AddExpense"
import UpdateExpense from "./components/Expense/UpdateExpense"
import ViewExpenses from "./components/Expense/ViewExpenses"
import GetUser from "./components/Users/GetUser"
import Login from "./components/Users/Login"
import Register from "./components/Users/Register"
import UpdateUser from "./components/Users/UpdateUser"



function App() {
  return (
    <>
      <BrowserRouter>
        <div className="App">
          {sessionStorage.getItem('jwtToken') !== null && <Navbar />}
          <div className="container">
            <Routes>
              <Route path="/" exact element={sessionStorage.getItem('jwtToken') !== null ? <ViewExpenses /> : <Home />} />
              <Route path="/login" element={sessionStorage.getItem('jwtToken') === null && <Login />} />
              <Route path="/register" element={sessionStorage.getItem('jwtToken') === null && <Register />} />
              <Route path="/getuser" element={sessionStorage.getItem('jwtToken') !== null ? <GetUser /> : <Navigate to='/' />} />
              <Route path="/updateuser" element={sessionStorage.getItem('jwtToken') !== null ? <UpdateUser /> : <Navigate to='/' />} />
              <Route path="/addexpense" element={sessionStorage.getItem('jwtToken') !== null ? <AddExpense /> : <Navigate to='/' />} />
              <Route path="/updateexpense/:id" element={sessionStorage.getItem('jwtToken') !== null ? <UpdateExpense /> : <Navigate to='/' />} />
            </Routes>
          </div>
        </div>
      </BrowserRouter >
    </>
  );
}

export default App;
