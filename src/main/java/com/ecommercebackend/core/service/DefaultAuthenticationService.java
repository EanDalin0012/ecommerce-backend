package com.ecommercebackend.core.service;


import com.ecommercebackend.core.model.map.ModelMap;

public interface DefaultAuthenticationService {
    ModelMap getUserByName(ModelMap param) throws Exception;
}
