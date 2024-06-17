import { useState, useEffect } from 'react';
import reactLogo from './assets/react.svg';
import './App.css';
import Product from './product';
import ProductForm from './product-form';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import NavBar from './navbar';

function App() {
  const [cart, setCart] = useState({});
  const [products, setProducts] = useState([]);


  useEffect(() => {
      fetch('http://localhost:8080/api/products/')
          .then(response => response.json())
          .then(json => setProducts(json))
  }, []);

  function handleBuy(product) {
    setCart(prevCart => {
      const newCart = { ...prevCart };
      if (newCart[product.id]) {
        newCart[product.id].quantity += 1;
      }
      else {
        newCart[product.id] = { ...product, quantity: 1 };
      }
      return newCart;
    });
  }

  function removeFromBuy(product) {
    setCart(prevCart => {
      const newCart = { ...prevCart };
      if (newCart[product.id].quantity > 1) {
        newCart[product.id].quantity--;
      } else {
        delete newCart[product.id];
      }
      return newCart;
    });
  }

  function handleAddProduct(newProduct) {
    setProducts([...products, newProduct]);
  }

  return (
    <Router>
      <>
        <NavBar />

        <Routes>
          <Route path="/" element={
            <>
              <h1>Produkty v eshopu</h1>
              <h3>{"Pocet veci v kosiku : " + Object.keys(cart).reduce((total, key) => total + cart[key].quantity, 0)}</h3>
              <div style={{display: "flex", flexWrap: "wrap"}}>
                {products.map(item => <Product key={item.id} product={item} onBuy={handleBuy} />)}
              </div>
            </>
          } />
          <Route path="/productform" element={<ProductForm onAddProduct={handleAddProduct} />} />
          <Route path="/cart" element={
            <>
              <p>Kosik</p>
              {Object.values(cart).map(item => (
                <div key={item.id}>
                  {item.productName} x {item.quantity}
                  <button onClick={() => removeFromBuy(item)}>-</button>
                </div>
              ))}
            </>
          } />
        </Routes>
      </>
    </Router>
  );
}

export default App;
