import React from "react";
import Joi from "joi-browser";
import Form from "./Form";

class StudentRegister extends Form {
  state = {
    data: { code: "", index: "", fullname: "" },
    errors: {},
  };

  schema = {
    code: Joi.number().required().label("Code"),
    index: Joi.number().required().label("Index"),
    fullname: Joi.string().required().label("Fullname"),
  };

  doSubmit = () => {
    // Save student
    console.log("Submitted");
  };

  render() {
    return (
      <div>
        <h1>Student Registration</h1>
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("code", "Acces code")}
          {this.renderInput("index", "Index")}
          {this.renderInput("fullname", "Fullname")}
          {this.renderButton("Register")}
        </form>
      </div>
    );
  }
}

export default StudentRegister;
