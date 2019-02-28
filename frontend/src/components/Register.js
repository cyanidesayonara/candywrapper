import React from 'react';

const Register = ({ register }) => {
  return (
    <div id='register'>
      <form onSubmit={ register() }>
        <h1>Register</h1>
        <label htmlFor='register-username'>Username</label>
        <br />
        <input type='text' id='register-username' name='username' />
        <br />
        <label htmlFor='register-password'>Password</label>
        <br />
        <input type='password' id='register-password' name='password' />
        <br />
        <button type='submit'>Register</button>
      </form>
    </div>
  )
}

export default Register