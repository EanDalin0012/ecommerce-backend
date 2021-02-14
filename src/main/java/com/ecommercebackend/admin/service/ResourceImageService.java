package com.ecommercebackend.admin.service;


import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;

public interface ResourceImageService {
    int save(ModelMap param) throws ValidatorException;
    int delete(ModelMap param) throws ValidatorException;
    int update(ModelMap param) throws ValidatorException;
    String getResourcesImageById(ModelMap param) throws ValidatorException;
}
