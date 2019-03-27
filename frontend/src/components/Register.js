import React from 'react';

const Register = ({
  register,
  username,
  password,
  passwordConfirm,
  roles,
  handleInputChange,
}) => {
  return (
    <div id='register'>
      <form onSubmit={ register() }>
        <h1>Register</h1>
        <label htmlFor='register_username'>Username</label>
        <br />
        <input
          type='text'
          id='register_username'
          name='register_username'
          value={ username }
          onChange={ handleInputChange() }
        />
        <br />
        <label htmlFor='register_password'>Password</label>
        <br />
        <input
          type='password'
          id='register_password'
          name = 'register_password'
          value={ password }
          onChange={ handleInputChange() }          
        />
        <br />
        <label htmlFor='register_passwordConfirm'>Password (Again)</label>
        <br />
        <input
          type='password'
          id='register_passwordConfirm'
          name='register_passwordConfirm'
          value={ passwordConfirm }
          onChange={ handleInputChange() }          
        />
        <br />
        <label>
          Select your roles:
          <select
            multiple={ true }
            id='register_roles'
            name='register_roles'
            value={ roles }
            onChange={ handleInputChange() }
          >
            <option value='USER'>
              User
            </option>
            <option value='ADMIN'>
              Admin
            </option>          
          </select>
        </label>
        <button type='submit'>Register</button>
      </form>
    </div>
  )
}

export default Register