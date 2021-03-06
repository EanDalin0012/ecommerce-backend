package com.ecommercebackend.admin.service;

import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;

public interface PersonalInfoDetailService {
    MultiModelMap retrieveListByPersonInfoId(ModelMap param);
    int save(ModelMap param) throws ValidatorException;
    int delete(ModelMap param);
    int update(ModelMap param);
}
