package com.ecommercebackend.admin.dao;

import com.ecommercebackend.core.model.map.ModelMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceImageDao {
    int save(ModelMap param);
    int delete(ModelMap param);
    int update(ModelMap param);
    String getResourcesImageById(ModelMap param);
}
