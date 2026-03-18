package com.epw.products.service;

import com.epw.products.dto.ProductSupplierResponse;

public interface ProductSupplierService {

    ProductSupplierResponse assignSupplier(Long productId, Long supplierId);

    ProductSupplierResponse removeSupplier(Long productId, Long supplierId);
}
