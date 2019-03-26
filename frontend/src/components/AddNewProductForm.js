import React from 'react';

const AddNewProductForm = ({
  addnew_name,
  addnew_description,
  addNewProduct,
  handleInputChange,
  user,
}) => {
  if (user === null) {
    return null
  }
  return (
    <div id='new'>
      <h1>
        Add new product
      </h1>
      <form onSubmit={ addNewProduct() }>
        <label htmlFor='addnew_name'>Name</label>
        <br />
        <input onChange={ handleInputChange() } type='text' id='addnew_name' name='addnew_name' value={ addnew_name } />
        <br />
        <label htmlFor='addnew_description'>Description</label>
        <br />
        <input onChange={ handleInputChange() } type='text' id='addnew_description' name='addnew_description' value={ addnew_description } />
        <br />
        <button type='submit'>Add new product</button>
      </form>
    </div>
  )
}

export default AddNewProductForm