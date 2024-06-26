import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ConfirmOrder = () => {
  const navigate = useNavigate();
  const [orderSent, setOrderSent] = useState(false);

  useEffect(() => {
    const confirmOrder = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/api/order`, {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        setOrderSent(true);
      } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
      }
    };

    confirmOrder();
  }, []);

  return (
    <div>
      {orderSent ? (
        <div>
          <h2>Objednávka odeslána</h2>
        </div>
      ) : (
        <div>
          <h2>Odesílání objednávky...</h2>
        </div>
      )}
    </div>
  );
};

export default ConfirmOrder;
