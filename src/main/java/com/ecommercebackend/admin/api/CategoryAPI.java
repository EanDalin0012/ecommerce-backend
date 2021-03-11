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
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

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
       return getCategories(lang);
    }

    @PostMapping(value = "/save")
    public ResponseData<ModelMap> save(@RequestParam("userId") int userId, @RequestParam("lang") String lang, @RequestBody ModelMap param) {
        return execute("save", userId, lang, param);
    }

    @PostMapping(value = "/update")
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<ModelMap>> update(@RequestParam("userId") int userId, @RequestParam("lang") String lang, @RequestBody ModelMap param) throws Exception {
        return CompletableFuture.completedFuture(execute("update", userId, lang, param));
    }

    @PostMapping(value = "/delete")
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<ModelMap>> delete(@RequestParam("userId") int userId, @RequestParam("lang") String lang, @RequestBody ModelMap param) throws Exception {
        return CompletableFuture.completedFuture(updateStatusToDelete(userId, lang, param.getMultiModelMap("body")));
    }

    private ResponseData<MultiModelMap> getCategories(String lang) {
        ResponseData<MultiModelMap> response = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
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
            message.setMessage( MessageUtil.message("category_" + ev.getKey(), lang));
            response.setError(message);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("============ error Exception api category get list", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            response.setError(message);
            return response;
        }
    }

    private ResponseData<ModelMap> execute(String function, int user_id, String lang, ModelMap param) {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("===== Start save category =====");

            ObjectMapper mapper = new ObjectMapper();
            ModelMap body = param.getModelMap("body");
            ModelMap responseBody = new ModelMap();

            ModelMap input = new ModelMap();

            input.setInt("user_id", user_id);
            input.setString("name", body.getString("name"));
            input.setString("description", body.getString("description"));
            input.setString("status", Status.Active.getValueStr());

            log.info("===== value : " + mapper.writeValueAsString(input));

            if (function == "save") {
                int sequence = categoryService.sequence();
                input.setInt("id", sequence);
                int save = categoryService.save(input);
                if (save > 0) {
                    responseBody.setString("status", "Y");
                }
            } else if (function == "update") {
                input.setInt("id", param.getInt("id"));
                int update = categoryService.update(input);
                if (update > 0) {
                    responseBody.setString(StatusYN.STATUS, StatusYN.Y);
                }
            }
            responseData.setBody(responseBody);

            log.info("======== Response Data:" + mapper.writeValueAsString(responseData));
            log.info("======== End save category ====");
        }
        catch (Exception | ValidatorException e) {
            e.printStackTrace();
            log.error("========= error Exception api save category", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
            return responseData;
        }
        return responseData;
    }

    private ResponseData<ModelMap> updateStatusToDelete(int userId, String lang, MultiModelMap param) {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("============ Start delete api category delete =============");

            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap out = new ModelMap();
            out.setString(StatusYN.STATUS, StatusYN.N);

            log.info("======= delete values: " + objectMapper.writeValueAsString(param));

            if (param.size() > 0) {
                ModelMap input = new ModelMap();
                for (ModelMap data : param.toListData()) {
                    input.setInt("id", data.getInt("id"));
                    input.setInt("userId", userId);
                    input.setString(StatusYN.STATUS, Status.Delete.getValueStr());
                    categoryService.delete(input);
                }

                transactionManager.commit(transactionStatus);
                out.setString(StatusYN.STATUS, StatusYN.Y);
                responseData.setBody(out);
                log.info("============ End delete api category delete =============");
            }

        } catch (ValidatorException ev) {
            ev.printStackTrace();
            log.error("error Application Exception api save category", ev);
            message.setMessage(MessageUtil.message("category_" + ev.getKey(), lang));
            responseData.setError(message);
            return responseData;
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
            log.error("get error exception delete api category", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
            return responseData;
        }
        return responseData;
    }
}
