import React from 'react';

const Login = ({
  login,
  username,
  password,
  handleInputChange,
}) => {
  return (
    <div id='login'>
      <form onSubmit={ login() }>
        <h1>Login</h1>
        <label htmlFor='login_username'>
          Username
          <br />
          <input
            type='text'
            id='login_username'
            name='login_username'
            value={ username }
            onChange={ handleInputChange() }          
          />
        </label>
        <br />
        <label htmlFor='login_password'>
          Password
          <br />
          <input
            type='password'
            id='login_password'
            name='login_password'
            value={ password }
            onChange={ handleInputChange() }          
          />
        </label>
        <br />
        <button type='submit'>Login</button>
      </form>
    </div>
  )
}

export default Login