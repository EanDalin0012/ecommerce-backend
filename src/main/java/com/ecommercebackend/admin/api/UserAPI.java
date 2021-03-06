package com.ecommercebackend.admin.api;

import com.ecommercebackend.admin.constant.StatusYN;
import com.ecommercebackend.admin.service.implement.*;
import com.ecommercebackend.admin.util.MessageUtil;
import com.ecommercebackend.core.constant.ErrorCode;
import com.ecommercebackend.core.constant.Status;
import com.ecommercebackend.core.dao.DefaultAuthenticationDao;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private EducationInfoServiceImplement educationInfoService;
    @Autowired
    private EmergencyContactServiceImplement emergencyContactService;
    @Autowired
    private PersonalInfoServiceImplement personalInfoService;
    @Autowired
    private FamilyInfoServiceImplement familyInfoService;
    @Autowired
    private PersonalInfoDetailServiceImplement personalInfoDetailService;
    @Autowired
    private UserServiceImplement userService;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private DefaultAuthenticationDao defaultAuthenticationProviderDao;
    @Autowired
    private TokenStore tokenStore;


    @GetMapping(value = "/get/list")
    public ResponseData<ModelMap> getUserList(@RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        return  this.userList(lang);
    }

    @PostMapping(value = "/info")
    public ResponseData<ModelMap> addUserInfo(@RequestBody ModelMap param, @RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        return info(param, "save", lang, user_id);
    }

    @GetMapping(value = "/info/detail")
    public ResponseData<MultiModelMap> addUserDetails(@RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        return getUserInfoDetails(lang);
    }

    @PostMapping(value = "/save")
    public ResponseData<ModelMap> save(@RequestBody ModelMap param, @RequestParam("userId") int user_id, @RequestParam("lang") String lang) {
        return execute(param, "save", lang, user_id);
    }

    @GetMapping(value = "/oauth/revoke-token")
    public ResponseData<ModelMap> oauthRevokeToken(HttpServletRequest request) {
        return this.revokeToken(request);
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

    @PostMapping(value = "/first/login")
    public ResponseData<ModelMap> firstLogin(@RequestBody ModelMap param, @RequestParam("userId") int userId, @RequestParam("lang") String lang) {
        return isFirstLogin(param, userId, lang);
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
        message.setCode(StatusYN.N);
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
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, "en", e.getMessage()));
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

    private ResponseData<ModelMap> info(ModelMap param, String function, String lang, int userId) {
        ResponseData<ModelMap> response = new ResponseData<>();
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        ErrorMessage message = new ErrorMessage();
        message.setCode(StatusYN.N);
        String note = "";
        int educationInfoID = 0;
        try {
            log.info("======== Start User " + function + "=============");

            ObjectMapper objectMapper = new ObjectMapper();

            ModelMap responseBody = new ModelMap();
            String Yn = StatusYN.N;

            ModelMap personalInfo               = param.getModelMap("personalInfo");
            MultiModelMap educationInformations = param.getMultiModelMap("educationInformations");
            MultiModelMap familyInformations    = param.getMultiModelMap("familyInformations");
            MultiModelMap emergencyContacts     = param.getMultiModelMap("emergencyContacts");

            int[] educationIdArr            = new int[educationInformations.size()];
            int[] familyInfoIdArr           = new int[familyInformations.size()];
            int[] emergencyContactIdArr     =  new int[emergencyContacts.size()];

            /* save personal info*/
            ModelMap personalInfoInput = new ModelMap();

            personalInfoInput.setInt("userId", userId);
            personalInfoInput.setString("firstName", personalInfo.getString("firstName"));
            personalInfoInput.setString("lastName", personalInfo.getString("lastName"));
            personalInfoInput.setString("phone", personalInfo.getString("phone"));
            personalInfoInput.setString("email", personalInfo.getString("email"));
            personalInfoInput.setString("birthDate", personalInfo.getString("birthDate"));
            personalInfoInput.setString("gender", personalInfo.getString("gender"));
            personalInfoInput.setString("religion", personalInfo.getString("religion"));
            personalInfoInput.setString("address", personalInfo.getString("address"));
            personalInfoInput.setString("reportsTo", personalInfo.getString("reportsTo"));
            personalInfoInput.setString("nationalID", personalInfo.getString("nationalID"));
            personalInfoInput.setString("nationality", personalInfo.getString("nationality"));
            personalInfoInput.setString("maritalStatus", personalInfo.getString("maritalStatus"));
            personalInfoInput.setString("resourceImageId", personalInfo.getString("resourceImageID"));
            personalInfoInput.setString("description", personalInfo.getString("description"));
            personalInfoInput.setString("status", Status.Active.getValueStr());
            if (function == "save") {
                int personalInfoId         = personalInfoService.sequence();
                personalInfoInput.setInt("id", personalInfoId);
                int savePer = personalInfoService.save(personalInfoInput);
            } else if(function == "update") {
                personalInfoInput.setInt("id", personalInfo.getInt("id"));
                int update = personalInfoService.update(personalInfoInput);

            }
            /* end save personal info*/

            /* save education information*/
            int educationInfoIndex = 0;
            if (educationInformations.size() > 0) {
                for (ModelMap educationInfo : educationInformations.toListData()) {

                    ModelMap educationInfoInput = new ModelMap();
                    educationInfoInput.setInt("userId", userId);
                    educationInfoInput.setString("institution", educationInfo.getString("institution"));
                    educationInfoInput.setString("subject", educationInfo.getString("subject"));
                    educationInfoInput.setString("startingDate", educationInfo.getString("startingDate"));
                    educationInfoInput.setString("completeDate", educationInfo.getString("completeDate"));
                    educationInfoInput.setString("degree", educationInfo.getString("degree"));
                    educationInfoInput.setString("grade", educationInfo.getString("grade"));
                    educationInfoInput.setString("status", Status.Active.getValueStr());
                    if (function == "save") {
                        educationInfoID = educationInfoService.sequence();
                        educationInfoInput.setInt("id", educationInfoID);
                        int savePer = educationInfoService.save(educationInfoInput);
                        if (savePer > 0) {
                            educationIdArr[educationInfoIndex] = educationInfoID;
                            educationInfoIndex++;
                        }
                    } else if (function == "update") {
                        educationInfoInput.setInt("id", educationInfo.getInt("id"));
                        int update = educationInfoService.update(educationInfoInput);
                    }
                }
            }
            /* end end save education information*/

            /* save family information*/
            int familyInfoIndex = 0;
            if(familyInformations.size() > 0) {
                for (ModelMap familyInfo: familyInformations.toListData()) {

                    ModelMap familyInfoInput = new ModelMap();
                    familyInfoInput.setInt("userId", userId);
                    familyInfoInput.setString("name", familyInfo.getString("name"));
                    familyInfoInput.setString("relationship", familyInfo.getString("relationship"));
                    familyInfoInput.setString("phone", familyInfo.getString("phone"));
                    familyInfoInput.setString("description", familyInfo.getString("description"));
                    familyInfoInput.setString("status", Status.Active.getValueStr());
                    if (function == "save") {
                        int familyInfoID  = familyInfoService.sequence();
                        familyInfoInput.setInt("id", familyInfoID);
                        int saveEduct = familyInfoService.save(familyInfoInput);
                        if (saveEduct > 0) {
                            familyInfoIdArr[familyInfoIndex] = familyInfoID;
                            familyInfoIndex++;
                        }
                    } else if(function == "update") {
                        familyInfoInput.setInt("id", familyInfo.getInt("id"));
                        int update = familyInfoService.update(familyInfoInput);

                    }
                }
            }
            /* end save education information*/

            /* save emergency Contact*/
            int emergencyContactIndex =0;
            if (emergencyContacts.size() > 0) {
                for (ModelMap emergencyInfo : emergencyContacts.toListData()) {
                    ModelMap emergencyContactInput = new ModelMap();
                    emergencyContactInput.setInt("userId", userId);
                    emergencyContactInput.setString("id", emergencyInfo.getString("name"));
                    emergencyContactInput.setString("name", emergencyInfo.getString("name"));
                    emergencyContactInput.setString("relationship", emergencyInfo.getString("relationship"));
                    emergencyContactInput.setString("phone", emergencyInfo.getString("phone"));
                    emergencyContactInput.setString("phone2", emergencyInfo.getString("phone2"));
                    emergencyContactInput.setString("status", Status.Active.getValueStr());
                    if (function == "save") {
                        int emergencyInfoID = emergencyContactService.sequence();
                        emergencyContactInput.setInt("id", emergencyInfoID);
                        int saveEmergencyContact = emergencyContactService.save(emergencyContactInput);
                        if (saveEmergencyContact > 0) {
                            emergencyContactIdArr[emergencyContactIndex] = emergencyInfoID;
                            emergencyContactIndex++;
                        }
                    } else if (function == "update") {
                        emergencyContactInput.setInt("id", emergencyInfo.getInt("id"));
                        int update = emergencyContactService.update(emergencyContactInput);

                    }
                }
            }
            if (function == "save") {
                /* end save education information*/
                int educationSize = educationIdArr.length;
                int familyInfoSize = familyInfoIdArr.length;
                int emergencyContactSize = emergencyContactIdArr.length;
                ModelMap personalDetailInput = new ModelMap();
                personalDetailInput.setInt("personalInfoId", personalInfoInput.getInt("id"));
                if (educationSize >= familyInfoSize && educationSize >= emergencyContactSize && educationSize > 0) {
                    for (int i = 0; i < educationSize; i++) {
                        personalDetailInput.setInt("educationId", educationIdArr[i]);
                        personalDetailInput.setInt("familyInfoId", 0);
                        personalDetailInput.setInt("emergencyContactId", 0);
                        if (familyInfoSize <= educationSize && familyInfoSize -1 >= i) {
                            personalDetailInput.setInt("familyInfoId", familyInfoIdArr[i]);
                        }
                        if (emergencyContactSize <= educationSize && emergencyContactSize-1 >= i) {
                            personalDetailInput.setInt("emergencyContactId", emergencyContactIdArr[i]);
                        }
                        personalInfoDetailService.save(personalDetailInput);
                    }
                } else if (familyInfoSize >= educationSize && familyInfoSize >= emergencyContactSize && familyInfoSize > 0) {
                    for (int i = 0; i < familyInfoSize; i++) {
                        personalDetailInput.setInt("familyInfoId", familyInfoIdArr[i]);
                        personalDetailInput.setInt("educationId", 0);
                        personalDetailInput.setInt("emergencyContactId", 0);
                        if (educationSize <= familyInfoSize && educationSize-1 >= i) {
                            personalDetailInput.setInt("educationId", educationIdArr[i]);
                        }
                        if (emergencyContactSize <= familyInfoSize && emergencyContactSize -1 >= i) {
                            personalDetailInput.setInt("emergencyContactId", emergencyContactIdArr[i]);
                        }
                        personalInfoDetailService.save(personalDetailInput);
                    }
                } else if (emergencyContactSize >= educationSize && emergencyContactSize >= familyInfoSize && emergencyContactSize > 0) {
                    for (int i = 0; i < emergencyContactSize; i++) {
                        personalDetailInput.setInt("emergencyContactId", emergencyContactIdArr[i]);
                        personalDetailInput.setInt("educationId", 0);
                        personalDetailInput.setInt("familyInfoId", 0);
                        if (educationSize <= emergencyContactSize && educationSize-1 >= i) {
                            personalDetailInput.setInt("educationId", educationIdArr[i]);
                        }
                        if (familyInfoSize <= emergencyContactSize && familyInfoSize-1 >= i) {
                            personalDetailInput.setInt("familyInfoId", familyInfoIdArr[i]);
                        }
                        personalInfoDetailService.save(personalDetailInput);
                    }
                }

            }
            /*save personal details*/

            /*end save personal details*/
            responseBody.setString(StatusYN.STATUS, StatusYN.Y);
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
            e.printStackTrace();
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
            input.setString("user_name", body.getString("userName"));
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

    public ResponseData<MultiModelMap> getUserInfoDetails(String lang) {
        ResponseData<MultiModelMap> response = new ResponseData<>();
        ErrorMessage message = new ErrorMessage();
        message.setCode(StatusYN.N);
        try {
            ModelMap input = new ModelMap();
            input.setString("status", Status.Delete.getValueStr());
            MultiModelMap list = personalInfoService.retrieveList(input);
            response.setBody(list);
        } catch (ValidatorException ex ) {
            log.error("========== get error:", ex);
            message.setMessage(MessageUtil.message("user_" + ex.getKey(), lang));
            response.setError(message);
            return response;
        } catch (Exception  e) {
            e.printStackTrace();
            log.error("============ error Exception api category get list", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang, e.getMessage()));
            response.setError(message);
            return response;
        }
        return response;
    }

    private ResponseData<ModelMap> isFirstLogin(ModelMap param, int userId, String lang) {
        ResponseData<ModelMap> response = new ResponseData<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ErrorMessage message = new ErrorMessage();
        message.setCode(StatusYN.N);
        try {
            ModelMap responseBody = new ModelMap();
            if (param.getString("newPassword").equals(param.getString("confirmPassword"))) {
                    String pw = passwordEncoder.encode(param.getString("newPassword"));
                    ModelMap input = new ModelMap();
                    input.setString("status", Status.Modify.getValueStr());
                    input.setString("user_name", param.getString("userName"));
                    input.setString("password", pw);
                    input.setBoolean("is_first_login", false);
                    int update = userService.updatePassword(input);
                    if (update > 0) {
                        responseBody.setString("status", StatusYN.Y);
                        response.setBody(responseBody);
                    }
            }

        } catch (ValidatorException ex) {
            log.error("========== get error:", ex);
            message.setMessage(MessageUtil.message("user_" + ex.getKey(), lang));
            response.setError(message);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("============ error Exception api category get list", e);
            message.setMessage(MessageUtil.message(ErrorCode.EXCEPTION_ERR, lang, e.getMessage()));
            response.setError(message);
            return response;
        }
        return  response;
    }
}
