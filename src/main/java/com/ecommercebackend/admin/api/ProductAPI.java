package com.ecommercebackend.admin.api;


import com.ecommercebackend.admin.constant.StatusYN;
import com.ecommercebackend.admin.service.implement.ProductServiceImplement;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/product/v1")
public class ProductAPI {
    private static final Logger log = LoggerFactory.getLogger(ProductAPI.class);

    @Autowired
    private ProductServiceImplement productService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @GetMapping(value = "/list")
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<MultiModelMap>> list(@RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        return CompletableFuture.completedFuture(getProductList(lang));
    }

    @PostMapping(value = "/save")
    public ResponseData<ModelMap> save(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) {
        return execute("save", user_id, lang, param);
    }

    @PostMapping(value = "/update")
    public ResponseData<ModelMap> update(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) {
        return execute("update", user_id, lang, param);
    }

    @PostMapping(value = "/delete")
    public ResponseData<ModelMap> delete(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) {
        return delete(user_id, lang, param);
    }

    @PostMapping(value = "/switch_web")
    public ResponseData<ModelMap> updateShowOnWeb(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) {
        return swichOn("web", param, user_id, lang);
    }

    @PostMapping(value = "/switch_mobile")
    public ResponseData<ModelMap> updateShowOnMobile(@RequestParam("userId") int user_id, @RequestParam("lang") String lang, @RequestBody ModelMap param) {
        return swichOn("mobile", param, user_id, lang);
    }

    private ResponseData<ModelMap> execute(String function, int user_id, String lang, ModelMap param) {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("====== Start Product " + function + "===========");

            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap input = new ModelMap();
            ModelMap out = new ModelMap();
            out.setString(StatusYN.STATUS, StatusYN.N);

            input.setInt("user_id", user_id);
            input.setString("name", param.getString("name"));
            input.setString("description", param.getString("description"));
            input.setString("status", Status.Active.getValueStr());
            input.setInt("category_id", param.getInt("category_id"));
            input.setString("resource_img_id", param.getString("resource_img_id"));


            if (function == "save") {
                int id = productService.sequence();
                input.setInt("id", id);

                log.info("========== product values:" + objectMapper.writeValueAsString(input));

                int save = productService.save(input);
                if (save > 0) {
                    out.setString(StatusYN.STATUS, StatusYN.Y);
                }

            } else if (function == "update") {
                input.setInt("id", param.getInt("id"));

                log.info("========== product values:" + objectMapper.writeValueAsString(input));

                int update = productService.update(input);
                if (update > 0) {
                    out.setString(StatusYN.STATUS, StatusYN.Y);
                }
            }

            responseData.setBody(out);

            log.info("===== Product response : " + objectMapper.writeValueAsString(responseData));
            log.info("===== End product ====");

            return responseData;
        } catch (ValidatorException ex) {
            log.error("===== get error save api product:", ex);
            message.setMessage(MessageUtil.message("product_" + ex.getKey(), lang));
            responseData.setError(message);
            return responseData;
        } catch (Exception e) {
            log.error("get error api product save exception", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
            return responseData;
        }

    }

    private ResponseData<ModelMap> delete(int user_id, String lang, MultiModelMap param) {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        ErrorMessage message = new ErrorMessage();
        message.setCode(StatusYN.N);
        try {
            log.info("====== Start Delete Product ========");

            ObjectMapper objectMapper = new ObjectMapper();
            log.info("====== Values:" + objectMapper.writeValueAsString(param));

            ModelMap out = new ModelMap();
            out.setString(StatusYN.STATUS, StatusYN.N);
            if (param.size() > 0) {
                ModelMap input = new ModelMap();
                for (ModelMap data : param.toListData()) {
                    input.setInt("id", data.getInt("id"));
                    input.setInt("user_id", user_id);
                    input.setString("status", Status.Delete.getValueStr());
                    productService.delete(input);
                }

                transactionManager.commit(transactionStatus);
                out.setString(StatusYN.STATUS, StatusYN.Y);
                responseData.setBody(out);

                log.info("====== Response Data: " + objectMapper.writeValueAsString(responseData));
                log.info("====== Start Delete Product ========");

            }
        } catch (ValidatorException ex) {
            log.error("===== get error save api product:", ex);
            message.setMessage(MessageUtil.message("product_" + ex.getKey(), lang));
            responseData.setError(message);
            return responseData;
        } catch (Exception e) {
            log.error("========== get error exection", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
            return responseData;
        }
        return responseData;
    }

    private ResponseData<MultiModelMap> getProductList(String lang) {
        ResponseData<MultiModelMap> responseData = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("====== Start Product get list ====");

            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap input = new ModelMap();
            input.setString("status", Status.Delete.getValueStr());
            MultiModelMap responseBody = productService.retrieveList(input);
            responseData.setBody(responseBody);

            log.info("===== Product list value:" + objectMapper.writeValueAsString(responseData));
            log.info("=====End Product get list====");

        } catch (ValidatorException ex) {
            log.error("====== get error api product list:", ex);
            message.setMessage(MessageUtil.message("product_" + ex.getKey(), lang));
            responseData.setError(message);
            return responseData;
        } catch (Exception e) {
            log.error("get error exception api product:", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
            return responseData;
        }
        return responseData;
    }


    public ResponseData<ModelMap> swichOn(String note, ModelMap data, int user_id, String lang) {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("======= Start Swich ON =======");

            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap input = new ModelMap();
            ModelMap output = new ModelMap();
            output.setString("status", StatusYN.N);

            input.setInt("id", data.getInt("product_id"));
            input.setString("status", Status.Modify.getValueStr());
            input.setInt("user_id", user_id);


            log.info("========= Values :", objectMapper.writeValueAsString(input));

            if (note == "web") {
                input.setBoolean("web_show", data.getBoolean("value"));
                int u = productService.updateShowOnWeb(input);
                if (u > 0) {
                    output.setString(StatusYN.STATUS, StatusYN.Y);
                }
            } else if (note == "mobile") {
                input.setBoolean("mobile_show", data.getBoolean("value"));
                int u = productService.updateShowOnMobile(input);
                if (u > 0) {
                    output.setString(StatusYN.STATUS, StatusYN.Y);
                }
            }
            responseData.setBody(output);

            log.info("========== Response values:" + objectMapper.writeValueAsString(responseData));
            log.info("========= End Swich ON =========");

            return responseData;
        } catch (ValidatorException ex) {
            log.error("get error updateShowOnWeb api product", ex);
            message.setMessage(MessageUtil.message("product_" + ex.getKey(), lang));
            responseData.setError(message);
            return responseData;
        } catch (Exception e) {
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
            return responseData;
        }
    }
}
