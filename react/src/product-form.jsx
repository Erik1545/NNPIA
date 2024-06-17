import { useState, useEffect } from 'react';
import './product-form.css';

function ProductForm() {
  const [product, setProduct] = useState({
    productName: '',
    description: '',
    price: '',
    image: null
  });
function handleAddProduct() {
  const formData = new FormData();
  formData.append('productName', product.productName);
  formData.append('description', product.description);
  formData.append('price', product.price);
  formData.append('image', product.image);

  const requestOptions = {
    method: 'POST',
    body: formData,
  };

  fetch('http://localhost:8080/api/products/', requestOptions)
    .then(response => response.json())
    .then(json => {
      console.log('Response from POST request:', json);
    })
    .catch(error => {
      console.error('Error sending POST request:', error);
    });
}


  function handleChange(event) {
    const { name, value, files } = event.target;

    if (name === 'image') {
      setProduct(prevProduct => ({
        ...prevProduct,
        [name]: files[0]
      }));
    } else {
      setProduct(prevProduct => ({
        ...prevProduct,
        [name]: value
      }));
    }
  }

  function handleSubmit(event) {
    event.preventDefault();

    handleAddProduct();

    setProduct({
      productName: '',
      description: '',
      price: '',
      image: null
    });
  }

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="productName"
          placeholder="Název produktu"
          value={product.productName}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="description"
          placeholder="Popis produktu"
          value={product.description}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="price"
          placeholder="Cena produktu"
          value={product.price}
          onChange={handleChange}
          required
        />
        <input
          type="file"
          name="image"
          accept="image/*"
          onChange={handleChange}
          required
        />
        <button type="submit">Přidat nový produkt</button>
      </form>
    </div>
  );
}

export default ProductForm;
