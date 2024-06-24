import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import './cart.css';
import { AuthContext } from './authcontext';

const Cart = ({ cart, addProduct, removeProduct }) => {
  const navigate = useNavigate();
  const { isAuthenticated } = useContext(AuthContext);

  const calculateTotalPrice = () => {
    let total = 0;
    Object.values(cart).forEach(item => {
      total += item.price * item.quantity;
    });
    return total.toFixed(2);
  };

  const handleConfirmOrder = () => {
    if (isAuthenticated) {
      navigate('/confirm-order');
    } else {
      navigate('/login');
    }
  };

  return (
    <div className="cart">
      <h2>Košík</h2>
      {Object.keys(cart).length === 0 ? (
        <p>Košík je prázdný.</p>
      ) : (
        <>
          <table>
            <thead>
              <tr>
                <th>Název produktu</th>
                <th>Popis</th>
                <th>Cena za kus</th>
                <th>Množství</th>
                <th></th>
                <th>Cena</th>
              </tr>
            </thead>
            <tbody>
              {Object.values(cart).map(item => (
                <tr key={item.id}>
                  <td>{item.productName}</td>
                  <td>{item.description}</td>
                  <td>{item.price.toFixed(2)}</td>
                  <td>{item.quantity}</td>
                  <td>
                    <button onClick={() => addProduct(item)}>+</button>
                    <button onClick={() => removeProduct(item)}>-</button>
                  </td>
                  <td>{(item.price * item.quantity).toFixed(2)}</td>
                </tr>
              ))}
              <tr>
                <td colSpan="4">Celková cena:</td>
                <td></td>
                <td>{calculateTotalPrice()}</td>
              </tr>
            </tbody>
          </table>
          <button onClick={handleConfirmOrder}>Potvrdit objednávku</button>
        </>
      )}
    </div>
  );
};

export default Cart;
