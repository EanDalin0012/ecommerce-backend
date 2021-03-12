package com.ecommercebackend.admin.dao;

import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EducationInfoDao {
    MultiModelMap retrieveList(ModelMap param);
    int save(ModelMap param);
    int delete(ModelMap param);
    int update(ModelMap param);
    int sequence();
}
