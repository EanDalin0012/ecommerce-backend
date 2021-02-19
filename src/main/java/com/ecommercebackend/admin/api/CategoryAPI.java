package com.ecommercebackend.admin.api;

import com.ecommercebackend.admin.constant.StatusYN;
import com.ecommercebackend.admin.service.implement.CategoryServiceImplement;
import com.ecommercebackend.admin.util.MessageUtil;
import com.ecommercebackend.core.constant.ErrorCode;
import com.ecommercebackend.core.constant.Status;
import com.ecommercebackend.core.dto.ErrorMessage;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import com.ecommercebackend.core.model.template.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/category/v1")
public class CategoryAPI {

    private static final Logger log = LoggerFactory.getLogger(CategoryAPI.class);

    @Autowired
    private CategoryServiceImplement categoryService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @GetMapping(value = "/list")
    public ResponseData<MultiModelMap> list(@RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        ResponseData<MultiModelMap> response = new ResponseData<>();
        try {
            log.info("===== Start retrieve list of category=====");
            ModelMap input = new ModelMap();
            input.setString("status", Status.Delete.getValueStr());
            MultiModelMap out = categoryService.retrieveList(input);
            response.setBody(out);
            ObjectMapper mapper = new ObjectMapper();
            log.info("===== Result : " + mapper.writeValueAsString(response));
            log.info("===== End get list of category=====");
            return response;
        } catch (ValidatorException ev) {
            log.error("========= error ValueException api category get list", ev);
            ev.printStackTrace();
            ErrorMessage message = MessageUtil.message("category_" + ev.getKey(), lang);
            response.setError(message);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("============ error Exception api category get list", e);
            ErrorMessage message = MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang);
            response.setError(message);
            return response;
        }
    }

    @PostMapping(value = "/save")
    public ResponseData<ModelMap> save(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) {
        ResponseData<ModelMap> responseData = new ResponseData<>();

        try {
            log.info("===== Start save category =====");

            ObjectMapper mapper = new ObjectMapper();
            ModelMap body = param.getModelMap("body");
            ModelMap responseBody = new ModelMap();
            int sequence = categoryService.sequence();
            ModelMap input = new ModelMap();
            input.setInt("id", sequence);
            input.setInt("user_id", user_id);
            input.setString("name", body.getString("name"));
            input.setString("description", body.getString("description"));
            input.setString("status", Status.Active.getValueStr());

            log.info("===== value : " + mapper.writeValueAsString(input));

            int save = categoryService.save(input);
            if (save > 0) {
                responseBody.setString("status", "Y");
            }
            responseData.setBody(responseBody);

            log.info("======== Response Data:" + mapper.writeValueAsString(responseData));
            log.info("======== End save category ====");
        }
        catch (Exception | ValidatorException e) {
            e.printStackTrace();
            log.error("========= error Exception api save category", e);
            ErrorMessage message = MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang);
            responseData.setError(message);
            return responseData;
        }
        return responseData;
    }

    @PostMapping(value = "/update")
    public ResponseData<ModelMap> update(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) throws Exception {
        ResponseData<ModelMap> responseData = new ResponseData<>();

        try {
            log.info("========= Start Update category update data ======");

            ObjectMapper objectMappter = new ObjectMapper();
            ModelMap out = new ModelMap();
            ModelMap body = param.getModelMap("body");
            ModelMap input = new ModelMap();
            input.setInt("user_id", user_id);
            input.setInt("id", body.getInt("id"));
            input.setString("name", body.getString("name"));
            input.setString("description", body.getString("description"));
            input.setString("status", Status.Modify.getValueStr());

            log.info("====== Value : " + objectMappter.writeValueAsString(input));

            int update = categoryService.update(input);
            if (update > 0) {
                out.setString(StatusYN.STATUS, StatusYN.Y);
            }

            responseData.setBody(out);

            log.info("====== Response data: " + responseData);
            log.info("====== End Update category update data ======");

        } catch (ValidatorException ev) {
            ev.printStackTrace();
            log.error("======= error:", ev);
            ErrorMessage message = MessageUtil.message("category_" + ev.getKey(), lang);
            responseData.setError(message);
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("======== error: ", e);
            ErrorMessage message = MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang);
            responseData.setError(message);
            return responseData;
        }
        return responseData;
    }

    @PostMapping(value = "/delete")
    public ResponseData<ModelMap> delete(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) throws Exception {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            log.info("============ Start delete api category delete =============");

            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap out = new ModelMap();
            out.setString(StatusYN.STATUS, StatusYN.N);
            MultiModelMap body = param.getMultiModelMap("body");

            log.info("======= delete values: " + objectMapper.writeValueAsString(body));

            if (body.size() > 0) {
                ModelMap input = new ModelMap();
                for (ModelMap data : body.toListData()) {
                    input.setInt("id", data.getInt("id"));
                    input.setInt("user_id", user_id);
                    input.setString("status", Status.Delete.getValueStr());
                    categoryService.delete(input);
                }

                transactionManager.commit(transactionStatus);
                out.setString(StatusYN.STATUS, StatusYN.Y);
                responseData.setBody(out);
                log.info("============ Response Date: " + objectMapper.writeValueAsString(responseData));
                log.info("============ End delete api category delete =============");

            }

        } catch (ValidatorException ev) {
            ev.printStackTrace();
            log.error("error Application Exception api save category", ev);
            ErrorMessage message = MessageUtil.message("category_" + ev.getKey(), lang);
            responseData.setError(message);
            return responseData;
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
            log.error("get error exception delete api category", e);
            ErrorMessage message = MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang);
            responseData.setError(message);
            return responseData;
        }
        return responseData;
    }

}
