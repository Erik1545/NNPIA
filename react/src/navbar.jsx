// navbar.js
import React, { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './navbar.css';
import { AuthContext } from './authcontext';

const NavBar = () => {
  const { isAuthenticated, setIsAuthenticated } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token');
    setIsAuthenticated(false);
    navigate('/');
  };

  return (
    <nav className="navbar">
      <ul className="navbar-list">
        <li className="navbar-item">
          <Link to="/" className="navbar-link">Home</Link>
        </li>
        <li className="navbar-item">
          <Link to="/productform" className="navbar-link">ProductForm</Link>
        </li>
        <li className="navbar-item">
          <Link to="/cart" className="navbar-link">Cart</Link>
        </li>
        <li className="navbar-item navbar-item-right">
          {isAuthenticated ? (
            <Link to="/" className="navbar-link" onClick={handleLogout}>Odhlásit se</Link>
          ) : (
            <Link to="/login" className="navbar-link">Přihlásit se</Link>
          )}
        </li>
      </ul>
    </nav>
  );
};

export default NavBar;
