import React, { useEffect, useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import { useParams } from "react-router-dom";
import AdminNavbarComponent from "./AdminNavbarComponent";

function UserTransactionsList() {
  const [transactions, setTransactions] = useState({});
  const [loading, setLoading] = useState(true);

  const [error, setError] = useState(null);

  const { accountId } = useParams();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/transactionsById/${accountId}`)
      .then((response) => {
        setTransactions(response.data);
      })
      .catch((err) => {
        setError("Failed to fetch transactions");
        console.log(err);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  if (loading) return <div className="text-center">Loading...</div>;
  if (error) return <div className="text-center text-danger">{error}</div>;

  return (
    <div>
      <AdminNavbarComponent />
      <div className="container mt-5">
        <div className="text-center">
          <strong>All Transactions</strong>
        </div>
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

export default UserTransactionsList;
