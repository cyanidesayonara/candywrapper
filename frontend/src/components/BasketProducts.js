import React from 'react';
import BasketProduct from './BasketProduct'

const BasketProducts = ({
  basketProducts,
  removeBasketProduct
}) => {
  return (
    <div id='basket'>
      { 
        basketProducts &&
        basketProducts.map((basketProduct) =>
          <BasketProduct
            key={ basketProduct.id }
            basketProduct={ basketProduct }
            removeBasketProduct={ removeBasketProduct }
          />
        )
      }
    </div>
  )
}

export default BasketProducts