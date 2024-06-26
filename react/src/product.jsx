import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './product.css';

function Product({product}) {

  return (
    <div className="product-container" style={{ margin: "10px", padding: "25px" }}>
      <h2 className="product-name">{product.productName}</h2>
      <div className="product-image-container">
              <img
                src={`http://localhost:8080/images/${product.image}`}
                alt={product.productName}
                className="product-image"
              />
            </div>
      <p className="product-price">{product.price} Kč</p>
      <Link to={`/detail/${product.id}`}>
        <button className="product-button">Detail</button>
      </Link>
    </div>
  );
}

export default Product;
