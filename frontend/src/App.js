import React, { Component } from 'react'
import logo from './logo.svg'
import './App.css'
import Nav from './components/Nav'
import Products from './components/Products'
import ReactGA from 'react-ga'

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      products: [],
    }
  }

  componentDidMount() {
    document.title = 'Candy Wrapper'
    this.fetchProducts()
    this.initializeGA()
  }

  fetchProducts = () => {
    fetch('api/products')
      .then(response => response.json())
      .then(products => this.setState({ products: products }))
  }

  initializeGA = () => {
    ReactGA.initialize('UA-120584024-5')
    ReactGA.pageview('/')
  }

  render() {
    return (
      <div id='main'>
        <Nav />
        <Products products={ this.state.products } />
        <Products products={ this.state.products } />
        <Products products={ this.state.products } />
      </div>
    );
  }
}

export default App
