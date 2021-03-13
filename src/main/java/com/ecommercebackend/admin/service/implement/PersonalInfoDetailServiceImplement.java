package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.PersonalInfoDetailDao;
import com.ecommercebackend.admin.service.PersonalInfoDetailService;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalInfoDetailServiceImplement implements PersonalInfoDetailService {
    @Autowired
    private PersonalInfoDetailDao personalInfoDetailDao;

    @Override
    public MultiModelMap retrieveListByPersonInfoId(ModelMap param) {

        return personalInfoDetailDao.retrieveListByPersonInfoId(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_CREATE')")
    public int save(ModelMap param) {
        return personalInfoDetailDao.save(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_DELETE')")
    public int delete(ModelMap param) {
        return personalInfoDetailDao.delete(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_INFO_UPDATE')")
    public int update(ModelMap param) {
        return  personalInfoDetailDao.update(param);

    }
}
