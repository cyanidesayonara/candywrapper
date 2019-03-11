import React from 'react';

const Nav = ({ user, changeView, logout }) => {
  return (
    <nav id='nav'>
      <h1 onClick={ changeView('browse') }>
        Candy Wrapper
      </h1>
      <ul>
        <li onClick={ changeView('browse') }>
          Browse
        </li>
        <li onClick={ changeView('about') }>
          About
        </li>
        {
          user === null &&
          <li onClick={ changeView('login') }>
            Login
          </li>
        }
        {
          user === null &&
          <li onClick={ changeView('register') }>
            Register
          </li>
        }
        {
          user !== null &&
          <li onClick={ changeView('basket') }>
            Basket
          </li>
        }
        {
          user !== null &&
          <li onClick={ logout() }>
            Logout
          </li>
        }
      </ul>
    </nav>
  )
}

export default Nav