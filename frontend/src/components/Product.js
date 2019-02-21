import React from 'react';

const Product = ({
    product,
    handleRemove,
  }) => {
  return (
    <div className='product clearfix' key={ product.id }>
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
      <button onClick={ handleRemove(product) }>
        Delete
      </button>
    </div>
  )
}

export default Product