import './App.css';
import StudentsList from "./components/StudentsList"
import LoginPage from "./components/LoginPage";

function App() {
  return (
    <div className="App">
      <StudentsList/>
        <LoginPage/>
    </div>
  );
}

export default App;
