import React, { useEffect, useState } from "react";
import axios from "axios";
import UserNavbarComponent from "../User/UserNavbarComponent";
import "bootstrap/dist/css/bootstrap.min.css";

function TransactionsList() {
  const [transactions, setTransactions] = useState([]);

  const storedAccountId = sessionStorage.getItem("accountId");
  useEffect(() => {
    axios
      .get(`http://localhost:8080/transactionsById/${storedAccountId}`)
      .then((response) => {
        setTransactions(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div>
      <UserNavbarComponent />
      <div className="container mt-5">
        <table className="table table-responsive table-bordered table-striped">
          <thead className="thead-dark">
            <tr>
              <th>ID</th>
              <th>Amount</th>
              <th>Type</th>
              <th>Transaction Date</th>
              <th>Account Number</th>
              <th>Balance</th>
              <th>User First Name</th>
              <th>User Last Name</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((transaction, index) => (
              <tr key={index}>
                <td>{transaction.id}</td>
                <td>{transaction.amount}</td>
                <td>{transaction.type}</td>
                <td>{transaction.transactionDate}</td>
                <td>{transaction.account.accountNumber}</td>
                <td>{transaction.account.balance}</td>
                <td>{transaction.account.user.userFirstName}</td>
                <td>{transaction.account.user.userLastName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default TransactionsList;
