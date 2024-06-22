import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './product-form.css';

function ProductForm() {
  const { id } = useParams();
  const isNewProduct = !id;
  const [product, setProduct] = useState({
    productName: '',
    description: '',
    price: '',
    image: null
  });

  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/api/products/${id}`)
        .then(response => response.json())
        .then(data => {
          setProduct({
            productName: data.productName,
            description: data.description || '',
            price: data.price.toString(),
            image: data.image || null
          });
        })
        .catch(error => {
          console.error('Error fetching product:', error);
        });
    }
  }, [id]);

  function handleSaveProduct() {
    const formData = new FormData();
    formData.append('productName', product.productName);
    formData.append('description', product.description);
    formData.append('price', product.price);

    const requestOptions = {
      method: isNewProduct ? 'POST' : 'PUT',
      body: formData,
    };

    let url = 'http://localhost:8080/api/products';
    if (!isNewProduct) {
      url += `/${id}`;
    }

    fetch(url, requestOptions)
      .then(response => response.json())
      .then(json => {
        console.log('Response from ' + (isNewProduct ? 'POST' : 'PUT') + ' request:', json);
      })
      .catch(error => {
        console.error('Error sending ' + (isNewProduct ? 'POST' : 'PUT') + ' request:', error);
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

    handleSaveProduct();

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
        />
        <button type="submit">{isNewProduct ? 'Přidat nový produkt' : 'Uložit změny'}</button>
      </form>
    </div>
  );
}

export default ProductForm;
