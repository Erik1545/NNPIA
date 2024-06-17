import { useState } from 'react';
import './product-form.css';

function ProductForm({ onAddProduct }) {
  const [newProductName, setNewProductName] = useState('');
  const [newProductPrice, setNewProductPrice] = useState('');
  const [newProductImage, setNewProductImage] = useState('');

  function handleSubmit(event) {
    event.preventDefault();
    const newProduct = {
      id: Math.random(),
      name: newProductName,
      price: Number(newProductPrice),
      image: newProductImage
    };
    onAddProduct(newProduct);
    setNewProductName('');
    setNewProductPrice('');
    setNewProductImage('');
  }

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nazev produktu"
          value={newProductName}
          onChange={(e) => setNewProductName(e.target.value)}
          required
        />
        <input
          type="number"
          placeholder="Cena produktu"
          value={newProductPrice}
          onChange={(e) => setNewProductPrice(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Nazev obrazku"
          value={newProductImage}
          onChange={(e) => setNewProductImage(e.target.value)}
          required
        />
        <button type="submit">Přidat nový produkt</button>
      </form>
    </div>
  );
}

export default ProductForm;