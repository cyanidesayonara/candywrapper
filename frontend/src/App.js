import React, { Component } from 'react'
import productService from './services/products.js'
import userService from './services/users.js'
import accountService from './services/accounts.js'
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
      register_username: '',
      register_password: '',
      register_passwordConfirm: '',
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
      this.setState({ 
        user: null,
        view: 'browse',
      })
    } catch (e) {
      this.setState({
        user: null,
        view: 'browse',
      })
    }
  }

  login = () => async (event) => {
    event.preventDefault()

    try {
      const user = await accountService.login({
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
    } catch (e) {
      this.setState({ 
        login_password: '',
      })
    }
  }

  register = () => async (event) => {
    event.preventDefault()

    try {
      const user = await accountService.register({
        username: this.state.register_username,
        password: this.state.register_password,
        passwordConfirm: this.state.register_passwordConfirm,
      })

      window.localStorage.setItem('user', JSON.stringify(user))
      //noteService.setToken(user.token)

      this.setState({
        user: user,
        view: 'browse',
        register_username: '',
        register_password: '',
        register_passwordConfirm: '',
      })
    } catch (e) {
      this.setState({ 
        register_password: '',
        register_passwordConfirm: '',
      })
    }
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

  handleInputChange = () => (event) => {
    const value = event.target.value
    const name = event.target.name
    this.setState({ [name]: value })
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
          this.state.user !== null &&
          <div>
            <p>Logged in as: { this.state.user.username }</p>
          </div>
        }
        {
          this.state.view === 'browse' &&
          <Products
            products={ this.state.products }
            addNewProduct= { this.addNewProduct }
            handleRemove={ this.handleRemove }
            user={ this.state.user }
          />
        }
        { this.state.view === 'about' &&
          <About />
        }
        { this.state.view === 'login' &&
          <Login
            login={ this.login }
            handleInputChange={ this.handleInputChange }
            username={ this.state.login_username }
            password={ this.state.login_password }
          />
        }
        { this.state.view === 'register' &&
          <Register
            register={ this.register }
            username={ this.state.register_username }
            password={ this.state.register_password }
            passwordConfirm={ this.state.register_passwordConfirm }
            handleInputChange={ this.handleInputChange }          
          />
        }
        { this.state.view === 'basket' &&
          <Basket />
        }
      </div>
    );
  }
}

export default App
