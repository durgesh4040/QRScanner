// import { useAuth } from "../Context/AuthContext";
import { useAuth } from "../Context/AuthContext";
export default function Home() {
  const { getUser, userIsAuthenticated } = useAuth();
  const getUserName = () => {
    const user = getUser();
    return user ? user.data.name : "";
  };
  const name = userIsAuthenticated() ? getUserName() : "Guest";
  return (
    <div className="flex justify-center items-center h-screen bg-slate-800 text-white">
      <div className="text-center">
        <h1 className="text-4xl font-bold">Welcome to Our Website!</h1>

        <h1 className="mt-4 text-3xl">
          We're glad you're{" "}
          <span className="text-blue-400 text-4xl">{name}</span>.
        </h1>
      </div>
    </div>
  );
  //
}
