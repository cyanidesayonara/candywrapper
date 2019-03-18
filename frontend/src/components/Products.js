import React from 'react';
import Product from './Product'
import AddNewProductForm from './AddNewProductForm'

const Products = ({
    products,
    addNewProduct,
    removeProduct,
    addBasketProduct,
    user,
    saveProduct,
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
          removeProduct={ removeProduct }
          addBasketProduct={ addBasketProduct }
          saveProduct={ saveProduct }
        />
      ) }
    </div>
  )
}

export default Products