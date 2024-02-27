import { QrScanner } from "@yudiel/react-qr-scanner";
import { useState } from "react";
import { parseJwt } from "../Misc/Helper";
import { useAuth } from "../Context/AuthContext";
import { Navigate } from "react-router-dom";
const QRScanner = () => {
  const Auth = useAuth();
  const { userIsAuthenticated, handleLogError } = useAuth();
  const isLoggedIn = Auth.userIsAuthenticated();
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);
  const handleDecode = (token) => {
    try {
      const data = parseJwt(token);
      console.log("parsejwt" + data);
      const authenticatedUser = { data, token };

      Auth.userLogin(authenticatedUser);
    } catch (error) {
      handleLogError(error);
      console.log(error);
    }
  };
  const handleError = (token) => {
    setError(token);
  };
  if (isLoggedIn) {
    return <Navigate to="/" />;
  }
  return (
    <div className="flex justify-center item-center">
      <div className="w-1/3">
        <div>
          <QrScanner
            onDecode={handleDecode}
            onError={handleError}
            style={{ width: 400 }}
          />
        </div>
      </div>
    </div>
  );
};

export default QRScanner;
