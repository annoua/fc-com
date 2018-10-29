import React, {Component} from 'react';
import {backendBaseURL} from '../jsonProperty';

class HelloWorld extends Component {

    constructor() {
        super();
        this.state = {
            title: "",
            fronttext:"",
            backtext:"",

        }
    }

    componentWillMount() {

        fetch(backendBaseURL + '/flashcard/100000')
            .then(results => {
                return results.json();
            })
            .then(data => {
                this.setState({title: data.title})
                this.setState({fronttext: data.frontText})
                this.setState({backtext: data.backText})
            })
           // .catch(e => console.log(e.toString()))

    }

    render() {
        return (
            <div>
                <p>Ich bin die HelloWorld-Seite</p>
                <p>Titel: {this.state.title}</p>
                <p>Vorderseite: {this.state.fronttext}</p>
                <p>Rückseite: {this.state.backtext}</p>
            </div>
        );
    }

}

export default HelloWorld;