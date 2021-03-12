package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.EmergencyContactDao;
import com.ecommercebackend.admin.service.EmergencyContactService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyContactServiceImplement implements EmergencyContactService {

    @Autowired
    private EmergencyContactDao emergencyContactDao;

    @Override
    public MultiModelMap retrieveList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param,  "status");
        return emergencyContactDao.retrieveList(param);
    }

    @Override
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "name", "relationship", "phone");
        return emergencyContactDao.save(param);
    }

    @Override
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status");
        return emergencyContactDao.delete(param);
    }

    @Override
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "name", "relationship", "phone");
        return emergencyContactDao.update(param);
    }

    @Override
    public int sequence() {
        return 0;
    }
}
