package com.ecommercebackend.admin.service;


import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;

public interface ProductService {
    MultiModelMap retrieveList(ModelMap param) throws ValidatorException;
    int save(ModelMap param) throws ValidatorException;
    int delete(ModelMap param) throws ValidatorException;
    int update(ModelMap param) throws ValidatorException;
    int updateShowOnWeb(ModelMap param) throws ValidatorException;
    int updateShowOnMobile(ModelMap param) throws ValidatorException;
    int count();
    int sequence();
}
