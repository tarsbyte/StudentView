import logo from './logo.svg';
import './App.css';
import * as ReactDOM from 'react-dom';
import React, { Component } from 'react';
import {Container, Row, Col, Button, Alert, Breadcrumb, Card, Form} from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import ShowCode from "./components/ShowCode";

//Sources:
//https://www.w3schools.com/js/js_output.asp
//https://getbootstrap.com/
//https://www.w3schools.com/react/showreact.asp?filename=demo2_react_render1
//https://www.w3schools.com/react/react_render.asp
//https://pl.reactjs.org/docs/react-dom.html
//https://create-react-app.dev/docs/importing-a-component/
//https://pl.reactjs.org/docs/components-and-props.html?fbclid=IwAR04WTWkUiUwgxOOBcbbkoWc5f4wqKerBWJ-s6c1Agf4K851zeUAFLdhkt8
//https://ourcodeworld.com/articles/read/562/how-to-use-properly-ace-editor-in-reactjs
//https://www.npmjs.com/package/brace
//https://www.youtube.com/watch?v=Ri4LUTdszkU
//https://github.com/securingsincity/react-ace/blob/master/docs/Ace.md
//https://github.com/ajaxorg/ace-builds/tree/master/src-noconflict
//https://www.w3.org/Style/Examples/007/center.pl.html
//https://www.w3schools.com/html/html_css.asp
//https://getbootstrap.com/docs/5.0/forms/form-control/
//https://stackoverflow.com/questions/32341358/how-do-i-limit-the-size-of-my-react-component-and-add-scrolling
//https://www.w3schools.com/bootstrap4/

function App() {

    var code = "import React from 'react';\n" +
        "import ReactDOM from 'react-dom';\n" +
        "import './index.css';\n" +
        "import App from './App';\n" +
        "import reportWebVitals from './reportWebVitals';\n" +
        "\n" +
        "ReactDOM.render(\n" +
        "  <React.StrictMode>\n" +
        "    <App />\n" +
        "  </React.StrictMode>,\n" +
        "  document.getElementById('root')\n" +
        ");\n" +
        "\n" +
        "// If you want to start measuring performance in your app, pass a function\n" +
        "// to log results (for example: reportWebVitals(console.log))\n" +
        "// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals\n" +
        "reportWebVitals();\n";

    var descr = "If you want to start measuring performance in your app, pass a function\n" +
        "to log results (for example: reportWebVitals(console.log))\n" +
        "or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals.If you want to start measuring performance in your app, pass a function\n" +
        "to log results (for example: reportWebVitals(console.log))\n" +
        "or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals.";

    return(<ShowCode lang = {"javascript"} nick = {"kowal"} fName = {"Janusz"} lName={"Kowalski"} code={code} descr= {descr}/>);

}

export default App;
