package com.ecommercebackend.admin.service;

import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.ui.ModelMap;

import java.io.IOException;

public interface CategoryService {
    MultiModelMap retrieveList(ModelMap param) throws ValidatorException;
    int save(ModelMap param) throws ValidatorException, IOException;
    int delete(ModelMap param) throws ValidatorException;
    int update(ModelMap param) throws ValidatorException;
    int count();
    int sequence();
}
