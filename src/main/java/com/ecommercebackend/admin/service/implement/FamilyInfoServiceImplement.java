package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.FamilyInfoDao;
import com.ecommercebackend.admin.service.FamilyInfoService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamilyInfoServiceImplement implements FamilyInfoService {

    @Autowired
    private FamilyInfoDao familyInfoDao;
    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_INFO_READ')")
    public MultiModelMap retrieveList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param,  "status");
        return familyInfoDao.retrieveList(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_CREATE')")
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "name", "relationship");
        return familyInfoDao.save(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_UPDATE')")
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status");
        return familyInfoDao.delete(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_UPDATE')")
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "name", "relationship");
        return familyInfoDao.update(param);
    }

    @Override
    public int sequence() {
        return familyInfoDao.sequence();
    }
}
