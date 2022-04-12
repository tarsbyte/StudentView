import React from "react";
import Joi from "joi-browser";
import Form from "./form";

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
        <h1>Rejestracja studenta</h1>
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("code", "Kod dostępu")}
          {this.renderInput("index", "Indeks")}
          {this.renderInput("fullname", "Imię i nazwisko")}
          {this.renderButton("Register")}
        </form>
      </div>
    );
  }
}

export default StudentRegister;
