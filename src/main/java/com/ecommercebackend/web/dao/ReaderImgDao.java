package com.ecommercebackend.web.dao;

import com.ecommercebackend.core.model.map.ModelMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReaderImgDao {
    ModelMap getResourcesImageById(ModelMap param);
}
