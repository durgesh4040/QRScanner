import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar";
import Home from "./Components/Home";
import LoginPage from "./Components/Login";
import { AuthProvider } from "./Context/AuthContext";
import Signup from "./Components/Signup";
function App() {
  return (
    <div>
      <AuthProvider>
        <BrowserRouter>
          <Navbar />
          <div className="pt-4">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<LoginPage />} />
              <Route path="/signup" element={<Signup />} />
              <Route path="/scanner" element={<div>Scanner</div>} />
              {/* Add more routes as needed */}
            </Routes>
          </div>
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;
