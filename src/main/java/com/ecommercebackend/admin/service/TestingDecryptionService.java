package com.ecommercebackend.admin.service;


import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.stereotype.Repository;

@Repository
public interface TestingDecryptionService {
    int save(ModelMap param);
    MultiModelMap retrieve();
}
