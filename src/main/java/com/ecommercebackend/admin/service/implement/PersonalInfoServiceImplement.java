package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.PersonalInfoDao;
import com.ecommercebackend.admin.service.PersonalInfoService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalInfoServiceImplement implements PersonalInfoService {
    @Autowired
    private PersonalInfoDao personalInfoDao;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('PERSONAL_INFO_READ')")
    public MultiModelMap retrieveList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "status");
        return personalInfoDao.retrieveList(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PERSONAL_INFO_CREATE')")
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "firstName", "lastName","phone","birthday","gender","nationalID","nationality","maritalStatus");
        return personalInfoDao.save(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PERSONAL_INFO_DELETE')")
    public int delete(ModelMap param) {
        return personalInfoDao.delete(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PERSONAL_INFO_UPDATE')")
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "firstName", "lastName","phone","birthday","gender","nationalID","nationality","maritalStatus");
        return personalInfoDao.update(param);
    }

    @Override
    public int sequence() {
        return personalInfoDao.sequence();
    }
}
