import React, { Component } from 'react'
import productService from './services/products.js'
//import userService from './services/users.js'
import accountService from './services/accounts.js'
import basketProductService from './services/basketProducts.js'
import Nav from './components/Nav'
import Products from './components/Products'
import Login from './components/Login'
import Register from './components/Register'
import About from './components/About'
import BasketProducts from './components/BasketProducts.js'
import ReactGA from 'react-ga'

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      products: [],
      view: 'browse',
      user: null,
      basket: [],
      addnew_name: '',
      addnew_description: '',
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
    this.fetchBasketProducts()
    this.initializeGA()
  }

  fetchProducts = () => {
    productService
      .getAll()
      .then(products => this.setState({ products: products }))
  }

  fetchBasketProducts = () => {
    basketProductService
      .getAll()
      .then(basketProducts => this.setState({ basketProducts: basketProducts }))
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
    const name = event.target.addnew_name.value
    const description = event.target.addnew_description.value
    if (name && description) {
      const product = {
        name: name,
        description: description,
      }
      productService
        .create(product)
        .then(createdProduct => {
          this.setState({
            products: this.state.products.concat(createdProduct),
            addnew_name: '',
            addnew_description: '',
          })
        })
        .catch(error => console.log(error))
    }
  }

  saveProduct = (product) => (event) => {
    event.preventDefault()
  }

  addBasketProduct = (product) => (event) => {
    const amount = event.target.getAttribute('data-amount')
    const parsedAmount = parseInt(amount)
    const basketid = event.target.getAttribute('data-basketid')

    if (!isNaN(parsedAmount)) {
      if (0 < parsedAmount && 100 > parsedAmount) {
        const basketProduct = {
          basket: basketid,
          product: product,
          amount: amount
        }
        console.log(basketProduct)
        basketProductService
          .create(basketProduct)
          .then(createdBasketProduct => {
            if (this.state.basket.length) {
              this.setState({
                basket: this.state.basket.concat(createdBasketProduct)
              })
            }
          })
          .catch(error => console.log(error))
      }
    }
  }

  removeBasketProduct = (basketProduct) => () => {
    if (window.confirm('Are you sure you want to remove this product?')) {
      basketProductService
        .remove(basketProduct)
        .then(response => {
          const basketProducts = this.state.basketProducts.filter(p => p.id !== basketProduct.id)
          this.setState({ basketProducts: basketProducts })
        })
        .catch(error => {
          const basketProducts = this.state.basketProducts.filter(p => p.id !== basketProduct.id)
          this.setState({ basketProducts: basketProducts })
        })
    }
  }  

  removeProduct = (product) => () => {
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
            handleInputChange={ this.handleInputChange }
            addnew_name={ this.state.addnew_name }
            addnew_description={ this.state.addnew_description }
            removeProduct={ this.removeProduct }
            addBasketProduct={ this.addBasketProduct }
            user={ this.state.user }
            saveProduct={ this.saveProduct }
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
        <BasketProducts
          basketProducts={ this.basketProducts }
          removeBasketProduct={ this.removeBasketProduct }
        />
      </div>
    );
  }
}

export default App
