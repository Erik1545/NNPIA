import { useState, useEffect } from 'react';
import './App.css';
import Product from './product';
import ProductForm from './product-form';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavBar from './navbar';
import Detail from './detail';
import Cart from './cart';
import Pagination from './pagination';
import Sort from './sort';

function App() {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(2);
  const [cart, setCart] = useState([]);
  const [hasMoreProducts, setHasMoreProducts] = useState(true);
  const [sortBy, setSortBy] = useState("id");

  useEffect(() => {
      fetch(`http://localhost:8080/api/products?pageNumber=${page}&pageSize=${pageSize}&sortBy=${sortBy}`)
          .then(response => response.json())
          .then(json => {
              setProducts(json);
              setHasMoreProducts(json.length === pageSize);
          });
      fetchCart();
  }, [page, pageSize, sortBy]);

  const fetchCart = () => {
      fetch('http://localhost:8080/api/cart')
          .then(response => {
              if (!response.ok) {
                  throw new Error('Network response was not ok');
              }
              return response.json();
          })
          .then(data => {
              const formattedCart = data.reduce((acc, item) => {
                  acc[item.product.id] = {
                      id: item.product.id,
                      productName: item.product.productName,
                      description: item.product.description,
                      price: item.product.price,
                      quantity: item.quantity
                  };
                  return acc;
              }, {});
              setCart(formattedCart);
          })
          .catch(error => console.error('There was a problem with the fetch operation:', error));
  };

  const nextPage = () => setPage(prevPage => prevPage + 1);
  const prevPage = () => setPage(prevPage => (prevPage > 0 ? prevPage - 1 : 0));

  const handlePageSizeChange = (newSize) => {
      setPageSize(newSize);
      setPage(0);
  };

  const addProduct = (product) => {
      fetch(`http://localhost:8080/api/cart/add/${product.id}`, { method: 'POST' })
          .then(response => {
              if (!response.ok) {
                  throw new Error('Network response was not ok');
              }
              return response.json();
          })
          .then(() => {
              fetchCart();
          })
          .catch(error => console.error('There was a problem with the fetch operation:', error));
  };

  const removeProduct = (product) => {
      fetch(`http://localhost:8080/api/cart/remove/${product.id}`, { method: 'POST' })
          .then(response => {
              if (!response.ok) {
                  throw new Error('Network response was not ok');
              }
              return response.json();
          })
          .then(() => {
              fetchCart();
          })
          .catch(error => console.error('There was a problem with the fetch operation:', error));
  };

  return (
    <Router>
      <>
        <NavBar />
        <Routes>
          <Route path="/" element={
            <>
              <h1>Produkty v eshopu</h1>
              <Sort sortBy={sortBy} setSortBy={setSortBy} />
              <div style={{ display: "flex", flexWrap: "wrap" }}>
                {products.map(item => <Product key={item.id} product={item} />)}
              </div>
              <Pagination
                prevPage={prevPage}
                nextPage={nextPage}
                page={page}
                handlePageSizeChange={handlePageSizeChange}
                hasMoreProducts={hasMoreProducts}
              />
            </>
          } />
          <Route path="/productform" element={<ProductForm />} />
          <Route path="/productform/:id" element={<ProductForm />} />
          <Route path="/cart" element={<Cart cart={cart} addProduct={addProduct} removeProduct={removeProduct} />} />
          <Route path="/detail/:id" element={<Detail addProduct={addProduct} />} />
        </Routes>
      </>
    </Router>
  );
}

export default App;
