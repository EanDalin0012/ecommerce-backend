package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.EducationInfoDao;
import com.ecommercebackend.admin.service.EducationInfoService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EducationInfoServiceImplement implements EducationInfoService {
    @Autowired
    private EducationInfoDao educationInfoDao;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('EDUCATION_INFO_READ')")
    public MultiModelMap retrieveList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "status");
        return educationInfoDao.retrieveList(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('EDUCATION_INFO_CREATE')")
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "institution", "subject","startingDate","completeDate","degree");
        return educationInfoDao.save(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('EDUCATION_INFO_DELETE')")
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status");
        return educationInfoDao.delete(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('EDUCATION_INFO_UPDATE')")
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "userId", "status", "institution", "subject","startingDate","completeDate","degree");
        return educationInfoDao.update(param);
    }

    @Override
    public int sequence() {
        return educationInfoDao.sequence();
    }
}
