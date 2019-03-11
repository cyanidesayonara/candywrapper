import React from 'react';
import Product from './Product'
import AddNewProductForm from './AddNewProductForm'

const Products = ({
    products,
    addNewProduct,
    handleRemove,
    user,
  }) => {
  return (
    <div id='products'>
      {
        user !== null &&
        <AddNewProductForm addNewProduct={ addNewProduct } />
      }
      { products.map((product) => 
        <Product
          key={ product.id }
          product={ product }
          handleRemove={ handleRemove }
        />
      ) }
    </div>
  )
}

export default Products