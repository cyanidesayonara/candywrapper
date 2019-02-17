import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Header from './components/Header'

class App extends Component {
  state = {}

  componentDidMount() {
    this.candy()
  }

  candy = () => {
    fetch('api/candy')
      .then(response => response.text())
      .then(message => this.setState({ message: message }))
  }

  render() {
    return (
      <div className="App">
        <Header />
        <h2>{ this.state.message }</h2>
      </div>
    );
  }
}

export default App;
