package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.ProductDao;
import com.ecommercebackend.admin.service.ProductService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public MultiModelMap retrieveList(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "status");
        return productDao.retrieveList(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PRODUCT_CREATE')")
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "name", "status", "userId", "categoryId");
        return productDao.save(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PRODUCT_DELETE')")
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "status", "userId");
        return productDao.delete(param);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "name", "status", "userId");
        return productDao.update(param);
    }

    @Override
    public int updateShowOnWeb(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "webShow", "status", "userId");
        return productDao.updateShowOnWeb(param);
    }

    @Override
    public int updateShowOnMobile(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "mobileShow", "status", "userId");
        return productDao.updateShowOnMobile(param);
    }

    @Override
    public int count() {
        return productDao.count();
    }

    @Override
    public int sequence() {
        return productDao.sequence();
    }
}
