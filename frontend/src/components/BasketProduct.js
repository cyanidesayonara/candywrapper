import React from 'react';

const BasketProduct = ({
    basketProduct,
    removeBasketProduct,
  }) => {
  return (
    <div className='product clearfix'>
      <a href={ '/products/' + basketProduct.product.id }>
        <img src='product.png' alt='Product' />
      </a>
      <h1>
        <a href={ '/products/' + basketProduct.product.id }>
          { basketProduct.product.name }
        </a>
      </h1>
      <p>id: { basketProduct.product.id }</p>
      <p>description: { basketProduct.product.description }</p>
      <button onClick={ removeBasketProduct(basketProduct) }>
        Delete
      </button>
    </div>
  )
}

export default BasketProduct