package com.ecommercebackend.admin.service;


import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;

public interface ProductDetailReferencesService {
    int save(ModelMap param) throws ValidatorException;
    int delete(ModelMap param) throws ValidatorException;
}
