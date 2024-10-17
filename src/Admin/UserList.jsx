import axios from "axios";
import React, { useEffect, useState } from "react";
import AdminNavbarComponent from "./AdminNavbarComponent";
import { Link } from "react-router-dom";

function UserList() {
  const [account, setAccount] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/getAllAccounts")
      .then((response) => {
        setAccount(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const deleteHandler = (id) => {
    if (window.confirm("are you sure?")) {
      axios
        .delete(`http://localhost:8080/deleteByUser/${id}`)
        .then((res) => {
          setAccount(account);
          console.log(res.data);
          window.location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  return (
    <div>
      <AdminNavbarComponent />
      <div className="container mt-5">
        <div className="text-center mt-4">
          <strong>All User</strong>
        </div>
        <table className="table table-responsive table-bordered table-striped">
          <thead className="thead-dark">
            <tr>
              <th>ID</th>
              <th>Account Number</th>
              <th>User First Name</th>
              <th>User Last Name</th>
              <th>Account Balance</th>
              <th>Account Creation Date</th>
              <th>Delete</th>
              <th>Transactions</th>
            </tr>
          </thead>
          <tbody>
            {account.map((account, index) => (
              <tr key={index}>
                <td>{account.user.userId}</td>
                <td>{account.accountNumber}</td>
                <td>{account.user.userFirstName}</td>
                <td>{account.user.userLastName}</td>
                <td>{account.balance}</td>
                <td>{account.accountCreationDate}</td>
                <td>
                  {" "}
                  <button
                    className="btn delete-btn btn-danger"
                    onClick={() => deleteHandler(account.user.userId)}
                  >
                    Delete
                  </button>
                </td>
                <td>
                  <Link
                    to={`/transactionsById/${account.user.userId}`}
                    className="btn bg-success"
                  >
                    Transactions
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default UserList;
