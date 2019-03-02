import React, { Component } from 'react'
import productService from './services/products.js'
import userService from './services/users.js'
import Nav from './components/Nav'
import Products from './components/Products'
import Login from './components/Login'
import Register from './components/Register'
import About from './components/About'
import Basket from './components/Basket'
import ReactGA from 'react-ga'

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      products: [],
      view: 'browse',
      user: null,
      login_username: '',
      login_password: '',
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

  changeView = (view) => () => {
    this.setState({ view: view })
  }

  logout = () => (event) => {
    event.preventDefault()

    try {
      //noteService.removeToken(this.state.user.token)
      window.localStorage.removeItem('user')
      this.setState({ user: null })
    } catch (exception) {
      this.setState({ user: null })
    }
  }

  login = () => (event) => {
    event.preventDefault()

    try {
      const user = userService.login({
        username: this.state.login_username,
        password: this.state.login_password
      })

      window.localStorage.setItem('user', JSON.stringify(user))
      //noteService.setToken(user.token)

      this.setState({
        user: user,
        view: 'browse',
        login_username: '',
        login_password: '',
      })
    } catch (exception) {
      console.log("sdfds")
      this.setState({ login_password: '', user: 'guy', view: 'browse', })
    }
  }

  register = () => (event) => {
    event.preventDefault()
  }

  addNewProduct = () => (event) => {
    event.preventDefault()
    const name = event.target.name.value
    const description = event.target.description.value
    if (name && description) {
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
        <Nav
          user={ this.state.user }
          changeView={ this.changeView }
          logout={ this.logout }
        />
        {
          this.state.view === 'browse' &&
          <Products
            products={ this.state.products }
            addNewProduct= { this.addNewProduct }
            handleRemove={ this.handleRemove }
          />
        }
        { this.state.view === 'about' &&
          <About />
        }
        { this.state.view === 'login' &&
          <Login login={ this.login } />
        }
        { this.state.view === 'register' &&
          <Register register={ this.register } />
        }
        { this.state.view === 'basket' &&
          <Basket />
        }
      </div>
    );
  }
}

export default App
