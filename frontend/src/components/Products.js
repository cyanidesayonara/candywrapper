import React from 'react';
import Product from './Product'
import AddNewProductForm from './AddNewProductForm'

const Products = ({
    products,
    addNewProduct,
    addnew_name,
    addnew_description,
    handleInputChange,
    removeProduct,
    addBasketProduct,
    user,
    saveProduct,
  }) => {
  return (
    <div id='products'>
      <AddNewProductForm
        addNewProduct={ addNewProduct }
        addnew_name={ addnew_name }
        addnew_description={ addnew_description }
        handleInputChange={ handleInputChange }
        user={ user }
      />
      { products.map((product) => 
        <Product
          key={ product.id }
          product={ product }
          removeProduct={ removeProduct }
          addBasketProduct={ addBasketProduct }
          saveProduct={ saveProduct }
          handleInputChange={ handleInputChange }
        />
      ) }
    </div>
  )
}

export default Products