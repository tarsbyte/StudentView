import { React, useState } from "react"
import StudentView from "./StudentView"


function StudentList() {

    const [hidden, setHidden] = useState(true);

    return (
      <div className="App">
        <div>
            <button onClick={() => setHidden(!hidden)}> 
            Student 
            </button>
        </div>
        {!hidden ? <div><StudentView/></div> : null}
      </div>
    );
  }
  
  export default StudentList;
  