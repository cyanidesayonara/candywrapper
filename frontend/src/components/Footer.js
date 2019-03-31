import React from 'react';

const Footer = ({ hideFooter }) => {
  const buttonStyle = {
    "marginLeft": "10px",
    "cursor": "pointer"
  }
  return (
    <footer id='footer'>
      <ul>
        <li>
          <a href='/products/'>
            Go to crappy site
          </a>
          <button style={ buttonStyle } onClick={ hideFooter }>
            X
          </button>
        </li>
      </ul>
    </footer>
  )
}

export default Footer