package com.ecommercebackend.admin.service;

import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;

public interface ProductDescriptionService {
    MultiModelMap retrieveList(ModelMap param);
    int save(ModelMap param);
    int delete(ModelMap param);
    int update(ModelMap param);
    ModelMap retrieveProductDescriptionByProductID(ModelMap param) throws ValidatorException;
}
