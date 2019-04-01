import React from 'react';

const Signup = ({
  signup,
  username,
  password,
  passwordConfirm,
  roles,
  handleInputChange,
}) => {
  return (
    <div id='signup'>
      <form onSubmit={ signup() }>
        <h1>Signup</h1>
        <label htmlFor='signup_username'>
          Username
          <br />
          <input
            type='text'
            id='signup_username'
            name='signup_username'
            value={ username }
            onChange={ handleInputChange() }
          />
        </label>
        <br />
        <label htmlFor='signup_password'>
          Password
          <br />
          <input
            type='password'
            id='signup_password'
            name = 'signup_password'
            value={ password }
            onChange={ handleInputChange() }          
          />
          </label>
        <br />
        <label htmlFor='signup_passwordConfirm'>
          Password (Again)
          <br />
          <input
            type='password'
            id='signup_passwordConfirm'
            name='signup_passwordConfirm'
            value={ passwordConfirm }
            onChange={ handleInputChange() }          
          />
        </label>
        <br />
        <label>
          Select your roles:
          <select
            multiple={ true }
            id='signup_roles'
            name='signup_roles'
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
        <button type='submit'>Signup</button>
      </form>
    </div>
  )
}

export default Signup