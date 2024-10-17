import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import CreateUserAccount from "./Admin/CreateUserAccount";
import LoginUser from "./User/LoginUser";
import ChangePassword from "./User/ChangePassword";
import UserProfile from "./User/UserProfile";
import UserAccountInfo from "./Admin/UserAccountInfo";
import DepositOrWithdraw from "./Transactions/DepositOrWithdraw";
import AdminNavbarComponent from "./Admin/AdminNavbarComponent";
import UserNavbarComponent from "./User/UserNavbarComponent";
import UserList from "./Admin/UserList";
import TransactionsList from "./Transactions/TransactionsList";
import UserTransactionsList from "./Admin/UserTransactionsList";


function App() {
  return(
    <div>
      <BrowserRouter>
      <Routes>
      <Route path="/createAccount" element={<CreateUserAccount/>}/>
      <Route path="/" element={<LoginUser/>}/>
      <Route path="/changePassword" element={<ChangePassword/>}/>
      <Route path="/profile" element={<UserProfile/>}/>
      <Route path="/userAccountInfo" element={<UserAccountInfo/>}/>
      <Route path="/transactionsList" element={<TransactionsList/>}/>
      <Route path="/depositOrWithdraw" element={<DepositOrWithdraw/>}/>
      <Route path="/adminNav" element={<AdminNavbarComponent/>}/>
      <Route path="/userNav" element={<UserNavbarComponent/>}/>
      <Route path="/userList" element={<UserList/>}/>  
      <Route path="/transactionsById/:accountId" element={<UserTransactionsList />} />
      </Routes>
      </BrowserRouter>
    </div>
  ) 
}

export default App;
