package com.ecommercebackend.admin.api;

import com.ecommercebackend.admin.constant.StatusYN;
import com.ecommercebackend.admin.service.implement.UserServiceImplement;
import com.ecommercebackend.admin.util.MessageUtil;
import com.ecommercebackend.core.constant.ErrorCode;
import com.ecommercebackend.core.constant.Status;
import com.ecommercebackend.core.dto.ErrorMessage;
import com.ecommercebackend.core.exception.ValidatorException;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import com.ecommercebackend.core.model.template.ResponseData;
import com.ecommercebackend.core.event.UserAuthenticateEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/user/v1")
public class UserAPI {
    private static final Logger log = LoggerFactory.getLogger(UserAPI.class);

    @Inject
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserServiceImplement userService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TokenStore tokenStore;


    @GetMapping(value = "/get/list")
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<ModelMap>> getUserList(@RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        return  CompletableFuture.completedFuture(this.userList(lang));
    }

    @PostMapping(value = "/save")
    public ResponseData<ModelMap> save(@RequestBody ModelMap param, @RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        return execute(param, "save", lang, user_id);
    }

    @GetMapping(value = "/oauth/revoke-token")
    @Async("asyncExecutor")
    public CompletableFuture<ResponseData<ModelMap>> oauthRevokeToken(HttpServletRequest request) {
        return CompletableFuture.completedFuture(this.revokeToken(request));
    }

    @GetMapping(value = "/load_user")
    public ResponseData<ModelMap> getUserByUserName(@RequestParam("userName") String userName, @RequestParam("lang") String lang, @RequestParam("deviceInfo") String deviceInfo, @RequestParam("networkIp") String networkIp) {
        ResponseData<ModelMap> out = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap input = new ModelMap();
            input.setString("user_name", userName);
            ModelMap outPut = userService.loadUserByUserName(input);

            if (outPut != null) {
                ModelMap device = objectMapper.convertValue(deviceInfo, ModelMap.class);
                ModelMap userAuthenticate = new ModelMap();
                userAuthenticate.set("networkIP", networkIp);
                userAuthenticate.setModelMap("device", device);
                log.info("======== Start load user info========");
                eventPublisher.publishEvent(new UserAuthenticateEvent(userAuthenticate));
            }

            out.setBody(outPut);

            log.info("======== Values : " + objectMapper.writeValueAsString(out));
            log.info("======== End load user info========");

            return out;
        } catch (ValidatorException ex) {
            log.error("======== error:", ex);
            message.setMessage(MessageUtil.message("user_" + ex.getKey(), lang));
            out.setError(message);
            return out;
        } catch (Exception e) {
            log.error("======== error execption:", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            out.setError(message);
            return out;
        }

    }

    @PostMapping(value = "/load/user")
    @Async("asyncExecutor")
    public  CompletableFuture<ModelMap> loadUser(@RequestBody ModelMap body, @RequestParam ("lang") String lang) throws ValidatorException {
        return CompletableFuture.completedFuture(this.loadUserByUserName(body, lang));
    }

    private ResponseData<ModelMap> userList(String lang) {
        ResponseData<ModelMap> responseData = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("======== Start retrieve list of user ============");
            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap input = new ModelMap();
            ModelMap body = new ModelMap();
            input.setString("status", Status.Delete.getValueStr());
            MultiModelMap userList = userService.getList(input);
            int count = userService.count();
            body.setMultiModelMap("items", userList);
            body.setInt("totalRecords", count);
            responseData.setBody(body);

            log.info("======== Response Values:" + objectMapper.writeValueAsString(responseData));
            log.info("======== End retrieve list of user ============");

            return responseData;
        } catch (ValidatorException ex) {
            log.error("=========== get error:", ex);
            message.setMessage(MessageUtil.message("user_" + ex.getKey(), lang));
            responseData.setError(message);
            return responseData;

        } catch (Exception e) {
            log.error("========== error execption", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            responseData.setError(message);
            return responseData;
        }
    }

