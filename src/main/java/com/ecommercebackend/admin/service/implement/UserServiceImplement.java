package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.UserDao;
import com.ecommercebackend.admin.service.UserService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public MultiModelMap getList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "status");
        return userDao.getList(param);
    }

    @Override
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "user_name", "passwd", "is_first_login", "enable", "account_lock", "credential_expired", "account_expired", "status", "user_id");
        return userDao.save(param);
    }

    @Override
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id");
        return userDao.delete(param);
    }


    @Override
    public ModelMap loadUserByUserName(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "user_name");
        return userDao.loadUserByUserName(param);
    }

    @Override
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "firstName", "lastName", "dateBirth", "email", "password", "contact", "gender", "addressID", "userID");
        return userDao.update(param);
    }

    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public int sequence() {
        return userDao.sequence();
    }
}
