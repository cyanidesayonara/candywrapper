import React from 'react';

const AddNewProductForm = ({ addNewProduct }) => {
  return (
    <form onSubmit={ addNewProduct() }>
      <input type='text' name='name' />
      <input type='text' name='description' />
      <button type='submit'>Add new product</button>
    </form>
  )
}

export default AddNewProductForm