    private ResponseData<ModelMap> revokeToken (HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        ErrorMessage message = new ErrorMessage();
        ModelMap output = new ModelMap();
        try {
            log.info("========== Start revoke toke===========");

            ObjectMapper objectMapper = new ObjectMapper();
            output.setString(StatusYN.STATUS, StatusYN.N);
            String authHeader = request.getHeader("Authorization");

            log.info("=========== token values: " + authHeader);

            if (authHeader != null) {
                String tokenValue = authHeader.replace("Bearer", "").trim();
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
                tokenStore.removeAccessToken(accessToken);
                output.setString(StatusYN.STATUS, StatusYN.Y);
            }
            responseData.setBody(output);

            log.info("========= Response values:" + objectMapper.writeValueAsString(responseData));
            log.info("========== End revoke toke===========");

            return responseData;
        } catch (Exception e) {
            log.error("======== get error revoke toke exception ", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, "en"));
            responseData.setError(message);
            return responseData;
        }
    }


    /**
     * <pre>
     *     register or update information of main category
     * </pre>
     *
     * @param param
     * @param function
     * @return ResponseEntity<MMap>
     * @throws Exception
     */
    private ResponseData<ModelMap> execute(ModelMap param, String function, String lang, int user_id) {
        ResponseData<ModelMap> response = new ResponseData<>();
        ModelMap body = param.getModelMap("body");
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        ErrorMessage message = new ErrorMessage();
        try {
            log.info("======== Start User " + function + "=============");

            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap input = new ModelMap();
            ModelMap responseBody = new ModelMap();
            String Yn = StatusYN.N;

            input.setString("firstName", body.getString("firstName"));
            input.setString("lastName", body.getString("lastName"));
            input.setString("dateBirth", body.getString("dateBirth"));
            input.setString("email", body.getString("email"));
            input.setString("password", body.getString("password"));
            input.setString("contact", body.getString("contact"));
            input.setString("gender", body.getString("gender"));
            input.setString("description", body.getString("description"));
            input.setInt("addressID", body.getInt("addressID"));
            input.setInt("resourceID", body.getInt("resourceID"));
            input.setInt("userID", user_id);

            if (function == "save") {
                input.setString(StatusYN.STATUS, Status.Active.getValueStr());

                log.info("========== values:" + objectMapper.writeValueAsString(input));

                int save = userService.save(input);
                if (save > 0) {
                    Yn = StatusYN.Y;
                }
            }
            if (function == "update") {
                input.setLong("id", body.getLong("id"));
                input.setString("status", Status.Modify.getValueStr());

                log.info("========== values:" + objectMapper.writeValueAsString(input));

                int update = userService.update(input);
                if (update > 0) {
                    Yn = StatusYN.Y;
                }
            }

            responseBody.setString(StatusYN.STATUS, Yn);
            response.setBody(responseBody);
            transactionManager.commit(transactionStatus);

            log.info("========== Response Values:" + objectMapper.writeValueAsString(response));
            log.info("========== End User " + function + "=============");

            return response;
        } catch (ValidatorException ex) {
            log.error("========== get error:", ex);
            message.setMessage(MessageUtil.message("user_" + ex.getKey(), lang));
            response.setError(message);
            return response;
        } catch (Exception e) {
            log.error("======== get error exception", e);
            transactionManager.rollback(transactionStatus);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang));
            response.setError(message);
            return response;
        }
    }

    private ModelMap loadUserByUserName (ModelMap body, String lang) throws ValidatorException {
        ModelMap out = new ModelMap();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("load user "+objectMapper.writeValueAsString(body));
            ModelMap input = new ModelMap();
            input.setString("user_name", "admin");
            out = userService.loadUserByUserName(input);
            if (out != null) {
                eventPublisher.publishEvent(new UserAuthenticateEvent(body));
            }
            return out;
        } catch (ValidatorException ex) {
            ex.setLanguage(lang);
            throw ex;
        } catch (Exception e) {
            return out;
        }
    }
}
