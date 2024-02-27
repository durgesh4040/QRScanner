// LoginPage.js
import React, { useState } from "react";
import { useAuth } from "../Context/AuthContext";
import QRScanner from "./QRScanner";
import axios from "axios";
import { parseJwt } from "../Misc/Helper";
import { Navigate } from "react-router-dom";
import Home from "./Home";
const LoginPage = () => {
  const Auth = useAuth();
  const { userIsAuthenticated, userLogout, handleLogError } = useAuth();
  const isLoggedIn = Auth.userIsAuthenticated();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showScanner, setShowScanner] = useState(false);
  const [isError, setIsError] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!(email && password)) {
      setIsError(true);
      return;
    }
    try {
      const response = await axios.post(
        "http://localhost:8080/auth/login",
        {
          email,
          password,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log(response.data);
      const { accessToken } = response.data;
      const data = parseJwt(accessToken);
      console.log("parsejwt" + data);
      const authenticatedUser = { data, accessToken };

      Auth.userLogin(authenticatedUser);

      setEmail("");
      setPassword("");
      setIsError(false);
    } catch (error) {
      handleLogError(error);
      console.log(error);
      setIsError(true);
    }
    // Implement your login logic here
    console.log("Logging in with:", email, password);
  };

  const handleQRScan = (e) => {
    // Trigger QR Scanner here
    e.preventDefault();
    console.log("Initiating QR Scanner");
    setShowScanner(true);
  };

  if (showScanner) {
    return <QRScanner />;
  }
  if (isLoggedIn) {
    return <Navigate to="/" />;
  }
  return (
    <div className="min-h-screen flex flex-col justify-center items-center bg-slate-800">
      <div className="max-w-md w-full bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <h2 className="text-3xl font-bold  text-gray-800 mb-6 text-center  font-serif">
          Login
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label
              className="block text-gray-700 sm:text-2xl font-bold mb-2"
              htmlFor="email"
            >
              Email
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-4 px-6 text-gray-700 sm:text-xl leading-tight focus:outline-none focus:shadow-outline"
              id="email"
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="mb-6">
            <label
              className="block text-gray-700 sm:text-2xl font-bold mb-2"
              htmlFor="password"
            >
              Password
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-4 px-6 text-gray-700 sm:text-xl mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              placeholder="******************"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="flex items-center justify-between">
            <button
              className="bg-blue-500 hover:bg-blue-700 text-white sm:text-xl font-bold py-4 px-6 rounded-md focus:outline-none focus:shadow-outline"
              type="submit"
            >
              Login
            </button>
            <button
              type="button"
              onClick={handleQRScan}
              className="bg-green-500 hover:bg-green-700 text-white sm:text-xl font-bold py-4 px-6 rounded-md focus:outline-none focus:shadow-outline"
            >
              QR Scanner
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;