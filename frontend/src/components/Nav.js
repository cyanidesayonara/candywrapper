import React from 'react';

const Nav = ({ changeView }) => {
  return (
    <nav id='nav'>
      <h1>
        Candywrapper
      </h1>
      <ul>
        <li onClick={ changeView('browse') }>
          Browse
        </li>
        <li onClick={ changeView('about') }>
          About
        </li>
        <li onClick={ changeView('login') }>
          Login
        </li>
        <li onClick={ changeView('register') }>
          Register
        </li>
      </ul>
    </nav>
  )
}

export default Nav