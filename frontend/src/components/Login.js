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
        <label htmlFor='login-username'>Username</label>
        <br />
        <input
          type='text'
          id='login-username'
          name='login-username'
          value={ username }
          onChange={ handleInputChange() }          
        />
        <br />
        <label htmlFor='login-password'>Password</label>
        <br />
        <input
          type='password'
          id='login-password'
          name='login-password'
          value={ password }
          onChange={ handleInputChange() }          
        />
        <br />
        <button type='submit'>Login</button>
      </form>
    </div>
  )
}

export default Login