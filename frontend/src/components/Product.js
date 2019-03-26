import React from 'react';

const Product = ({
  product,
  removeProduct,
  addBasketProduct,
  saveProduct,
  handleInputChange,
}) => {
  if (product.amount === undefined) {
    product.amount = '0'
  }
  return (
    <div className='product clearfix'>
      <form onSubmit={ saveProduct(product) }>
        <a href={ '/products/' + product.id }>
          <img src='product.png' alt='Product' />
        </a>
        <h3>
          <a href={ '/products/' + product.id }>
            { product.name }
          </a>
        </h3>
        <p>description: { product.description }</p>
        <button type='submit'>
          Save
        </button>
        <button type='button' onClick={ removeProduct(product) }>
          Delete
        </button>
        <br />
        <input type="number"  name={ product.id + "_amount" } onChange={ handleInputChange() } value={ product.amount } />
        <button data-amount='1' onClick={ addBasketProduct(product) }>
          Add To Basket
        </button>
      </form>
    </div>
  )
}

export default Product