import React from 'react';
import './pagination.css';

const Pagination = ({ prevPage, nextPage, page, handlePageSizeChange, hasMoreProducts }) => {
  const pageSizeOptions = [2, 4, 6, 8];

  return (
    <div className="pagination-container">
      <div className="pagination-controls">
        <button onClick={prevPage} disabled={page === 0}>
          &lt; Previous
        </button>
        <p className="pagination-page-info">Page {page + 1}</p>
        <button onClick={nextPage} disabled={!hasMoreProducts}>
          Next &gt;
        </button>
      </div>
      <div className="pagination-page-size">
        {pageSizeOptions.map(size => (
          <button key={size} onClick={() => handlePageSizeChange(size)}>
            {size} per page
          </button>
        ))}
      </div>
    </div>
  );
};

export default Pagination;
