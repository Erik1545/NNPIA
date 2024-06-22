import React from 'react';
import './SortOptions.css';

const SortOptions = ({ sortBy, setSortBy }) => {
  const handleSortChange = (event) => {
    setSortBy(event.target.value);
  };

  return (
    <div className="sort-options">
      <label htmlFor="sort-select">Řadit podle :</label>
      <select id="sort-select" value={sortBy} onChange={handleSortChange}>
        <option value="id">ID produktu</option>
        <option value="productName">Nazev produktu</option>
        <option value="price">Cena produktu</option>
      </select>
    </div>
  );
};

export default SortOptions;
