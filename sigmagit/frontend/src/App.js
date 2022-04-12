import logo from "./logo.svg";
import { Route, Navigate, Routes } from "react-router-dom";
import StudentRegister from "./components/studentRegister";
import StudentsList from "./components/StudentsList";
import "./App.css";

function App() {
  return (
    <main className="container">
      <Routes>
        <Route path="/register" element={<StudentRegister />} />
        <Route path="/" element={<StudentsList />} />
      </Routes>
    </main>
  );
}

export default App;
