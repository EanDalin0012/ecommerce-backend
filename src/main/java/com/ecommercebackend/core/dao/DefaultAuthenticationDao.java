package com.ecommercebackend.core.dao;

import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.security.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DefaultAuthenticationDao {
    ModelMap getUserByName(ModelMap param);
    User getUserByName1(ModelMap param);
    int lockedUser(ModelMap param);
    int trackSaveUserLock(ModelMap param);
    int trackUpdateUserLock(ModelMap param);
    int trackUpdateUserIsLocked(ModelMap param);
    int updateLoginSuccess(ModelMap param);
    int deleteUserLockCountBYUserName(ModelMap param);
    ModelMap getTrackUserLockByUserName(ModelMap param);
    ModelMap getUserAccountLockByUserName(ModelMap param);
}
