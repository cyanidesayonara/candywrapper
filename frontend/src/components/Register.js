import React from 'react';

const Register = ({
  register,
  username,
  password,
  passwordConfirm,
  handleInputChange,
}) => {
  return (
    <div id='register'>
      <form onSubmit={ register() }>
        <h1>Register</h1>
        <label htmlFor='register-username'>Username</label>
        <br />
        <input
          type='text'
          id='register-username'
          name='register-username'
          value={ username }
          onChange={ handleInputChange() }
        />
        <br />
        <label htmlFor='register-password'>Password</label>
        <br />
        <input
          type='password'
          id='register-password'
          name = 'register-password'
          value={ password }
          onChange={ handleInputChange() }          
        />
        <br />
        <label htmlFor='register-passwordConfirm'>Password (Again)</label>
        <br />
        <input
          type='password'
          id='register-passwordConfirm'
          name='register-passwordConfirm'
          value={ passwordConfirm }
          onChange={ handleInputChange() }          
        />
        <br />        
        <button type='submit'>Register</button>
      </form>
    </div>
  )
}

export default Register