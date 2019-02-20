import React, { Component } from 'react'
import logo from './logo.svg'
import './App.css'
import Header from './components/Header'
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
    const tableStyle = {
      margin: '20px auto',
      border: '1px solid black',
    }
    return (
      <div className="App">
        <Header />
        <table style={ tableStyle }>
          <thead>
            <tr>
              <td>id</td>
              <td>name</td>
              <td>description</td>
            </tr>
          </thead>
          <tbody>
            { this.state.products.map((product) =>
              <tr key={ product.id }>
                <td>{ product.id }</td>
                <td>{ product.name }</td>
                <td>{ product.description }</td>
              </tr>
            ) }
          </tbody>
        </table>
      </div>
    );
  }
}

export default App
