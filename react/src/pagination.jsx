import React from 'react';
import './pagination.css';

const Pagination = ({ prevPage, nextPage, page, handlePageSizeChange, hasMoreProducts }) => {
  const pageSizeOptions = [2, 4, 6, 8];

  return (
    <div className="pagination-container">
      <div className="pagination-controls">
        <button onClick={prevPage} disabled={page === 0}>
          &lt; Předchozí
        </button>
        <p className="pagination-page-info">Strana {page + 1}</p>
        <button onClick={nextPage} disabled={!hasMoreProducts}>
          Další &gt;
        </button>
      </div>
      <div className="pagination-page-size">
        {pageSizeOptions.map(size => (
          <button key={size} onClick={() => handlePageSizeChange(size)}>
            {size}
          </button>
        ))}
        <span>&nbsp;&nbsp;&nbsp;produktů na stránku</span>
      </div>
    </div>
  );
};

export default Pagination;
