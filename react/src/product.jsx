import React from 'react';
import './product.css';
import {useState} from 'react'

function Product({product, onBuy }) {

  const [isInCart, setIsInCart] = useState(false)
  return (
     <div className="product-container" style={{margin: "10px", padding: "25px"}}>
          <h2 className="product-name">{product.productName}</h2>
          <img src={`http://localhost:8080/images/${product.image}`} alt={product.productName} className="product-image" />
          <p className="product-price">{product.price} Kč</p>
          <div>{isInCart && "V kosiku"}</div>

          <button className="product-button" onClick={() => {onBuy(product); setIsInCart(true)}}>Buy</button>
     </div>
  );
}

export default Product;