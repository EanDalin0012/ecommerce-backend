package com.ecommercebackend.admin.service;


import com.ecommercebackend.core.model.map.ModelMap;

public interface ResourceFileInfoService {
    long getLastId();
    int fileUpload(ModelMap param);
    ModelMap getResourceById(ModelMap param);
    int deleteById(ModelMap param);
}
