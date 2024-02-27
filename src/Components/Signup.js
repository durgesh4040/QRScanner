import React, { useState } from "react";
import { useAuth } from "../Context/AuthContext";

import axios from "axios";
import { Navigate } from "react-router-dom";
import { parseJwt } from "../Misc/Helper";
import Home from "./Home";

const Signup = () => {
  const Auth = useAuth();
  const { userIsAuthenticated, userLogout, handleLogError } = useAuth();
  const isLoggedIn = Auth.userIsAuthenticated();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!(username && email && password && name)) {
      return;
    }
    try {
      const response = await axios.post(
        "http://localhost:8080/auth/signup",
        {
          username,
          email,
          password,
          name,
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
      setName("");
      setUsername("");
      setEmail("");
      setPassword("");
    } catch (error) {
      handleLogError(error);
    }
    // Implement your signup logic here
    console.log("Signing up with:", username, email, password, name);
  };

  if (isLoggedIn) {
    return <Navigate to="/" />;
  }
  return (
    <div className="min-h-screen flex flex-col justify-center items-center bg-slate-800">
      <div className="max-w-md w-full bg-white shadow-md rounded px-8 pt-6 pb-8 mb-6">
        <h2 className="text-3xl font-bold  text-gray-800 mb-6 text-center  font-serif">
          Signup
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label
              className="block text-gray-700 sm:text-2xl font-bold mb-2"
              htmlFor="username"
            >
              Username
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-4 px-6 text-gray-700 sm:text-xl leading-tight focus:outline-none focus:shadow-outline"
              id="username"
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label
              className="block text-gray-700 sm:text-2xl font-bold mb-2"
              htmlFor="name"
            >
              Name
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-4 px-6 text-gray-700 sm:text-xl leading-tight focus:outline-none focus:shadow-outline"
              id="name"
              type="text"
              placeholder="Name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>
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
          <div className="flex justify-center item-center">
            <button
              className="bg-blue-500 hover:bg-blue-700   text-white sm:text-xl font-bold py-4 px-6 rounded-md focus:outline-none focus:shadow-outline"
              type="submit"
            >
              Signup
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Signup;
