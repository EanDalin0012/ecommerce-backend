package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.EmergencyContactDao;
import com.ecommercebackend.admin.service.EmergencyContactService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmergencyContactServiceImplement implements EmergencyContactService {

    @Autowired
    private EmergencyContactDao emergencyContactDao;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_INFO_READ')")
    public MultiModelMap retrieveList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param,  "status");
        return emergencyContactDao.retrieveList(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_CREATE')")
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "name", "relationship", "phone");
        return emergencyContactDao.save(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_DELETE')")
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status");
        return emergencyContactDao.delete(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_UPDATE')")
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "name", "relationship", "phone");
        return emergencyContactDao.update(param);
    }

    @Override
    public int sequence() {
        return emergencyContactDao.sequence();
    }
}
