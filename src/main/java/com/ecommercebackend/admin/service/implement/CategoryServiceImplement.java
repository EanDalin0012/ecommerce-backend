package com.ecommercebackend.admin.service.implement;


import com.ecommercebackend.admin.dao.CategoryDao;
import com.ecommercebackend.admin.service.CategoryService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImplement implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImplement.class);
    @Autowired
    private CategoryDao categoryDao;


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('CATEGORY_READ')")
    public MultiModelMap retrieveList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "status");
        return categoryDao.retrieveList(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('CATEGORY_CREATE')")
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "name", "status", "userId");
        return categoryDao.save(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "status", "userId");
        return categoryDao.delete(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "status", "name", "userId");
        return categoryDao.update(param);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int sequence() {
        return categoryDao.sequence();
    }
}
