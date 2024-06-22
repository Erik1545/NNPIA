import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './detail.css';

function Detail({addProduct}) {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [isInCart, setIsInCart] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`http://localhost:8080/api/products/${id}`)
      .then(response => response.json())
      .then(json => setProduct(json));
  }, [id]);



  if (!product) {
    return <div>Načítání...</div>;
  }

  const handleEdit = () => {
    navigate(`/productform/${id}`);
  };

  return (
    <div className="detail-container">
      <h1>{product.productName || "Název není dostupný"}</h1>
      <img src={`http://localhost:8080/images/${product.image}`} alt={product.productName} />
      <p>Cena: {product.price || "Cena není dostupná"} Kč</p>
      <p>Popis: {product.description || "Popis není dostupný"}</p>
      {isInCart && <p>Produkt je v košíku.</p>}
      <button
        className="product-button"
        onClick={() => {
          addProduct(product);
          setIsInCart(true);
        }}
      >
        Koupit
      </button>
      <button
        className="product-button"
        onClick={handleEdit}
      >
        Upravit
      </button>
    </div>
  );
}

export default Detail;
