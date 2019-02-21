import React, { Component } from 'react'
import productService from './services/products.js'
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
    productService
      .getAll()
      .then(products => this.setState({ products: products }))
  }

  initializeGA = () => {
    ReactGA.initialize('UA-120584024-5')
    ReactGA.pageview('/')
  }

  addNewProduct = () => (event) => {
    event.preventDefault()
    const name = event.target.name.value
    const description = event.target.description.value
    if (!name || !description) {
      console.log(event)
      return null
    }
    const product = {
      name: name,
      description: description,
    }
    productService
      .create(product)
      .then(createdProduct => {
        this.setState({
          products: this.state.products.concat(createdProduct)
        })
      })
      .catch(error => console.log(error))
  }

  handleRemove = (product) => () => {
    if (!product.author) {
      if (window.confirm('Are you sure you want to remove this product?')) {
        productService
          .remove(product)
          .then(response => {
            const products = this.state.products.filter(p => p.id !== product.id)
            this.setState({ products: products })
          })
          .catch(error => {
            const products = this.state.products.filter(p => p.id !== product.id)
            this.setState({ products: products })
          })
      }
    }
  }  

  render() {
    return (
      <div id='main'>
        <Nav />
        <Products
          products={ this.state.products }
          addNewProduct= { this.addNewProduct }
          handleRemove={ this.handleRemove }
        />
      </div>
    );
  }
}

export default App
