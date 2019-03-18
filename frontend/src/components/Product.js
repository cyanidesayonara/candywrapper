import React from 'react';

const Product = ({
    product,
    removeProduct,
    addBasketProduct,
    saveProduct,
  }) => {
  return (
    <div className='product clearfix'>
      <form onSubmit={ saveProduct(product) }>
        <a href={ '/products/' + product.id }>
          <img src='product.png' alt='Product' />
        </a>
        <h1>
          <a href={ '/products/' + product.id }>
            { product.name }
          </a>
        </h1>
        <p>id: { product.id }</p>
        <p>description: { product.description }</p>
        <button type='submit'>
          Save
        </button>
        <button type='button' onClick={ removeProduct(product) }>
          Delete
        </button>
        <button data-amount='1' data-basketid='xxx' onClick={ addBasketProduct(product) }>
          Add To Basket
        </button>
      </form>
    </div>
  )
}

export default Product