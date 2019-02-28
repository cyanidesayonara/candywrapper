import React from 'react';

const AddNewProductForm = ({ addNewProduct }) => {
  return (
    <div id='new'>
      <form onSubmit={ addNewProduct() }>
        <label htmlFor='new-name'>Name</label>
        <br />
        <input type='text' id='new-name' name='name' />
        <br />
        <label htmlFor='new-description'>Description</label>
        <br />
        <input type='text' id='new-description' name='description' />
        <br />
        <button type='submit'>Add new product</button>
      </form>
    </div>
  )
}

export default AddNewProductForm