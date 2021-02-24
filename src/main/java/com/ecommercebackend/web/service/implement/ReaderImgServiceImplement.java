package com.ecommercebackend.web.service.implement;

import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.web.dao.ReaderImgDao;
import com.ecommercebackend.web.service.ReaderImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderImgServiceImplement implements ReaderImgService {

    @Autowired
    private ReaderImgDao readerImgDao;

    @Override
    public ModelMap getResourcesImageById(ModelMap param) {
        return readerImgDao.getResourcesImageById(param);
    }
}
