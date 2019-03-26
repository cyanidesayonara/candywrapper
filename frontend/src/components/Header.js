import React from 'react';

const Header = ({ changeView }) => {
  return (
    <header id='header'>
      <h1 onClick={ changeView('splash') }>
        Candy Wrapper
      </h1>
    </header>
  )
}

export default Header