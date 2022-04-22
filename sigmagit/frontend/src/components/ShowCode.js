
import React from "react";
import {Alert, Card, Col, Container, Form, Row} from "react-bootstrap";
import AceEditor from 'react-ace';
import "ace-builds/src-noconflict/theme-terminal";
import 'brace/mode/java';
import 'brace/theme/github';

class ShowCode extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            code: props.code,
            descr: props.descr,
            lastName: props.lName,
            firstName: props.fName,
            nick: props.nick,
            lang: props.lang,
        };
        this.onChange = this.onChange.bind(this);
    }

    onChange(newValue) {
        console.log('change', newValue);
    }

    renderTextField(description,text,theme,mode,height,width)
    {
        return(<Card className="mb-3" style={{color: "#000"}} fluid>

            <Card.Body>
                <Card.Title style={{fontSize: 30,textAlign: "center"}}>{description}</Card.Title>
            </Card.Body>

            <div className="row code-area">
                <AceEditor
                    mode={mode}
                    theme={theme}
                    height={height}
                    width={width}
                    focus={true}
                    enableSnippets={true}
                    enableBasicAutocompletion={true}
                    enableLiveAutocompletion={true}
                    wrapEnabled={true}
                    onChange={this.onChange}
                    value={text}
                    name="UNIQUE_ID_OF_DIV"
                    editorProps={{
                        $blockScrolling: true
                    }}
                />
            </div>

        </Card>);
    }

    render() {
        return (
            <div className="App">

                <div className="jumbotron text-center" >
                    <h1>Widok pracy studenta</h1>
                </div>

                <header className={"App-header"}>

                    <Container fluid>

                        <Row><p/><p/></Row>

                        <Form>
                            <Row>
                                <Col md>
                                    <Form.Group controlId="formNickname">
                                        <Form.Control type="nick" disabled={true} style={{fontSize: 22, textAlign: "center"}} placeholder= {"Nick: " + this.state.nick}></Form.Control>
                                    </Form.Group>
                                </Col>
                                <Col md>
                                    <Form.Group controlId="formName">
                                        <Form.Control type="name" disabled={true} style={{fontSize: 22, textAlign: "center"}} placeholder= {"Imię i nazwisko: " + this.state.firstName + " "+ this.state.lastName}></Form.Control>
                                    </Form.Group>
                                </Col>
                            </Row>
                        </Form>

                        <Row><p/><p/></Row>

                        <Card className="mb-3" style={{color: "#000"}} fluid>
                        <Card.Body>
                            <Card.Title style={{fontSize: 30, textAlign: "center"}}>{"Opis zadania"}</Card.Title>
                            <Row><p/><p/></Row>
                            <Card.Text style={{fontSize: 20}}>{this.state.descr}</Card.Text>
                        </Card.Body>
                        </Card>


                        <div className="boardCode">
                            {this.renderTextField("Kod studenta", this.state.code, "terminal", this.state.lang, "600px", "1285px")}
                        </div>

                        <Alert></Alert>

                    </Container>
                </header>
            </div>
        );

    }
}
export default ShowCode;
