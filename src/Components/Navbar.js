// Navbar.js
import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../Context/AuthContext";

const Navbar = () => {
  const { getUser, userLogout, userIsAuthenticated } = useAuth();

  const getUserName = () => {
    const user = getUser();
    return user ? user.data.name : "";
  };

  const logout = () => {
    userLogout();
  };
  const name = userIsAuthenticated() ? getUserName() : "Guest";

  return (
    <nav className="bg-slate-900 text-white p-4">
      <div className="container mx-auto flex justify-between items-center">
        <div className="text-lg font-semibold">
          <Link to="/" className="hover:text-gray-300 sm:text-2xl">
            Home
          </Link>
        </div>
        <div className="flex items-center space-x-5">
          <div>
            <Link to="/Login" className="hover:text-gray-300  sm:text-2xl">
              Login
            </Link>
          </div>
          <div>
            <Link to="/signup" className="hover:text-gray-300 sm:text-2xl">
              Signup
            </Link>
          </div>

          <div className="hover:text-gray-300 sm:text-2xl font-semibold">
            <span className="bg-blue-500 text-white px-3 py-1 rounded">
              {name}
            </span>
          </div>
          {userIsAuthenticated() && (
            <div>
              <button
                onClick={logout}
                className="hover:text-gray-300 sm:text-2xl"
              >
                Logout
              </button>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
