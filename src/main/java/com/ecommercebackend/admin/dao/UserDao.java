package com.ecommercebackend.admin.dao;


import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    MultiModelMap getList(ModelMap param);
    int save(ModelMap param);
    int updatePassword(ModelMap param);
    int delete(ModelMap param);
    ModelMap loadUserByUserName(ModelMap param);
    int update(ModelMap param);
    int count();
    int sequence();
}
