package com.ecommercebackend.admin.api;


import com.ecommercebackend.admin.service.implement.ResourceImageServiceImplement;
import com.ecommercebackend.admin.util.Base64ImageUtil;
import com.ecommercebackend.admin.util.MessageUtil;
import com.ecommercebackend.core.component.Translator;
import com.ecommercebackend.core.constant.ErrorCode;
import com.ecommercebackend.core.constant.Status;
import com.ecommercebackend.core.dto.ErrorMessage;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.template.ResponseData;
import com.ecommercebackend.core.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/base64/image/v1")
public class Base64ImageAPI {

    private static final Logger log = LoggerFactory.getLogger(Base64ImageAPI.class);

    @Autowired
    private ResourceImageServiceImplement resourceImageService;

    @PostMapping(value = "/write")
    public ResponseData<ModelMap> index(@RequestBody ModelMap param, @RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        ModelMap output = new ModelMap();
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("================Start write image=============");

            log.info("param:", param);
            ModelMap input = new ModelMap();

            output.setString("status", "N");

            String path = "/uploads/products";
            String base64 = param.getString("base64");
            String id = param.getString("id");
            String fileExtension = param.getString("fileExtension");

            log.info("path:" + path + ",id" + id + ",name:" + id + ",extension:" + fileExtension);
            log.info("base64:" + base64);

            String basePath = Base64ImageUtil.decodeToImage(path, base64, id, fileExtension);

            if (!basePath.equals("")) {
                log.info("data:" + input);
                input.setString("id", id);
                input.setString("fileName", id);
                input.setInt("fileSize", param.getInt("fileSize"));
                input.setString("fileType", param.getString("fileType"));
                input.setString("fileExtension", param.getString("fileExtension"));
                input.setString("originalName", param.getString("fileName"));
                input.setString("filePath", basePath);
                input.setString("status", Status.Active.getValueStr());
                input.setInt("userId", user_id);
                int save = resourceImageService.save(input);
                if (save > 0) {
                    output.setString("status", "Y");
                }
            } else {
                log.info("can not write image");
                message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
                responseData.setError(message);
            }

        } catch (ValidatorException ex) {
            log.info("Wrong validation", ex);
            message.setMessage(MessageUtil.message(ex.getKey(), lang));
            responseData.setError(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error Exception api category get list", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
        }
        responseData.setBody(output);
        return responseData;
    }

    @PostMapping(value = "/delete")
    public ResponseData<ModelMap> delete() {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        try {

        } catch (Exception e) {
            throw e;
        }
        return responseData;
    }

    @GetMapping("/read/{resource_id}")
    public String resourcesImage(@PathVariable("resource_id") String resource_id) {
        String base64 = "dd";
        log.info("========Start api base64 img====");
        try {
            log.info("====resource_id ======" + resource_id);
            ModelMap input = new ModelMap();
            input.setString("uuid", resource_id);
            String path = resourceImageService.getResourcesImageById(input);
            log.info("===path:" + path);
            String fullpath = SystemUtil.projectPath() + path;
            base64 = Base64ImageUtil.encoder(fullpath);

        } catch (ValidatorException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw e;
        }

        return base64;
    }

    private ErrorMessage message(String key, String lang) {
        ErrorMessage data = new ErrorMessage();
        String message = Translator.translate(lang, "image_" + key);
        if (ErrorCode.EXCEPTION_ERR == key) {
            message = Translator.translate(lang, key);
        } else if ("status".equals(key)) {
            message = Translator.translate(lang, "status");
        } else if ("user_id".equals(key)) {
            message = Translator.translate(lang, "user_id");
        }
        data.setCode(key);
        data.setMessage(message);
        return data;
    }
}
