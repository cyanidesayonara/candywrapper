import React from 'react';
import Product from './Product'

const Products = ({ products }) => {
  return (
    <div id='products'>
      { products.map((product) => 
        <Product product={ product } />
      ) }
    </div>
  )
}

export default Products