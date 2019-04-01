import React, { Component } from 'react'
import productService from './services/products.js'
//import userService from './services/users.js'
import accountService from './services/accounts.js'
import roleService from './services/roles.js'
import basketProductService from './services/basketProducts.js'
import Header from './components/Header'
import Nav from './components/Nav'
import Footer from './components/Footer'
import UserInfo from './components/UserInfo'
import Splash from './components/Splash'
import Products from './components/Products'
import Login from './components/Login'
import Signup from './components/Signup'
import About from './components/About'
import BasketProducts from './components/BasketProducts.js'
import ReactGA from 'react-ga'

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      products: [],
      basketProducts: [],
      view: 'splash',
      user: null,
      addnew_name: '',
      addnew_description: '',
      login_username: '',
      login_password: '',
      signup_username: '',
      signup_password: '',
      signup_passwordConfirm: '',
      signup_roles: ['USER'],
      showFooter: true,
    }
  }

  componentDidMount() {
    document.title = 'Candy Wrapper'

    this.getUser()
    this.fetchProducts()
    this.fetchBasketProducts()
    this.initializeGA()
  }

  getUser = () => {
    // save user to local storage
    const loggedUserJSON = window.localStorage.getItem('user')
    if (loggedUserJSON) {
      const user = JSON.parse(loggedUserJSON)
      this.setState({ user: user })
      basketProductService.setToken(user.token)
      //if (user.roles.includes('ADMIN')) {
      //
      //}
    }
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

  hideFooter = () => {
    this.setState({ showFooter: false })
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
        view: 'splash',
      })
    } catch (e) {
      this.setState({
        user: null,
        view: 'splash',
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

  signup = () => async (event) => {
    event.preventDefault()
    const roles = await roleService.getAll(roleService.getAll)
    let selectedRoles = []
    for (let i = 0; i > roles; i++) {
      if (this.state.signup_roles.includes(roles[i].name)) {
        selectedRoles.push(roles[i])
      }
    }
    try {
      const user = await accountService.signup({
        username: this.state.signup_username,
        password: this.state.signup_password,
        passwordConfirm: this.state.signup_passwordConfirm,
        roles: selectedRoles,
      })

      window.localStorage.setItem('user', JSON.stringify(user))
      //noteService.setToken(user.token)

      this.setState({
        user: user,
        view: 'browse',
        signup_username: '',
        signup_password: '',
        signup_passwordConfirm: '',
      })
    } catch (e) {
      this.setState({ 
        signup_password: '',
        signup_passwordConfirm: '',
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
    let basketProducts = this.state.basketProducts
    let amount = event.target.getAttribute('data-amount')
    const parsedAmount = parseInt(amount)

    if (!isNaN(parsedAmount)) {
      if (0 < parsedAmount && 100 > parsedAmount) {

        if (basketProducts.length) {
          const oldBasketProduct = basketProducts.find(bp => bp.product.id === product.id)
          if (oldBasketProduct) {
            amount += +oldBasketProduct.amount
            basketProducts = basketProducts.filter(bp => bp.id !== oldBasketProduct.id)
          }
        }
        
        const basketProduct = {
          product: product,
          amount: amount
        }

        
        basketProductService
          .create(basketProduct)
          .then(createdBasketProduct => {
            this.setState({
              basketProducts: basketProducts.concat(createdBasketProduct)
            })
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
    let value = ''
    const name = event.target.name
    if (event.target.selectedOptions) {
      value = Array.from(event.target.selectedOptions, (item) => item.value)
    } else {
      value = event.target.value
    }
    this.setState({ [name]: value })
    console.log(event.target.value, event.target.name)
  }

  render() {
    return (
      <div id='container'>
        <Header
          changeView={ this.changeView }        
        />
        <Nav
          user={ this.state.user }
          changeView={ this.changeView }
          logout = { this.logout }
        />
        <UserInfo
          user = { this.state.user }
        />
        <div id="main">
          { this.state.view === 'splash' &&
            <Splash />
          }
          { this.state.view === 'browse' &&
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
          { this.state.view === 'signup' &&
            <Signup
              signup={ this.signup }
              username={ this.state.signup_username }
              password={ this.state.signup_password }
              passwordConfirm={ this.state.signup_passwordConfirm }
              roles={ this.state.signup_roles }
              handleInputChange={ this.handleInputChange }          
            />
          }
          { this.state.view === 'basket' &&
            <BasketProducts
              basketProducts={ this.state.basketProducts }
              removeBasketProduct={ this.removeBasketProduct }
            />
          }
        </div>
        { this.state.showFooter &&
          <Footer
            hideFooter={ this.hideFooter }
          />
        }
      </div>
    )
  }
}

export default App
