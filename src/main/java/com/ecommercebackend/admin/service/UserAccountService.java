package com.ecommercebackend.admin.service;

import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;

public interface UserAccountService {
    int updateUserAccount(ModelMap param) throws ValidatorException;
    ModelMap retrieveUserAccountByID(ModelMap param) throws ValidatorException;
    MultiModelMap getList(ModelMap param);
    int count();
}
