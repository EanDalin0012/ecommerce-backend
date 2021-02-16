package com.ecommercebackend.core.dao;

import com.ecommercebackend.core.model.map.ModelMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuccessLoginDao {
    int save(ModelMap param);
}
