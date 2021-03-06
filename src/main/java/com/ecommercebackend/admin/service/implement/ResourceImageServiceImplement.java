package com.ecommercebackend.admin.service.implement;

import com.ecommercebackend.admin.dao.ResourceImageDao;
import com.ecommercebackend.admin.service.ResourceImageService;
import com.ecommercebackend.admin.util.ValidatorUtil;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceImageServiceImplement implements ResourceImageService {

    @Autowired
    private ResourceImageDao resourceImageDao;

    @Override
    public int save(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id", "originalName", "fileSize", "fileType", "fileExtension", "filePath");
        return resourceImageDao.save(param);
    }

    @Override
    public int delete(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id");
        return resourceImageDao.delete(param);
    }

    @Override
    public int update(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "id");
        return resourceImageDao.update(param);
    }

    @Override
    public String getResourcesImageById(ModelMap param) throws ValidatorException {
        ValidatorUtil.validate(param, "uuid");
        return resourceImageDao.getResourcesImageById(param);
    }
}
