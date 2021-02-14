package com.ecommercebackend.core.config.interceptor;


import com.ecommercebackend.core.constant.KeyCode;
import com.ecommercebackend.core.encryption.EASEncrypter;
import com.ecommercebackend.core.model.map.ModelMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
    private static final Logger log = LoggerFactory.getLogger(CustomRequestBodyAdviceAdapter.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    public CustomRequestBodyAdviceAdapter() {
        super();
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return super.handleEmptyBody(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return customizeAfterBodyRead(body);
    }

    private ModelMap customizeAfterBodyRead(Object body) {
        log.info("=== Start Request Body Advice Adapter ===");

        ModelMap mMap = new ModelMap();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            String clientData = objectMapper.writeValueAsString(body);
            log.info("Request Body Advice Adapter Data Client = " + clientData);

            JSONObject jsonObject = objectMapper.readValue(clientData, JSONObject.class);
            log.info("Request Body Advice Adapter Data JSONObject Data Convert = " + objectMapper.writeValueAsString(jsonObject));

            String encodeKey = EASEncrypter.encodeKey(KeyCode.keyCode);
            String rawData = (String) jsonObject.get("body");
            log.info("Request Body Advice Adapter Client Encrypt Data = " + rawData);

            String decrypt = EASEncrypter.decrypt(rawData, encodeKey);
            log.info("Request Body Advice Adapter Client Decrypt Data = " + decrypt);

             mMap = objectMapper.readValue(decrypt, ModelMap.class);
//            mMap.setMMap("body", dataBody);
            log.info("mMap data return to controller= " + objectMapper.writeValueAsString(mMap));

            log.info("=== End Request Body Advice Adapter ===");
            return  mMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mMap;
    }

}

