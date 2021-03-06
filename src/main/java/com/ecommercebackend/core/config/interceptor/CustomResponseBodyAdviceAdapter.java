package com.ecommercebackend.core.config.interceptor;


import com.ecommercebackend.core.constant.KeyCode;
import com.ecommercebackend.core.encryption.EASEncrypter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {
    private static final Logger log = LoggerFactory.getLogger(CustomResponseBodyAdviceAdapter.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        String url = request.getURI().toString();
        String arr[] = url.split("/");
        String mUrl = arr[4];
        if(mUrl.equals("web")) {
            return body;
        } else if(servletResponse.getStatus() == 200) {
            return  customizeBeforeBodyWrite(body);
        } else {
            return body;
        }
    }


    private String customizeBeforeBodyWrite(Object body) {
        log.info("Start Response Body Advice");
        JSONObject jsonObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String restData = objectMapper.writeValueAsString(body);
            log.info("Rest Data To Client Side (JSON) = " + restData);
            JSONObject jsonNode = objectMapper.readValue(restData, JSONObject.class);
            String key = KeyCode.keyCode;
            String encodeKey = EASEncrypter.encodeKey(key);
            String rawData = objectMapper.writeValueAsString(jsonNode);
            String bodyData = EASEncrypter.encrypt(rawData, encodeKey);
            jsonObject.put("body", bodyData);
            log.info("Rest Data To Client Side (String[encryption]) = " + objectMapper.writeValueAsString(jsonObject));
            log.info("End Response Body Advice");
            return objectMapper.writeValueAsString(jsonObject);
        }catch (Exception e) {
            log.error("CustomResponseBodyAdviceAdapter get error:", e);
            e.printStackTrace();
        }
        return null;
    }
}
